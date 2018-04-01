package org.walkerljl.boss.support.sdk.i18n;

import java.util.Locale;

/**
 * Lang
 * @author lijunlin
 */
public enum Lang {

    zh_CN,
    en_US;

    public static final Lang DEFAULT = Lang.zh_CN;

    public Locale getLocale() {
        String[] a = this.name().split("_");
        return a.length == 1 ?
                new Locale(a[0]) :
                new Locale(a[0], a[1]);
    }

    /**
     * Get object
     * @param lang
     * @return
     */
    public static Lang get(String lang) {
        Lang[] langs = Lang.values();
        for (Lang l : langs) {
            if (l.name().equals(lang)) {
                return l;
            }
        }
        return null;
    }

}
