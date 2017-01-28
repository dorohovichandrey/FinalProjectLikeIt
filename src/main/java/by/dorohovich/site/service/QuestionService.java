package by.dorohovich.site.service;

import by.dorohovich.site.DAO.QuestionDAO;
import by.dorohovich.site.DAO.ThemeDAO;
import by.dorohovich.site.connectionpool.ConnectionPool;
import by.dorohovich.site.connectionpool.ProxyConnection;
import by.dorohovich.site.entity.Question;
import by.dorohovich.site.entity.Theme;
import by.dorohovich.site.entity.User;
import by.dorohovich.site.exception.ConnectionPoolException;
import by.dorohovich.site.exception.DAOException;
import by.dorohovich.site.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.criteria.CriteriaBuilder;

/**
 * Created by User on 26.01.2017.
 */
public class QuestionService {
    private static final Logger LOGGER = LogManager.getLogger();

    public void askQuestion(Integer ownerId, String text, String themeName, String header) throws ServiceException {
        try {
            tryAskQuestion(ownerId, text, themeName, header);
        } catch (ConnectionPoolException e) {
            LOGGER.error("Problem with getting connection, while trying to ask question", e);
            throw new ServiceException(e);
        } catch (DAOException e) {
            LOGGER.error("Problem with QuestionDAO, while trying to ask question", e);
            throw new ServiceException(e);
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
}
