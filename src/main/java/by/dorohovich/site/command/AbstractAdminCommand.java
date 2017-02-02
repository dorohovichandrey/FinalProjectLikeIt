package by.dorohovich.site.command;

import by.dorohovich.site.entity.User;
import by.dorohovich.site.exception.CommandException;
import by.dorohovich.site.utility.MappingManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by User on 26.01.2017.
 */
public abstract class AbstractAdminCommand extends AbstractCommand {
    private static final String USER_ATTR = "user";
    private static final String KEY_FOR_WARNING_PAGE ="page.warningAccess";

    @Override
    protected String checkAccessRights(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession(true);
        User user = (User)session.getAttribute(USER_ATTR);
        String page = (user!=null && user.isAdmin()) ? null : MappingManager.getProperty(KEY_FOR_WARNING_PAGE);
        return page;
    }

}
