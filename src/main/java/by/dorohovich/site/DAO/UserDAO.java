package by.dorohovich.site.DAO;

import by.dorohovich.site.entity.Role;
import by.dorohovich.site.entity.User;
import by.dorohovich.site.exception.DAOException;
import by.dorohovich.site.connectionpool.ProxyConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 28.12.2016.
 */
public class UserDAO extends AbstractDAO<Integer, User> {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final String SELECT_ALL = "SELECT * FROM user";
    private static final String SELECT_USERS_SORTED_BY_RATING = "SELECT userId, login, password, email, isAdmin, rating FROM user ORDER BY rating DESC";
    private static final String CREATE_USER = "INSERT INTO user (login, password, email, isAdmin) VALUES (?, ?, ?, ?)";
    private static final String FIND_USER_BY_LOGIN = "SELECT * FROM user WHERE login = ?";
    private static final String UPDATE_PASSWORD = "UPDATE user SET password = ? WHERE login = ?";
    private static final String UPDATE_EMAIL = "UPDATE user SET email = ? WHERE login = ?";


    public UserDAO(ProxyConnection connection) {
        super(connection);
    }

    @Override
    public List<User> findAll() throws DAOException {
        try {
            return findUsersByQuery(SELECT_ALL);
        } catch (SQLException e) {
            throw new DAOException("Exception in UserDAO, while trying to findAll", e);
        }

    }

    public List<User> findUsersSortedByRating() throws DAOException {
        try {
            return findUsersByQuery(SELECT_USERS_SORTED_BY_RATING);
        } catch (SQLException e) {
            throw new DAOException("Exception in UserDAO", e);
        }

    }

    private List<User> findUsersByQuery(String query) throws SQLException {
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(query);
            List<User> list = makeUserList(rs);
            return list;
        }
    }


    @Override
    public User findEntityById(Integer id) {
        return null;
    }


    @Override
    public void create(User entity) throws DAOException {
        try {
            tryCreate(entity);
        } catch (SQLException e) {
            throw new DAOException("Problems in UserDAO, while trying to create user", e);
        }
    }

    private void tryCreate(User entity) throws SQLException, DAOException {
        try (PreparedStatement preparedSt = connection.prepareStatement(CREATE_USER)) {
            preparedSt.setString(1, entity.getLogin());
            preparedSt.setString(2, entity.getPassword());
            preparedSt.setString(3, entity.getEmail());
            preparedSt.setInt(4, entity.getRole().getBit());
            preparedSt.executeUpdate();
            if (preparedSt.getUpdateCount() != 1) {
                throw new DAOException("User was not created");
            }
        }
    }

    @Override
    public User update(User entity) {
        return null;
    }

    public User findUserByLogin(String login) throws DAOException {
        try {
            return tryFindUserByLogin(login);
        } catch (SQLException e) {
            throw new DAOException("Exception in UserDAO", e);
        }
    }

    private User tryFindUserByLogin(String login) throws SQLException {
        try (PreparedStatement preparedSt = connection.prepareStatement(FIND_USER_BY_LOGIN)) {
            preparedSt.setString(1, login);
            ResultSet rs = preparedSt.executeQuery();
            List<User> list = makeUserList(rs);
            return list.size() == 1 ? list.get(0) : null;
        }
    }

    public void updatePassword(String login, String password) throws DAOException{
        try {
            tryUpdatePassword(login, password);
        } catch (SQLException e){
            throw new DAOException("Problems in UserDAO, while trying change password", e);
        }
    }

    private void tryUpdatePassword(String login, String password) throws SQLException, DAOException{
        try (PreparedStatement preparedSt = connection.prepareStatement(UPDATE_PASSWORD)) {
            preparedSt.setString(1, password);
            preparedSt.setString(2, login);
            preparedSt.executeUpdate();
            if (preparedSt.getUpdateCount() != 1) {
                throw new DAOException("Password was not updated");
            }
        }
    }

    public void updateEmail(String login, String email) throws DAOException{
        try {
            tryUpdateEmail(login, email);
        } catch (SQLException e){
            throw new DAOException("Problems in UserDAO, while trying change email", e);
        }
    }

    private void tryUpdateEmail(String login, String email) throws SQLException, DAOException{
        try (PreparedStatement preparedSt = connection.prepareStatement(UPDATE_EMAIL)) {
            preparedSt.setString(1, email);
            preparedSt.setString(2, login);
            preparedSt.executeUpdate();
            if (preparedSt.getUpdateCount() != 1) {
                throw new DAOException("Email was not updated");
            }
        }
    }

    private List<User> makeUserList(ResultSet rs) throws SQLException {
        ArrayList<User> list = new ArrayList<User>();
        while (rs.next()) {
            int id = rs.getInt(1);
            String login = rs.getString(2);
            String password = rs.getString(3);
            String email = rs.getString(4);
            int isAdmin = rs.getInt(5);
            Role role = Role.getRole(isAdmin);
            int rating = rs.getInt(6);
            User user = new User(id, login, password, email, role, rating);
            list.add(user);
        }
        return list;
    }



}
