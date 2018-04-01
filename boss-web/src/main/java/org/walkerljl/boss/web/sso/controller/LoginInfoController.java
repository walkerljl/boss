package org.walkerljl.boss.web.sso.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.walkerljl.boss.dao.dataobject.sso.LoginInfoDO;
import org.walkerljl.boss.sdk.auth.annotation.Authentication;
import org.walkerljl.boss.sdk.auth.enums.AuthType;
import org.walkerljl.boss.service.sso.LoginInfoService;
import org.walkerljl.boss.support.mvc.controller.template.JqueryDatatableCurdTemplate;
import org.walkerljl.boss.support.mvc.model.ObjectIdentifier;
import org.walkerljl.boss.support.service.JqueryDatatableBaseService;

/**
 * LoginInfoController
 *
 * @author lijunlin
 */
@Controller
@Authentication(type = AuthType.CODE)
@RequestMapping(value = "/sso/logininfo", method = {RequestMethod.POST, RequestMethod.GET})
public class LoginInfoController extends JqueryDatatableCurdTemplate<LoginInfoDO> {

    @Resource
    private LoginInfoService loginInfoService;

    public LoginInfoController() {
        ObjectIdentifier objectIdentifier = new ObjectIdentifier("登录信息管理", "/sso/logininfo");
        //TODO
        //objectIdentifier.setParentMenus(new Menu[]{new Menu("单点登录", null)});
        objectIdentifier.setButtonActives(new int[]{0, 0, 0, 1, 0, 0, 0});//禁用按钮
        setObjectIdentifier(objectIdentifier);
    }

    @Override
    public JqueryDatatableBaseService<Long, LoginInfoDO> getJqueryDatatableBaseService() {
        return loginInfoService;
    }
}