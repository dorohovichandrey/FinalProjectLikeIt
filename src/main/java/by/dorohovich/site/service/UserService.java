package by.dorohovich.site.service;

import by.dorohovich.site.DAO.UserDAO;
import by.dorohovich.site.entity.User;
import by.dorohovich.site.exception.ConnectionPoolException;
import by.dorohovich.site.exception.ConnectionProducerException;
import by.dorohovich.site.exception.DAOException;
import by.dorohovich.site.pool.ConnectionPool;
import by.dorohovich.site.pool.ProxyConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Created by User on 04.01.2017.
 */
public class UserService extends AbstractService<Integer, User>{

    private static final Logger LOGGER = LogManager.getLogger();

    public void register(String login, String password, String email) throws ConnectionPoolException, DAOException{
        try (ProxyConnection connection= ConnectionPool.getInstance().takeConnection();) {


            UserDAO userDAO = new UserDAO(connection);

            userDAO.create(new User(login, password));


            List<User> lst = userDAO.findAll();
            if (lst.size() > 0) {
                LOGGER.info(lst);
            } else {
                LOGGER.info("Not found");
            }


        }
    }

    public User findUserByLogin(String Login){

        return null;
    }
}
