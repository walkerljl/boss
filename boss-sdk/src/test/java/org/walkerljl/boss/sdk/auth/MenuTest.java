package org.walkerljl.boss.sdk.auth;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.walkerljl.boss.sdk.auth.model.Button;
import org.walkerljl.boss.sdk.auth.model.Menu;
import org.walkerljl.boss.sdk.auth.util.AuthenticationUtil;

/**
 *
 * @author xingxun
 */
public class MenuTest {

    private Menu rootMenu = null;
    private Set<String> authCodes = new HashSet<String>();

    public void test() {
        buildTestData();

        List<Menu> authedMenus = AuthenticationUtil.filterAuthedMenus(rootMenu.getChildren(), authCodes);

    }

    private void buildTestData() {
        rootMenu = new Menu("10000000", "root", "统一身份", "-1");

        /*
        基础信息管理
         */
        Menu basicInfoMngMenu = new Menu("20000000", "basicInfoMng", "基础信息管理", rootMenu.getId());
        rootMenu.addChild(basicInfoMngMenu);
        //人员管理
        Menu personMngMenu = new Menu("20100000", "personMng", "人员管理", basicInfoMngMenu.getId());
        basicInfoMngMenu.addChild(personMngMenu);
        addButton(personMngMenu, "add");
        addButton(personMngMenu, "remove");
        addButton(personMngMenu, "edit");
        addButton(personMngMenu, "get");

        //账号管理
        Menu accountMngMenu = new Menu("20200000", "accountMng", "账号管理", basicInfoMngMenu.getId());
        basicInfoMngMenu.addChild(accountMngMenu);
        addButton(accountMngMenu, "add");
        addButton(accountMngMenu, "remove");

        //机构管理
        Menu orgMngMenu = new Menu("20300000", "orgMng", "机构管理", basicInfoMngMenu.getId());
        basicInfoMngMenu.addChild(orgMngMenu);
        addButton(orgMngMenu, "add");
        addButton(orgMngMenu, "remove");

        /*
        权限管理
         */
        Menu authMngMenu = new Menu("30000000", "authMng", "权限管理", rootMenu.getId());
        rootMenu.addChild(authMngMenu);
        //角色管理
        Menu roleMngMenu = new Menu("30100000", "roleMng", "角色管理", authMngMenu.getId());
        authMngMenu.addChild(roleMngMenu);
        addButton(roleMngMenu, "add");
        addButton(roleMngMenu, "remove");
        //访问授权管理
        authMngMenu.addChild(new Menu("30200000", "accessAuthMng", "访问授权管理", authMngMenu.getId()));
        //访问授权统计
        authMngMenu.addChild(new Menu("30300000", "accessAuthStaMng", "访问授权统计", authMngMenu.getId()));

        /*
        系统设置
         */
        Menu systemSettingMenu = new Menu("40000000", "systemSetting", "系统设置", rootMenu.getId());
        rootMenu.addChild(systemSettingMenu);
        //基础设置
        Menu baseSettingMenu = new Menu("40100000", "baseSetting", "基础设置", systemSettingMenu.getId());
        systemSettingMenu.addChild(baseSettingMenu);
        addButton(baseSettingMenu, "add");
        addButton(baseSettingMenu, "remove");
        //消息设置
        Menu msgSetting = new Menu("40200000", "msgSetting", "消息设置", systemSettingMenu.getId());
        systemSettingMenu.addChild(msgSetting);
        addButton(msgSetting, "remove");

        /////
        authCodes.add(personMngMenu.getCode()+"get");
        authCodes.add(roleMngMenu.getCode()+"add");
        authCodes.add(roleMngMenu.getCode()+"remove");
    }

    private void addButton(Menu menu, String buttonCode) {
        menu.addButton(new Button(menu.getCode() + buttonCode));
    }
}