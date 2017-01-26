package by.dorohovich.site.command.authusercommand;

import by.dorohovich.site.command.AbstractAuthenticatedUserCommand;
import by.dorohovich.site.entity.User;
import by.dorohovich.site.exception.CommandException;
import by.dorohovich.site.exception.ServiceException;
import by.dorohovich.site.service.QuestionService;
import by.dorohovich.site.service.UserService;
import by.dorohovich.site.utility.MappingManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by User on 27.01.2017.
 */
public class AskQuestionCommand extends AbstractAuthenticatedUserCommand {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final String HEADER_PARAM = "login";
    private static final String THEME_NAME_PARAM = "password";
    private static final String TEXT_PARAM = "passwordConfirmation";

    private static final String USER_ATTR = "user";


    private static final String KEY_FOR_PAGE_IF_SUCCESS = "page.logIn";
    private static final String KEY_FOR_PAGE_IF_FAILED = "page.registration";


    @Override
    public String doLogic(HttpServletRequest request) throws CommandException {
        try {
            return tryDoLogic(request);
        } catch (ServiceException e) {
            LOGGER.error("Problem with QuestionService, while trying to ask question", e);
            throw new CommandException(e);
        }
    }

    private String tryDoLogic(HttpServletRequest request) throws ServiceException {
        String header = request.getParameter(HEADER_PARAM);
        String themeName = request.getParameter(THEME_NAME_PARAM);
        String text = request.getParameter(TEXT_PARAM);
        HttpSession session = request.getSession(true);
        User owner = (User)session.getAttribute(USER_ATTR);
        QuestionService questionService = new QuestionService();
        questionService.askQuestion(owner, text, themeName, header);
        packAttributes(login, password, email, isLoginFree, request);
        String page = choosePage(isLoginFree);
        return page;
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
