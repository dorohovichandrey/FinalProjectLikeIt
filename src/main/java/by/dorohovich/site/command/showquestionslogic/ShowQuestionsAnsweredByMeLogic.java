package by.dorohovich.site.command.showquestionslogic;

import by.dorohovich.site.service.wrapper.entitywrapperimpl.QuestionWrapper;
import by.dorohovich.site.exception.ServiceException;
import by.dorohovich.site.service.serviceimpl.QuestionService;

import java.util.List;

/**
 * Created by User on 31.01.2017.
 */
public class ShowQuestionsAnsweredByMeLogic extends AbstractShowQuestionsLogic {
    private static final String KEY_FOR_QUESTION_LIST_HEADER = "text.questionList.questionsAnsweredByMeHeader";

    @Override
    protected List<QuestionWrapper> showQuestions(QuestionService questionService, String searchParam) throws ServiceException {
        Integer userId = Integer.valueOf(searchParam);
        List<QuestionWrapper> questions = questionService.showQuestionsAnsweredByUser(userId);
        return questions;
    }

    @Override
    protected String takeKeyForQuestionListHeader() {
        return KEY_FOR_QUESTION_LIST_HEADER;
    }
}
