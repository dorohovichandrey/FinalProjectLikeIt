package by.dorohovich.site.command.factory;

import by.dorohovich.site.command.ActionCommand;
import by.dorohovich.site.command.impl.EmptyCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by User on 26.11.2016.
 */
public class ActionFactory {
    private static final Logger LOGGER = LogManager.getLogger();

    public ActionCommand defineCommand(HttpServletRequest request) {
        ActionCommand current = new EmptyCommand();
// извлечение имени команды из запроса
        String action = request.getParameter("command");
        LOGGER.info("Command: " + action);
        if (action == null || action.equals("")) {
// если команда не задана в текущем запросе
            return current;
        }
// получение объекта, соответствующего команде
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCommand();

        return current;
    }
}
