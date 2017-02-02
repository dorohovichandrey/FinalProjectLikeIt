package by.dorohovich.site.service.wrapper.wrappermasterimpl;

import by.dorohovich.site.DAO.AbstractDAO;
import by.dorohovich.site.DAO.daoimpl.QuestionDAO;
import by.dorohovich.site.DAO.daoimpl.UserDAO;
import by.dorohovich.site.entity.Answer;
import by.dorohovich.site.entity.Question;
import by.dorohovich.site.entity.User;
import by.dorohovich.site.exception.DAOException;
import by.dorohovich.site.service.wrapper.WrapperBuilder;
import by.dorohovich.site.service.wrapper.entitywrapperimpl.AnswerWrapper;

import java.util.List;

/**
 * Created by User on 01.02.2017.
 */
public class AnswerWrapperBuilder extends WrapperBuilder<Answer, AnswerWrapper> {

    public List<AnswerWrapper> makeWrappers(List<Answer> list, UserDAO userDAO, QuestionDAO questionDAO) throws DAOException{
        return makeEntityWrapperList(list, userDAO, questionDAO);
    }

    @Override
    protected AnswerWrapper makeEntityWrapper(Answer entity, AbstractDAO... daos) throws DAOException {
            Integer userId = entity.getUserId();
            User user = ((UserDAO)daos[0]).findEntityById(userId);
            Integer questionId = entity.getQuestionId();
            Question question = ((QuestionDAO)daos[1]).findEntityById(questionId);
            AnswerWrapper answerWrapper = new AnswerWrapper(entity, question, user);
            return answerWrapper;

    }
}
