package by.dorohovich.site.command.implcommand.authusercommand;

import by.dorohovich.site.command.AbstractAuthenticatedUserCommand;
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
 * Created by User on 25.01.2017.
 */
public class ChangePasswordCommand extends AbstractAuthenticatedUserCommand {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final String CURRENT_PASSWORD_PARAM = "curPass";
    private static final String NEW_PASSWORD_PARAM = "password";
    private static final String NEW_PASSWORD_CONFIRMATION_PARAM = "passwordConfirmation";

    private static final String USER_ATTR = "user";
    private static final String CURRENT_PASSWORD_ATTR = "curPass";
    private static final String NEW_PASSWORD_ATTR = "newPass";
    private static final String CHANGE_PASSWORD_FAILED_ATTR = "passChangeFailed";

    private static final String KEY_FOR_PAGE= "page.editProfile";



    @Override
    public String doLogic(HttpServletRequest request) throws CommandException {
        try {
            return tryDoLogic(request);
        } catch (ServiceException e) {
            LOGGER.error("Problem with UserService, while trying to change password", e);
            throw new CommandException(e);
        }
    }

    public String tryDoLogic(HttpServletRequest request) throws ServiceException {
        String curPass = request.getParameter(CURRENT_PASSWORD_PARAM);
        String newPass = request.getParameter(NEW_PASSWORD_PARAM);
        String newPassConfirm = request.getParameter(NEW_PASSWORD_CONFIRMATION_PARAM);
        HttpSession session = request.getSession(true);
        User user = (User)session.getAttribute(USER_ATTR);
        UserService userService = new UserService();
        User updatedUser = userService.changePassword(user, curPass, newPass);
        boolean isCommandFailed = (updatedUser == null);
        packAttributes(session, isCommandFailed, curPass, newPass, updatedUser);
        String page = MappingManager.getProperty(KEY_FOR_PAGE);
        return page;


    }

    private void packAttributes(HttpSession session, boolean isCommandFailed, String curPass, String newPass,User updatedUser) {
        if(!isCommandFailed) {
            session.setAttribute(USER_ATTR, updatedUser);
            session.setAttribute(CHANGE_PASSWORD_FAILED_ATTR, false);
        } else {
            session.setAttribute(CURRENT_PASSWORD_ATTR, curPass);
            session.setAttribute(NEW_PASSWORD_ATTR, newPass);
            session.setAttribute(CHANGE_PASSWORD_FAILED_ATTR, true);
        }
    }


}
