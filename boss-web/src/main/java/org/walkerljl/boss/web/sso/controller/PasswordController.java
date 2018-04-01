package org.walkerljl.boss.web.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.walkerljl.boss.support.mvc.controller.BaseController;
import org.walkerljl.boss.sdk.auth.annotation.Authentication;
import org.walkerljl.boss.sdk.auth.enums.AuthType;
import org.walkerljl.boss.support.mvc.model.ObjectIdentifier;

/**
 * PasswordController
 *
 * @author lijunlin
 */
@Controller
@Authentication(type = AuthType.CODE)
@RequestMapping(value = "/sso/password", method = {RequestMethod.POST, RequestMethod.GET})
public class PasswordController extends BaseController {

    public PasswordController() {
        setObjectIdentifier(new ObjectIdentifier("密码重置", "/sso/password"));
    }

    @RequestMapping(value = "/update")
    public ModelAndView updatePassword() {
        return toViewResult(getTemplate("update"));
    }

    @RequestMapping(value = "/reset")
    public ModelAndView reset() {
        return toViewResult(getTemplate("reset"));
    }
}