package by.dorohovich.site.command.implcommand.guestcommand;

import by.dorohovich.site.command.AbstractGuestCommand;
import by.dorohovich.site.entity.User;
import by.dorohovich.site.exception.CommandException;
import by.dorohovich.site.exception.ServiceException;
import by.dorohovich.site.service.serviceimpl.UserService;
import by.dorohovich.site.utility.MappingManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by User on 06.01.2017.
 */
public class LogInCommand extends AbstractGuestCommand {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final String LOGIN_PARAM = "login";
    private static final String PASSWORD_PARAM = "password";

    private static final String LOGIN_ATTR = "login";
    private static final String PASSWORD_ATTR = "password";
    private static final String USER_ATTR = "user";
    private static final String LOG_IN_FAILED_ATTR = "logInFailed";

    private static final String KEY_FOR_PAGE_IF_SUCCESS = "page.freshestQuestions";
    private static final String KEY_FOR_PAGE_IF_FAILED = "page.logIn";


    @Override
    public String doLogic(HttpServletRequest request) throws CommandException {
        try {
            return tryDoLogic(request);
        } catch (ServiceException e) {
            throw new CommandException("Problem with UserService, while trying to logIn", e);
        }
    }

    private String tryDoLogic(HttpServletRequest request) throws ServiceException {
        String login = request.getParameter(LOGIN_PARAM);
        String password = request.getParameter(PASSWORD_PARAM);
        UserService userService = new UserService();
        User user = userService.logIn(login, password);
        boolean isCommandFailed = (user == null);
        packAttributes(login, password, user, isCommandFailed, request);
        String page = choosePage(isCommandFailed);
        return page;
    }

    private void packAttributes(String login, String password, User user, boolean isCommandFailed, HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        if (!isCommandFailed) {
            session.setAttribute(USER_ATTR, user);
            LOGGER.info(user + " logged in");
        } else {
            session.setAttribute(LOGIN_ATTR, login);
            session.setAttribute(PASSWORD_ATTR, password);
            LOGGER.info("Log in was failed");
        }
        session.setAttribute(LOG_IN_FAILED_ATTR, isCommandFailed);
    }

    private String choosePage(boolean isCommandFailed) {
        String key = isCommandFailed ? KEY_FOR_PAGE_IF_FAILED : KEY_FOR_PAGE_IF_SUCCESS;
        String page = MappingManager.getProperty(key);
        return page;
    }
}

