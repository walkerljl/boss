package org.walkerljl.boss.support.mvc.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.walkerljl.boss.support.mvc.WebConstants;
import org.walkerljl.toolkit.lang.StringUtils;

/**
 * 抽象的异常处理器
 *
 * @author xingxun
 */
public abstract class AbstractExceptionHandler implements ExceptionHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) {
        if (request == null) {
            return;
        }
        String requestUri = request.getRequestURI();
        if (StringUtils.isBlank(requestUri)) {
            return;
        }
        if (requestUri.endsWith(WebConstants.JSON_SUFFIX)
                || requestUri.endsWith(WebConstants.JSON_SUFFIX.toUpperCase())) {
            handleJSONRequest(request, response, handler, exception);
        } else {
            handleNonJSONRequest(request, response, handler, exception);
        }
    }

    public abstract void handleJSONRequest(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception);

    public abstract void handleNonJSONRequest(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception);
}