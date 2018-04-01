package org.walkerljl.boss.web.sso.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.walkerljl.boss.dao.dataobject.sso.UserDO;
import org.walkerljl.boss.sdk.auth.annotation.Authentication;
import org.walkerljl.boss.sdk.auth.enums.AuthType;
import org.walkerljl.boss.service.sso.UserService;
import org.walkerljl.boss.support.mvc.controller.template.JqueryDatatableCurdTemplate;
import org.walkerljl.boss.support.mvc.model.ObjectIdentifier;
import org.walkerljl.boss.support.service.JqueryDatatableBaseService;

/**
 * UserController
 *
 * @author lijunlin
 */
@Controller
@Authentication(type = AuthType.CODE)
@RequestMapping(value = "/sso/user", method = {RequestMethod.POST, RequestMethod.GET})
public class UserController extends JqueryDatatableCurdTemplate<UserDO> {

    @Resource
    private UserService userService;

    public UserController() {
        ObjectIdentifier objectIdentifier = new ObjectIdentifier("用户信息", "/sso/user");
        objectIdentifier.setButtonActives(new int[]{0, 0, 1, 1, 1, 1, 1});//禁用按钮
        //TODO
        //objectIdentifier.setParentMenus(new Menu[]{new Menu("单点登录", null)});
        setObjectIdentifier(objectIdentifier);
    }

    @Override
    public JqueryDatatableBaseService<Long, UserDO> getJqueryDatatableBaseService() {
        return userService;
    }
}