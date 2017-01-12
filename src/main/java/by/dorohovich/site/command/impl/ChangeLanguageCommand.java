package by.dorohovich.site.command.impl;

import by.dorohovich.site.command.ActionCommand;
import by.dorohovich.site.utility.MappingManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by User on 01.12.2016.
 */
public class ChangeLanguageCommand implements ActionCommand {
    private static final String PARAM_LANG = "lang";
    private static final String ATR_LOCALE = "locale";
    private static final String ATR_PAGE = "page";

    public String execute(HttpServletRequest request) {
        String lang = request.getParameter(PARAM_LANG);
        HttpSession session = request.getSession(true);
        session.setAttribute(ATR_LOCALE, lang);
        String currPage = (String) session.getAttribute(ATR_PAGE);
        return MappingManager.getProperty(currPage);
    }
}
