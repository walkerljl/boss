package org.walkerljl.boss.model.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import org.walkerljl.boss.support.common.util.StringUtil;

/**
 * 基础核心模型
 *
 * @author xingxun
 */
public class BaseCoreModel implements Serializable {

    private static final long serialVersionUID = -3631715832151460408L;

    /**
     * fastjson 统一配置
     */
    private static final SerializerFeature[] FASTJSON_CONFIG = new SerializerFeature[] {
            //SerializerFeature.DisableCircularReferenceDetect,
            SerializerFeature.WriteDateUseDateFormat,
            SerializerFeature.WriteMapNullValue,
            SerializerFeature.WriteNullStringAsEmpty
            //,SerializerFeature.PrettyFormat
    };

    /**
     * 将对象转成JSON字符串
     *
     * @param object
     * @return
     */
    public static String toJSONString(Object object) {
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

    /**
     * 解析成List对象
     *
     * @param text
     * @param clazz
     * @return
     */
    public static <T> List<T> parseList(String text, Class<T> clazz) {
        if (text == null || "".equalsIgnoreCase(text.trim())) {
            return null;
        }
        return JSONObject.parseArray(text, clazz);
    }

    /**
     * 解析成Map
     *
     * @param text JSON字符串
     * @return
     */
    public static Map<String, String> parseMap(String text) {
        if (text == null) {
            return null;
        }

        return JSON.parseObject(text, Map.class);
    }

    @Override
    public String toString() {
        return toJSONString(this);
    }
}