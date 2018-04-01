package org.walkerljl.boss.sdk.auth;


import org.walkerljl.boss.sdk.auth.enums.AuthObjectType;

/**
 * Authentication service
 *
 * @author lijunlin
 */
public interface AuthenticationService {

    boolean auth(AuthObjectType authObjectType, String authObjectId, String authCode);
}
