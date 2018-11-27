package org.walkerljl.boss.common.util;

/**
 * StringUtil
 *
 * @author xingxun
 */
public class StringUtil {

    public static boolean isEmpty(String string) {
        return string == null || string.trim().equals("");
    }

    public static boolean isNotEmpty(String string) {
        return string != null && !string.trim().equals("");
    }

    public static String[] split(String string, String separator) {
        return string == null ? null : string.split(separator);
    }
}