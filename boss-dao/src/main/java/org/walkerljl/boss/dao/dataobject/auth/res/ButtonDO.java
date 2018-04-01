/*
 * Copyright (c) 2010-2015 www.walkerljl.org All Rights Reserved.
 * The software source code all copyright belongs to the author, 
 * without permission shall not be any reproduction and transmission.
 */
package org.walkerljl.boss.dao.dataobject.auth.res;

/**
 * 功能按钮
 *
 * @author lijunlin
 */
public class ButtonDO extends BaseResDO {

    private static final long serialVersionUID = 1L;

    /**
     * 所属菜单Id
     */
    private Long menuId;
    /**
     * URL
     */
    private String url;

    public ButtonDO() {}

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}