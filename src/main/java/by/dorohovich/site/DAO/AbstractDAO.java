package by.dorohovich.site.DAO;

import by.dorohovich.site.entity.Entity;
import by.dorohovich.site.exception.DAOException;
import by.dorohovich.site.connectionpool.ProxyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 28.12.2016.
 */
public abstract class AbstractDAO<K,T extends Entity> {

    protected ProxyConnection connection;

    public AbstractDAO() {}

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

    protected T tryFindEntityByPrStatement(String query, StatementMaster master, Object... params) throws SQLException{
        List<T> entityList = tryFindEntityListByPrStatement(query, master, params);
        T entity = (entityList.isEmpty()) ? null : entityList.get(0);
        return entity;
    }

    protected List<T> tryFindEntityListByPrStatement(String query, StatementMaster master, Object... params) throws SQLException {
        try (PreparedStatement preparedSt = connection.prepareStatement(query)) {
            master.prepare(preparedSt, params);
            List<T> entityList = takeEntityListByPrStatement(preparedSt);
            return entityList;
        }
    }

    private List<T> takeEntityListByPrStatement(PreparedStatement preparedSt) throws SQLException {
        try(ResultSet rs = preparedSt.executeQuery()) {
            List<T> list = makeEntityList(rs);
            return list;
        }
    }

    protected List<T> tryFindEntityListByQuery(String query) throws SQLException {
        try (Statement st = connection.createStatement()) {
            List<T> questionList = takeEntityListByQuery(query, st);
            return questionList;
        }
    }

    private List<T> takeEntityListByQuery(String query, Statement st) throws SQLException{
        try(ResultSet rs = st.executeQuery(query)) {
            List<T> entityList = makeEntityList(rs);
            return entityList;
        }
    }

    private List<T> makeEntityList(ResultSet rs) throws SQLException{
        List<T> list = new ArrayList<T>();
        while (rs.next()) {
            T entity = makeEntity(rs);
            list.add(entity);
        }
        return list;
    }

    protected abstract T makeEntity(ResultSet rs) throws SQLException;

}
