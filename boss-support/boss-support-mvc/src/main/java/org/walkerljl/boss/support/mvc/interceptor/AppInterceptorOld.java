package org.walkerljl.boss.support.mvc.interceptor;///*
// * Copyright (c) 2010-2015 lijunlin All Rights Reserved.
// * The software source code all copyright belongs to the author,
// * without permission shall not be any reproduction and transmission.
// */
//package org.walkerljl.smart.mvc.interceptor;
//
//import java.util.Locale;
//import java.util.Map;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//import org.walkerljl.auth.sdk.annotation.Authentication;
//import org.walkerljl.auth.sdk.enums.AuthType;
//import org.walkerljl.auth.sdk.support.Authenticator;
//import org.walkerljl.configuration.client.ConfiguratorFactory;
//import org.walkerljl.smart.common.Constants;
//import org.walkerljl.smart.mvc.MvcSupporter;
//import org.walkerljl.smart.mvc.context.ServletContext;
//import org.walkerljl.smart.mvc.i18n.LocaleUtils;
//import org.walkerljl.smart.mvc.util.CookieUtils;
//import org.walkerljl.smart.mvc.util.MvcUtils;
//import org.walkerljl.toolkit.lang.MapUtils;
//import org.walkerljl.toolkit.lang.StringPool;
//import org.walkerljl.toolkit.logging.Logger;
//import org.walkerljl.toolkit.logging.LoggerFactory;
//
///**
// * 应用拦截器
// *
// * @author xingxun
// */
//public class AppInterceptorOld extends HandlerInterceptorAdapter {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(AppInterceptorOld.class);
//
//    /**
//     * 升级页面
//     */
//    private String upgradingPage;
//
//    @Resource
//    private MvcSupporter       mvcSupporter;
//    @Resource
//    private Authenticator      authenticator;
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//            throws Exception {
//        boolean result = true;
//        try {
//            //设置请求响应编码
//            request.setCharacterEncoding(Constants.DEFAULT_CHARSET);
//            response.setCharacterEncoding(request.getCharacterEncoding());
//
//            if (!(handler instanceof HandlerMethod)) {
//                //request.setAttribute(Constants.STATIC_RES_REQUEST, true);
//                return result;
//            }
//            //设置本次请求上下文
//            ServletContext.initContext(request, response);
//            //validate login
//            result = validateLogin(handler);
//            //init locale
//            initLocale(request);
//        } catch (Exception e) {
//            LOGGER.error(e.getMessage(), e);
//            throw e;
//        }
//        return result;
//    }
//
//    /**
//     * Init locale
//     *
//     * @param request
//     */
//    private void initLocale(HttpServletRequest request) {
//        String localeString = CookieUtils.getCookieValue(request, "locale");
//        if (localeString == null || "".equals(localeString)) {
//            localeString = "zh";
//        }
//
//        Locale locale = Locale.SIMPLIFIED_CHINESE;
//        if ("en".equals(localeString)) {
//            locale = Locale.ENGLISH;
//        } else if ("zh".equals(localeString)) {
//            locale = Locale.SIMPLIFIED_CHINESE;
//        } else if ("zh_TW".equals(localeString)) {
//            locale = Locale.TRADITIONAL_CHINESE;
//        }
//        LocaleUtils.setLocale(locale);
//    }
//
//    /**
//     * Do login validation
//     *
//     * @param handler
//     * @return
//     */
//    private boolean validateLogin(Object handler) {
//        boolean result = true;
//        HandlerMethod handlerMethod = (HandlerMethod) handler;
//        Authentication clazz = handlerMethod.getBean().getClass().getAnnotation(Authentication.class);
//        Authentication method = handlerMethod.getMethodAnnotation(Authentication.class);
//        AuthType authType = authenticator.getType(clazz, method);
//        if (LOGGER.isDebugEnabled()) {
//            LOGGER.debug(String.format("The user id of the current access:%s, has logined:%s",
//                    mvcSupporter.getCurrentUserId(), mvcSupporter.isLogin()));
//        }
//        if (authType == AuthType.LOGIN || authType == AuthType.CODE) {
//            result = mvcSupporter.isLogin();
//            if (result) {
//                result = filterUpgrading();
//            } else {
//                boolean isJsonRequest = ServletContext.getRequestURI().endsWith(StringPool.JSON_SUFFIX);
//                if (isJsonRequest) {
//                    Map<String, Object> context = MapUtils.newHashMap();
//                    context.put(StringPool.RESPONSE_STATUS_KEY, false);
//                    context.put(StringPool.RESPONSE_MESSAGE_KEY, "Please login.");
//                    context.put(StringPool.RESPONSE_DATA_KEY, "notLogin");
//
//                    //JSONUtils.write(ServletContext.getResponseWriter(), context, null);
//                } else {
//                    mvcSupporter.login();
//                }
//            }
//        }
//        return result;
//    }
//
//    @Override
//    public void postHandle(
//            HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
//            throws Exception {
//        super.postHandle(request, response, handler, modelAndView);
//    }
//
//    @Override
//    public void afterCompletion(
//            HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
//            throws Exception {
//
//        ServletContext.remove();
//    }
//
//    /**
//     * 系统升级拦截
//     *
//     * @return
//     */
//    private boolean filterUpgrading() {
//        boolean result = true;
//        Boolean isUpgrading = ConfiguratorFactory.getStdConfigurator().getAsBoolean("app.upgrading");
//        if (isUpgrading != null && isUpgrading
//                && !mvcSupporter.currentUserIsAdmin()) {
//            if (LOGGER.isDebugEnabled()) {
//                LOGGER.debug(String.format("The user id of the current access:%s, redirect to upgrading page.",
//                        mvcSupporter.getCurrentUserId()));
//            }
//            MvcUtils.sendRedirect(upgradingPage);
//        }
//        return result;
//    }
//
//    /**
//     * 设置升级页面
//     *
//     * @param upgradingPage
//     */
//    public void setUpgradingPage(String upgradingPage) {
//        this.upgradingPage = upgradingPage;
//    }
//}