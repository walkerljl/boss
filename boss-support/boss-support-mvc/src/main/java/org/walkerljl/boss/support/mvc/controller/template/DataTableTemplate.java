/*
 * Copyright (c) 2010-2015 lijunlin All Rights Reserved.
 * The software source code all copyright belongs to the author, 
 * without permission shall not be any reproduction and transmission.
 */
package org.walkerljl.boss.support.mvc.controller.template;

import java.util.HashMap;
import java.util.Map;

/**
 * 基于Datatable表格插件的Template
 *
 * @author xingxun
 */
public abstract class DataTableTemplate<T> extends CurdTemplate<T> {

    /**
     * Datatable 对象标识符
     */
    private static final String DATATABLE_IDENTIFIER_KEY = "datatableIdentifier";
    /**
     * Datatable checkbox标识符
     */
    private static final String CHECKBOX_IDENTIFIER_KEY = "checkboxIdentifier";
    /**
     * Datatable checkbox item标识符
     */
    private static final String CHECKBOX_ITEM_IDENTIFIER_KEY = "checkboxItemIdentifier";
    /**
     * Datatable 对象标识符 后缀
     */
    private static final String DATATABLE_IDENTIFIER_SUFFIX = "_table";
    /**
     * Datatable checkbox标识符后缀
     */
    private static final String CHECKBOX_IDENTIFIER_SUFFIX = "_chk";
    /**
     * Datatable checkbox item标识符后缀
     */
    private static final String CHECKBOX_ITEM_IDENTIFIER_SUFFIX = "_chk_item";

    @Override
    public Map<String, Object> getDefaultContext() {
        Map<String, Object> context = super.getDefaultContext();
        if (context == null) {
            context = new HashMap<>(3);
        }
        context.put(DATATABLE_IDENTIFIER_KEY, getDatatableIdentifier());
        context.put(CHECKBOX_IDENTIFIER_KEY, getCheckboxIdentifier());
        context.put(CHECKBOX_ITEM_IDENTIFIER_KEY, getCheckboxItemIdentifier());
        return context;
    }

    /**
     * 获取DataTable标识符
     *
     * @return
     */
    private String getDatatableIdentifier() {
        return getObjectIdentifier().getCode() + DATATABLE_IDENTIFIER_SUFFIX;
    }

    /**
     * 获取Datatable checkbox 标识符
     *
     * @return
     */
    private String getCheckboxIdentifier() {
        return getObjectIdentifier().getCode() + CHECKBOX_IDENTIFIER_SUFFIX;
    }

    /**
     * 获取Datatable checkbox item 标识符
     *
     * @return
     */
    private String getCheckboxItemIdentifier() {
        return getObjectIdentifier().getCode() + CHECKBOX_ITEM_IDENTIFIER_SUFFIX;
    }
}