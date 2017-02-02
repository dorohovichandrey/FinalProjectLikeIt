package by.dorohovich.site.command.implcommand.controllercommand;

import by.dorohovich.site.command.AbstractControllerCommand;
import by.dorohovich.site.entity.User;
import by.dorohovich.site.exception.CommandException;
import by.dorohovich.site.exception.ServiceException;
import by.dorohovich.site.utility.MappingManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by User on 02.02.2017.
 */
public class PrepareForCreatingAnswerCommand extends AbstractControllerCommand {

    private static final String USER_ATTR = "user";
    private static final String QUESTION_ID_PARAM = "questionId";
    private static final String QUESTION_ID_ATTR = "questionIdAttr";

    private static final String KEY_FOR_PAGE = "page.createAnswer";
    private static final String KEY_FOR_WARNING_PAGE = "page.warningAccess";

    @Override
    protected String doLogic(HttpServletRequest request) throws CommandException {
        try {
            return tryDoLogic(request);
        } catch (ServiceException e) {
            throw new CommandException("Problem in command, while preparing to create answer", e);
        }
    }

    private String tryDoLogic(HttpServletRequest request) throws ServiceException {
        String questionIdParam = request.getParameter(QUESTION_ID_PARAM);
        Integer questionId = Integer.valueOf(questionIdParam);
        HttpSession session = request.getSession(true);
        session.setAttribute(QUESTION_ID_ATTR, questionId);
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
