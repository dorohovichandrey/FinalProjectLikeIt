package by.dorohovich.site.command.implcommand.guestcommand;

import by.dorohovich.site.command.AbstractGuestCommand;
import by.dorohovich.site.exception.CommandException;
import by.dorohovich.site.exception.ServiceException;
import by.dorohovich.site.service.serviceimpl.AnswerService;
import by.dorohovich.site.service.serviceimpl.QuestionService;
import by.dorohovich.site.service.wrapper.entitywrapperimpl.AnswerWrapper;
import by.dorohovich.site.service.wrapper.entitywrapperimpl.QuestionWrapper;
import by.dorohovich.site.utility.MappingManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by User on 01.02.2017.
 */
public class ShowQuestionPlusAnswersCommand extends AbstractGuestCommand{

    private static final String QUESTION_ID_PARAM = "questionId";

    private static final String QUESTION_ATTR = "question";
    private static final String ANSWER_LIST_ATTR = "answerList";

    private static final String KEY_FOR_PAGE = "page.questionPlusAnswers";

    @Override
    protected String doLogic(HttpServletRequest request) throws CommandException {
        try {
            return tryDoLogic(request);
        } catch (ServiceException e){
            throw new CommandException("Exception when trying to show question plus answers", e);
        }
    }

    private String tryDoLogic(HttpServletRequest request) throws ServiceException {
        String questionIdParam = request.getParameter(QUESTION_ID_PARAM);
        Integer questionId = Integer.valueOf(questionIdParam);
        QuestionService questionService = new QuestionService();
        QuestionWrapper questionWrapper = questionService.showQuestionById(questionId);
        AnswerService answerService = new AnswerService();
        List<AnswerWrapper> answerWrapperList = answerService.showAnswersByQuestionId(questionId);
        String page = packAttributes(request, questionWrapper, answerWrapperList);
        return page;
    }

    private String packAttributes(HttpServletRequest request, QuestionWrapper questionWrapper, List<AnswerWrapper> answerWrapperList) {
        HttpSession session = request.getSession(true);
        session.setAttribute(QUESTION_ATTR, questionWrapper);
        session.setAttribute(ANSWER_LIST_ATTR, answerWrapperList);
        return MappingManager.getProperty(KEY_FOR_PAGE);
    }
}
