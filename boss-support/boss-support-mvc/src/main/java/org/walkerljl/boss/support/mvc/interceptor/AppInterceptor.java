package org.walkerljl.boss.support.mvc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.walkerljl.boss.support.UserInfo;
import org.walkerljl.boss.support.enums.UserTypeEnum;
import org.walkerljl.boss.support.mvc.WebConstants;
import org.walkerljl.boss.support.mvc.model.context.ServletContext;
import org.walkerljl.boss.support.mvc.model.context.UserContext;
import org.walkerljl.boss.support.mvc.model.context.UserContextFactory;

/**
 * AppInterceptor
 *
 * @author xingxun
 */
public class AppInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        boolean result = true;

        //初始化Servlet上下文
        ServletContext.initContext(request, response);
        //初始化用户上下文
        initUserContext();

        //设置请求响应编码
        request.setCharacterEncoding(WebConstants.DEFAULT_CHARSET);
        response.setCharacterEncoding(request.getCharacterEncoding());
        if (!(handler instanceof HandlerMethod)) {
            request.setAttribute(WebConstants.STATIC_RES_REQUEST, true);
            return result;
        }

        return result;
    }

    @Override
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(
            HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        ServletContext.remove();
    }

    /**
     * 初始化用户上下文
     */
    private void initUserContext() {
        UserContext userContext = UserContextFactory.getContext();
        if (userContext == null) {
            UserInfo user = new UserInfo("xingxun", "行寻", UserTypeEnum.ADMIN);
            userContext = new UserContext(user);
            UserContextFactory.setContext(userContext);
        }
    }
}