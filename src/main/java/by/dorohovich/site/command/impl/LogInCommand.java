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

    private UserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        try {
            return tryExecute(request);
        } catch (ServiceException e) {
            LOGGER.error("Problem with UserService, while trying to logIn", e);
            throw new CommandException(e);
        }
    }

    private String tryExecute(HttpServletRequest request) throws ServiceException {
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        User user = userService.logIn(login, password);
        boolean isCommandFailed = (user == null);
        packAttributes(login, password, user, isCommandFailed, request);
        String page = choosePage(isCommandFailed);
        return page;
    }

    private void packAttributes(String login, String password, User user, boolean isCommandFailed, HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        if( !isCommandFailed){
            session.setAttribute(USER_ATR, user);
            LOGGER.info(user+" logged in");
        } else {
            session.setAttribute(LOG_IN_FAILED, isCommandFailed);
            session.setAttribute(LOGIN, login);
            session.setAttribute(PASSWORD, password);
            LOGGER.info("Log in was failed");
        }
    }

    private String choosePage(boolean isCommandFailed){
        String key = isCommandFailed ? "page.logIn" : "page.index";
        String page = MappingManager.getProperty(key);
        return page;
    }
}

