package org.walkerljl.boss.support.mvc.exception.impl;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.walkerljl.boss.support.mvc.exception.AbstractExceptionHandler;
import org.walkerljl.boss.support.mvc.util.ResponseUtils;
import org.walkerljl.toolkit.logging.Logger;
import org.walkerljl.toolkit.logging.LoggerFactory;
import org.walkerljl.toolkit.standard.Result;
import org.walkerljl.toolkit.template.handle.service.ServiceErrorCode;
import org.walkerljl.toolkit.template.log.LoggerUtil;

/**
 * @author xingxun
 */
public class GlobalExceptionHandler extends AbstractExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 错误展示地址
     */
    private String errorDisplayAddress;

    @Override
    public void handleJSONRequest(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) {
        Result<String> result = Result.failure(ServiceErrorCode.UNKNOWN.getCode(),
                ServiceErrorCode.UNKNOWN.getDescription());
        ResponseUtils.toJSON(response, result);
    }

    @Override
    public void handleNonJSONRequest(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) {
        String message = exception == null ? "" : exception.getMessage();
        try {
            response.sendRedirect(String.format("%s&msg%s", errorDisplayAddress, message));
        } catch (IOException e) {
            LoggerUtil.error(LOGGER, e);
        }
    }

    /**
     * Setter method for property <tt>errorDisplayAddress</tt>.
     *
     * @param errorDisplayAddress value to be assigned to property errorDisplayAddress
     */
    public void setErrorDisplayAddress(String errorDisplayAddress) {
        this.errorDisplayAddress = errorDisplayAddress;
    }
}