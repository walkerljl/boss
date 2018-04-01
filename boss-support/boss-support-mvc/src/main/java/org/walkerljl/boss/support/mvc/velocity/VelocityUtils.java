/*
 * Copyright (c) 2010-2015 lijunlin All Rights Reserved.
 * The software source code all copyright belongs to the author, 
 * without permission shall not be any reproduction and transmission.
 */
package org.walkerljl.boss.support.mvc.velocity;

import org.walkerljl.toolkit.lang.datetime.DateUtils;

import java.util.Date;

/**
 * VelocityUtils
 *
 * @author xingxun
 */
public class VelocityUtils {

    /**
     * 日期转换
     *
     * @param date
     * @return
     */
    public static String dateFormatDateTime(Date date) {
        return DateUtils.dateFormatDateTime(date);
    }
}