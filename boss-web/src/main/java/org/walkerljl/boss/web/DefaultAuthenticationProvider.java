package org.walkerljl.boss.web;

import java.util.List;

import javax.annotation.Resource;

import org.walkerljl.boss.sdk.auth.model.Button;
import org.walkerljl.boss.sdk.auth.model.Menu;
import org.walkerljl.boss.sdk.auth.support.AuthenticationProvider;
import org.walkerljl.boss.service.auth.AuthorizationService;
import org.walkerljl.boss.service.auth.PostRoleMappService;
import org.walkerljl.boss.service.auth.PostService;
import org.walkerljl.boss.service.auth.RoleResMappService;
import org.walkerljl.boss.service.auth.res.MenuService;

/**
 * 默认的权限提供者
 *
 * @author lijunlin
 */
public class DefaultAuthenticationProvider implements AuthenticationProvider {

    /**
     * appId
     */
    private Long appId;
    /**
     * token
     */
    private String token;

    @Resource
    private MenuService menuService;
    @Resource
    private AuthorizationService authorizationService;
    @Resource
    private RoleResMappService roleResMappService;
    @Resource
    private PostService postService;
    @Resource
    private PostRoleMappService postRoleMappService;

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public List<Menu> getUserAuthMenus(String userId) {
        //if (StringUtils.isBlank(userId)) {
        //    return null;
        //}
        //Menu condition = new Menu();
        //List<Menu> authMenus = menuService.selectList(condition);
        //if (CollectionUtils.isEmpty(authMenus)) {
        //    return null;
        //}
        //List<Menu> menus = ListUtils.newArrayList();
        //for (Menu authMenu : authMenus) {
        //    menus.add(new Menu(authMenu.getId(), authMenu.getName(), authMenu.getParentId(),
        //            authMenu.getSortSequence(), authMenu.getUrl(), authMenu.getIcon(),
        //            authMenu.getCss(), authMenu.getAuthCode()));
        //}
        //return menus;
        return null;
    }

    @Override
    public List<Menu> getUserAuthRootMenus(String userId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Menu> getUserAuthChildMenus(String userId, Long menuId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Button> getUserAuthButtons(String userId, Long menuId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<String> getUserAuthCodes(String userId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean validateUserAuth(String userId, String authCode) {
        return true;
    }
}