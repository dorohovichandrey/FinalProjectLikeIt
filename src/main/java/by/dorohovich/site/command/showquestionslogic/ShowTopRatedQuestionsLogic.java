package by.dorohovich.site.command.showquestionslogic;

import by.dorohovich.site.service.wrapper.entitywrapperimpl.QuestionWrapper;
import by.dorohovich.site.exception.ServiceException;
import by.dorohovich.site.service.serviceimpl.QuestionService;

import java.util.List;

/**
 * Created by User on 29.01.2017.
 */
public class ShowTopRatedQuestionsLogic extends AbstractShowQuestionsLogic{
    private static final String KEY_FOR_QUESTION_LIST_HEADER = "text.questionList.topRatedQuestionsHeader";

    @Override
    protected List<QuestionWrapper> showQuestions(QuestionService questionService, String searchParam) throws ServiceException {
        List<QuestionWrapper> questions = questionService.showTopRatedQuestions();
        return questions;
    }

    @Override
    protected String takeKeyForQuestionListHeader() {
        return KEY_FOR_QUESTION_LIST_HEADER;
    }
}
