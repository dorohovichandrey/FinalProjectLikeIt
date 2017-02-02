package by.dorohovich.site.service.wrapper;

import by.dorohovich.site.DAO.AbstractDAO;
import by.dorohovich.site.connectionpool.ProxyConnection;
import by.dorohovich.site.entity.Entity;
import by.dorohovich.site.entity.Question;
import by.dorohovich.site.exception.DAOException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 01.02.2017.
 */
public abstract class WrapperBuilder<T extends Wrappable, W extends EntityWrapper> {

    protected List<W> makeEntityWrapperList(List<T> entityList, AbstractDAO... daos) throws DAOException {
        List<W> questionWrapperList = new ArrayList<W>();
        for(T entity : entityList){
            W wrapper = makeEntityWrapper(entity, daos);
            questionWrapperList.add(wrapper);
        }
        return questionWrapperList;
    }

    protected abstract W makeEntityWrapper(T entity, AbstractDAO... daos) throws DAOException;
}
