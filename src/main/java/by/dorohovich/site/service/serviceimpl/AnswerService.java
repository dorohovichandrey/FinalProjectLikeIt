package by.dorohovich.site.service.serviceimpl;

import by.dorohovich.site.DAO.daoimpl.AnswerDAO;
import by.dorohovich.site.DAO.daoimpl.QuestionDAO;
import by.dorohovich.site.DAO.daoimpl.UserDAO;
import by.dorohovich.site.connectionpool.ConnectionPool;
import by.dorohovich.site.connectionpool.ProxyConnection;
import by.dorohovich.site.entity.Answer;
import by.dorohovich.site.exception.ConnectionPoolException;
import by.dorohovich.site.exception.DAOException;
import by.dorohovich.site.exception.ServiceException;
import by.dorohovich.site.service.AbstractService;
import by.dorohovich.site.service.EntityListFinder;
import by.dorohovich.site.service.wrapper.entitywrapperimpl.AnswerWrapper;
import by.dorohovich.site.service.wrapper.wrappermasterimpl.AnswerWrapperBuilder;

import java.util.List;

/**
 * Created by User on 01.02.2017.
 */
public class AnswerService extends AbstractService<Integer, Answer> {

   public void createAnswer(Integer questionId, Integer ownerId, String text) throws ServiceException {
        try{
        tryCreate(questionId, ownerId, text);
        } catch (ConnectionPoolException e) {
            throw new ServiceException("Problem with getting connection, when trying to ask question", e);
        } catch (DAOException e) {
            throw new ServiceException("Problem with QuestionDAO, when trying to ask question", e);
        }

    }

    private void tryCreate(Integer questionId, Integer ownerId, String text) throws DAOException, ConnectionPoolException {
        try (ProxyConnection connection = ConnectionPool.getInstance().takeConnection()) {
            AnswerDAO answerDAO = new AnswerDAO(connection);
            Answer answer = new Answer(questionId, ownerId, text);
            answerDAO.create(answer);
        }
    }

    public List<AnswerWrapper> showAnswersByQuestionId(Integer questionId) throws ServiceException {
        try {
            return tryShowAnswers((answerDAO, searchParams) -> answerDAO.findAnswerListByQuestionId(questionId), questionId);
        } catch (ConnectionPoolException e) {
            throw new ServiceException("Problem with getting connection, when trying to show questions by userId", e);
        } catch (DAOException e) {
            throw new ServiceException("Problem with QuestionDAO, when trying to show questions by userId", e);
        }
    }

    private List<AnswerWrapper> tryShowAnswers(EntityListFinder<Answer, AnswerDAO> finder, Object searchParam) throws ConnectionPoolException, DAOException {
        try (ProxyConnection connection = ConnectionPool.getInstance().takeConnection()) {
            AnswerDAO answerDAO = new AnswerDAO(connection);
            List<Answer> answerList = finder.find(answerDAO, searchParam);
            List<AnswerWrapper> answerWrapperList = makeWrappers(connection, answerList);
            return answerWrapperList;
        }
    }

    private List<AnswerWrapper> makeWrappers(ProxyConnection connection, List<Answer> answerList) throws DAOException {
        UserDAO userDAO = new UserDAO(connection);
        QuestionDAO questionDAO = new QuestionDAO(connection);
        AnswerWrapperBuilder builder = new AnswerWrapperBuilder();
        return builder.makeWrappers(answerList, userDAO, questionDAO);
    }

}
