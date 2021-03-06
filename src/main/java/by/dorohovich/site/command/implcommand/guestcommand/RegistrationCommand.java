package by.dorohovich.site.command.implcommand.guestcommand;

import by.dorohovich.site.command.AbstractGuestCommand;
import by.dorohovich.site.exception.CommandException;
import by.dorohovich.site.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.dorohovich.site.exception.ValidationException;
import by.dorohovich.site.service.serviceimpl.UserService;
import by.dorohovich.site.utility.MappingManager;
import by.dorohovich.site.validator.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by User on 08.12.2016.
 */
public class RegistrationCommand extends AbstractGuestCommand {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final String LOGIN_PARAM = "login";
    private static final String PASSWORD_PARAM = "password";
    private static final String PASSWORD_CONFIRMATION_PARAM = "passwordConfirmation";
    private static final String EMAIL_ADDR_PARAM = "emailAddr";

    private static final String LOGIN_ATTR = "login";
    private static final String PASSWORD_ATTR = "password";
    private static final String EMAIL_ADDR_ATTR = "emailAddr";
    private static final String IS_LOGIN_FREE_ATTR = "isLoginFree";

    private static final String KEY_FOR_PAGE_IF_SUCCESS = "page.logIn";
    private static final String KEY_FOR_PAGE_IF_FAILED = "page.registration";


    @Override
    public String doLogic(HttpServletRequest request) throws CommandException {
        try {
            return tryDoLogic(request);
        } catch (ServiceException e) {
            throw new CommandException("Problem with userService, while trying to register", e);
        } catch (ValidationException e) {
            throw new CommandException("Data is not valid", e);
        }
    }

    private String tryDoLogic(HttpServletRequest request) throws ServiceException, ValidationException {
        String login = request.getParameter(LOGIN_PARAM);
        String password = request.getParameter(PASSWORD_PARAM);
        String passwordConfirm = request.getParameter(PASSWORD_CONFIRMATION_PARAM);
        String email = request.getParameter(EMAIL_ADDR_PARAM);
        Validator validator = new Validator();
        validate(login, password, passwordConfirm, email, validator);
        UserService userService = new UserService();
        boolean isLoginFree = userService.checkIsLoginFree(login);
        if (isLoginFree) {
            userService.register(login, password, email);
        }
        packAttributes(login, password, email, isLoginFree, request);
        String page = choosePage(isLoginFree);
        return page;
    }

    private void validate(String login, String password, String passwordConfirm, String email, Validator validator) throws ValidationException {
        validator.validateLogin(login);
        validator.validatePassword(password);
        validator.validatePassConfirm(password, passwordConfirm);
        validator.validateEmail(email);
    }

    private void packAttributes(String login, String password, String email, boolean isLoginFree, HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        session.setAttribute(IS_LOGIN_FREE_ATTR, isLoginFree);
        if (!isLoginFree) {
            session.setAttribute(LOGIN_ATTR, login);
            session.setAttribute(PASSWORD_ATTR, password);
            session.setAttribute(EMAIL_ADDR_ATTR, email);
        }
    }

    private String choosePage(boolean isLoginFree) {
        String key = isLoginFree ? KEY_FOR_PAGE_IF_SUCCESS : KEY_FOR_PAGE_IF_FAILED;
        String page = MappingManager.getProperty(key);
        return page;
    }

}
