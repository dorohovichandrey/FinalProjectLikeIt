package by.dorohovich.site.service.wrapper.wrappermasterimpl;

import by.dorohovich.site.DAO.AbstractDAO;
import by.dorohovich.site.DAO.daoimpl.ThemeDAO;
import by.dorohovich.site.DAO.daoimpl.UserDAO;
import by.dorohovich.site.entity.Question;
import by.dorohovich.site.entity.Theme;
import by.dorohovich.site.entity.User;
import by.dorohovich.site.exception.DAOException;
import by.dorohovich.site.service.wrapper.WrapperBuilder;
import by.dorohovich.site.service.wrapper.entitywrapperimpl.QuestionWrapper;

import java.util.List;

/**
 * Created by User on 01.02.2017.
 */
public class QuestionWrapperBuilder extends WrapperBuilder<Question, QuestionWrapper> {

    public List<QuestionWrapper> makeWrappers(List<Question> list, UserDAO userDAO, ThemeDAO themeDAO) throws DAOException{
        return makeEntityWrapperList(list, userDAO, themeDAO);
    }

    public QuestionWrapper makeWrapper(Question question, UserDAO userDAO, ThemeDAO themeDAO) throws DAOException{
        return makeEntityWrapper(question, userDAO, themeDAO);
    }

    @Override
    protected QuestionWrapper makeEntityWrapper(Question entity, AbstractDAO... daos) throws DAOException {
        Integer userId = entity.getUserId();
        User user = ((UserDAO)daos[0]).findEntityById(userId);
        Integer themeId = entity.getThemeId();
        Theme theme = ((ThemeDAO)daos[1]).findEntityById(themeId);
        QuestionWrapper questionWrapper = new QuestionWrapper(entity, user, theme);
        return questionWrapper;
    }
}
