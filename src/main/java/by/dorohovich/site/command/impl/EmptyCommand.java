package by.dorohovich.site.command.impl;



import by.dorohovich.site.command.ActionCommand;
import by.dorohovich.site.utility.MappingManager;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by User on 26.11.2016.
 */
public class EmptyCommand implements ActionCommand {

    private static final String PARAM_LANG = "lang";
    private static final String PARAM_LOCALE = "locale";
    private static final String DEFAULT_LOCALE = "ru";
    private static final String PARAM_INDEX = "path.page.index";
    private static final String PARAM_PAGE = "page";
    public String execute(HttpServletRequest request) {
        /*HttpSession session = request.getSession(true);
        if(session.getAttribute(PARAM_LANG) == null){
            session.setAttribute(PARAM_LANG, DEFAULT_LOCALE);
        }

        String lang = (String) session.getAttribute(PARAM_LANG);
        request.setAttribute(PARAM_LOCALE, lang);
        session.setAttribute(PARAM_PAGE, PARAM_INDEX);*/
        request.setAttribute(PARAM_LOCALE, DEFAULT_LOCALE);

        return MappingManager.getProperty(PARAM_INDEX);
    }
}

