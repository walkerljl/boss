package org.walkerljl.boss.support.mvc.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 异常处理器
 *
 * @author xingxun
 */
public interface ExceptionHandler {

    void handle(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception);
}