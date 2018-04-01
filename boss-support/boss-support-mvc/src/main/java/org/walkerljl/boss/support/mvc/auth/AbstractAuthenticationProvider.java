package org.walkerljl.boss.support.mvc.auth;

import org.walkerljl.boss.sdk.auth.support.AuthenticationProvider;

/**
 * 抽象的远程服务提供者
 *
 * @author lijunlin
 */
public abstract class AbstractAuthenticationProvider implements AuthenticationProvider {

    /**
     * appId
     */
    private String appId;
    /**
     * token
     */
    private String token;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}