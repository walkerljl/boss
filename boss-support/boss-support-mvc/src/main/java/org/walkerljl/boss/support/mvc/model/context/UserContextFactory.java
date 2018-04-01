package org.walkerljl.boss.support.mvc.model.context;

import javax.servlet.http.HttpSession;

/**
 * 用户上下文Factory
 *
 * @author xingxun
 */
public class UserContextFactory {

    /**
     * 用户上下文Key
     */
    private static final String USER_CONTEXT_KEY = "usercontext";

    /**
     * 获取上下文
     *
     * @return
     */
    public static UserContext getContext() {
        HttpSession session = ServletContext.getRequest().getSession();
        if (session == null) {
            return null;
        }
        Object userContextObject = session.getAttribute(USER_CONTEXT_KEY);
        if (userContextObject instanceof UserContext) {
            UserContext userContext = (UserContext) userContextObject;
            return userContext;
        } else {
            return null;
        }
    }

    /**
     * 设置上下文
     *
     * @param userContext 用户上下文
     */
    public static void setContext(UserContext userContext) {
        HttpSession session = ServletContext.getRequest().getSession();
        if (session == null) {
            return;
        }
        session.setAttribute(USER_CONTEXT_KEY, userContext);
    }
}