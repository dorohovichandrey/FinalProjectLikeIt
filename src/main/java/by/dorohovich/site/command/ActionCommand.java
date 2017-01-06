package by.dorohovich.site.command;

import by.dorohovich.site.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by User on 25.11.2016.
 */
public interface ActionCommand {
    String execute(HttpServletRequest request) throws CommandException;
}
