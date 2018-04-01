package org.walkerljl.boss.sdk.auth.support.impl;

import java.util.List;
import java.util.Set;

import org.walkerljl.boss.sdk.auth.annotation.Authentication;
import org.walkerljl.boss.sdk.auth.enums.AuthCondition;
import org.walkerljl.boss.sdk.auth.enums.AuthType;
import org.walkerljl.boss.sdk.auth.support.AuthenticationSupport;
import org.walkerljl.boss.sdk.auth.util.ArrayUtil;
import org.walkerljl.boss.sdk.auth.util.CollectionUtil;

/**
 * 默认的权限验证支撑类
 *
 * @author xingxun
 */
public class DefaultAuthenticationSupport implements AuthenticationSupport {

    private static final String CODE_SEPERATOR = "-";

    @Override
    public AuthCondition getAuthCondition(Authentication clazzAuthentication, Authentication methodAuthentication) {
        AuthCondition authCondition = null;
        if (clazzAuthentication != null && methodAuthentication != null) {
            authCondition = methodAuthentication.condition();
        } else if (clazzAuthentication == null && methodAuthentication != null) {
            authCondition = methodAuthentication.condition();
        } else if (clazzAuthentication != null && methodAuthentication == null) {
            authCondition = clazzAuthentication.condition();
        }
        return authCondition;
    }

    @Override
    public AuthType getType(Authentication clazzAuthentication, Authentication methodAuthentication) {
        AuthType authType = null;
        if (clazzAuthentication != null && methodAuthentication != null) {
            authType = methodAuthentication.type();
        } else if (clazzAuthentication == null && methodAuthentication != null) {
            authType = methodAuthentication.type();
        } else if (clazzAuthentication != null && methodAuthentication == null) {
            authType = clazzAuthentication.type();
        }
        return authType;
    }

    @Override
    public String[] getAuthCodes(Authentication clazzAuthentication, Authentication methodAuthentication) {
        String[] authCodes = null;
        if (validateAuthCodes(clazzAuthentication) && validateAuthCodes(methodAuthentication)) {
            String authCodePrefix = clazzAuthentication.codes()[0];
            authCodes = new String[methodAuthentication.codes().length];
            for (int index = 0; index < methodAuthentication.codes().length; index++) {
                authCodes[index] = authCodePrefix + CODE_SEPERATOR + methodAuthentication.codes()[index];
            }
        } else if (!validateAuthCodes(clazzAuthentication) && validateAuthCodes(methodAuthentication)) {
            authCodes = methodAuthentication.codes();
        } else if (validateAuthCodes(clazzAuthentication) && !validateAuthCodes(methodAuthentication)) {
            authCodes = clazzAuthentication.codes();
        }
        return authCodes;
    }

    @Override
    public boolean authenticate(List<String> authCodes, Set<String> userAuthCodes,
                                AuthCondition authCondition) {
        if (CollectionUtil.isEmpty(authCodes)) {
            return true;
        }
        if (CollectionUtil.isEmpty(userAuthCodes)) {
            return false;
        }
        if (authCondition == AuthCondition.OR) {
            for (String authCode : authCodes) {
                if (userAuthCodes.contains(authCode)) {
                    return true;
                }
            }
        } else {
            for (String authCode : authCodes) {
                if (!userAuthCodes.contains(authCode)) {
                    return false;
                }
            }
        }

        return false;
    }

    @Override
    public String getPermissionDeniedMessage(Authentication clazzAuthentication, Authentication methodAuthentication) {
        if (methodAuthentication == null) {
            return "";
        }
        return String.format("%s(%s)", methodAuthentication.description(),
                ArrayUtil.toString(methodAuthentication.codes(), ","));
    }

    /**
     * 校验权限码的合法性
     *
     * @param authentication
     * @return
     */
    private boolean validateAuthCodes(Authentication authentication) {
        return authentication != null && authentication.codes() != null && authentication.codes().length > 0;
    }
}