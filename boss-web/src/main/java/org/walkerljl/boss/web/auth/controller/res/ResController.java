package org.walkerljl.boss.web.auth.controller.res;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.walkerljl.boss.support.mvc.controller.BaseController;
import org.walkerljl.boss.sdk.auth.annotation.Authentication;
import org.walkerljl.boss.support.mvc.model.ObjectIdentifier;

/**
 * 权限资源管理
 *
 * @author lijunlin
 */
@Controller
@Authentication
@RequestMapping(value = "/identity/auth/res", method = {RequestMethod.POST, RequestMethod.GET})
public class ResController extends BaseController {

    public ResController() {
        setObjectIdentifier(new ObjectIdentifier("应用资源管理", "/identity/auth/res"));
    }

    @RequestMapping(value = "", method = {RequestMethod.GET})
    public ModelAndView index(Long appId) {
        return toViewResult(getTemplate("index"));
    }
}