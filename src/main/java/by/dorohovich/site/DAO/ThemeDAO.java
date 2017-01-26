package by.dorohovich.site.DAO;

import by.dorohovich.site.connectionpool.ProxyConnection;
import by.dorohovich.site.entity.Theme;
import by.dorohovich.site.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 27.01.2017.
 */
public class ThemeDAO extends AbstractDAO<Integer, Theme> {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final String FIND_THEME_BY_NAME = "SELECT themeId, themeName FROM theme WHERE themeName = ? and isDeleted = 0";

    public ThemeDAO(ProxyConnection connection) {
        super(connection);
    }

    @Override
    public List<Theme> findAll() throws DAOException {
        return null;
    }

    @Override
    public Theme findEntityById(Integer id) {
        return null;
    }

    @Override
    public void create(Theme entity) throws DAOException {

    }

    @Override
    public Theme update(Theme entity) {
        return null;
    }

    public Theme findThemeByName(String themeName) throws DAOException {
        try {
            return tryFindThemeByName(themeName);
        } catch (SQLException e) {
            throw new DAOException("Exception in ThemeDAO", e);
        }
    }

    private Theme tryFindThemeByName(String themeName) throws SQLException {
        try (PreparedStatement preparedSt = connection.prepareStatement(FIND_THEME_BY_NAME)) {
            preparedSt.setString(1, themeName);
            ResultSet rs = preparedSt.executeQuery();
            List<Theme> list = makeUserList(rs);
            return list.size() == 1 ? list.get(0) : null;
        }
    }

    private List<Theme> makeUserList(ResultSet rs) throws SQLException {
        ArrayList<Theme> list = new ArrayList<Theme>();
        while (rs.next()) {
            int id = rs.getInt(1);
            String themeName = rs.getString(2);
            Theme theme = new Theme(id, themeName);
            list.add(theme);
        }
        return list;
    }
}
