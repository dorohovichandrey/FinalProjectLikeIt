package by.dorohovich.site.command;

import by.dorohovich.site.DAO.UserDAO;
import by.dorohovich.site.exception.ConnectionPoolException;
import by.dorohovich.site.entity.User;
import by.dorohovich.site.exception.ConnectionProducerException;
import by.dorohovich.site.pool.ConnectionPool;
import by.dorohovich.site.pool.ProxyConnection;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * Created by User on 08.12.2016.
 */
public class RegistrationCommand  implements ActionCommand {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String PASSWORD_CONFIRMATION = "passwordConfirmation";
    private static final String EMAIL_ADDR = "emailAddr";



    @Override
    public String execute(HttpServletRequest request) {

        try (  ProxyConnection connection=ConnectionPool.getInstance().takeConnection();){



            UserDAO userDAO = new UserDAO(connection);
            String login = request.getParameter(LOGIN);
            String password = request.getParameter(PASSWORD);
            //String passwordConfirmation = request.getParameter(PASSWORD_CONFIRMATION);

            userDAO.create(new User(login, password));


            List<User> lst =  userDAO.findAll();
                if (lst.size() > 0) {
                    LOGGER.info(lst);
                } else {
                    LOGGER.info("Not found");
                }


        } catch (ConnectionPoolException | ConnectionProducerException e) {
            LOGGER.info(e);
        } finally {

        }


        return null;
    }

}
