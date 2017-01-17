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
import java.util.List;

/**
 * Created by User on 14.01.2017.
 */
public class ShowUsersTopCommand implements ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final String USERS_ATR = "users";

    private static final String KEY_FOR_TABLE_HEADER_ATR = "keyForTableHeader";
    private static final String KEY_FOR_TABLE_HEADER = "text.users.topUsersTableHeader";

    private static final String KEY_FOR_PAGE = "page.users";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        try {
            return tryExecute(request);
        } catch (ServiceException e) {
            LOGGER.error("Problem with userService, while trying to showUsersTop", e);
            throw new CommandException(e);
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
        session.setAttribute(USERS_ATR, users);
        session.setAttribute(KEY_FOR_TABLE_HEADER_ATR, KEY_FOR_TABLE_HEADER);

    }
}
