package by.dorohovich.site.service;

import by.dorohovich.site.DAO.AbstractDAO;
import by.dorohovich.site.entity.Entity;
import by.dorohovich.site.exception.DAOException;

import java.util.List;

/**
 * Created by User on 01.02.2017.
 */
public interface EntityListFinder<T extends Entity, D extends AbstractDAO> {
    List<T> find(D dao, Object... searchParams) throws DAOException;
}
