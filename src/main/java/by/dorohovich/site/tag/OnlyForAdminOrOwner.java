package by.dorohovich.site.tag;


import by.dorohovich.site.entity.User;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * Created by User on 01.02.2017.
 */
public class OnlyForAdminOrOwner extends BodyTagSupport {
    private static final String USER_ATTR = "user";

    private String ownerId;

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public int doStartTag() throws JspException {
        User user = (User) pageContext.getSession().getAttribute(USER_ATTR);
        Integer userId = Integer.valueOf(ownerId);
        if (user != null && (user.isAdmin() || user.getId() == userId)) {
            return EVAL_BODY_INCLUDE;
        }

        return SKIP_BODY;
    }
}
