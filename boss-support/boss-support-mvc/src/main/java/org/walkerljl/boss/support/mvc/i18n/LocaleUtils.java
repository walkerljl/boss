package org.walkerljl.boss.support.mvc.i18n;

import java.util.Locale;

/**
 * LocaleUtils
 *
 * @author xingxun
 */
public class LocaleUtils {

    private static ThreadLocal<Locale> userLocale = new ThreadLocal<Locale>();

    public static void cleanLocale() {
        userLocale.remove();
    }

    public static Locale getLocale() {
        return userLocale.get();
    }

    public static void setLocale(Locale locale) {
        userLocale.set(locale);
    }
}
