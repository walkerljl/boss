package org.walkerljl.boss.web.sso.controller;

import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.walkerljl.boss.client.UserAuthenticationTicketFactory;
import org.walkerljl.boss.dao.dataobject.sso.UserDO;
import org.walkerljl.boss.model.sso.LoginCommand;
import org.walkerljl.boss.model.enums.sso.AgentType;
import org.walkerljl.boss.service.sso.UserService;
import org.walkerljl.boss.support.ViewResult;
import org.walkerljl.boss.support.mvc.controller.BaseController;
import org.walkerljl.boss.support.mvc.model.ObjectIdentifier;
import org.walkerljl.boss.support.mvc.model.context.ServletContext;
import org.walkerljl.boss.support.mvc.util.CookieUtils;
import org.walkerljl.boss.support.sdk.Message;
import org.walkerljl.toolkit.lang.StringUtils;

/**
 * SsoController
 *
 * @author lijunlin
 */
@Controller
@RequestMapping(value = "/sso", method = {RequestMethod.POST, RequestMethod.GET})
public class SsoController extends BaseController {

    @Value(value = "${sso.auth.cookie.domain}")
    private String ssoAuthCookieDomain;
    @Value(value = "${sso.auth.cookie.name}")
    private String ssoAuthCookieName;
    @Value(value = "${sso.auth.cookie.key}")
    private String ssoAuthCookieKey;

    @Resource
    private UserService userService;

    public SsoController() {
        ObjectIdentifier objectIdentifier = new ObjectIdentifier("单点登录", "/sso");
        objectIdentifier.setLayout("/sso/default");
        setObjectIdentifier(objectIdentifier);
    }

    /**
     * 登录
     *
     * @param command
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/login")
    public ModelAndView login(LoginCommand command) throws IOException {
        ViewResult viewResult = new ViewResult();
        String operate = ServletContext.getRequest().getParameter("operate");
        String returnAddress = ServletContext.getRequest().getParameter("returnAddress");
        if (StringUtils.equals(operate, "signin")) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Current sign in user id: " + command.getUserId());
            }
            //填充command字段值
            command.setLoginAgent(AgentType.PC);
            command.setLoginIp(CookieUtils.getIpAddr(ServletContext.getRequest()));
            Message<Object> message = userService.login(command);
            if (message.isSuccess()) {
                if (LOG.isDebugEnabled()) {
                    LOG.debug("sendRedirect: " + returnAddress);
                }
                new UserAuthenticationTicketFactory(ssoAuthCookieDomain, ssoAuthCookieName, ssoAuthCookieKey)
                        .generateTicket(ServletContext.getResponse(), command.getUserId(), command.getUserId());
                if (StringUtils.isBlank(returnAddress)) {
                    returnAddress = ServletContext.getRequest().getContextPath();
                }
                ServletContext.getResponse().sendRedirect(returnAddress);
            } else {
                message.setBody("登录失败");
                viewResult.addModel("message", message.getBody());
            }
        }
        viewResult.addModel("returnAddress", returnAddress);
        return toViewResult(getTemplate("login"), viewResult);
    }

    /**
     * 注销
     */
    @RequestMapping(value = "/logout")
    public void logout() {
        new UserAuthenticationTicketFactory(ssoAuthCookieDomain, ssoAuthCookieName, ssoAuthCookieKey).deleteTicket(ServletContext.getResponse());
        sendRedirect(ServletContext.getRequest().getContextPath());
    }

    /**
     * 注册
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/register")
    public ModelAndView register(UserDO user) {
        ViewResult viewResult = new ViewResult();
        user.setAlias("");
        if (user.getUserId() != null) {
            //initBaseDomainWhenAdd(user);
            user.setCreator(user.getUserId());
            user.setModifier(user.getCreator());
            user.setLastLoginTime(user.getCreatedTime());
            user.setLastLoginIp(CookieUtils.getIpAddr(ServletContext.getRequest()));
            user.setLastLoginAgent(AgentType.PC.getValue());
            if (user.isConfirmEmail()) {//验证邮箱
                //user.setStatusType(Status.DISABLED);
            }
            viewResult.addModel("message", userService.register(user));
        }
        return toViewResult(getTemplate("register"), viewResult);
    }
}