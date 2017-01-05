package by.dorohovich.site.pool;


import by.dorohovich.site.exception.ConnectionProducerException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by User on 25.12.2016.
 */
public class ConnectionProducer {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final String URL = "jdbc:mysql://localhost:3306/like";
    private static final String USER = "root";
    private static final String PASSWORD = "7102555andre";
    private static final String AUTO_RECONNECT = "true";
    private static final String CHARACTER_ENCODING = "UTF-8";
    private static final String USE_UNICODE = "true";

    private Properties configProp;

    {
        configProp = new Properties();
        configProp.put("user", USER);
        configProp.put("password", PASSWORD);
        configProp.put("autoReconnect", AUTO_RECONNECT);
        configProp.put("characterEncoding", CHARACTER_ENCODING);
        configProp.put("useUnicode", USE_UNICODE);
    }


    ConnectionProducer() {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());

        } catch (SQLException e) {
            LOGGER.fatal("Problem with DriverManager registration", e);
            throw new RuntimeException(e);
        }
    }

    ProxyConnection produce() throws ConnectionProducerException {
        try {
            Connection connection = DriverManager.getConnection(URL, configProp);
            return new ProxyConnection(connection);
        } catch (SQLException e) {
            throw new ConnectionProducerException("Connection was not produced", e);
        }

    }

}
