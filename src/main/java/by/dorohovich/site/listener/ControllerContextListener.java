package by.dorohovich.site.listener;

import com.mysql.jdbc.AbandonedConnectionCleanupThread;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;


public class ControllerContextListener implements ServletContextListener {

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        deregisterDrivers();
    }

    private void deregisterDrivers() {

        try {
            tryDeregisterDrivers();
        } catch (InterruptedException | SQLException e) {
            LOGGER.warn("error while deregister driver", e);
        }
    }

    private void tryDeregisterDrivers() throws SQLException, InterruptedException {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            LOGGER.info("deregistering jdbc driver: " + driver);
            DriverManager.deregisterDriver(driver);
        }

        AbandonedConnectionCleanupThread.shutdown();
    }
}