package by.dorohovich.site.command.impl;

import by.dorohovich.site.command.ActionCommand;
import by.dorohovich.site.exception.CommandException;
import by.dorohovich.site.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;

import by.dorohovich.site.service.UserService;
import by.dorohovich.site.utility.MappingManager;
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
    public String execute(HttpServletRequest request) throws CommandException {

       /* try (  ProxyConnection connection=ConnectionPool.getInstance().takeConnection();){



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


        return null;*/

        try {
            String login = request.getParameter(LOGIN);
            String password = request.getParameter(PASSWORD);
            String email = request.getParameter(EMAIL_ADDR);

            UserService userService = new UserService();
            String page;
            if(userService.isLoginFree(login)){
                if(!userService.register(login, password, email)) {
                    throw new CommandException("User was not registered");
                }
                LOGGER.info("User with login = \"" + login + "\" was registered");
                page = MappingManager.getProperty("page.logIn");
            } else {
                page = MappingManager.getProperty("page.registration");
            }


            return page;
        }
        catch (ServiceException e)
        {
            LOGGER.error("Problem with UserService", e);
            throw new CommandException(e);
        }
    }



}
