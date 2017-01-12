package by.dorohovich.site.DAO;

import by.dorohovich.site.entity.Role;
import by.dorohovich.site.entity.User;
import by.dorohovich.site.exception.ConnectionProducerException;
import by.dorohovich.site.exception.DAOException;
import by.dorohovich.site.pool.ConnectionPool;
import by.dorohovich.site.pool.ProxyConnection;

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

    private static final String SELECT_ALL = "SELECT * FROM user";
    private static final String CREATE_USER = "INSERT INTO user (login, password, email, isAdmin) VALUES (?, ?, ?, ?)";
    private static final String FIND_USER_BY_LOGIN = "SELECT * FROM user WHERE login = ?";


    public UserDAO(ProxyConnection connection) {

        super(connection);
    }

    @Override
    public List<User> findAll() throws DAOException {
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(SELECT_ALL);
            List<User> list = createUserList(rs);
            return list;
        } catch (SQLException e) {
            throw new DAOException("Exception in UserDAO", e);
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
            List<User> list = createUserList(rs);
            return list.size() == 1 ? list.get(0) : null;
        }
    }

    private List<User> createUserList(ResultSet rs) throws SQLException {
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
