package by.dorohovich.site.DAO;

import by.dorohovich.site.entity.Question;
import by.dorohovich.site.exception.DAOException;

import java.util.List;

/**
 * Created by User on 29.01.2017.
 */
public interface QuestionListFinder {
    List<Question> find(QuestionDAO questionDAO) throws DAOException;
}
