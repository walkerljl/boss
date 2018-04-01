package org.walkerljl.boss.support.mvc.i18n;

/**
 * Message resource service
 *
 * @author xingxun
 */
public interface MessageResourceService {

    /**
     * Get resource message
     *
     * @param key
     * @param args
     * @return
     */
    String get(String key, Object... args);

    /**
     * Get resource message
     *
     * @param key
     * @param args
     * @return
     */
    String getMessage(String key, Object... args);
}
