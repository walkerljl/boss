package org.walkerljl.boss.sdk.auth.support;

import java.util.List;

import org.walkerljl.boss.sdk.auth.model.Button;
import org.walkerljl.boss.sdk.auth.model.Menu;

/**
 * 权限提供者
 *
 * @author xingxun
 */
public interface AuthenticationProvider {

    /**
     * 获取用户授权的所有菜单
     *
     * @param userId 用户ID
     * @return
     */
    List<Menu> getUserAuthMenus(String userId);

    /**
     * 获取用户授权的一级菜单
     *
     * @param userId 用户ID
     * @return
     */
    List<Menu> getUserAuthRootMenus(String userId);

    /**
     * 用户授权的下级菜单
     *
     * @param userId 用户ID
     * @param menuId
     * @return
     */
    List<Menu> getUserAuthChildMenus(String userId, Long menuId);

    /**
     * 获取用户授权的功能按钮
     *
     * @param userId 用户ID
     * @param menuId
     * @return
     */
    List<Button> getUserAuthButtons(String userId, Long menuId);

    /**
     * 获取用户授权码
     *
     * @param userId 用户ID
     * @return
     */
    List<String> getUserAuthCodes(String userId);

    /**
     * 校验用户是否有此权限码的权限
     *
     * @param userId   用户ID
     * @param authCode 权限码
     * @return
     */
    boolean validateUserAuth(String userId, String authCode);
}