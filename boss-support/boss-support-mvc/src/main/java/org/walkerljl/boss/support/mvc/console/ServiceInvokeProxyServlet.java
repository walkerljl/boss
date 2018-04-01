package org.walkerljl.boss.support.mvc.console;//package org.walkerljl.smart.mvc.command;
//
//import java.io.IOException;
//import java.util.Map;
//
//import javax.servlet.ServletConfig;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.walkerljl.boss.support.common.Constants;
//import org.walkerljl.boss.support.service.ApplicationContextUtils;
//import org.walkerljl.commons.invoke.ServiceInvokeProxy;
//import org.walkerljl.commons.invoke.support.DefaultServiceInvokeProxy;
//import org.walkerljl.commons.log.Logger;
//import org.walkerljl.commons.log.LoggerFactory;
//import org.walkerljl.commons.security.ServiceInvokeSecurityValidator;
//import org.walkerljl.commons.util.JSONUtils;
//import org.walkerljl.commons.util.StringUtils;
//
///**
// * 服务调用代理
// * <p>
// * /command/service/invoke?serviceInterface=org.walkerljl.commons.invoke
// .PingService&methodName=ping&appId=boss&timestamp=1477378359997&nonce=123456&signature=a764440375cf58a0
// * /command/service/invoke?serviceInterface=org.walkerljl.commons.invoke.PingService&serviceImpl=org.walkerljl.commons.invoke.support
// .PingServiceImpl&methodName=ping&appId=boss&timestamp=1477378359997&nonce=123456&signature=a764440375cf58a0
// * /command/service/invoke?serviceInterface=org.walkerljl.commons.invoke.PingService&methodName=ping&params=[{%22type%22:%22java.lang
// .String%22,%22value%22:%22walkerljl%22}]&appId=boss&timestamp=1477378359997&nonce=123456&signature=a764440375cf58a0
// *
// * @author lijunlin
// */
//public class ServiceInvokeProxyServlet extends HttpServlet {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceInvokeProxyServlet.class);
//    private ServiceInvokeSecurityValidator serviceInvokeSecurityValidator = new ServiceInvokeSecurityValidator();
//    private ServiceInvokeProxy serviceInvokeProxy = new ServiceInvokeProxyImpl();
//
//    @Override
//    public void init(ServletConfig config) throws ServletException {
//        super.init(config);
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
//        String serviceInterface = request.getParameter("serviceInterface");
//        String serviceImpl = request.getParameter("serviceImpl");
//        String methodName = request.getParameter("methodName");
//        String staticInvokeString = request.getParameter("staticInvoke");
//        boolean staticInvoke = false;
//        if (StringUtils.isNotBlank(staticInvokeString)) {
//            staticInvoke = Boolean.valueOf(staticInvokeString);
//        }
//        String paramsJSONString = request.getParameter("params");
//        String signature = request.getParameter("signature");
//
//        String appId = request.getParameter("appId");
//        String token = Constants.getConfigurator().getAsString("openservice.token." + appId, "123456");
//        String timestamp = request.getParameter("timestamp");
//        String nonce = request.getParameter("nonce");
//        Object invokeResult = null;
//
//        try {
//            boolean isValidRequest = serviceInvokeSecurityValidator.validateSignature(
//                    signature, appId, token, timestamp, nonce);
//            if (isValidRequest) {
//                invokeResult = serviceInvokeProxy.invoke(serviceInterface, serviceImpl, methodName, paramsJSONString,
//                        staticInvoke, request.getParameterMap());
//            } else {
//                invokeResult = "Signature is error.";
//            }
//
//            if (LOGGER.isInfoEnabled()) {
//                LOGGER.info(String.format("Do service invoke,serviceInterface:%s,serviceImpl:%s,methodName:%s,appId:%s," +
//                                "timestamp:%s,nonce:%s,signature:%s->%s.",
//                        serviceInterface, serviceImpl, methodName, appId, timestamp, nonce, signature,
//                        JSONUtils.toJSONString(invokeResult)));
//            }
//        } catch (Throwable e) {
//            invokeResult = "Fail to service invocation.";
//            LOGGER.error(String.format("Do service invoke,serviceInterface:%s,serviceImpl:%s,methodName:%s,appId:%s," +
//                            "timestamp:%s,nonce:%s,signature:%s->%s.",
//                    serviceInterface, serviceImpl, methodName, appId, timestamp, nonce, signature,
//                    JSONUtils.toJSONString(invokeResult)), e);
//        } finally {
//            try {
//                JSONUtils.write(response.getWriter(), invokeResult);
//            } catch (Throwable e) {
//                LOGGER.error("Fail to output service invocation result.", e);
//            }
//        }
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        this.doGet(request, response);
//    }
//}
//
//class ServiceInvokeProxyImpl extends DefaultServiceInvokeProxy {
//
//    @Override
//    public Object getInvokeObject(Class<?> serviceInterfaceClass, Map<String, Object> otherParameterMap) {
//        String serviceImplByName = (String) otherParameterMap.get("serviceImplByName");
//        if (StringUtils.isNotBlank(serviceImplByName)) {
//            return ApplicationContextUtils.getBean(serviceImplByName);
//        }
//        return ApplicationContextUtils.getBean(serviceInterfaceClass);
//    }
//}
//
