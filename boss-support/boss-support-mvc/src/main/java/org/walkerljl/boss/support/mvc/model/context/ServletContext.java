/*
 * Copyright (c) 2010-2015 lijunlin All Rights Reserved.
 * The software source code all copyright belongs to the author, 
 * without permission shall not be any reproduction and transmission.
 */
package org.walkerljl.boss.support.mvc.model.context;

import org.walkerljl.toolkit.standard.exception.AppException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * ServletContext
 *
 * @author xingxun
 */
public class ServletContext {

    /**
     * 容器
     */
    private static final ThreadLocal<ServletContext> SERVLET_CONTEXT = new ThreadLocal<ServletContext>();
    /**
     * HttpServletRequest
     */
    private HttpServletRequest request;
    /**
     * HttpServletResponse
     */
    private HttpServletResponse response;

    /**
     * 私有构造函数
     */
    private ServletContext() {
        SERVLET_CONTEXT.set(this);
    }

    /**
     * 初始化上下文
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     */
    public static final void initContext(HttpServletRequest request, HttpServletResponse response) {
        ServletContext context = new ServletContext();
        context.request = request;
        context.response = response;
    }

    /**
     * 获取Servlet上下文
     *
     * @return
     */
    public static ServletContext getServletContext() {
        return SERVLET_CONTEXT.get();
    }

    /**
     * 移除上下文
     */
    public static final void remove() {
        SERVLET_CONTEXT.remove();
    }

    /**
     * 获取HttpServletRequest对象
     *
     * @return
     */
    public static final HttpServletRequest getRequest() {
        ServletContext context = getServletContext();
        if (context == null) {
            return null;
        }
        return context.request;
    }

    /**
     * 获取HttpServletResponse对象
     *
     * @return
     */
    public static final HttpServletResponse getResponse() {
        ServletContext context = getServletContext();
        if (context == null) {
            return null;
        }
        return context.response;
    }

    /**
     * 获取请求上下文路径
     *
     * @return
     */
    public static final String getContextPath() {
        HttpServletRequest request = getRequest();
        if (request == null) {
            return null;
        }
        return request.getContextPath();
    }

    /**
     * 获取请求URI
     *
     * @return
     */
    public static final String getRequestURI() {
        HttpServletRequest request = getRequest();
        if (request == null) {
            return null;
        }
        return request.getRequestURI();
    }

    /**
     * 设置Request生命周期参数
     *
     * @param key   参数Key
     * @param value 参数Value
     */
    public static final void setRequestAttribute(String key, Object value) {
        HttpServletRequest request = getRequest();
        if (request != null) {
            request.setAttribute(key, value);
        }
    }

    /**
     * 获取Request生命周期参数
     *
     * @param key 参数Key
     * @return
     */
    public static final Object getRequestAttribute(String key) {
        HttpServletRequest request = getRequest();
        if (request == null) {
            return null;
        }
        return request.getAttribute(key);
    }

    /**
     * 设置Session生命周期参数
     *
     * @param key   参数Key
     * @param value 参数Value
     */
    public static final void setSessionAttribute(String key, Object value) {
        HttpSession session = getRequest().getSession();
        if (session != null) {
            session.setAttribute(key, value);
        }
    }

    /**
     * 获取Session生命周期参数
     *
     * @param key 参数Key
     * @return
     */
    public static final Object getSessionAttribute(String key) {
        HttpSession session = getRequest().getSession();
        if (session == null) {
            return null;
        }
        return session.getAttribute(key);
    }

    /**
     * 获取HttpServletResponse的Writer
     *
     * @return
     */
    public static PrintWriter getResponseWriter() {
        HttpServletResponse response = getResponse();
        try {
            return response == null ? null : response.getWriter();
        } catch (IOException e) {
            throw new AppException(e);
        }
    }
}