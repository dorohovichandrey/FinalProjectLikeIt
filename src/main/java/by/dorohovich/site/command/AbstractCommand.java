package by.dorohovich.site.command;

import by.dorohovich.site.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by User on 26.01.2017.
 */
public abstract class AbstractCommand {
    public String execute(HttpServletRequest request) throws CommandException{
        checkAccessRights(request);
        return doLogic(request);
    }

    protected abstract void checkAccessRights(HttpServletRequest request) throws CommandException;

    protected abstract String doLogic(HttpServletRequest request) throws CommandException;
}
