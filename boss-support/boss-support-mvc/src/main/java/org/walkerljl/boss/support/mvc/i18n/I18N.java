package org.walkerljl.boss.support.mvc.i18n;

import org.walkerljl.boss.support.service.ApplicationContextUtils;

/**
 * I18N
 *
 * @author xingxun
 */
public class I18N {

    private static MessageResourceService messageResourceService;

    public I18N() {}

    /**
     * Get resource message
     *
     * @param key
     * @param args
     * @return
     */
    public static String get(String key, Object... args) {
        MessageResourceService messageResourceService = ApplicationContextUtils.getBean(MessageResourceService.class);
        return messageResourceService.get(key, args);
    }

    /**
     * Get resource message
     *
     * @param key
     * @param args
     * @return
     */
    public static String getMessage(String key, Object... args) {
        return get(key, args);
    }
}
