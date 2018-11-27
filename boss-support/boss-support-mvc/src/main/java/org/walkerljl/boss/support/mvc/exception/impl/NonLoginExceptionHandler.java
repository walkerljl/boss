package org.walkerljl.boss.support.mvc.exception.impl;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.walkerljl.boss.support.mvc.auth.AuthenticationErrorCode;
import org.walkerljl.boss.support.mvc.auth.AuthenticationException;
import org.walkerljl.boss.support.mvc.exception.AbstractExceptionHandler;
import org.walkerljl.boss.support.mvc.util.ResponseUtils;
import org.walkerljl.toolkit.standard.Result;
import org.walkerljl.toolkit.template.log.Logger;
import org.walkerljl.toolkit.template.log.LoggerFactory;
import org.walkerljl.toolkit.template.log.util.LoggerUtil;

/**
 * @author xingxun
 */
public class NonLoginExceptionHandler extends AbstractExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(NonLoginExceptionHandler.class);

    private String loginAddress;

    @Override
    public void handleJSONRequest(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) {
        boolean isHit = isHit(exception);
        if (!isHit) {
            return;
        }
        Result<String> result = Result.failure(AuthenticationErrorCode.NOT_LOGGED_IN.getCode(),
                AuthenticationErrorCode.NOT_LOGGED_IN.getDescription());
        ResponseUtils.toJSON(response, result);
    }

    @Override
    public void handleNonJSONRequest(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) {
        boolean isHit = isHit(exception);
        if (!isHit) {
            return;
        }
        try {
            response.sendRedirect(loginAddress);
        } catch (IOException e) {
            LoggerUtil.error(LOGGER, e);
        }
    }

    private boolean isHit(Exception exception) {
        if (!(exception instanceof AuthenticationException)) {
            return false;
        }
        AuthenticationException authenticationException = (AuthenticationException) exception;
        return authenticationException.getCode() == AuthenticationErrorCode.NOT_LOGGED_IN;
    }

    /**
     * Setter method for property <tt>loginAddress</tt>.
     *
     * @param loginAddress value to be assigned to property loginAddress
     */
    public void setLoginAddress(String loginAddress) {
        this.loginAddress = loginAddress;
    }
}