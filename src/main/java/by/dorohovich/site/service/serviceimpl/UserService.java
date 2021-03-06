package by.dorohovich.site.service.serviceimpl;

import by.dorohovich.site.DAO.daoimpl.UserDAO;
import by.dorohovich.site.entity.User;
import by.dorohovich.site.exception.ConnectionPoolException;
import by.dorohovich.site.exception.DAOException;
import by.dorohovich.site.exception.ServiceException;
import by.dorohovich.site.connectionpool.ConnectionPool;
import by.dorohovich.site.connectionpool.ProxyConnection;
import by.dorohovich.site.service.AbstractService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Created by User on 04.01.2017.
 */
public class UserService extends AbstractService<Integer, User> {

    private static final Logger LOGGER = LogManager.getLogger();

    public void register(String login, String password, String email) throws ServiceException {
        try {
            tryRegister(login, password, email);
        } catch (ConnectionPoolException e) {
            LOGGER.error("Problem with getting connection, while trying to register", e);
            throw new ServiceException(e);
        } catch (DAOException e) {
            LOGGER.error("Problem with UserDAO, while trying to register", e);
            throw new ServiceException(e);
        }
    }

    private void tryRegister(String login, String password, String email) throws DAOException, ConnectionPoolException {
        try (ProxyConnection connection = ConnectionPool.getInstance().takeConnection()) {
            UserDAO userDAO = new UserDAO(connection);
            User user = new User(login, password, email);
            userDAO.create(user);
        }
    }

    public boolean checkIsLoginFree(String login) throws ServiceException {
        try {
            return tryCheckIsLoginFree(login);
        } catch (ConnectionPoolException e) {
            LOGGER.error("Problem with getting connection, while trying to check is login free", e);
            throw new ServiceException(e);
        } catch (DAOException e) {
            LOGGER.error("Problem with UserDAO, while trying to check is login free", e);
            throw new ServiceException(e);
        }

    }

    private boolean tryCheckIsLoginFree(String login) throws ConnectionPoolException, DAOException {
        try (ProxyConnection connection = ConnectionPool.getInstance().takeConnection()) {
            UserDAO userDAO = new UserDAO(connection);
            User user = userDAO.findUserByLogin(login);
            return user == null;
        }
    }

    public User logIn(String login, String password) throws ServiceException {
        try {
            return tryLogIn(login, password);
        } catch (ConnectionPoolException e) {
            LOGGER.error("Problem with getting connection", e);
            throw new ServiceException(e);
        } catch (DAOException e) {
            LOGGER.error("Problem with UserDAO", e);
            throw new ServiceException(e);
        }
    }

    private User tryLogIn(String login, String password) throws DAOException, ConnectionPoolException {
        try (ProxyConnection connection = ConnectionPool.getInstance().takeConnection()) {
            UserDAO userDAO = new UserDAO(connection);
            User user = userDAO.findUserByLogin(login);
            if (user == null) {
                return null;
            }
            return (user.getPassword().equals(password)) ? user : null;
        }
    }

    public List<User> showUsersTop() throws ServiceException {
        try {
            return tryShowUsersTop();
        } catch (ConnectionPoolException e) {
            LOGGER.error("Problem with getting connection, when trying to showUsersTop", e);
            throw new ServiceException(e);
        } catch (DAOException e) {
            LOGGER.error("Problem with UserDAO, when trying to showUsersTop", e);
            throw new ServiceException(e);
        }
    }

    private List<User> tryShowUsersTop() throws ConnectionPoolException, DAOException {
        try (ProxyConnection connection = ConnectionPool.getInstance().takeConnection()) {
            UserDAO userDAO = new UserDAO(connection);
            List<User> userList = userDAO.findUsersOrderByRating();
            return userList;
        }
    }

    public User changePassword(User user, String curPassword, String newPassword) throws ServiceException {
        try {
            return tryChangePassword(user, curPassword, newPassword);
        } catch (ConnectionPoolException e) {
            LOGGER.error("Problem with getting connection, while trying to change password", e);
            throw new ServiceException(e);
        } catch (DAOException e) {
            LOGGER.error("Problem with UserDAO, while trying to change password", e);
            throw new ServiceException(e);
        }
    }


    private User tryChangePassword(User user, String curPassword, String newPassword) throws ConnectionPoolException, DAOException {
        try (ProxyConnection connection = ConnectionPool.getInstance().takeConnection()) {
            User updatedUser = null;
            if (user.getPassword().equals(curPassword)) {
                UserDAO userDAO = new UserDAO(connection);
                String login = user.getLogin();
                userDAO.updatePassword(login, newPassword);
                updatedUser = userDAO.findUserByLogin(login);
            }
            return updatedUser;
        }
    }

    public User changeEmail(User user, String email) throws ServiceException {
        try {
            return tryChangeEmail(user, email);
        } catch (ConnectionPoolException e) {
            LOGGER.error("Problem with getting connection, while trying to change email", e);
            throw new ServiceException(e);
        } catch (DAOException e) {
            LOGGER.error("Problem with UserDAO, while trying to change email", e);
            throw new ServiceException(e);
        }
    }

    private User tryChangeEmail(User user, String email) throws ConnectionPoolException, DAOException{
        try (ProxyConnection connection = ConnectionPool.getInstance().takeConnection()) {
            User updatedUser = null;
            UserDAO userDAO = new UserDAO(connection);
            String login = user.getLogin();
            userDAO.updateEmail(login, email);
            updatedUser = userDAO.findUserByLogin(login);
            return updatedUser;
        }
    }

}
