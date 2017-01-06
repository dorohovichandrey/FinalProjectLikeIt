package by.dorohovich.site.DAO;

import by.dorohovich.site.entity.Role;
import by.dorohovich.site.entity.User;
import by.dorohovich.site.exception.ConnectionProducerException;
import by.dorohovich.site.exception.DAOException;
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

    private static final String SELECT_ALL="SELECT * FROM user";
    private static final String CREATE_USER="INSERT INTO user (login, password, isAdmin) VALUES (?, ?, ?)";
    private static final String FIND_USER_BY_LOGIN="SELECT * FROM user WHERE login = ?";

    public UserDAO(ProxyConnection connection) {
        super(connection);
    }

    @Override
    public List<User> findAll() throws DAOException {
        try(Statement st = connection.createStatement()) {

            ResultSet rs = st.executeQuery(SELECT_ALL);
            List<User> list = createUserList(rs);
            return list;
        }
        catch (SQLException e)
        {
            throw new DAOException("Exception in UserDAO", e);
        }

    }

    @Override
    public User findEntityById(Integer id) {
        return null;
    }


    @Override
    public boolean create(User entity) throws DAOException {
        try (PreparedStatement preparedSt = connection.prepareStatement(CREATE_USER);){

            preparedSt.setString(1, entity.getLogin());
            preparedSt.setString(2, entity.getPassword());
            preparedSt.setInt(3, entity.getRole().getBit());
            preparedSt.executeUpdate();


            return preparedSt.getUpdateCount() == 1;



        } catch (SQLException e) {
            throw new DAOException("Exception in UserDAO", e);
        }

    }

    @Override
    public User update(User entity) {
        return null;
    }

    public User findUserByLogin(String login) throws DAOException {
        try(PreparedStatement preparedSt = connection.prepareStatement(FIND_USER_BY_LOGIN)) {
            preparedSt.setString(1, login);
            ResultSet rs=preparedSt.executeQuery();

            List<User> list = createUserList(rs);
            return list.size() == 1 ? list.get(0) : null;

        } catch (SQLException e) {
            throw new DAOException("Exception in UserDAO", e);
        }

    }

    private List<User> createUserList(ResultSet rs) throws SQLException{
        ArrayList<User> list = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt(1);
            String login = rs.getString(2);
            String password = rs.getString(3);
            int isAdmin = rs.getInt(4);
            Role role = Role.getRole(isAdmin);
            int rating = rs.getInt(5);
            User user = new User(id, login, password, role, rating);
            list.add(user);

        }
        rs.close();

        return list;
    }


}
