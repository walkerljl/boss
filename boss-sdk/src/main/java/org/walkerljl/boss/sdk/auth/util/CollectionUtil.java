package org.walkerljl.boss.sdk.auth.util;

import java.util.Collection;

/**
 * CollectionUtil
 *
 * @author xingxun
 */
public class CollectionUtil {

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

}