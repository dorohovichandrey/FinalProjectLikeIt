package by.dorohovich.site.command.guestcommand;

import by.dorohovich.site.command.AbstractGuestCommand;
import by.dorohovich.site.entity.Question;
import by.dorohovich.site.entity.User;
import by.dorohovich.site.exception.CommandException;
import by.dorohovich.site.exception.ServiceException;
import by.dorohovich.site.service.QuestionService;
import by.dorohovich.site.service.UserService;
import by.dorohovich.site.utility.MappingManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by User on 27.01.2017.
 */
public class ShowFreshestQuestionsCommand extends AbstractGuestCommand {
    private static final String QUESTIONS_ATTR = "questions";
    private static final String KEY_FOR_QUESTION_LIST_HEADER_ATTR = "keyForQuestionListHeader";

    private static final String KEY_FOR_QUESTION_LIST_HEADER = "text.questionList.freshestQuestionsHeader";

    private static final String KEY_FOR_PAGE = "page.questionList";

    @Override
    public String doLogic(HttpServletRequest request) throws CommandException {
        try {
            return tryDoLogic(request);
        } catch (ServiceException e) {
            throw new CommandException("Problem with userService, while trying to showUsersTop", e);
        }
    }

    private String tryDoLogic(HttpServletRequest request) throws ServiceException {
        QuestionService questionService = new QuestionService();
        List<Question> questions = questionService.showFreshestQuestions();
        packAttributes(questions, request);
        String page = MappingManager.getProperty(KEY_FOR_PAGE);
        return page;
    }

    private void packAttributes(List<Question> questions, HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        session.setAttribute(QUESTIONS_ATTR, questions);
        session.setAttribute(KEY_FOR_QUESTION_LIST_HEADER_ATTR, KEY_FOR_QUESTION_LIST_HEADER);

    }
}
