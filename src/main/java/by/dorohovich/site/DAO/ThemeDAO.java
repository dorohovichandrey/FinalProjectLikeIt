package by.dorohovich.site.DAO;

import by.dorohovich.site.connectionpool.ProxyConnection;
import by.dorohovich.site.entity.Theme;
import by.dorohovich.site.exception.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 27.01.2017.
 */
public class ThemeDAO extends AbstractDAO<Integer, Theme> {

    private static final String SELECTED_COLUMNS = "themeId, themeName";
    private static final String FIND_THEME_BY_NAME = "SELECT " + SELECTED_COLUMNS + " FROM theme WHERE themeName = ? and isDeleted = 0";
    private static final String FIND_THEME_BY_ID = "SELECT " + SELECTED_COLUMNS + " FROM theme WHERE themeId = ? and isDeleted = 0";

    public ThemeDAO(ProxyConnection connection) {
        super(connection);
    }

    @Override
    public List<Theme> findAll() throws DAOException {
        return null;
    }

    private List<Theme> tryFindThemesByQuery(String query) throws SQLException {
        try (Statement st = connection.createStatement()) {
            List<Theme> themeList = takeThemeListByQuery(query, st);
            return themeList;
        }
    }

    private List<Theme> takeThemeListByQuery(String query, Statement st) throws SQLException {
        try(ResultSet rs = st.executeQuery(query)) {
            List<Theme> questionList = makeThemeList(rs);
            return questionList;
        }
    }


    @Override
    public void create(Theme entity) throws DAOException {

    }

    @Override
    public Theme update(Theme entity) {
        return null;
    }

    @Override
    public Theme findEntityById(Integer id) throws DAOException {
        try {
            return tryFindEntityById(id);
        } catch (SQLException e) {
            throw new DAOException("Exception in ThemeDAO", e);
        }
    }

    private Theme tryFindEntityById(Integer id) throws SQLException {
        try (PreparedStatement preparedSt = connection.prepareStatement(FIND_THEME_BY_ID)) {
            preparedSt.setInt(1, id);
            Theme theme = takeThemeByPrStatement(preparedSt);
            return theme;
        }
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
            Theme theme = takeThemeByPrStatement(preparedSt);
            return theme;
        }
    }

    private Theme takeThemeByPrStatement(PreparedStatement preparedSt) throws SQLException {
        try(ResultSet rs = preparedSt.executeQuery()) {
            List<Theme> list = makeThemeList(rs);
            return list.size() == 1 ? list.get(0) : null;
        }
    }

    private List<Theme> makeThemeList(ResultSet rs) throws SQLException {
        ArrayList<Theme> list = new ArrayList<Theme>();
        while (rs.next()) {
            Theme theme = makeTheme(rs);
            list.add(theme);
        }
        return list;
    }

    private Theme makeTheme(ResultSet rs) throws SQLException {
        int id = rs.getInt(1);
        String themeName = rs.getString(2);
        return new Theme(id, themeName);
    }
}
