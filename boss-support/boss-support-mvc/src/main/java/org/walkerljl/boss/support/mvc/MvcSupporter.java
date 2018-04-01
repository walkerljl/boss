/*
 * Copyright (c) 2010-2015 lijunlin All Rights Reserved.
 * The software source code all copyright belongs to the author, 
 * without permission shall not be any reproduction and transmission.
 */
package org.walkerljl.boss.support.mvc;

import java.util.Map;

import org.walkerljl.boss.support.UserInfo;

/**
 * MvcSupporter
 *
 * @author xingxun
 */
public interface MvcSupporter {

    /**
     * 是否已经登录
     *
     * @return
     */
    boolean isLogin();

    /**
     * 登录
     */
    void login();

    /**
     * 获取单点登录地址
     *
     * @return
     */
    String getSsoLoginAddress();

    /**
     * 注销
     */
    void logoutAndRedirectToLogin();

    /**
     * 注销
     */
    void logout();

    /**
     * 获取当前登录用户信息
     *
     * @return
     */
    UserInfo getCurrentUser();

    /**
     * 获取当前登录用户Id
     *
     * @return
     */
    String getCurrentUserId();

    /**
     * 当前用户是否超级管理员
     *
     * @return
     */
    boolean currentUserIsAdmin();

    /**
     * 校验用户权限
     *
     * @param authCode 权限码
     * @return
     */
    boolean validateUserAuth(String authCode);

    /**
     * 获取自定义Context
     *
     * @return
     */
    Map<String, Object> getBussinessContext();

    /**
     * 获取菜单条
     *
     * @param userId
     * @param contextPath
     * @param uri
     * @return
     */
    String getMenuBar(String userId, String contextPath, String uri);
}