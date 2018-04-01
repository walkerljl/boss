package org.walkerljl.boss.support.mvc.exception.impl;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.walkerljl.boss.sdk.auth.annotation.Authentication;
import org.walkerljl.boss.sdk.auth.enums.AuthType;
import org.walkerljl.boss.sdk.auth.support.AuthenticationSupport;
import org.walkerljl.boss.support.mvc.auth.AuthenticationErrorCode;
import org.walkerljl.boss.support.mvc.auth.AuthenticationException;
import org.walkerljl.boss.support.mvc.exception.AbstractExceptionHandler;
import org.walkerljl.boss.support.mvc.util.ResponseUtils;
import org.walkerljl.toolkit.logging.Logger;
import org.walkerljl.toolkit.logging.LoggerFactory;
import org.walkerljl.toolkit.standard.Result;
import org.walkerljl.toolkit.template.log.LoggerUtil;

/**
 * @author xingxun
 */
public class PermissionDeniedExceptionHandler extends AbstractExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(NonLoginExceptionHandler.class);

    private String                permissionDeniedAddress;
    private AuthenticationSupport authenticationSupport;

    @Override
    public void handleJSONRequest(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) {
        boolean isHit = isHit(handler, exception);
        if (!isHit) {
            return;
        }
        String message = getMessage(handler);
        Result<String> result = Result.failure(AuthenticationErrorCode.PERMISSION_DENIED.getCode(), message);
        ResponseUtils.toJSON(response, result);
    }

    @Override
    public void handleNonJSONRequest(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) {
        boolean isHit = isHit(handler, exception);
        if (!isHit) {
            return;
        }
        String message = getMessage(handler);
        try {
            response.sendRedirect(String.format("%s&%s", permissionDeniedAddress, message));
        } catch (IOException e) {
            LoggerUtil.error(LOGGER, e);
        }
    }

    private boolean isHit(Object handler, Exception exception) {
        if (!(handler instanceof HandlerMethod)) {
            return false;
        }
        if (!(exception instanceof AuthenticationException)) {
            return false;
        }
        AuthenticationException authenticationException = (AuthenticationException) exception;
        return authenticationException.getCode() == AuthenticationErrorCode.PERMISSION_DENIED;
    }

    private String getMessage(Object handler) {
        //权限校验
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Authentication classAuthentication = handlerMethod.getBean().getClass().getAnnotation(Authentication.class);
        Authentication methodAuthentication = handlerMethod.getMethodAnnotation(Authentication.class);
        AuthType authType = authenticationSupport.getType(classAuthentication, methodAuthentication);
        if (authType == null) {
            return "";
        }
        String permissionDeniedMessage = authenticationSupport.getPermissionDeniedMessage(classAuthentication, methodAuthentication);
        String message = String.format("%s:%s", AuthenticationErrorCode.PERMISSION_DENIED.getDescription(), permissionDeniedMessage);
        return message;
    }

    /**
     * Setter method for property <tt>permissionDeniedAddress</tt>.
     *
     * @param permissionDeniedAddress value to be assigned to property permissionDeniedAddress
     */
    public void setPermissionDeniedAddress(String permissionDeniedAddress) {
        this.permissionDeniedAddress = permissionDeniedAddress;
    }

    /**
     * Setter method for property <tt>authenticationSupport</tt>.
     *
     * @param authenticationSupport value to be assigned to property authenticationSupport
     */
    public void setAuthenticationSupport(AuthenticationSupport authenticationSupport) {
        this.authenticationSupport = authenticationSupport;
    }
}