package by.dorohovich.site.service;

import by.dorohovich.site.DAO.AbstractDAO;
import by.dorohovich.site.entity.Entity;
import by.dorohovich.site.exception.DAOException;

/**
 * Created by User on 02.02.2017.
 */
public interface EntityFinder<T extends Entity, D extends AbstractDAO> {
    T find(D dao, Object... searchParams) throws DAOException;
}
