package by.dorohovich.site.service.serviceimpl;

import by.dorohovich.site.DAO.daoimpl.QuestionDAO;
import by.dorohovich.site.DAO.daoimpl.ThemeDAO;
import by.dorohovich.site.DAO.daoimpl.UserDAO;
import by.dorohovich.site.connectionpool.ConnectionPool;
import by.dorohovich.site.connectionpool.ProxyConnection;
import by.dorohovich.site.entity.Question;
import by.dorohovich.site.entity.Theme;
import by.dorohovich.site.service.AbstractService;
import by.dorohovich.site.service.EntityFinder;
import by.dorohovich.site.service.EntityListFinder;
import by.dorohovich.site.service.wrapper.entitywrapperimpl.QuestionWrapper;
import by.dorohovich.site.exception.ConnectionPoolException;
import by.dorohovich.site.exception.DAOException;
import by.dorohovich.site.exception.ServiceException;
import by.dorohovich.site.service.wrapper.wrappermasterimpl.QuestionWrapperBuilder;

import java.util.List;

/**
 * Created by User on 26.01.2017.
 */
public class QuestionService extends AbstractService<Integer, Question> {

    public void askQuestion(Integer ownerId, String text, String themeName, String header) throws ServiceException {
        try {
            tryAskQuestion(ownerId, text, themeName, header);
        } catch (ConnectionPoolException e) {
            throw new ServiceException("Problem with getting connection, when trying to ask question", e);
        } catch (DAOException e) {
            throw new ServiceException("Problem with QuestionDAO, when trying to ask question", e);
        }
    }

    private void tryAskQuestion(Integer ownerId, String text, String themeName, String header) throws DAOException, ConnectionPoolException, ServiceException {
        try (ProxyConnection connection = ConnectionPool.getInstance().takeConnection()) {
            QuestionDAO questionDAO = new QuestionDAO(connection);
            ThemeDAO themeDAO = new ThemeDAO(connection);
            Theme theme = themeDAO.findThemeByName(themeName);
            checkThemeFinding(themeName, theme);
            Integer themeId = theme.getId();
            Question question = new Question(ownerId, text, themeId, header);
            questionDAO.create(question);
        }
    }

    private void checkThemeFinding(String themeName, Theme theme) throws ServiceException {
        if(theme == null){
            throw new ServiceException("Theme with name = " + themeName + " not exist");
        }
    }

    public QuestionWrapper showQuestionById(Integer id) throws ServiceException{
        try {
            return tryShowQuestionById(id);
        } catch (ConnectionPoolException e) {
            throw new ServiceException("Problem with getting connection, when trying to show questions by userId", e);
        } catch (DAOException e) {
            throw new ServiceException("Problem with QuestionDAO, when trying to show questions by userId", e);
        }
    }

    private QuestionWrapper tryShowQuestionById(Integer id) throws ConnectionPoolException, DAOException, ServiceException {
        QuestionWrapper question = tryShowQuestion(((questionDAO, searchParams) -> questionDAO.findEntityById(id)));
        checkQuestionFinding(id, question);
        return question;
    }

    private void checkQuestionFinding(Integer id, QuestionWrapper question) throws ServiceException {
        if(question == null){
            throw new ServiceException("Question with id" + id + " not exist");
        }
    }

    public List<QuestionWrapper> showQuestionsByThemeId(Integer themeId) throws ServiceException {
        try {
            return tryShowQuestions((questionDAO, searchParam) -> questionDAO.findQuestionsByThemeId(themeId), themeId);
        } catch (ConnectionPoolException e) {
            throw new ServiceException("Problem with getting connection, when trying to show questions by userId", e);
        } catch (DAOException e) {
            throw new ServiceException("Problem with QuestionDAO, when trying to show questions by userId", e);
        }
    }

    public List<QuestionWrapper> showQuestionsByUserId(Integer userId) throws ServiceException {
        try {
            return tryShowQuestions((questionDAO, searchParams) -> questionDAO.findQuestionsByUserId(userId), userId);
        } catch (ConnectionPoolException e) {
            throw new ServiceException("Problem with getting connection, when trying to show questions by userId", e);
        } catch (DAOException e) {
            throw new ServiceException("Problem with QuestionDAO, when trying to show questions by userId", e);
        }
    }

    public List<QuestionWrapper> showQuestionsAnsweredByUser(Integer userId) throws ServiceException {
        try {
            return tryShowQuestions((questionDAO, searchParam) -> questionDAO.findQuestionsAnsweredByUser(userId), userId);
        } catch (ConnectionPoolException e) {
            throw new ServiceException("Problem with getting connection, when trying to showUnansweredQuestions", e);
        } catch (DAOException e) {
            throw new ServiceException("Problem with QuestionDAO, when trying to showUnansweredQuestions", e);
        }
    }

    public List<QuestionWrapper> showUnansweredQuestions() throws ServiceException {
        try {
            return tryShowQuestions((questionDAO, searchParam) -> questionDAO.findUnansweredQuestions());
        } catch (ConnectionPoolException e) {
            throw new ServiceException("Problem with getting connection, when trying to showUnansweredQuestions", e);
        } catch (DAOException e) {
            throw new ServiceException("Problem with QuestionDAO, when trying to showUnansweredQuestions", e);
        }
    }

    public List<QuestionWrapper> showTopRatedQuestions() throws ServiceException {
        try {
            return tryShowQuestions((questionDAO, searchParam) -> questionDAO.findQuestionsOrderByRating());
        } catch (ConnectionPoolException e) {
            throw new ServiceException("Problem with getting connection, when trying to show top rated questions", e);
        } catch (DAOException e) {
            throw new ServiceException("Problem with QuestionDAO, when trying to show top rated questions", e);
        }
    }

    public List<QuestionWrapper> showFreshestQuestions() throws ServiceException {
        try {
            return tryShowQuestions((questionDAO, searchParam) -> questionDAO.findQuestionsOrderByDateAndTime());
        } catch (ConnectionPoolException e) {
            throw new ServiceException("Problem with getting connection, when trying to show freshest questions", e);
        } catch (DAOException e) {
            throw new ServiceException("Problem with QuestionDAO, when trying to show freshest questions", e);
        }
    }

    private QuestionWrapper tryShowQuestion(EntityFinder<Question, QuestionDAO> finder, Object... searchParams) throws ConnectionPoolException, DAOException {
        try (ProxyConnection connection = ConnectionPool.getInstance().takeConnection()) {
            QuestionDAO questionDAO = new QuestionDAO(connection);
            Question question = finder.find(questionDAO, searchParams);
            QuestionWrapper questionWrapper = makeWrapper(connection, question);
            return questionWrapper;
        }
    }

    private QuestionWrapper makeWrapper(ProxyConnection connection, Question question) throws DAOException {
        UserDAO userDAO = new UserDAO(connection);
        ThemeDAO themeDAO = new ThemeDAO(connection);
        QuestionWrapperBuilder builder = new QuestionWrapperBuilder();
        return builder.makeWrapper(question, userDAO, themeDAO);
    }

    private List<QuestionWrapper> tryShowQuestions(EntityListFinder<Question, QuestionDAO> finder, Object... searchParams) throws ConnectionPoolException, DAOException {
        try (ProxyConnection connection = ConnectionPool.getInstance().takeConnection()) {
            QuestionDAO questionDAO = new QuestionDAO(connection);
            List<Question> questionList = finder.find(questionDAO, searchParams);
            List<QuestionWrapper> questionWrapperList = makeWrappers(connection, questionList);
            return questionWrapperList;
        }
    }

    private List<QuestionWrapper> makeWrappers(ProxyConnection connection, List<Question> questionList) throws DAOException {
        UserDAO userDAO = new UserDAO(connection);
        ThemeDAO themeDAO = new ThemeDAO(connection);
        QuestionWrapperBuilder builder = new QuestionWrapperBuilder();
        return builder.makeWrappers(questionList, userDAO, themeDAO);
    }


}
