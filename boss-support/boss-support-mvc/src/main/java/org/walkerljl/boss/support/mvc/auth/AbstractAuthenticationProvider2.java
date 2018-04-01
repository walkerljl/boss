package org.walkerljl.boss.support.mvc.auth;//package org.walkerljl.smart.mvc;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Random;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
///**
// * 抽象的远程服务提供者
// *
// * @author lijunlin
// */
//public class AbstractAuthenticationProvider2 {
//
//    private static final String MESSAGE_PREFIX = "调用UIM鉴权开放服务鉴权";
//    protected final Logger LOGGER = LoggerFactory.getLogger(getClass());
//    private int connectTimeout = 3000;
//    private int readTimeout = 3000;
//    /**
//     * 鉴权开放服务地址
//     */
//    private String serviceAddress;
//    /**
//     * 鉴权开放服务版本
//     */
//    private String serviceVersion;
//    /**
//     * appId
//     */
//    private String appId;
//    /**
//     * token
//     */
//    private String token;
//    /**
//     * 系统资源码
//     */
//    private String systemResCode;
//
//    /**
//     * 执行鉴权请求
//     *
//     * @param params
//     * @return
//     */
//    protected String executeRequest(Map<String, Object> params) {
//        if (params == null) {
//            params = new HashMap<String, Object>();
//        }
//        HttpUtils httpClient = new HttpUtils(serviceAddress);
//        //超时设置
//        httpClient.setConnectTimeout(this.connectTimeout);
//        httpClient.setReadTimeout(this.readTimeout);
//
//        //设置appKey
//        params.put("app_key", appId);
//        //设置版本号
//        params.put("v", serviceVersion);
//        //设置返回数据类型
//        params.put("format", "json");
//        //设置时间戳
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String timestamp = simpleDateFormat.format(new Date());
//        params.put("timestamp", timestamp);
//        //设置随机数
//        String random = new Random().nextInt(1000000000) + "";
//        params.put("random", random);
//        //生成签名
//        String sign = JopApiDigest.getInstance().generate(appId, token, timestamp, random);
//        params.put("sign", sign);//设置参数签名
//
//        //设置请求参数
//        params.put("systemResCode", systemResCode);
//
//        StringBuilder debugInfos = new StringBuilder();
//        for (Map.Entry<String, Object> entry : params.entrySet()) {
//            httpClient.addParameter(entry.getKey(), entry.getValue());
//            if (LOGGER.isDebugEnabled()) {
//                debugInfos.append(entry.getKey()).append("=").append(entry.getValue()).append(", ");
//            }
//        }
//        //debug 请求参数
//        if (LOGGER.isDebugEnabled()) {
//            LOGGER.debug("{}, Request: serviceAddress={}{}",
//                    new Object[]{MESSAGE_PREFIX, serviceAddress, (debugInfos.length() != 0 ? ", " + debugInfos.toString() : "")});
//        }
//
//        //执行
//        String response = null;
//        try {
//            response = httpClient.execute();
//            //debug原始响应数据
//            if (LOGGER.isDebugEnabled()) {
//                LOGGER.debug(MESSAGE_PREFIX + ", Response:" + response);
//            }
//        } catch (Throwable e) {
//            String errMsg = MESSAGE_PREFIX + ", Response" + e.getMessage();
//            LOGGER.error(errMsg, e);
//            throw new RuntimeException(errMsg, e);
//        }
//        return response;
//    }
//
//
//    /**
//     * 设置连接超时时间
//     *
//     * @param connectTimeout
//     */
//    public void setConnectTimeout(int connectTimeout) {
//        this.connectTimeout = connectTimeout;
//    }
//
//    /**
//     * 设置读取超时时间
//     *
//     * @param readTimeout
//     */
//    public void setReadTimeout(int readTimeout) {
//        this.readTimeout = readTimeout;
//    }
//
//    /**
//     * 设置服务地址
//     *
//     * @param serviceAddress
//     */
//    public void setServiceAddress(String serviceAddress) {
//        this.serviceAddress = serviceAddress;
//    }
//
//    /**
//     * 设置服务版本
//     *
//     * @param serviceVersion
//     */
//    public void setServiceVersion(String serviceVersion) {
//        this.serviceVersion = serviceVersion;
//    }
//
//    /**
//     * 设置分配给业务系统的Id
//     *
//     * @param appId
//     */
//    public void setAppId(String appId) {
//        this.appId = appId;
//    }
//
//    /**
//     * 设置分配给业务系统的Token
//     *
//     * @param token
//     */
//    public void setToken(String token) {
//        this.token = token;
//    }
//
//    /**
//     * 设置获取权限的系统资源码
//     *
//     * @param systemResCode
//     */
//    public void setSystemResCode(String systemResCode) {
//        this.systemResCode = systemResCode;
//    }
//}