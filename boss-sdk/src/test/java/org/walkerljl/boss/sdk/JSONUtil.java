package org.walkerljl.boss.sdk;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 *
 * @author xingxun
 */
public class JSONUtil {

    /**
     * fastjson 统一配置
     */
    private static final SerializerFeature[] FASTJSON_CONFIG = new SerializerFeature[]{
            SerializerFeature.DisableCircularReferenceDetect,
            SerializerFeature.WriteDateUseDateFormat,
            SerializerFeature.WriteMapNullValue,
            SerializerFeature.WriteNullStringAsEmpty
    };

    public static String toJSONString(Object object) {
        if (object == null) {
            return null;
        }
        return JSON.toJSONString(object, FASTJSON_CONFIG);
    }

}