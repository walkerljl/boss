package org.walkerljl.boss.support.mvc.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.walkerljl.toolkit.lang.StringUtils;
import org.walkerljl.toolkit.standard.exception.AppException;

/**
 * 异常工具
 *
 * @author xingxun
 */
public class ExceptionUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionUtils.class);

    /**
     * 打印异常并报警
     *
     * @param messagePrefix
     * @param e
     * @see
     */
    public static void print(String messagePrefix, Throwable e) {
        ExceptionUtils.print(null, messagePrefix, e);
    }

    /**
     * 打印异常并报警(未知异常Throwable使用UMP进行报警)
     *
     * @param 业务报警Key
     * @param messagePrefix 消息前缀
     * @param ex            异常对象
     */
    public static void print(String alarmKey, String messagePrefix, Throwable e) {
        String errMsg = (messagePrefix == null ? "未知的异常消息前缀" : messagePrefix) + ", 响应: " + (e == null ? "未知异常原因" : e.getMessage());
        if (e != null && e instanceof AppException) {
            LOGGER.warn(errMsg);//只打印异常消息不打印异常堆栈
        } else {
            LOGGER.error(errMsg, e);
            if (StringUtils.isEmpty(alarmKey)) {
                //UMPProxy.alarm(errMsg);
            } else {
                //UMPProxy.businessAlarm(alarmKey, errMsg);
            }
        }
    }
}