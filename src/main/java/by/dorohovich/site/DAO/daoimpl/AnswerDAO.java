package by.dorohovich.site.DAO.daoimpl;

import by.dorohovich.site.DAO.AbstractDAO;
import by.dorohovich.site.connectionpool.ProxyConnection;
import by.dorohovich.site.entity.Answer;
import by.dorohovich.site.exception.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by User on 01.02.2017.
 */
public class AnswerDAO extends AbstractDAO<Integer, Answer> {

    private static final String INSERTED_COLUMNS = "answer.questionId, answer.userId, answer.dateAndTime, answer.text, answer.rating";
    private static final String SELECTED_COLUMNS = "answer.answerId, " + INSERTED_COLUMNS;
    private static final String CREATE_ANSWER = "INSERT INTO answer (" + INSERTED_COLUMNS + ") VALUES (?, ?, ?, ?, ?)";
    private static final String ORDER_BY = "ORDER BY rating DESC, dateAndTime DESC";
    private static final String FIND_ANSWERS_BY_QUESTION_ID = "SELECT " + SELECTED_COLUMNS
            + " FROM answer WHERE questionId = ? " + ORDER_BY;
    private static final String UPDATE_ANSWER = "UPDATE answer SET dateAndTime = ?, text = ?, rating = ? WHERE id = ?";

    public AnswerDAO(ProxyConnection connection) {
        super(connection);
    }

    @Override
    public List<Answer> findAll() throws DAOException {
        return null;
    }

    @Override
    public Answer findEntityById(Integer id) throws DAOException {
        return null;
    }



    @Override
    public void create(Answer entity) throws DAOException {
        try {
            tryCreate(entity);
        } catch (SQLException e) {
            throw new DAOException("Problems in AnswerDAO, while trying to create question", e);
        }
    }

    private void tryCreate(Answer entity) throws SQLException, DAOException {
        try (PreparedStatement preparedSt = connection.prepareStatement(CREATE_ANSWER)) {
            preparedSt.setInt(1, entity.getQuestionId());
            preparedSt.setInt(2, entity.getUserId());
            preparedSt.setTimestamp(3, entity.getDateAndTime());
            preparedSt.setString(4, entity.getText());
            preparedSt.setInt(5, entity.getRating());
            preparedSt.executeUpdate();
            checkUpdating(preparedSt);
        }
    }

    private void checkUpdating(PreparedStatement preparedSt) throws SQLException, DAOException {
        if (preparedSt.getUpdateCount() == 0) {
            throw new DAOException("Problem in AnswerDAO, when trying to make changes in answer table");
        }
    }

    @Override
    public void update(Answer entity) {

    }

    private void tryUpdateAnswer(Answer entity) throws SQLException, DAOException{
        try (PreparedStatement preparedSt = connection.prepareStatement(UPDATE_ANSWER)) {
            preparedSt.setTimestamp(1, entity.getDateAndTime());
            preparedSt.setString(2, entity.getText());
            preparedSt.setInt(3, entity.getRating());
            preparedSt.setInt(4, entity.getId());
            preparedSt.executeUpdate();
            checkUpdating(preparedSt);
        }
    }



    public List<Answer> findAnswerListByQuestionId(Integer questionId) throws DAOException{
        try {
            return tryFindEntityListByPrStatement(FIND_ANSWERS_BY_QUESTION_ID, ((prSt, params) -> prSt.setInt(1, questionId)), questionId);
        } catch (SQLException e) {
            throw new DAOException("Problem in AnswerDAO, while trying to find answers", e);
        }
    }

    @Override
    protected Answer makeEntity(ResultSet rs) throws SQLException {
        int answerId = rs.getInt(1);
        int questionId = rs.getInt(2);
        int ownerId = rs.getInt(3);
        Timestamp dateAndTime = rs.getTimestamp(4);
        String text = rs.getString(5);
        int rating = rs.getInt(6);
        Answer answer = new Answer(answerId, questionId, ownerId, dateAndTime, text, rating);
        return answer;
    }
}
