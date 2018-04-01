package org.walkerljl.boss.support.mvc.util;

import org.walkerljl.toolkit.standard.exception.AppException;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * UrlEncoderUtils
 *
 * @author xingxun
 */
public class UrlEncoderUtils {

    public static String encode(String text, String encoding) {
        try {
            return URLEncoder.encode(text, encoding);
        } catch (UnsupportedEncodingException e) {
            throw new AppException(e);
        }
    }
}