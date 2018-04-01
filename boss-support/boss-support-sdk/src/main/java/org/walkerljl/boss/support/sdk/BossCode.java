package org.walkerljl.boss.support.sdk;

import org.walkerljl.boss.support.sdk.i18n.Lang;
import org.walkerljl.boss.support.sdk.i18n.MessageSource;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lijunlin
 */
public enum BossCode {

    FAILURE(0, "失败"),
    SUCCESS(1, "成功");

    static {
        MessageSource.load("i18n/boss_code");
    }

    private int code;
    private String message;
    private static Map<Integer, BossCode> instances;
    private static final String KEY_PREFIX = "boss.code.";

    private BossCode(int code, String message) {
        this.code = code;
        this.message = message;
        put();
    }

    private void put() {
        if (instances == null) {
            instances = new HashMap<Integer, BossCode>();
        }
        instances.put(code, this);
    }

    public static BossCode get(int code) {
        return instances.get(code);
    }

    public int getCode() {
        return code;
    }

    public String getMessage(Lang lang) {
        if (lang == null) {
            lang = Lang.zh_CN;
        }
        if (lang == Lang.zh_CN) {
            return message;
        }
        return MessageSource.getMessage(lang, getWrappedCode());
    }

    public String getMessage(Lang lang, String... params) {
        if (params == null || params.length == 0) {
            return getMessage(lang);
        }
        if (lang == null) {
            lang = Lang.zh_CN;
        }
        if (lang == Lang.zh_CN) {
            return String.format(message, new Object[]{params});
        }
        return String.format(MessageSource.getMessage(lang, getWrappedCode()), new Object[]{params});
    }

    private String getWrappedCode() {
        return KEY_PREFIX + this.code;
    }
}
