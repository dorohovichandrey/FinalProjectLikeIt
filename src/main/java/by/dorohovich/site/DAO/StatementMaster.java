package by.dorohovich.site.DAO;

import java.sql.PreparedStatement;

/**
 * Created by User on 31.01.2017.
 */
public interface StatementMaster {
    void prepare(PreparedStatement prSt, Object... params);
}
