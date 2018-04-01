package org.walkerljl.boss.support.mvc.interceptor;//package org.walkerljl.smart.mvc.interceptor;
//
//import java.util.Map;
//import java.util.Set;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//import org.walkerljl.auth.sdk.annotation.Authentication;
//import org.walkerljl.auth.sdk.enums.AuthType;
//import org.walkerljl.auth.sdk.support.AuthenticationProvider;
//import org.walkerljl.auth.sdk.support.Authenticator;
//import org.walkerljl.configuration.client.ConfiguratorFactory;
//import org.walkerljl.smart.mvc.JSONUtils;
//import org.walkerljl.smart.mvc.util.MvcUtils;
//import org.walkerljl.toolkit.lang.MapUtils;
//import org.walkerljl.toolkit.lang.StringPool;
//import org.walkerljl.toolkit.lang.StringUtils;
//
///**
// * 权限拦截器
// *
// * @author lijunlin
// */
//public class AuthenticationInterceptorOld extends HandlerInterceptorAdapter {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationInterceptorOld.class);
//
//    private static final String CURRENT_USER_ID_KEY = "currentUserId";
//    private static final String ADMIN_USER_LIST_KEY = "administrators";
//    private Authenticator          authenticator;
//    private AuthenticationProvider authenticationProvider;
//
//    /**
//     * Access denied page
//     */
//    private String accessDeniedPage;
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//            throws Exception {
//        boolean result = true;
//        if (!(handler instanceof HandlerMethod)) {
//            //request.setAttribute(Constants.STATIC_RES_REQUEST, true);
//            return result;
//        }
//        //权限校验
//        HandlerMethod handlerMethod = (HandlerMethod) handler;
//        Authentication clazz = handlerMethod.getBean().getClass().getAnnotation(Authentication.class);
//        Authentication method = handlerMethod.getMethodAnnotation(Authentication.class);
//        AuthType authType = authenticator.getType(clazz, method);
//        boolean validateAuthCode = false;
//
//        if (authType == AuthType.CODE) {
//            validateAuthCode = true;
//        }
//
//        //权限校验
//        if (validateAuthCode) {
//            String currentUserId = (String) request.getAttribute(CURRENT_USER_ID_KEY);
//            Set<String> administrators = ConfiguratorFactory.getStdConfigurator().getAsSet(String.class, ADMIN_USER_LIST_KEY, ",");
//            if (administrators.contains(currentUserId)) {
//                return result;
//            }
//            String authCode = authenticator.getAuthCode(clazz, method);
//            if (StringUtils.isBlank(authCode)) {
//                return result;
//            }
//
//            result = validateUserAuth(currentUserId, authCode);
//            if (LOGGER.isDebugEnabled()) {
//                LOGGER.debug(String.format("Do access authentication：userId=%s, authCode=%s, isAccess=%s",
//                        new Object[]{currentUserId, authCode, result}));
//            }
//
//            if (!result) {
//                boolean isJsonRequest = request.getRequestURI().endsWith(StringPool.JSON_SUFFIX);
//                if (isJsonRequest) {
//                    Map<String, Object> context = MapUtils.newHashMap();
//                    context.put(StringPool.RESPONSE_STATUS_KEY, false);
//                    context.put(StringPool.RESPONSE_DATA_KEY, "accessDenied");
//                    context.put(StringPool.RESPONSE_MESSAGE_KEY, "Access denied");
//                    JSONUtils.write(response.getWriter(), context, null);
//                } else {
//                    MvcUtils.sendRedirect(this.accessDeniedPage);
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
//        super.afterCompletion(request, response, handler, ex);
//    }
//
//    /**
//     * 设置无权限访问页面地址
//     *
//     * @param accessDeniedPage
//     */
//    public void setAccessDeniedPage(String accessDeniedPage) {
//        this.accessDeniedPage = accessDeniedPage;
//    }
//
//    /**
//     * @param userId
//     * @param authCode
//     * @return
//     */
//    protected boolean validateUserAuth(String userId, String authCode) {
//        return authenticationProvider.validateUserAuth(userId, authCode);
//    }
//
//    /**
//     * 设置权限器
//     *
//     * @param authenticator
//     */
//    public void setAuthenticator(Authenticator authenticator) {
//        this.authenticator = authenticator;
//    }
//
//    /**
//     * 设置权限校验提供者
//     *
//     * @param authenticationProvider
//     */
//    public void setAuthenticationProvider(AuthenticationProvider authenticationProvider) {
//        this.authenticationProvider = authenticationProvider;
//    }
//}