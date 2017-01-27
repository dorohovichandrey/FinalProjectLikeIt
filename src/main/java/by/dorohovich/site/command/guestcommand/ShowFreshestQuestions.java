package by.dorohovich.site.command.guestcommand;

import by.dorohovich.site.command.AbstractGuestCommand;
import by.dorohovich.site.entity.User;
import by.dorohovich.site.exception.CommandException;
import by.dorohovich.site.exception.ServiceException;
import by.dorohovich.site.service.UserService;
import by.dorohovich.site.utility.MappingManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by User on 27.01.2017.
 */
public class ShowFreshestQuestions extends AbstractGuestCommand {
    private static final String USERS_ATTR = "users";
    private static final String KEY_FOR_TABLE_HEADER_ATTR = "keyForTableHeader";

    private static final String KEY_FOR_TABLE_HEADER = "text.users.topUsersTableHeader";

    private static final String KEY_FOR_PAGE = "page.users";

    @Override
    public String doLogic(HttpServletRequest request) throws CommandException {
        try {
            return tryExecute(request);
        } catch (ServiceException e) {
            throw new CommandException("Problem with userService, while trying to showUsersTop", e);
        }
    }

    private String tryExecute(HttpServletRequest request) throws ServiceException {
        UserService userService = new UserService();
        List<User> users = userService.showUsersTop();
        packAttributes(users, request);
        String page = MappingManager.getProperty(KEY_FOR_PAGE);
        return page;
    }

    private void packAttributes(List<User> users, HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        session.setAttribute(USERS_ATTR, users);
        session.setAttribute(KEY_FOR_TABLE_HEADER_ATTR, KEY_FOR_TABLE_HEADER);

    }
}
