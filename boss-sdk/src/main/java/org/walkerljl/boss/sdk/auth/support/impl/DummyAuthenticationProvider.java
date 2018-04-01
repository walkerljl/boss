package org.walkerljl.boss.sdk.auth.support.impl;

import java.util.List;

import org.walkerljl.boss.sdk.auth.model.Button;
import org.walkerljl.boss.sdk.auth.model.Menu;
import org.walkerljl.boss.sdk.auth.support.AuthenticationProvider;

/**
 *
 * @author xingxun
 */
public class DummyAuthenticationProvider implements AuthenticationProvider {

    @Override
    public List<Menu> getUserAuthMenus(String userId) {
        return null;
    }

    @Override
    public List<Menu> getUserAuthRootMenus(String userId) {
        return null;
    }

    @Override
    public List<Menu> getUserAuthChildMenus(String userId, Long menuId) {
        return null;
    }

    @Override
    public List<Button> getUserAuthButtons(String userId, Long menuId) {
        return null;
    }

    @Override
    public List<String> getUserAuthCodes(String userId) {
        return null;
    }

    @Override
    public boolean validateUserAuth(String userId, String authCode) {
        return false;
    }
}