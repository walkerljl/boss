package org.walkerljl.boss.support.sdk.i18n;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * <pre>
 *
 * 资源文件工具，会加载以下文件：
 *  1、classpath根路径下的语言包
 *  2、项目classpath根路径下的：i18n.properties 文件所配置语言包，
 *    配置示例：xxx.config=lang/web，最后一个'/'后的单词（web）为资源文件的前缀，以行分隔不同的配置文件
 *
 *  此外，您还可以使用 {@link #load(String)} 方法加载自己的语言包
 *
 *  </pre>
 *
 * @author lijunlin
 */
public final class MessageSource {
    private static Map<Lang, Properties> propertiesMap = new HashMap<Lang, Properties>();
    private static final String SUFFIX = ".properties";
    private static final String SEPARATOR = "_";
    private static final String FILE_SEPARATOR = "/";
    private static final String CONFIG = "i18n.properties";

    static {
        loadConfig();
    }

    /**
     * 加载制定路径下的properties文件语言包
     *
     * @param path 文件路径，默认路径相对于classpath路径，以'/'分割路径
     */
    public synchronized static void load(String path) {
        String namePrefix = "";
        if (path != null) {
            namePrefix += path;
            if (!namePrefix.endsWith(FILE_SEPARATOR)) {
                namePrefix += SEPARATOR;
            }
        }

        String name;
        for (Lang lang : Lang.values()) {
            name = (namePrefix + lang.name() + SUFFIX);
            load0(lang, name);
        }
    }

    public static String getMessage(Lang lang, String code) {
        Properties prop = propertiesMap.get(lang);
        if (prop != null) {
            return prop.getProperty(code);
        }
        return null;
    }

    private static void loadConfig() {
        try {
            Properties config = loadProperties(CONFIG);
            if (config != null) {
                for (Object value : config.values()) {
                    load(value.toString().trim());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Load config error, config=" + CONFIG, e);
        }
    }

    private static void load0(Lang lang, String name) {
        try {
            Properties prop = loadProperties(name);
            if (prop != null) {
                put(lang, prop);
            }
        } catch (Exception e) {
            throw new RuntimeException("Load message error, lang=" + lang + ", name=" + name, e);
        }
    }

    private static Properties loadProperties(String name) throws Exception {
        InputStream is = MessageSource.class.getClassLoader().getResourceAsStream(name);
        Properties prop = null;

        if (is != null) {
            try {
                Properties temp = new Properties();
                temp.load(is);
                prop = trim(temp);
            } finally {
                is.close();
            }
        }

        return prop;
    }

    private static Properties trim(Properties prop) {
        Properties trim = new Properties();
        String value;
        for (Object key : prop.keySet()) {
            value = prop.getProperty(key.toString(), "");
            trim.put(key.toString().trim(), value.trim());
        }
        return trim;
    }

    private static void put(Lang lang, Properties temp) {
        Properties msgs = propertiesMap.get(lang);
        if (msgs != null) {
            msgs.putAll(temp);
        } else {
            msgs = temp;
        }

        propertiesMap.put(lang, msgs);
    }
}
