package by.dorohovich.site.DAO.daoimpl;

import by.dorohovich.site.DAO.AbstractDAO;
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
    private static final String FIND_ALL_THEMES = "SELECT " + SELECTED_COLUMNS + " FROM theme WHERE isDeleted = 0 ORDER BY themeName ASC";

    public ThemeDAO(ProxyConnection connection) {
        super(connection);
    }

    @Override
    public List<Theme> findAll() throws DAOException {
        try{
            return tryFindEntityListByQuery(FIND_ALL_THEMES);
        } catch (SQLException e){
            throw new DAOException("Problem in ThemeDAO, while trying to fina all themes", e);
        }
    }

    @Override
    public Theme findEntityById(Integer id) throws DAOException {
        try {
            return tryFindEntityByPrStatement(FIND_THEME_BY_ID, ((prSt, params) -> prSt.setInt(1,id)), id);
        } catch (SQLException e) {
            throw new DAOException("Exception in ThemeDAO", e);
        }
    }

    public Theme findThemeByName(String themeName) throws DAOException {
        try {
            return tryFindEntityByPrStatement(FIND_THEME_BY_NAME, ((prSt, params) -> prSt.setString(1,themeName)), themeName);
        } catch (SQLException e) {
            throw new DAOException("Exception in ThemeDAO", e);
        }
    }

    protected Theme makeEntity(ResultSet rs) throws SQLException {
        int id = rs.getInt(1);
        String themeName = rs.getString(2);
        return new Theme(id, themeName);
    }

    @Override
    public void create(Theme entity) throws DAOException {

    }

    @Override
    public void update(Theme entity) {
    }


}
