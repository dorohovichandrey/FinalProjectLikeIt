package by.dorohovich.site.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by User on 02.01.2017.
 */
public class LikeItSessionListener implements HttpSessionListener {
    private final static String PARAM_LOCALE = "locale";
    private final static String DEFAULT_LOCALE = "en";
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        httpSessionEvent.getSession().setAttribute(PARAM_LOCALE, DEFAULT_LOCALE);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
    }
}
