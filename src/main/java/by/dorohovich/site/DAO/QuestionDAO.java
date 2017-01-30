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

    private static final String INSERTED_COLUMNS = "question.userId, question.dateAndTime, question.text, question.themeId, question.rating, question.queHeader";
    private static final String SELECTED_COLUMNS = "question.questionId, " + INSERTED_COLUMNS;

    private static final String CREATE_QUESTION = "INSERT INTO question (" + INSERTED_COLUMNS + ") VALUES (?, ?, ?, ?, ?, ?)";
    private static final String ORDER_BY = "ORDER BY rating DESC, dateAndTime DESC";
    private static final String SELECT_QUESTIONS_ORDER_BY_DATE_AND_TIME = "SELECT " + SELECTED_COLUMNS + " FROM question ORDER BY dateAndTime DESC";
    private static final String SELECT_QUESTIONS_ORDER_BY_RATING = "SELECT " + SELECTED_COLUMNS + " FROM question " + ORDER_BY;
    private static final String SELECT_QUESTIONS_BY_THEME_ID = "SELECT " + SELECTED_COLUMNS + " FROM question WHERE themeId = ? " + ORDER_BY;
    private static final String SELECT_QUESTIONS_BY_USER_ID = "SELECT " + SELECTED_COLUMNS + " FROM question WHERE userId = ? " + ORDER_BY;
    private static final String SELECT_UNANSWERED_QUESTIONS =
            "SELECT " + SELECTED_COLUMNS
                    + " FROM question LEFT JOIN answer ON question.questionId = answer.questionId "
                    + "WHERE answer.questionId IS NULL "
            + ORDER_BY;

    private static final String SELECT_ANSWERED_BY_USER =
            "SELECT " + SELECTED_COLUMNS
                    + " FROM question INNER JOIN answer ON question.questionId = answer.questionId "
                    + "WHERE answer.userId = ?  "
                    + ORDER_BY;

    public QuestionDAO(ProxyConnection connection) {
        super(connection);
    }

    @Override
    public List<Question> findAll() throws DAOException {
        return null;
    }

    public List<Question> findQuestionsAnsweredByUser(Integer userId) throws DAOException {
        try {
            return tryFindQuestionsAnsweredByUser(userId);
        } catch (SQLException e) {
            throw new DAOException("Exception in questionDAO", e);
        }
    }

    private List<Question> tryFindQuestionsAnsweredByUser(Integer userId) throws SQLException {
        try (PreparedStatement preparedSt = connection.prepareStatement(SELECT_ANSWERED_BY_USER)) {
            preparedSt.setInt(1, userId);
            List<Question> questionList = takeQuestionListByPrStatement(preparedSt);
            return questionList;
        }
    }

    public List<Question> findQuestionsByThemeId(Integer themeId) throws DAOException {
        try {
            return tryFindQuestionsByThemeId(themeId);
        } catch (SQLException e) {
            throw new DAOException("Exception in questionDAO", e);
        }
    }

    private List<Question> tryFindQuestionsByThemeId(Integer themeId) throws SQLException {
        try (PreparedStatement preparedSt = connection.prepareStatement(SELECT_QUESTIONS_BY_THEME_ID)) {
            preparedSt.setInt(1, themeId);
            List<Question> questionList = takeQuestionListByPrStatement(preparedSt);
            return questionList;
        }
    }

    public List<Question> findQuestionsByUserId(Integer userId) throws DAOException {
        try {
            return tryFindQuestionsByUserId(userId);
        } catch (SQLException e) {
            throw new DAOException("Exception in questionDAO", e);
        }
    }

    private List<Question> tryFindQuestionsByUserId(Integer userId) throws SQLException {
        try (PreparedStatement preparedSt = connection.prepareStatement(SELECT_QUESTIONS_BY_USER_ID)) {
            preparedSt.setInt(1, userId);
            List<Question> questionList = takeQuestionListByPrStatement(preparedSt);
            return questionList;
        }
    }

    private List<Question> takeQuestionListByPrStatement(PreparedStatement preparedSt) throws SQLException {
        try(ResultSet rs = preparedSt.executeQuery()) {
            List<Question> list = makeQuestionList(rs);
            return list;
        }
    }

    public List<Question> findUnansweredQuestions() throws DAOException {
        try {
            return tryFindQuestionsByQuery(SELECT_UNANSWERED_QUESTIONS);
        } catch (SQLException e) {
            throw new DAOException("Exception in questionDAO", e);
        }
    }

    public List<Question> findQuestionsOrderByDateAndTime() throws DAOException {
        try {
            return tryFindQuestionsByQuery(SELECT_QUESTIONS_ORDER_BY_DATE_AND_TIME);
        } catch (SQLException e) {
            throw new DAOException("Exception in questionDAO", e);
        }
    }

    public List<Question> findQuestionsOrderByRating() throws DAOException {
        try {
            return tryFindQuestionsByQuery(SELECT_QUESTIONS_ORDER_BY_RATING);
        } catch (SQLException e) {
            throw new DAOException("Exception in questionDAO", e);
        }
    }

    private List<Question> tryFindQuestionsByQuery(String query) throws SQLException {
        try (Statement st = connection.createStatement()) {
            List<Question> questionList = takeQuestionListByQuery(query, st);
            return questionList;
        }
    }

    private List<Question> takeQuestionListByQuery(String query, Statement st) throws SQLException {
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
            checkUpdating(preparedSt);
        }
    }

    private void checkUpdating(PreparedStatement preparedSt) throws SQLException, DAOException {
        if (preparedSt.getUpdateCount() == 0) {
            throw new DAOException("Problem in QuestionDAO, when trying to make changes in question table");
        }
    }

    @Override
    public Question update(Question entity) {
        return null;
    }





}
