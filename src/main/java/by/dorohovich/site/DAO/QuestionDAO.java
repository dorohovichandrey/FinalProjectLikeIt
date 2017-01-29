package by.dorohovich.site.DAO;

import by.dorohovich.site.connectionpool.ProxyConnection;
import by.dorohovich.site.entity.Question;
import by.dorohovich.site.exception.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 26.01.2017.
 */
public class QuestionDAO extends AbstractDAO<Integer, Question> {

    private static final String INSERTED_COLUMNS = "userId, dateAndTime, text, themeId, rating, queHeader";
    private static final String SELECTED_COLUMNS = "questionId, " + INSERTED_COLUMNS;

    private static final String CREATE_QUESTION = "INSERT INTO question (" + INSERTED_COLUMNS + ") VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_QUESTIONS_ORDER_BY_DATE_AND_TIME = "SELECT " + SELECTED_COLUMNS + " FROM question ORDER BY dateAndTime DESC";
    private static final String SELECT_QUESTIONS_ORDER_BY_RATING = "SELECT " + SELECTED_COLUMNS + " FROM question ORDER BY rating DESC";


    public QuestionDAO(ProxyConnection connection) {
        super(connection);
    }

    @Override
    public List<Question> findAll() throws DAOException {
        return null;
    }

    public List<Question> findQuestionsOrderByDateAndTime() throws DAOException {
        try {
            return findQuestionsByQuery(SELECT_QUESTIONS_ORDER_BY_DATE_AND_TIME);
        } catch (SQLException e) {
            throw new DAOException("Exception in questionDAO", e);
        }
    }

    public List<Question> findQuestionsOrderByRating() throws DAOException {
        try {
            return findQuestionsByQuery(SELECT_QUESTIONS_ORDER_BY_RATING);
        } catch (SQLException e) {
            throw new DAOException("Exception in questionDAO", e);
        }
    }

    private List<Question> findQuestionsByQuery(String query) throws SQLException {
        try (Statement st = connection.createStatement()) {
            List<Question> questionList = takeQuestionList(query, st);
            return questionList;
        }
    }

    private List<Question> takeQuestionList(String query, Statement st) throws SQLException {
        try(ResultSet rs = st.executeQuery(query)) {
            List<Question> questionList = makeQuestionList(rs);
            return questionList;
        }
    }

    private List<Question> makeQuestionList(ResultSet rs) throws SQLException {
        ArrayList<Question> list = new ArrayList<Question>();
        while (rs.next()) {
            Question question = makeQuestion(rs);
            list.add(question);
        }
        return list;
    }

    private Question makeQuestion(ResultSet rs) throws SQLException {
        int questionId = rs.getInt(1);
        int ownerId = rs.getInt(2);
        Timestamp dateAndTime = rs.getTimestamp(3);
        String text = rs.getString(4);
        int themeId = rs.getInt(5);
        int rating = rs.getInt(6);
        String queHeader = rs.getString(7);
        Question question = new Question(questionId, ownerId, dateAndTime, text, themeId, rating, queHeader);
        return question;
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
            preparedSt.setInt(1, entity.getUserId());
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
