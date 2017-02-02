package by.dorohovich.site.command.implcommand.controllercommand;

import by.dorohovich.site.command.AbstractControllerCommand;
import by.dorohovich.site.entity.Theme;
import by.dorohovich.site.entity.User;
import by.dorohovich.site.exception.CommandException;
import by.dorohovich.site.exception.ServiceException;
import by.dorohovich.site.service.serviceimpl.ThemeService;
import by.dorohovich.site.utility.MappingManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by User on 31.01.2017.
 */
public class PrepareForCreatingQuestionCommand extends AbstractControllerCommand {

    private static final String USER_ATTR = "user";
    private static final String THEMES_ATTR = "themeList";

    private static final String KEY_FOR_PAGE = "page.createQuestion";
    private static final String KEY_FOR_WARNING_PAGE = "page.warningAccess";

    @Override
    protected String doLogic(HttpServletRequest request) throws CommandException {
        try {
            return tryDoLogic(request);
        } catch (ServiceException e){
            throw new CommandException("Problem in command, while trying to prepare themes", e);
        }
    }

    private String tryDoLogic(HttpServletRequest request) throws ServiceException {
        ThemeService themeService = new ThemeService();
        List<Theme> themeList = themeService.showThemes();
        HttpSession session = request.getSession(true);
        session.setAttribute(THEMES_ATTR, themeList);
        String page = MappingManager.getProperty(KEY_FOR_PAGE);
        return page;
    }

    @Override
    protected String checkAccessRights(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession(true);
        User user = (User)session.getAttribute(USER_ATTR);
        String page = (user != null) ? null : MappingManager.getProperty(KEY_FOR_WARNING_PAGE);
        return page;
    }
}
