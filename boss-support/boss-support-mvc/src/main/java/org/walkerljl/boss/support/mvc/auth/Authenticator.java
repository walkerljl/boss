package org.walkerljl.boss.support.mvc.auth;

import org.walkerljl.boss.sdk.auth.annotation.Authentication;

/**
 * Authenticator
 *
 * @author xingxun
 */
public interface Authenticator {

    /**
     * 鉴权
     *
     * @param userId
     * @param authCode
     * @return
     */
    boolean authenticate(String userId, String authCode);

    /**
     * 鉴权
     *
     * @param methodAuthentication
     * @param classAuthentication
     * @return
     */
    boolean authenticate(Authentication methodAuthentication, Authentication classAuthentication);

    /**
     * 响应拒绝响应
     *
     * @param permissionDeniedMessage
     */
    void responsePermissionDenied(String permissionDeniedMessage);
}