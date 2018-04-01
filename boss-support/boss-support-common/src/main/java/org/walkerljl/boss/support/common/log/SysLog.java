package org.walkerljl.boss.support.common.log;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统日志
 *
 * @author lijunlin
 */
public class SysLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 应用编码
     */
    private String appCode;
    /**
     * Trace id
     */
    private String traceId;
    /**
     * 关键字
     */
    private String keyword;
    /**
     * 请求
     */
    private String request;
    /**
     * 响应
     */
    private String response;
    /**
     * 操作者
     */
    private String operator;
    /**
     * 操作时间
     */
    private Date operated;

    /**
     * 默认构造函数
     */
    public SysLog() {
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getOperated() {
        return operated;
    }

    public void setOperated(Date operated) {
        this.operated = operated;
    }
}