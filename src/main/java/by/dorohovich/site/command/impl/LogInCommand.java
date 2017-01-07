package by.dorohovich.site.command.impl;

import by.dorohovich.site.command.ActionCommand;
import by.dorohovich.site.entity.User;
import by.dorohovich.site.exception.CommandException;
import by.dorohovich.site.exception.ServiceException;
import by.dorohovich.site.service.UserService;
import by.dorohovich.site.utility.MappingManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by User on 06.01.2017.
 */
public class LogInCommand implements ActionCommand {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";


    private static final String USER_ATR= "user";
    private static final String LOG_IN_FAILED = "logInFailed";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        try {
            String login = request.getParameter(LOGIN);
            String password = request.getParameter(PASSWORD);


            UserService userService = new UserService();
            String page;
            User user = userService.logIn(login, password);
            if( user != null){
                HttpSession session = request.getSession(true);
                session.setAttribute(USER_ATR, user);
                LOGGER.info(user+" logged in");
                page = MappingManager.getProperty("page.index");
            } else {
                request.setAttribute(LOG_IN_FAILED, true);
                LOGGER.info("Log in was failed");
                page = MappingManager.getProperty("page.logIn");
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

