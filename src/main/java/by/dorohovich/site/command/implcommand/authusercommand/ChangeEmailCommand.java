package by.dorohovich.site.command.implcommand.authusercommand;

import by.dorohovich.site.command.AbstractAuthenticatedUserCommand;
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
 * Created by User on 25.01.2017.
 */
public class ChangeEmailCommand extends AbstractAuthenticatedUserCommand {

    private static final String EMAIL_PARAM = "emailAddr";

    private static final String USER_ATTR = "user";

    private static final String KEY_FOR_PAGE= "page.infoSuccess";



    @Override
    public String doLogic(HttpServletRequest request) throws CommandException {
        try {
            return tryDoLogic(request);
        } catch (ServiceException e) {
            throw new CommandException("Problem with UserService, while trying to change password", e);
        }
    }

    public String tryDoLogic(HttpServletRequest request) throws ServiceException {
        String email = request.getParameter(EMAIL_PARAM);
        HttpSession session = request.getSession(true);
        User user = (User)session.getAttribute(USER_ATTR);
        UserService userService = new UserService();
        User updatedUser = userService.changeEmail(user, email);
        session.setAttribute(USER_ATTR, updatedUser);
        String page = MappingManager.getProperty(KEY_FOR_PAGE);
        return page;
    }


}
