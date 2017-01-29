package by.dorohovich.site.command.showquestions;

import by.dorohovich.site.command.AbstractGuestCommand;
import by.dorohovich.site.entity.wrapper.QuestionWrapper;
import by.dorohovich.site.exception.CommandException;
import by.dorohovich.site.exception.ServiceException;
import by.dorohovich.site.service.QuestionService;
import by.dorohovich.site.utility.MappingManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by User on 29.01.2017.
 */
public abstract class AbstractShowQuestionsLogic {

    private static final String SEARCH_PARAM = "searchParam";
    private static final String QUESTIONS_ATTR = "questions";
    private static final String KEY_FOR_QUESTION_LIST_HEADER_ATTR = "keyForQuestionListHeader";

    private static final String KEY_FOR_PAGE = "page.questionList";


    public String tryDoLogic(HttpServletRequest request) throws ServiceException {
        String searchParam = request.getParameter(SEARCH_PARAM);
        QuestionService questionService = new QuestionService();
        List<QuestionWrapper> questions = showQuestions(questionService, searchParam);
        packAttributes(questions, request);
        String page = MappingManager.getProperty(KEY_FOR_PAGE);
        return page;
    }

    protected abstract List<QuestionWrapper> showQuestions(QuestionService questionService, String searchParam) throws ServiceException;

    private void packAttributes(List<QuestionWrapper> questions, HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        session.setAttribute(QUESTIONS_ATTR, questions);
        session.setAttribute(KEY_FOR_QUESTION_LIST_HEADER_ATTR, takeKeyForQuestionListHeader());

    }

    protected abstract String takeKeyForQuestionListHeader();
}
