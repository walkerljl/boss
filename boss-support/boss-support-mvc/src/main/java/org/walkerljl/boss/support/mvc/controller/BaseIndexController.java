package org.walkerljl.boss.support.mvc.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.walkerljl.boss.sdk.auth.annotation.Authentication;
import org.walkerljl.boss.support.mvc.MvcSupporter;
import org.walkerljl.boss.support.mvc.WebConstants;
import org.walkerljl.boss.support.mvc.model.ObjectIdentifier;
import org.walkerljl.boss.support.mvc.model.context.ServletContext;
import org.walkerljl.configuration.client.ConfiguratorFactory;
import org.walkerljl.toolkit.lang.MapUtils;

/**
 * BaseIndexController
 *
 * @author xingxun
 */
public abstract class BaseIndexController extends BaseController {

    /**
     * 管理员联系方式数据Key
     */
    private static final String ADMINISTRATOR_CONTACTS_MODEL_KEY = "administrator.contacts";

    @Resource
    private MvcSupporter mvcSupporter;

    /**
     * 构造函数,初始化
     */
    public BaseIndexController() {
        ObjectIdentifier objectIdentifier = new ObjectIdentifier("", "common", "/common");
        setObjectIdentifier(objectIdentifier);
    }

    /**
     * 首页
     *
     * @return
     */
    @Authentication
    @RequestMapping
    public ModelAndView index() {
        return toViewResult(getTemplate("/index", "index"));
    }

    /**
     * 登出
     *
     * @return
     */
    @RequestMapping(value = "/logout")
    public void logout() {
        mvcSupporter.logoutAndRedirectToLogin();
    }

    /**
     * 系统升级提示页面
     *
     * @return
     */
    @RequestMapping(value = "/upgrading")
    public ModelAndView upgrading() {
        //隐藏菜单条
        getObjectIdentifier().setLoadMenuBar(false);
        boolean upgrading = ConfiguratorFactory.getStdConfigurator().getAsBoolean("system.upgrading", false);
        if (ServletContext.getRequestURI().endsWith(WebConstants.JSON_SUFFIX)) {
            if (!upgrading) {
                getObjectIdentifier().setLoadMenuBar(true);
            }
            return toJSON(null, true, (upgrading ? "系统正在升级" : "系统升级完成,欢迎继续使用"));
        }
        if (!upgrading) {
            getObjectIdentifier().setLoadMenuBar(true);
            sendRedirect(ServletContext.getContextPath() + "/");
            return null;
        }
        Map<String, Object> model = MapUtils.newHashMap();
        model.put(ADMINISTRATOR_CONTACTS_MODEL_KEY, getAdministratorContacts());
        return toViewResult(getTemplate("upgrading"), model);
    }

    /**
     * 访问拒绝页面
     *
     * @return
     */
    @RequestMapping(value = "/accessDenied")
    public ModelAndView accessDenied() {
        Map<String, Object> model = MapUtils.newHashMap();
        model.put(ADMINISTRATOR_CONTACTS_MODEL_KEY, getAdministratorContacts());
        return toViewResult(getTemplate("accessDenied"), model);
    }

    /**
     * 系统错误反馈页面
     *
     * @return
     */
    @RequestMapping(value = "/error")
    public ModelAndView error() {
        Map<String, Object> model = MapUtils.newHashMap();
        model.put(ADMINISTRATOR_CONTACTS_MODEL_KEY, getAdministratorContacts());
        return toViewResult(getTemplate("error"), model);
    }

    /**
     * 获取系统管理员联系方式
     *
     * @return
     */
    private String getAdministratorContacts() {
        return ConfiguratorFactory.getStdConfigurator().getAsString(ADMINISTRATOR_CONTACTS_MODEL_KEY);
    }
}