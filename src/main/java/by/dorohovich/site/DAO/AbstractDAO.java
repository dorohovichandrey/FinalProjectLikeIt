package by.dorohovich.site.DAO;

import by.dorohovich.site.entity.Entity;
import by.dorohovich.site.exception.DAOException;
import by.dorohovich.site.connectionpool.ProxyConnection;

import java.util.List;

/**
 * Created by User on 28.12.2016.
 */
public abstract class AbstractDAO<K,T extends Entity> {

    protected ProxyConnection connection;

    public AbstractDAO() {
    }

    public AbstractDAO(ProxyConnection connection) {
        this.connection = connection;
    }

    public ProxyConnection getConnection() {
        return connection;
    }

    public void setConnection(ProxyConnection connection) {
        this.connection = connection;
    }

    public abstract List<T> findAll() throws DAOException;
    public abstract T findEntityById(K id) throws DAOException;
    public abstract void create(T entity) throws DAOException;
    public abstract T update(T entity);
}
