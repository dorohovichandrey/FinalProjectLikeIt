package by.dorohovich.site.command.implcommand.authusercommand;

import by.dorohovich.site.command.AbstractAuthenticatedUserCommand;
import by.dorohovich.site.entity.User;
import by.dorohovich.site.exception.CommandException;
import by.dorohovich.site.exception.ServiceException;
import by.dorohovich.site.service.QuestionService;
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

    private static final String HEADER_PARAM = "header";
    private static final String THEME_NAME_PARAM = "theme";
    private static final String TEXT_PARAM = "questionText";

    private static final String USER_ATTR = "user";

    private static final String KEY_FOR_PAGE = "page.myQuestions";



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
        Integer ownerId = owner.getId();
        QuestionService questionService = new QuestionService();
        questionService.askQuestion(ownerId, text, themeName, header);
        String page = MappingManager.getProperty(KEY_FOR_PAGE);
        return page;
    }


}
