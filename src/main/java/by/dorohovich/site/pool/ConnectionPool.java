package by.dorohovich.site.pool;

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

    private static ConnectionPool instance;

    private ConnectionPool() {
        availableConnections = new ArrayBlockingQueue<ProxyConnection>(POOL_SIZE);
        connectionProducer = new ConnectionProducer();

        while (availableConnections.size()!=POOL_SIZE) {
            int rest=POOL_SIZE - availableConnections.size();
            for (int i = 0; i < rest ; i++) {
                try {
                    ProxyConnection connection = connectionProducer.produce();
                    connection.setAutoCommit(true);
                    availableConnections.put(connection);
                    LOG.info("Connection was initialized and added to pool");
                } catch (ConnectionProducerException e) {
                    LOG.error("Connection was not added, problem in producing",e);
                } catch (InterruptedException e) {
                    LOG.error("Connection was not added, problem in queue",e);
                } catch (SQLException e) {
                    LOG.error("Connection was not added, problem in setting auto commit",e);
                }
            }
            if (availableConnections.isEmpty()) {
                LOG.fatal("Pool was not initialized");
                throw new RuntimeException();
            }
        }
    }

    public static ConnectionPool getInstance() {
        //if (!isInitialized.get()) {
        if (isInitialized.compareAndSet(false, true)) {
            initializationLock.lock();
            try {
                if (instance == null) {
                    instance = new ConnectionPool();
                    //isInitialized.set(true);
                }
            } finally {
                initializationLock.unlock();
            }
        }

        return instance;
    }

    public ProxyConnection takeConnection() throws ConnectionPoolException {
        ProxyConnection connection = null;
        try {
            connection = availableConnections.take();
            LOG.info("Connection was taken from pool");
        } catch (InterruptedException e) {
            throw new ConnectionPoolException("Exception in ConnectionPool while trying to take connection", e);
        }

        return connection;
    }

    public void putConnection(ProxyConnection connection) throws ConnectionPoolException  {
        try {
            if (connection.isValid(TIMEOUT_VALID)) {
                availableConnections.put(connection);
            } else {
                ProxyConnection newConnection = connectionProducer.produce();
                newConnection.setAutoCommit(true);
                availableConnections.put(newConnection);
            }
            LOG.info("Connection was put to pool");
        } catch (ConnectionProducerException | InterruptedException | SQLException e) {
            throw new ConnectionPoolException("Exception in ConnectionPool while trying to put connection", e);
        }
    }


    public void closeAll() {
        //if (isInitialized.get()) {
        if (isInitialized.compareAndSet(true, false)) {
            //isInitialized.set(false);

            for (int i = 0; i < POOL_SIZE; i++) {
                try {
                    ProxyConnection connection = availableConnections.take();

                    if (!connection.getAutoCommit()) {
                        connection.commit();
                    }

                    connection.realClose();
                    LOG.info(String.format("closed successfully (#%d)", i));
                } catch (SQLException | InterruptedException e) {
                    LOG.warn(String.format("problem with connection closing (#%d)", i));
                }
            }
        }
    }
}
