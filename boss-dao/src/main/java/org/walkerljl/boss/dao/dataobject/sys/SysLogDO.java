package org.walkerljl.boss.dao.dataobject.sys;

import java.util.Date;

import org.walkerljl.boss.support.dao.dataobject.JqueryDatatableBaseDONoRule;
import org.walkerljl.toolkit.db.api.annotation.Column;
import org.walkerljl.toolkit.db.api.annotation.Entity;

/**
 * @author lijunlin
 */
@Entity("sys_log")
public class SysLogDO extends JqueryDatatableBaseDONoRule {

    private static final long serialVersionUID = -1046073078422376911L;

    /**
     * 编号
     */
    @Column(key = true, value = "id")
    private Long id;
    /**
     * 应用编码
     */
    @Column("app_code")
    private String appCode;
    /**
     * Trace id
     */
    @Column("trace_id")
    private String traceId;
    /**
     * 关键字
     */
    @Column("keyword")
    private String keyword;
    /**
     * 请求
     */
    @Column("request")
    private String request;
    /**
     * 响应
     */
    @Column("response")
    private String response;
    /**
     * 操作者
     */
    @Column("operator")
    private String operator;
    /**
     * 操作时间
     */
    @Column("operated")
    private Date operated;

    /**
     * 默认构造函数
     */
    public SysLogDO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
