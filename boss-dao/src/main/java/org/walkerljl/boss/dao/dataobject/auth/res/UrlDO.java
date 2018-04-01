/*
 * Copyright (c) 2010-2015 www.walkerljl.org All Rights Reserved.
 * The software source code all copyright belongs to the author, 
 * without permission shall not be any reproduction and transmission.
 */
package org.walkerljl.boss.dao.dataobject.auth.res;

/**
 * Url
 *
 * @author lijunlin
 */
public class UrlDO extends BaseResDO {

    private static final long serialVersionUID = 1L;

    /**
     * 地址
     */
    private String address;

    public UrlDO() {}

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}