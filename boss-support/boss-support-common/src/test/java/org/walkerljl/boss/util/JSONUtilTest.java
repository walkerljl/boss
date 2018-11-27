package org.walkerljl.boss.util;

import com.alibaba.fastjson.serializer.SerializerFeature;

import org.junit.Test;

/**
 * JSONUtilTest
 *
 * @author xingxun
 * @Date 2018/5/19
 */
public class JSONUtilTest {

    private static final SerializerFeature[] FASTJSON_CONFIG = new SerializerFeature[] {
            SerializerFeature.DisableCircularReferenceDetect,
            SerializerFeature.WriteDateUseDateFormat,
            SerializerFeature.WriteMapNullValue,
            SerializerFeature.WriteNullStringAsEmpty
    };

    @Test
    public void toJSONString() {


    }
}
