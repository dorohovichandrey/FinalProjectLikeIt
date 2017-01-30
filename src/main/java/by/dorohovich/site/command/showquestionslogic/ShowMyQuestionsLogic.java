package by.dorohovich.site.command.showquestionslogic;

import by.dorohovich.site.entity.wrapper.QuestionWrapper;
import by.dorohovich.site.exception.ServiceException;
import by.dorohovich.site.service.QuestionService;

import java.util.List;

/**
 * Created by User on 30.01.2017.
 */
public class ShowMyQuestionsLogic extends AbstractShowQuestionsLogic {
    private static final String KEY_FOR_QUESTION_LIST_HEADER = "text.questionList.myQuestionsHeader";

    @Override
    protected List<QuestionWrapper> showQuestions(QuestionService questionService, String searchParam) throws ServiceException {
        Integer userId = Integer.valueOf(searchParam);
        List<QuestionWrapper> questions = questionService.showQuestionsByUserId(userId);
        return questions;
    }

    @Override
    protected String takeKeyForQuestionListHeader() {
        return KEY_FOR_QUESTION_LIST_HEADER;
    }
}
