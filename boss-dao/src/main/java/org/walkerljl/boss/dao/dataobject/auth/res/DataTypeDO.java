/*
 * Copyright (c) 2010-2015 www.walkerljl.org All Rights Reserved.
 * The software source code all copyright belongs to the author, 
 * without permission shall not be any reproduction and transmission.
 */
package org.walkerljl.boss.dao.dataobject.auth.res;

/**
 * 数据权限分类
 *
 * @author lijunlin
 */
public class DataTypeDO extends BaseResDO {

    private static final long serialVersionUID = 1L;

    /**
     * 数据权限Key
     */
    private String key;

    public DataTypeDO() {}

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}