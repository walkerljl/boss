/*
 * Copyright (c) 2010-2015 www.walkerljl.org All Rights Reserved.
 * The software source code all copyright belongs to the author, 
 * without permission shall not be any reproduction and transmission.
 */
package org.walkerljl.boss.support.mvc.filter;

import org.walkerljl.toolkit.lang.StringUtils;

import javax.servlet.*;
import java.io.IOException;

/**
 * CharsetInterceptor
 *
 * @author xingxun
 */
public class CharsetFilter implements Filter {

    private FilterConfig config = null;
    private String encoding = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.config = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        if (encoding == null) {
            encoding = config.getInitParameter("encoding");
        }

        if (StringUtils.isBlank(encoding)) {
            encoding = "UTF-8";
        }

        request.setCharacterEncoding(encoding);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        config = null;
        encoding = null;
    }
}