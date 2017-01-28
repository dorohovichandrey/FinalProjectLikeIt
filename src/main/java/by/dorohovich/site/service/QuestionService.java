package by.dorohovich.site.service;

import by.dorohovich.site.DAO.QuestionDAO;
import by.dorohovich.site.DAO.ThemeDAO;
import by.dorohovich.site.DAO.UserDAO;
import by.dorohovich.site.connectionpool.ConnectionPool;
import by.dorohovich.site.connectionpool.ProxyConnection;
import by.dorohovich.site.entity.Question;
import by.dorohovich.site.entity.Theme;
import by.dorohovich.site.entity.User;
import by.dorohovich.site.entity.wrapper.QuestionWrapper;
import by.dorohovich.site.exception.ConnectionPoolException;
import by.dorohovich.site.exception.DAOException;
import by.dorohovich.site.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 26.01.2017.
 */
public class QuestionService {
    private static final Logger LOGGER = LogManager.getLogger();

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
            if(theme == null){
                throw new ServiceException("Theme with name = " + themeName + " not exist");
            }
            Integer themeId = theme.getId();
            Question question = new Question(ownerId, text, themeId, header);
            questionDAO.create(question);
        }
    }

    public List<QuestionWrapper> showFreshestQuestions() throws ServiceException {
        try {
            return tryShowFreshestQuestions();
        } catch (ConnectionPoolException e) {
            throw new ServiceException("Problem with getting connection, when trying to show freshest questions", e);
        } catch (DAOException e) {
            throw new ServiceException("Problem with QuestionDAO, when trying to show freshest questions", e);
        }
    }

    private List<QuestionWrapper> tryShowFreshestQuestions() throws ConnectionPoolException, DAOException {
        try (ProxyConnection connection = ConnectionPool.getInstance().takeConnection()) {
            QuestionDAO questionDAO = new QuestionDAO(connection);
            List<Question> questionList = questionDAO.findQuestionsOrderByDateAndTime();
            List<QuestionWrapper> questionWrapperList = makeQuestionWrapperList(questionList, connection);
            return questionWrapperList;
        }
    }

    private List<QuestionWrapper> makeQuestionWrapperList(List<Question> questionList, ProxyConnection connection) throws DAOException{
        List<QuestionWrapper> questionWrapperList = new ArrayList<QuestionWrapper>();
        UserDAO userDAO = new UserDAO(connection);
        ThemeDAO themeDAO = new ThemeDAO(connection);
        for(Question question : questionList){
            QuestionWrapper questionWrapper = makeQuestionWrapper(userDAO, themeDAO, question);
            questionWrapperList.add(questionWrapper);
        }
        return questionWrapperList;
    }

    private QuestionWrapper makeQuestionWrapper(UserDAO userDAO, ThemeDAO themeDAO, Question question) throws DAOException {
        Integer userId = question.getUserId();
        User user = userDAO.findEntityById(userId);
        Integer themeId = question.getThemeId();
        Theme theme = themeDAO.findEntityById(themeId);
        QuestionWrapper questionWrapper = new QuestionWrapper(question, user, theme);
        return questionWrapper;
    }


}
