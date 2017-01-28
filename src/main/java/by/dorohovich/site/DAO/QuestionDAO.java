package by.dorohovich.site.DAO;

import by.dorohovich.site.connectionpool.ProxyConnection;
import by.dorohovich.site.entity.Question;
import by.dorohovich.site.entity.Role;
import by.dorohovich.site.entity.User;
import by.dorohovich.site.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 26.01.2017.
 */
public class QuestionDAO extends AbstractDAO<Integer, Question> {

    private static final String CREATE_QUESTION = "INSERT INTO question (userId, dateAndTime, text, themeId, rating, queHeader) VALUES (?, ?, ?, ?, ?, ?)";

    public QuestionDAO(ProxyConnection connection) {
        super(connection);
    }

    @Override
    public List<Question> findAll() throws DAOException {
        return null;
    }

    @Override
    public Question findEntityById(Integer id) {
        return null;
    }

    @Override
    public void create(Question entity) throws DAOException {
        try {
            tryCreate(entity);
        } catch (SQLException e) {
            throw new DAOException("Problems in QuestionDAO, while trying to create question", e);
        }
    }

    private void tryCreate(Question entity) throws SQLException, DAOException {
        try (PreparedStatement preparedSt = connection.prepareStatement(CREATE_QUESTION)) {
            preparedSt.setInt(1, entity.getOwnerId());
            preparedSt.setTimestamp(2, entity.getDateAndTime());
            preparedSt.setString(3, entity.getText());
            preparedSt.setInt(4, entity.getThemeId());
            preparedSt.setInt(5, entity.getRating());
            preparedSt.setString(6, entity.getHeader());
            preparedSt.executeUpdate();
            if (preparedSt.getUpdateCount() != 1) {
                throw new DAOException("Question was not created");
            }
        }
    }

    @Override
    public Question update(Question entity) {
        return null;
    }




}
