package org.walkerljl.boss.sdk.auth.util;

/**
 * ArrayUtil
 *
 * @author xingxun
 */
public class ArrayUtil {

    /**
     * 是否为空
     *
     * @param array
     * @param <E>
     * @return
     */
    public static <E> boolean isEmpty(E[] array) {
        return array == null || array.length == 0;
    }

    /**
     * ToString
     *
     * @param array
     * @param separator
     * @param <E>
     * @return
     */
    public static <E> String toString(E[] array, String separator) {
        if (isEmpty(array)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(array[0]);

        for (int index = 1; index < array.length; index++) {
            sb.append(separator).append(array[index]);
        }

        return sb.toString();
    }
}