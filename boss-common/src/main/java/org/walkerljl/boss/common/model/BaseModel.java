package org.walkerljl.boss.common.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import org.walkerljl.boss.common.util.CollectionUtil;
import org.walkerljl.boss.common.util.StringUtil;

/**
 * 基础模型
 *
 * @author xingxun
 */
public class BaseModel implements Serializable {

    private static final long serialVersionUID = -2178195448223718688L;

    /**
     * fastjson 统一配置
     */
    private static final SerializerFeature[] FASTJSON_CONFIG = new SerializerFeature[] {
            SerializerFeature.DisableCircularReferenceDetect,
            SerializerFeature.WriteDateUseDateFormat,
            //SerializerFeature.WriteMapNullValue,
            //SerializerFeature.WriteNullStringAsEmpty
            //,SerializerFeature.PrettyFormat
    };

    /**
     * 将对象转成JSON字符串
     *
     * @param object
     * @return
     */
    public static String toJSONString(Object object) {
        if (object == null) {
            return null;
        }
        return JSON.toJSONString(object, FASTJSON_CONFIG);
    }

    /**
     * 将JSON字符串解析成JSONObject对象
     *
     * @param text
     * @return JSONObject
     */
    public static JSONObject parseObject(String text) {
        if (StringUtil.isEmpty(text)) {
            return null;
        }
        return JSON.parseObject(text);
    }

    public static <T> T parseObject(String text, Class<T> clazz) {
        if (StringUtil.isEmpty(text)) {
            return null;
        }
        return JSON.parseObject(text, clazz);
    }

    /**
     * 解析成List对象
     *
     * @param text
     * @param clazz
     * @return
     */
    public static <T> List<T> parseList(String text, Class<T> clazz) {
        if (StringUtil.isEmpty(text)) {
            return null;
        }
        return JSONObject.parseArray(text, clazz);
    }

    public static <K, V> List<Map<K, V>> parseListMap(String json) {
        if (StringUtil.isEmpty(json)) {
            return null;
        }

        List<String> items = parseList(json, String.class);
        if (CollectionUtil.isEmpty(items)) {
            return null;
        }

        List<Map<K, V>> listMap = new ArrayList<>(items.size());
        for (String item : items) {
            Map<K, V> itemMap = parseMap(item);
            listMap.add(itemMap);
        }
        return listMap;
    }

    /**
     * 解析成Map
     *
     * @param text JSON字符串
     * @return
     */
    public static <K, V> Map<K, V> parseMap(String text) {
        if (StringUtil.isEmpty(text)) {
            return null;
        }

        return JSON.parseObject(text, Map.class);
    }

    @Override
    public String toString() {
        return toJSONString(this);
    }
}