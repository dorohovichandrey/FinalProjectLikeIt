package by.dorohovich.site.command.implcommand.definer;

import by.dorohovich.site.command.AbstractCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by User on 26.11.2016.
 */
public class CommandDefiner {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final String COMMAND_PARAM = "command";

    public AbstractCommand define(HttpServletRequest request) {
        String command = request.getParameter(COMMAND_PARAM);
        LOGGER.info("Command: " + command);
        CommandEnum currentEnum = CommandEnum.valueOf(command.toUpperCase());
        AbstractCommand curCommand = currentEnum.getCommand();
        return curCommand;
    }
}
