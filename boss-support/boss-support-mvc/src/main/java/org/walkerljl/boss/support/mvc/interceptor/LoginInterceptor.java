package org.walkerljl.boss.support.mvc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.walkerljl.boss.sdk.auth.annotation.Authentication;
import org.walkerljl.boss.sdk.auth.enums.AuthType;
import org.walkerljl.boss.sdk.auth.support.AuthenticationSupport;
import org.walkerljl.boss.support.mvc.auth.Authenticator;
import org.walkerljl.boss.support.mvc.model.context.ServletContext;
import org.walkerljl.boss.support.mvc.model.context.UserContext;
import org.walkerljl.boss.support.mvc.model.context.UserContextFactory;

/**
 * 登录拦截器
 *
 * @author xingxun
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    private AuthenticationSupport authenticationSupport;
    private Authenticator         authenticator;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        //登录校验
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Authentication clazz = handlerMethod.getBean().getClass().getAnnotation(Authentication.class);
        Authentication method = handlerMethod.getMethodAnnotation(Authentication.class);
        AuthType authType = authenticationSupport.getType(clazz, method);
        if (authType == null) {
            return true;
        }
        boolean hasLogin = hasLogin();
        if (hasLogin) {
            return true;
        }

        return true;
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
     * 是否已经登录
     *
     * @return
     */
    private boolean hasLogin() {
        UserContext userContext = UserContextFactory.getContext();
        if (userContext == null) {
            return false;
        }
        return userContext.getUser() != null;
    }

    /**
     * Setter method for property <tt>authenticationSupport</tt>.
     *
     * @param authenticationSupport value to be assigned to property authenticationSupport
     */
    public void setAuthenticationSupport(AuthenticationSupport authenticationSupport) {
        this.authenticationSupport = authenticationSupport;
    }

    /**
     * Setter method for property <tt>authenticator</tt>.
     *
     * @param authenticator value to be assigned to property authenticator
     */
    public void setAuthenticator(Authenticator authenticator) {
        this.authenticator = authenticator;
    }
}