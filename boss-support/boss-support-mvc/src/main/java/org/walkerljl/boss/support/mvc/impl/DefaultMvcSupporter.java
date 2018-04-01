/*
 * Copyright (c) 2010-2015 www.walkerljl.org All Rights Reserved.
 * The software source code all copyright belongs to the author, 
 * without permission shall not be any reproduction and transmission.
 */
package org.walkerljl.boss.support.mvc.impl;

import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.walkerljl.boss.client.UserAuthenticationTicketFactory;
import org.walkerljl.boss.sdk.auth.model.Menu;
import org.walkerljl.boss.sdk.auth.model.tree.ManyNodeTree;
import org.walkerljl.boss.sdk.auth.model.tree.ManyTreeNode;
import org.walkerljl.boss.sdk.auth.model.tree.TreeNode;
import org.walkerljl.boss.sdk.auth.model.tree.TreeNodeAscOrderComparator;
import org.walkerljl.boss.sdk.auth.support.AuthenticationProvider;
import org.walkerljl.boss.sdk.sso.model.UserAuthenticationTicket;
import org.walkerljl.boss.support.UserInfo;
import org.walkerljl.boss.support.mvc.MvcSupporter;
import org.walkerljl.boss.support.mvc.WebConstants;
import org.walkerljl.boss.support.mvc.model.context.ServletContext;
import org.walkerljl.boss.support.mvc.util.MvcUtils;
import org.walkerljl.configuration.client.ConfiguratorFactory;
import org.walkerljl.toolkit.lang.ListUtils;
import org.walkerljl.toolkit.lang.StringUtils;
import org.walkerljl.toolkit.standard.exception.AppException;

/**
 * DefaultMvcSupporter
 *
 * @author xingxun
 */
public class DefaultMvcSupporter implements MvcSupporter {

    private static final String USER_KEY = "currentUser";

    /**
     * 默认菜单构建时间限制
     */
    private static final long DEFAULT_MENU_BUILD_TIME_LIMIT = 1 * 1000;
    @Value(value = "${sso.auth.cookie.domain}")
    private String                 ssoAuthCookieDomain;
    @Value(value = "${sso.auth.cookie.name}")
    private String                 ssoAuthCookieName;
    @Value(value = "${sso.auth.cookie.key}")
    private String                 ssoAuthCookieKey;
    @Value(value = "${sso.auth.address}")
    private String                 ssoAuthAddress;
    @Value(value = "${sso.return.address}")
    private String                 ssoReturnAddress;
    @Resource
    private AuthenticationProvider authenticationProvider;

    @Override
    public boolean isLogin() {
        UserInfo user = getCurrentUser();
        return user != null && StringUtils.isNotBlank(user.getId());
    }

    @Override
    public void login() {
        String encodedReturnAddress = null;
        try {
            encodedReturnAddress = URLEncoder.encode(ssoReturnAddress, WebConstants.DEFAULT_CHARSET);
        } catch (Exception e) {
            throw new AppException(e);
        }
        MvcUtils.sendRedirect(ssoAuthAddress + "?returnAddress=" + encodedReturnAddress);
    }

    @Override
    public String getSsoLoginAddress() {
        return ssoAuthAddress;
    }

    @Override
    public void logoutAndRedirectToLogin() {
        logout();
        login();
    }

    @Override
    public void logout() {
        new UserAuthenticationTicketFactory(ssoAuthCookieDomain, ssoAuthCookieName, ssoAuthCookieKey)
                .deleteTicket(ServletContext.getResponse());
    }

    @Override
    public UserInfo getCurrentUser() {
        UserInfo user = (UserInfo) ServletContext.getSessionAttribute(USER_KEY);
        if (user == null) {
            //Parse user info from cookie
            UserAuthenticationTicket ticket =
                    new UserAuthenticationTicketFactory(ssoAuthCookieDomain, ssoAuthCookieName, ssoAuthCookieKey)
                            .getTicket(ServletContext.getRequest());
            if (ticket != null && ticket.isLogin()) {
                user = new UserInfo();
                user.setId(ticket.getUserId());
                user.setName(ticket.getUserName());
                ServletContext.setSessionAttribute(USER_KEY, user);
            }
        }
        return user;
    }

    @Override
    public String getCurrentUserId() {
        UserInfo user = getCurrentUser();
        return user == null ? null : user.getId();
    }

    @Override
    public boolean currentUserIsAdmin() {
        String userId = getCurrentUserId();
        if (StringUtils.isEmpty(userId)) {
            return false;
        }
        Set<String> administrators = ConfiguratorFactory.getStdConfigurator().getAsSet(String.class, "administrators", ",");
        return administrators != null && administrators.contains(userId);
    }

    @Override
    public boolean validateUserAuth(String authCode) {
        return authenticationProvider.validateUserAuth(getCurrentUserId(), authCode);
    }

    @Override
    public Map<String, Object> getBussinessContext() {
        return null;
    }

    @Override
    public String getMenuBar(String userId, String contextPath, String uri) {
        ManyNodeTree manyNodeTree = createMenuTree(authenticationProvider.getUserAuthMenus(userId));
        if (manyNodeTree == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder("<ul class=\"nav nav-list\">");
        List<ManyTreeNode> manyTreeNodes = manyNodeTree.getRootNode().getChildren();
        for (ManyTreeNode manyTreeNode : manyTreeNodes) {
            TreeNode treeNode = manyTreeNode.getNode();
            List<ManyTreeNode> children = manyTreeNode.getChildren();
            boolean isActiveParent = isActiveParent(uri, children);
            if (children.size() > 0) {
                sb.append("<li" + (isActiveParent ? " class=\"open active\"" : "") + ">");
                sb.append("<a href=\"#\" class=\"dropdown-toggle\"><i class=\"" + treeNode.getIcon() + "\"></i><span class=\"menu-text\">")
                        .append(treeNode.getName()).append("</span><b class=\"arrow icon-angle-down\"></b></a>");
                sb.append("<ul class=\"submenu\"").append((isActiveParent ? " style=\"display:block;\"" : "")).append(">");
                for (ManyTreeNode subManyTreeNode : manyTreeNode.getChildren()) {
                    String subUrl = subManyTreeNode.getNode().getUrl();
                    boolean isActive = StringUtils.equals(subUrl, uri);
                    sb.append("<li " + (isActive ? "class=\"active\"" : "") +
                            "><a href=\"").append(StringUtils.isBlank(subUrl) ? '#' : contextPath + subUrl).append("\"><i class=\"icon-double-angle-right\"></i>");
                    sb.append(subManyTreeNode.getNode().getName()).append("</a></li>");
                }
                sb.append("</ul>");
            } else {
                sb.append("<li " + (uri.equals(treeNode.getUrl()) ? " class=\"active\"" : "") + ">");
                sb.append("<a href=\"" + contextPath + treeNode.getUrl() + "\"><i class=\"" + treeNode.getIcon() + "\"></i><span class=\"menu-text\">"
                        + treeNode.getName() + "</span></a>");
                sb.append("</li>");
            }
            sb.append("</li>");
        }
        sb.append("</ul>");
        return sb.toString();
    }

    /**
     * 创建菜单树
     *
     * @param menus
     * @return
     */
    private ManyNodeTree createMenuTree(List<Menu> menus) {
        if (ListUtils.isEmpty(menus)) {
            return null;
        }
        TreeSet<TreeNode> treeNodes = new TreeSet<TreeNode>(new TreeNodeAscOrderComparator());
        for (Menu menu : menus) {
            TreeNode treeNode = new TreeNode();
            treeNode.setId(menu.getId());
            treeNode.setName(menu.getName());
            treeNode.setParentId(menu.getParentId());
            treeNode.setUrl(menu.getUrl());
            treeNode.setIcon(menu.getIcon());
            treeNode.setSortSequence(menu.getSortSequence());
            treeNodes.add(treeNode);
        }
        ManyNodeTree manyNodeTree = ManyNodeTree.createTree("0", "root", treeNodes, DEFAULT_MENU_BUILD_TIME_LIMIT);
        return manyNodeTree;
    }

    /**
     * 是否激活父亲
     *
     * @param uri
     * @param manyTreeNodes
     * @return
     */
    private boolean isActiveParent(String uri, List<ManyTreeNode> manyTreeNodes) {
        boolean flag = false;
        for (ManyTreeNode manyTreeNode : manyTreeNodes) {
            if (StringUtils.equals(manyTreeNode.getNode().getUrl(), uri)) {
                flag = true;
                break;
            }
        }
        return flag;
    }
}