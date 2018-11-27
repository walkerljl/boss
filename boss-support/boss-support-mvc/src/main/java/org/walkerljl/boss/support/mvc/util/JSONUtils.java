package org.walkerljl.boss.support.mvc.util;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.NameFilter;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;

import org.walkerljl.toolkit.lang.CollectionUtils;
import org.walkerljl.toolkit.lang.MapUtils;
import org.walkerljl.toolkit.lang.StringUtils;
import org.walkerljl.toolkit.lang.io.StreamUtils;
import org.walkerljl.toolkit.logging.Logger;
import org.walkerljl.toolkit.logging.LoggerFactory;
import org.walkerljl.toolkit.standard.exception.AppException;

/**
 * JSONUtils
 *
 * @author xingxun
 */
public class JSONUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(JSONUtils.class);

    /**
     * fastjson 统一配置
     */
    private static final SerializerFeature[] FASTJSON_CONFIG = new SerializerFeature[]{
            SerializerFeature.DisableCircularReferenceDetect,
            SerializerFeature.WriteDateUseDateFormat,
            SerializerFeature.WriteMapNullValue,
            SerializerFeature.WriteNullStringAsEmpty
    };
    //,SerializerFeature.PrettyFormat};

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
     * 将Map<String, Object>转换成Map<String, String>即将Map的value序列化成JSON字符串
     *
     * @param dataMap
     * @return
     */
    public static Map<String, String> toJSON(Map<String, Object> dataMap) {
        if (MapUtils.isEmpty(dataMap)) {
            return null;
        }
        Map<String, String> result = new HashMap<String, String>();
        for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
            result.put(entry.getKey(), JSONUtils.toJSONString(entry.getValue()));
        }
        return result;
    }

    /**
     * 将JSON字符串解析成JSONObject对象
     *
     * @param text
     * @return JSONObject
     */
    public static JSONObject parseObject(String text) {
        if (StringUtils.isBlank(text)) {
            return null;
        }
        return JSON.parseObject(text);
    }

    /**
     * 将JSON字符串解析成Java对象
     *
     * @param text
     * @param clazz
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T parseObject(String text, Class<T> clazz) {
        if (StringUtils.isBlank(text)) {
            return null;
        }
        if (clazz == null || clazz == String.class) {
            return (T) text;
        }
        return (T) JSON.parseObject(text, clazz);
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

    /**
     * 解析成List对象
     *
     * @param texts
     * @param clazz
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> parseList(Collection<String> texts, Class<T> clazz) {
        if (CollectionUtils.isEmpty(texts)) {
            return null;
        }
        if (clazz == null || clazz == String.class) {
            return (List<T>) texts;
        }
        List<T> result = new ArrayList<T>();
        for (String text : texts) {
            if (StringUtils.isBlank(text)) {
                continue;
            }
            T obj = JSONUtils.parseObject(text, clazz);
            if (obj != null) {
                result.add(obj);
            }
        }
        return result;
    }

    /**
     * 解析成List集合对象,解析对象为Map<String, String>的value
     *
     * @param textMap
     * @param clazz
     * @return
     */
    public static <T> List<T> parseList(Map<String, String> textMap, Class<T> clazz) {
        if (MapUtils.isEmpty(textMap)) {
            return null;
        }
        List<T> result = new ArrayList<T>();
        for (Map.Entry<String, String> entry : textMap.entrySet()) {
            T object = JSONUtils.parseObject(entry.getValue(), clazz);
            if (object != null) {
                result.add(object);
            }
        }
        return result;
    }

    /**
     * 输出JSON格式数据
     *
     * @param out
     * @param context
     * @param serializeFormat
     */
    public static void write(PrintWriter out, Object context, final Map<String, String> serializeFormat) {
        NameFilter nameFilter = null;
        if (MapUtils.isNotEmpty(serializeFormat)) {
            nameFilter = new NameFilter() {
                @Override
                public String process(Object source, String name, Object value) {
                    Object cvnName = serializeFormat.get(name);
                    if (cvnName != null && !cvnName.toString().equals("")) {
                        return cvnName.toString();
                    }
                    return name;
                }
            };
        }

        SerializeWriter sw = new SerializeWriter(FASTJSON_CONFIG);
        sw.config(SerializerFeature.QuoteFieldNames, true);
        JSONSerializer serializer = new JSONSerializer(sw);
        if (nameFilter != null) {
            serializer.getNameFilters().add(nameFilter);
        }
        serializer.write(context);
        try {
            String json = escapeJson(sw.toString());
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(json);
            }
            out.print(json);
            out.flush();
        } catch (Exception ex) {
            throw new AppException(ex);
        } finally {
            StreamUtils.close(out);
        }
    }

    private static String escapeJson(String json) {
        return json.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
    }

    /**
     * 输出JSON格式数据
     *
     * @param out
     * @param context
     */
    public static void write(PrintWriter out, Object context) {
        write(out, context, null);
    }
}