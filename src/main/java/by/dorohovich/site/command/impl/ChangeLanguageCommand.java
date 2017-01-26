package by.dorohovich.site.command.impl;

import by.dorohovich.site.command.Command;
import by.dorohovich.site.utility.MappingManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by User on 01.12.2016.
 */
public class ChangeLanguageCommand implements Command {
    private static final String LOCALE_PARAM = "lang";
    private static final String LOCALE_ATTR = "locale";
    private static final String KEY_FOR_PAGE_ATTR = "page";

    public String execute(HttpServletRequest request) {
        String locale = request.getParameter(LOCALE_PARAM);
        HttpSession session = request.getSession(true);
        session.setAttribute(LOCALE_ATTR, locale);
        String keyForCurPage = (String) session.getAttribute(KEY_FOR_PAGE_ATTR);
        String curPage = MappingManager.getProperty(keyForCurPage);
        return curPage;
    }
}
