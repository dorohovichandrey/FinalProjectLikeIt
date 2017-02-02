package by.dorohovich.site.connectionpool;

import by.dorohovich.site.exception.ConnectionPoolException;
import by.dorohovich.site.exception.ConnectionProducerException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by User on 24.12.2016.
 */
public class ConnectionPool {

    private static final Logger LOG = LogManager.getLogger();

    private static final int POOL_SIZE = 5;
    private static final int TIMEOUT_VALID = 3;

    private BlockingQueue<ProxyConnection> availableConnections;
    private ConnectionProducer connectionProducer;

    private static AtomicBoolean isInitialized = new AtomicBoolean(false);
    private static Lock initializationLock = new ReentrantLock();

    private static ConnectionPool instance = null;

    public static ConnectionPool getInstance() {
        if (instance == null) {
            if (isInitialized.compareAndSet(false, true)) {
                initializationLock.lock();
                try {
                    if (instance == null) {
                        instance = new ConnectionPool();
                    }
                } finally {
                    initializationLock.unlock();
                }
            }
        }

        return instance;

    }

    private ConnectionPool() {
        availableConnections = new ArrayBlockingQueue<ProxyConnection>(POOL_SIZE);
        connectionProducer = new ConnectionProducer();
        initConnections();
    }

    private void initConnections() {
        while (availableConnections.size() != POOL_SIZE) {
            int rest = POOL_SIZE - availableConnections.size();
            initRestConnections(rest);
            checkSize();
        }
    }

    private void initRestConnections(int rest){
        for (int i = 0; i < rest; i++) {
            initConnection();
        }
    }

    private void initConnection() {
        try {
            ProxyConnection connection = tryProduceConnection();
            availableConnections.put(connection);
            LOG.info("Connection was initialized and added to connection pool");
        } catch (SQLException | ConnectionProducerException | InterruptedException e) {
            LOG.warn("Connection was not added to connection pool");
        }
    }

    private ProxyConnection tryProduceConnection() throws ConnectionProducerException, SQLException {
        ProxyConnection connection = connectionProducer.produce();
        connection.setAutoCommit(true);
        return connection;
    }

    private void checkSize() {
        if (availableConnections.isEmpty()) {
            LOG.fatal("Pool was not initialized");
            throw new RuntimeException();
        }
    }

    public ProxyConnection takeConnection() throws ConnectionPoolException {
        try {
            return tryTakeConnection();
        } catch (InterruptedException e) {
            throw new ConnectionPoolException("Exception in ConnectionPool while trying to take connection", e);
        }
    }

    private ProxyConnection tryTakeConnection() throws InterruptedException {
        ProxyConnection connection = availableConnections.take();
        LOG.info("Connection was taken from connection pool");
        return connection;
    }

    public void putConnection(ProxyConnection connection) throws ConnectionPoolException {
        try {
            tryPutConnection(connection);
            LOG.info("Connection was put to connection pool");
        } catch (ConnectionProducerException | InterruptedException | SQLException e) {
            throw new ConnectionPoolException("Exception in ConnectionPool while trying to put connection", e);
        }
    }

    private void tryPutConnection(ProxyConnection connection) throws SQLException, InterruptedException, ConnectionProducerException {
        /*if (connection.isValid(TIMEOUT_VALID)) {
            availableConnections.put(connection);
        } else {
            ProxyConnection newConnection = connectionProducer.produce();
            newConnection.setAutoCommit(true);
            availableConnections.put(newConnection);
        }*/

        if (!connection.isValid(TIMEOUT_VALID)) {
            connection = tryProduceConnection();
        }
        availableConnections.put(connection);
    }


    public void closeAll() {
        if (isInitialized.compareAndSet(true, false)) {
            for (int i = 0; i < POOL_SIZE; i++) {
                closeConnection(i);
            }
        }
    }

    private void closeConnection(int i) {
        try {
            ProxyConnection connection = availableConnections.take();
            connection.realClose();
            LOG.info(String.format("closed successfully (#%d)", i));
        } catch (SQLException | InterruptedException e) {
            LOG.warn(String.format("problem with connection closing (#%d)", i));
        }
    }
}
