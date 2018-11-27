package org.walkerljl.boss.common.util;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import org.walkerljl.toolkit.lang.StringUtils;

/**
 * JSONUtil
 *
 * @author xingxun
 */
public class JSONUtil {
    /**
     * fastjson 统一配置
     */
    private static final SerializerFeature[] FASTJSON_CONFIG = new SerializerFeature[] {
            //SerializerFeature.DisableCircularReferenceDetect,
            //SerializerFeature.WriteDateUseDateFormat,
            //SerializerFeature.WriteMapNullValue,
            //SerializerFeature.WriteNullStringAsEmpty,
            //SerializerFeature.PrettyFormat
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
     * 解析成List对象
     *
     * @param text
     * @param clazz
     * @return
     */
    public static <T> List<T> parseList(String text, Class<T> clazz) {
        if (StringUtils.isBlank(text)) {
            return null;
        }
        return JSONObject.parseArray(text, clazz);
    }

}