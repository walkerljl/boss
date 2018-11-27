package org.walkerljl.boss.common.util;

import java.util.Collection;

/**
 * CollectionUtil
 *
 * @author xingxun
 */
public class CollectionUtil extends org.walkerljl.boss.support.common.util.CollectionUtil {

    public static <T> boolean isEmpty(Collection<T> collection) {
        return collection == null ? true : collection.isEmpty();
    }

    public static <T> boolean isNotEmpty(Collection<T> collection) {
        return (collection != null && !collection.isEmpty());
    }

    public static <T> int size(Collection<T> collection) {
        return collection == null ? 0 : collection.size();
    }

    public static <T> void addAll(Collection<T> dest, Collection<T> src) {
        if (dest == null) {
            return;
        }
        if (src == null) {
            return;
        }
        dest.addAll(src);
    }
}