package by.dorohovich.site.command;

import by.dorohovich.site.utility.MappingManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

/**
 * Created by User on 01.12.2016.
 */
public class ChangeLanguageCommand implements ActionCommand {
    private static final String PARAM_LANG = "lang";
    private static final String PARAM_LOCALE = "locale";
    private static final String DEFAULT_LOCALE = "ru";
    private static final String PARAM_PAGE = "page";
    private static final String PARAM_INDEX = "path.page.index";
    public String execute(HttpServletRequest request) {
        String lang = request.getParameter(PARAM_LANG);
        HttpSession session = request.getSession(true);

        if(lang.isEmpty()){
            session.setAttribute(PARAM_LOCALE, DEFAULT_LOCALE);
            //request.setAttribute(PARAM_LOCALE, DEFAULT_LOCALE);
        }else{
            session.setAttribute(PARAM_LOCALE, lang);
            //request.setAttribute(PARAM_LOCALE, lang);
        }

        String currPage = (String) session.getAttribute(PARAM_PAGE);

        if(currPage == null){
            currPage = PARAM_INDEX;
        }
        return MappingManager.getProperty(currPage);
    }
}
