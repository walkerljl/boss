/*
 * Copyright (c) 2010-2015 lijunlin All Rights Reserved.
 * The software source code all copyright belongs to the author, 
 * without permission shall not be any reproduction and transmission.
 */
package org.walkerljl.boss.support.mvc.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.walkerljl.boss.support.mvc.WebConstants;
import org.walkerljl.boss.support.mvc.exception.ExceptionHandler;
import org.walkerljl.toolkit.lang.CollectionUtils;

/**
 * GlobalExceptionResolver
 *
 * @author xingxun
 */
public class GlobalExceptionResolver extends SimpleMappingExceptionResolver {

    /**
     * 异常处理器列表
     */
    private List<ExceptionHandler> exceptionHandlers;

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        Boolean resource = (Boolean) request.getAttribute(WebConstants.STATIC_RES_REQUEST);
        //静态资源
        if (resource != null && resource) {
            return null;
        }

        if (CollectionUtils.isEmpty(exceptionHandlers)) {
            return null;
        }
        for (ExceptionHandler exceptionHandler : exceptionHandlers) {
            exceptionHandler.handle(request, response, handler, ex);
        }
        return null;
    }

    /**
     * Setter method for property <tt>exceptionHandlers</tt>.
     *
     * @param exceptionHandlers value to be assigned to property exceptionHandlers
     */
    public void setExceptionHandlers(List<ExceptionHandler> exceptionHandlers) {
        this.exceptionHandlers = exceptionHandlers;
    }
}