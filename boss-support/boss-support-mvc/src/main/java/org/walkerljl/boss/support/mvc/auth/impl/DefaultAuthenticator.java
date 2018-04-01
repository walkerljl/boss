package org.walkerljl.boss.support.mvc.auth.impl;

import java.io.IOException;
import java.util.Arrays;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.walkerljl.boss.sdk.auth.annotation.Authentication;
import org.walkerljl.boss.sdk.auth.enums.AuthCondition;
import org.walkerljl.boss.sdk.auth.enums.AuthType;
import org.walkerljl.boss.sdk.auth.support.AuthenticationSupport;
import org.walkerljl.boss.support.UserInfo;
import org.walkerljl.boss.support.enums.UserTypeEnum;
import org.walkerljl.boss.support.mvc.WebConstants;
import org.walkerljl.boss.support.mvc.auth.AuthenticationErrorCode;
import org.walkerljl.boss.support.mvc.auth.AuthenticationException;
import org.walkerljl.boss.support.mvc.auth.Authenticator;
import org.walkerljl.boss.support.mvc.model.context.ServletContext;
import org.walkerljl.boss.support.mvc.model.context.UserContext;
import org.walkerljl.boss.support.mvc.model.context.UserContextFactory;
import org.walkerljl.boss.support.mvc.util.UrlEncoderUtils;
import org.walkerljl.toolkit.lang.ArraysUtils;
import org.walkerljl.toolkit.lang.SetUtils;
import org.walkerljl.toolkit.lang.StringUtils;
import org.walkerljl.toolkit.logging.Logger;
import org.walkerljl.toolkit.logging.LoggerFactory;
import org.walkerljl.toolkit.template.log.LoggerUtil;

/**
 * DefaultAuthenticator
 *
 * @author xingxun
 */
public class DefaultAuthenticator implements Authenticator {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultAuthenticator.class);

    private String                exceptionAddress;
    private AuthenticationSupport authenticationSupport;

    @Override
    public boolean authenticate(String userId, String authCode) {
        if (StringUtils.isBlank(authCode)) {
            return true;
        }
        if (UserContextFactory.getContext().getUser().getType() == UserTypeEnum.ADMIN) {
            return true;
        }
        Set<String> userAuthCodes = UserContextFactory.getContext().getAuthCodes();
        return SetUtils.contains(userAuthCodes, authCode);
    }

    @Override
    public boolean authenticate(Authentication methodAuthentication, Authentication classAuthentication) {
        AuthType authType = authenticationSupport.getType(classAuthentication, methodAuthentication);
        if (authType != AuthType.CODE) {
            return true;
        }
        String[] authCodes = authenticationSupport.getAuthCodes(classAuthentication, methodAuthentication);
        if (ArraysUtils.isEmpty(authCodes)) {
            return true;
        }
        UserContext userContext = UserContextFactory.getContext();
        if (userContext == null) {
            return false;
        }
        UserInfo user = userContext.getUser();
        if (user == null) {
            return false;
        }
        if (user.getType() == UserTypeEnum.ADMIN) {
            return true;
        }
        Set<String> userAuthCodes = userContext.getAuthCodes();
        AuthCondition authCondition = authenticationSupport.getAuthCondition(classAuthentication, methodAuthentication);
        return authenticationSupport.authenticate(Arrays.asList(authCodes), userAuthCodes, authCondition);
    }

    @Override
    public void responsePermissionDenied(String permissionDeniedMessage) {
        HttpServletRequest request = ServletContext.getRequest();
        if (request == null) {
            return;
        }
        String requestUri = request.getRequestURI();
        if (StringUtils.isBlank(requestUri)) {
            return;
        }
        if (requestUri.endsWith(WebConstants.JSON_SUFFIX)
                || requestUri.endsWith(WebConstants.JSON_SUFFIX.toUpperCase())) {
            String message = AuthenticationErrorCode.PERMISSION_DENIED.getDescription();
            if (StringUtils.isNotBlank(permissionDeniedMessage)) {
                message += ":" + permissionDeniedMessage;
            }
            throw new AuthenticationException(AuthenticationErrorCode.PERMISSION_DENIED, message);
        } else {
            if (StringUtils.isNotBlank(permissionDeniedMessage)) {
                exceptionAddress += "&message" + UrlEncoderUtils.encode(permissionDeniedMessage, WebConstants.DEFAULT_ENCODING);
            }
            HttpServletResponse response = ServletContext.getResponse();
            if (response == null) {
                return;
            }
            try {
                response.sendRedirect(exceptionAddress);
            } catch (IOException e) {
                LoggerUtil.error(LOGGER, e);
            }
        }
    }

    /**
     * Setter method for property <tt>exceptionAddress</tt>.
     *
     * @param exceptionAddress value to be assigned to property exceptionAddress
     */
    public void setExceptionAddress(String exceptionAddress) {
        this.exceptionAddress = exceptionAddress;
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