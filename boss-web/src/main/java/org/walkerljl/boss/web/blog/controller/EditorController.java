package org.walkerljl.boss.web.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.walkerljl.boss.support.mvc.controller.BaseController;
import org.walkerljl.boss.sdk.auth.annotation.Authentication;
import org.walkerljl.boss.support.mvc.model.ObjectIdentifier;

/**
 * EditorController
 *
 * @author lijunlin
 */
@Controller
@Authentication
@RequestMapping(value = "/blog/editor", method = {RequestMethod.POST, RequestMethod.GET})
public class EditorController extends BaseController {

    public EditorController() {
        setObjectIdentifier(new ObjectIdentifier("editor", ""));
    }

    @RequestMapping
    public ModelAndView index() {
        return toViewResult(getTemplate("index"));
    }
}
