package by.dorohovich.site.command.impl;

import by.dorohovich.site.command.ActionCommand;
import by.dorohovich.site.entity.User;
import by.dorohovich.site.exception.CommandException;
import by.dorohovich.site.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.dorohovich.site.service.UserService;
import by.dorohovich.site.utility.MappingManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * Created by User on 08.12.2016.
 */
public class RegistrationCommand  implements ActionCommand {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final String LOGIN_PARAM = "login";
    private static final String PASSWORD_PARAM = "password";
    private static final String PASSWORD_CONFIRMATION_PARAM = "passwordConfirmation";
    private static final String EMAIL_ADDR_PARAM = "emailAddr";

    private static final String LOGIN_ATR = "login";
    private static final String PASSWORD_ATR = "password";
    private static final String PASSWORD_CONFIRMATION_ATR = "passwordConfirmation";
    private static final String EMAIL_ADDR_ATR = "emailAddr";
    private static final String IS_LOGIN_FREE_ATR = "isLoginFree";

    UserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        try {
            return tryExecute(request);
        }
        catch (ServiceException e)
        {
            LOGGER.error("Problem with userService, while trying to register", e);
            throw new CommandException(e);
        }
    }

    private String tryExecute(HttpServletRequest request) throws ServiceException {
        String login = request.getParameter(LOGIN_PARAM);
        String password = request.getParameter(PASSWORD_PARAM);
        String email = request.getParameter(EMAIL_ADDR_PARAM);
        boolean isLoginFree = userService.checkIsLoginFree(login);
        if(isLoginFree) {
            userService.register(login, password, email);
        }
        packAttributes(login, password, email, isLoginFree, request);
        String page = choosePage(isLoginFree);
        return page;
    }

    private void packAttributes(String login, String password, String email, boolean isLoginFree, HttpServletRequest request) {
        if( !isLoginFree){
            HttpSession session = request.getSession(true);
            session.setAttribute(IS_LOGIN_FREE_ATR, isLoginFree);
            session.setAttribute(LOGIN_ATR, login);
            session.setAttribute(PASSWORD_ATR, password);
            session.setAttribute(EMAIL_ADDR_ATR, email);
        }
    }

    private String choosePage(boolean isLoginFree){
        String key = isLoginFree ? "page.logIn" : "page.registration";
        String page = MappingManager.getProperty(key);
        return page;
    }

}
