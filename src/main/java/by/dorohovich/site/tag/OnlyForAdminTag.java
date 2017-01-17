package by.dorohovich.site.tag;

import by.dorohovich.site.entity.User;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * Created by User on 18.01.2017.
 */
public class OnlyForAdminTag extends BodyTagSupport {
    private static final String USER_ATTR = "user";

    @Override
    public int doStartTag() throws JspException {
        User user = (User) pageContext.getSession().getAttribute(USER_ATTR);

        if (user != null && user.isAdmin()) {
                return EVAL_BODY_INCLUDE;
        }

        return SKIP_BODY;
    }
}