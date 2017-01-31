package by.dorohovich.site.service;

import by.dorohovich.site.DAO.ThemeDAO;
import by.dorohovich.site.connectionpool.ConnectionPool;
import by.dorohovich.site.connectionpool.ProxyConnection;
import by.dorohovich.site.entity.Theme;
import by.dorohovich.site.exception.ConnectionPoolException;
import by.dorohovich.site.exception.DAOException;
import by.dorohovich.site.exception.ServiceException;

import java.util.List;

/**
 * Created by User on 31.01.2017.
 */
public class ThemeService {


    public List<Theme> showThemes() throws ServiceException
    {
        try {
            return tryShowThemes();
        }  catch (ConnectionPoolException e) {
            throw new ServiceException("Problem with getting connection, when trying to show thems", e);
        } catch (DAOException e) {
            throw new ServiceException("Problem with QuestionDAO, when trying to show themes", e);
        }
    }

    private List<Theme> tryShowThemes() throws ConnectionPoolException, DAOException {
        try (ProxyConnection connection = ConnectionPool.getInstance().takeConnection()) {
            ThemeDAO themeDAO = new ThemeDAO(connection);
            List<Theme> themeList = themeDAO.findAll();
            return themeList;
        }
    }
}
