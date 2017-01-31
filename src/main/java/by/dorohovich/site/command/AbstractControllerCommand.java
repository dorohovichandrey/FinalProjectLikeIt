package by.dorohovich.site.command;

import by.dorohovich.site.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by User on 31.01.2017.
 */
public abstract class AbstractControllerCommand extends AbstractCommand {

    @Override
    protected void checkAccessRights(HttpServletRequest request) throws CommandException {

    }

}
