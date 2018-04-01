/*
 * Copyright (c) 2010-2015 lijunlin All Rights Reserved.
 * The software source code all copyright belongs to the author, 
 * without permission shall not be any reproduction and transmission.
 */
package org.walkerljl.boss.dao.dataobject.auth.res;

import org.walkerljl.boss.support.dao.dataobject.BaseDO;
import org.walkerljl.toolkit.db.api.annotation.Column;

/**
 * 基础资源实体
 *
 * @author lijunlin
 */
public class BaseResDO extends BaseDO {

    private static final long serialVersionUID = 1L;

    /**
     * 应用Id
     */
    @Column("app_id")
    private Long appId;
    /**
     * 资源名称
     */
    @Column("name")
    private String name;
    /**
     * 资源码Id
     */
    @Column("res_code_id")
    private Long resCodeId;


    //======扩展属性
    /**
     * 资源码编码
     */
    private String resCodeStr;

    /**
     * 默认构造函数
     */
    public BaseResDO() {}

    /**
     * 获取应用Id
     *
     * @return
     */
    public Long getAppId() {
        return appId;
    }

    /**
     * 设置应用Id
     *
     * @param appId
     */
    public void setAppId(Long appId) {
        this.appId = appId;
    }

    /**
     * 获取资源码Id
     *
     * @return
     */
    public Long getResCodeId() {
        return resCodeId;
    }

    /**
     * 设置资源码Id
     *
     * @param resCodeId
     */
    public void setResCodeId(Long resCodeId) {
        this.resCodeId = resCodeId;
    }

    /**
     * 获取资源名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置资源名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取资源码编码
     *
     * @return
     */
    public String getResCodeStr() {
        return resCodeStr;
    }

    /**
     * 设置资源码编码
     *
     * @param resCodeStr
     */
    public void setResCodeStr(String resCodeStr) {
        this.resCodeStr = resCodeStr;
    }
}