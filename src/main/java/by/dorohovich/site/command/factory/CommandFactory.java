package by.dorohovich.site.command.factory;

import by.dorohovich.site.command.Command;
import by.dorohovich.site.command.impl.EmptyCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by User on 26.11.2016.
 */
public class CommandFactory {
    private static final Logger LOGGER = LogManager.getLogger();

    public Command defineCommand(HttpServletRequest request) {
        Command current = new EmptyCommand();
        String command = request.getParameter("command");
        LOGGER.info("Command: " + command);
        if (command == null || command.equals("")) {
// если команда не задана в текущем запросе
            return current;
        }
// получение объекта, соответствующего команде
            CommandEnum currentEnum = CommandEnum.valueOf(command.toUpperCase());
            current = currentEnum.getCommand();

        return current;
    }
}
