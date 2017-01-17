package by.dorohovich.site.command.impl;

import by.dorohovich.site.command.ActionCommand;
import by.dorohovich.site.exception.CommandException;
import by.dorohovich.site.utility.MappingManager;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by User on 06.01.2017.
 */
public class LogOutCommand implements ActionCommand {


    private static final String KEY_FOR_PAGE = "page.index";
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        request.getSession().invalidate();
        String page = MappingManager.getProperty(KEY_FOR_PAGE);
        return page;
    }
}
