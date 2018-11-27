package org.walkerljl.boss.common.util;

/**
 * ArrayUtil
 *
 * @author xingxun
 */
public class ArrayUtil {

    public static <T> boolean isEmpty(T[] array) {
        return array == null || array.length == 0;
    }

    public static <T> boolean isNotEmpty(T[] array) {
        return array != null && array.length > 0;
    }

    public static <T> int length(T[] array) {
        return array == null ? 0 : array.length;
    }

}