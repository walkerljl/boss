package org.walkerljl.boss.support.mvc.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.walkerljl.boss.support.mvc.WebConstants;
import org.walkerljl.toolkit.lang.StringUtils;
import org.walkerljl.toolkit.standard.exception.AppException;

/**
 * ResponseUtils
 *
 * @author xingxun
 */
public class ResponseUtils {

    /**
     * 返回数据
     *
     * @param request
     * @param response
     * @param outputResult
     */
    public static void toResult(HttpServletRequest request, HttpServletResponse response, Object outputResult) {
        String contentType = request.getParameter("contentType");
        contentType = (contentType == null ? "" : contentType.trim().toLowerCase());
        if ("json".equals(contentType)) {
            toJSON(response, outputResult);
        } else if ("jsonp".equals(contentType)) {
            toJSONP(request, response, outputResult);
        } else if ("html".equals(contentType)) {
            toHTML(request, response, outputResult.toString());
        } else {
            toJSON(response, outputResult);
        }
    }

    /**
     * 获取请求的字符集
     *
     * @param request
     * @return
     */
    public static String getCharset(HttpServletRequest request) {
        String charset = request.getParameter("charset");
        if (StringUtils.isBlank(charset)) {
            charset = WebConstants.DEFAULT_CHARSET;
        }
        return charset;
    }

    /**
     * 返回JSONP格式的数据
     *
     * @param request
     * @param outputResult
     */
    public static void toJSONP(HttpServletRequest request, HttpServletResponse response,
                               Object outputResult) {
        String callback = request.getParameter("callback");
        String charset = getCharset(request);
        toJSONP(response, outputResult, charset, callback);
    }

    /**
     * 返回JSONP格式的数据
     *
     * @param response
     * @param outputResult
     * @param charset
     * @param callback
     */
    public static void toJSONP(HttpServletResponse response,
                               Object outputResult, String charset, String callback) {
        callback = filterXSS(callback);
        if (StringUtils.isBlank(charset)) {
            charset = WebConstants.DEFAULT_CHARSET;
        }
        response.setContentType("text/javascript; charset=" + charset);
        response.setHeader("pragma", "no-cache");
        response.setHeader("cache-control", "no-cache");
        response.setHeader("P3P", "CP='IDC DSP COR ADM DEVi " +
                "TAIi PSA PSD IVAi IVDi CONi HIS OUR IND CNT'");
        try {
            response.getWriter().print(callback + "(" + String.valueOf(outputResult) + ");");
            response.flushBuffer();
        } catch (Throwable e) {
            throw new AppException(e);
        }
    }

    /**
     * 返回JSON格式数据
     *
     * @param response
     * @param outputResult
     */
    public static void toJSON(HttpServletResponse response, Object outputResult) {
        toJSON(response, outputResult, null);
    }

    /**
     * 返回JSON格式数据
     *
     * @param response
     * @param outputResult
     * @param format
     */
    public static void toJSON(HttpServletResponse response, Object outputResult, Map<String, String> format) {
        try {
            JSONUtils.write(response.getWriter(), outputResult, format);
        } catch (Throwable e) {
            throw new AppException(e);
        }
    }

    /**
     * 返回HTML格式数据
     *
     * @param request
     * @param response
     * @param content
     */
    public static void toHTML(HttpServletRequest request, HttpServletResponse response, String content) {
        String charset = getCharset(request);
        response.setContentType("text/html;charset=" + charset);
        StringBuilder sb = new StringBuilder();
        sb.append("<html><head></head><body>")
                .append(content).append("</body></html>");
        try {
            response.getWriter().println(sb.toString());
            response.flushBuffer();
        } catch (Throwable e) {
            throw new AppException(e);
        }
    }

    /**
     * 过滤XSS攻击
     *
     * @param src
     * @return
     */
    private static String filterXSS(String src) {
        return (src != null && !"".equals(src)) ? src.replace("<", "&lt;").replace(">", "&gt;") : src;
    }
}
