package by.dorohovich.site.service;

import by.dorohovich.site.DAO.UserDAO;
import by.dorohovich.site.entity.User;
import by.dorohovich.site.exception.ConnectionPoolException;
import by.dorohovich.site.exception.ConnectionProducerException;
import by.dorohovich.site.exception.DAOException;
import by.dorohovich.site.exception.ServiceException;
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

    public boolean register(String login, String password, String email) throws ServiceException{
        try (ProxyConnection connection = ConnectionPool.getInstance().takeConnection()) {

            UserDAO userDAO = new UserDAO(connection);
            boolean isRegistered = userDAO.create(new User(login, password));

            //testS
            List<User> lst = userDAO.findAll();
            if (lst.size() > 0) {
                LOGGER.info(lst);
            } else {
                LOGGER.info("Not found");
            }
            //testE

            return isRegistered;

        }
        catch (ConnectionPoolException e){
            LOGGER.error("Problem with getting connection", e);
            throw new ServiceException(e);
        }
        catch (DAOException e){
            LOGGER.error("Problem with UserDAO", e);
            throw new ServiceException(e);
        }
    }

    public boolean isLoginFree(String login) throws ServiceException {
        try (ProxyConnection connection = ConnectionPool.getInstance().takeConnection()) {

            UserDAO userDAO = new UserDAO(connection);
            return userDAO.findUserByLogin(login) == null;
        }
        catch (ConnectionPoolException e){
            LOGGER.error("Problem with getting connection", e);
            throw new ServiceException(e);
        }
        catch (DAOException e){
            LOGGER.error("Problem with UserDAO", e);
            throw new ServiceException(e);
        }

    }
}
