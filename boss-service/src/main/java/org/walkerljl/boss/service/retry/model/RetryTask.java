package org.walkerljl.boss.service.retry.model;

import java.util.Date;
import java.util.List;

import org.walkerljl.boss.service.retry.model.base.StdBaseRetryModel;
import org.walkerljl.boss.service.retry.model.enums.RetryPriorityEnum;

/**
 * 重试任务
 *
 * @author xingxun
 */
public class RetryTask extends StdBaseRetryModel {

    private static final long serialVersionUID = -6017516180911424002L;

    /** 业务编码*/
    private String            bizCode;
    /** 业务ID*/
    private String            bizId;
    /** 重试优先级*/
    private RetryPriorityEnum priority;
    /** 重试次数*/
    private Integer           attempts;
    /** 最大重试次数*/
    private Integer           maxAttempts;
    /** 重试规则*/
    private String            retryRule;
    /** 处理器ID*/
    private String            handlerId;
    /** 参数列表*/
    private List<RetryParam>  params;
    /** 上次重试时间*/
    private Date              lastRetryTime;
    /** 下次重试时间*/
    private Date              nextRetryTime;

    /**
     * Constructor
     */
    public RetryTask() {}

    /**
     * Getter method for property <tt>bizCode</tt>.
     *
     * @return property value of bizCode
     */
    public String getBizCode() {
        return bizCode;
    }

    /**
     * Setter method for property <tt>bizCode</tt>.
     *
     * @param bizCode  value to be assigned to property bizCode
     */
    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

    /**
     * Getter method for property <tt>bizId</tt>.
     *
     * @return property value of bizId
     */
    public String getBizId() {
        return bizId;
    }

    /**
     * Setter method for property <tt>bizId</tt>.
     *
     * @param bizId  value to be assigned to property bizId
     */
    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    /**
     * Getter method for property <tt>priority</tt>.
     *
     * @return property value of priority
     */
    public RetryPriorityEnum getPriority() {
        return priority;
    }

    /**
     * Setter method for property <tt>priority</tt>.
     *
     * @param priority  value to be assigned to property priority
     */
    public void setPriority(RetryPriorityEnum priority) {
        this.priority = priority;
    }

    /**
     * Getter method for property <tt>attempts</tt>.
     *
     * @return property value of attempts
     */
    public Integer getAttempts() {
        return attempts;
    }

    /**
     * Setter method for property <tt>attempts</tt>.
     *
     * @param attempts  value to be assigned to property attempts
     */
    public void setAttempts(Integer attempts) {
        this.attempts = attempts;
    }

    /**
     * Getter method for property <tt>maxAttempts</tt>.
     *
     * @return property value of maxAttempts
     */
    public Integer getMaxAttempts() {
        return maxAttempts;
    }

    /**
     * Setter method for property <tt>maxAttempts</tt>.
     *
     * @param maxAttempts  value to be assigned to property maxAttempts
     */
    public void setMaxAttempts(Integer maxAttempts) {
        this.maxAttempts = maxAttempts;
    }

    /**
     * Getter method for property <tt>retryRule</tt>.
     *
     * @return property value of retryRule
     */
    public String getRetryRule() {
        return retryRule;
    }

    /**
     * Setter method for property <tt>retryRule</tt>.
     *
     * @param retryRule  value to be assigned to property retryRule
     */
    public void setRetryRule(String retryRule) {
        this.retryRule = retryRule;
    }

    /**
     * Getter method for property <tt>handlerId</tt>.
     *
     * @return property value of handlerId
     */
    public String getHandlerId() {
        return handlerId;
    }

    /**
     * Setter method for property <tt>handlerId</tt>.
     *
     * @param handlerId  value to be assigned to property handlerId
     */
    public void setHandlerId(String handlerId) {
        this.handlerId = handlerId;
    }

    /**
     * Getter method for property <tt>params</tt>.
     *
     * @return property value of params
     */
    public List<RetryParam> getParams() {
        return params;
    }

    /**
     * Setter method for property <tt>params</tt>.
     *
     * @param params  value to be assigned to property params
     */
    public void setParams(List<RetryParam> params) {
        this.params = params;
    }

    /**
     * Getter method for property <tt>lastRetryTime</tt>.
     *
     * @return property value of lastRetryTime
     */
    public Date getLastRetryTime() {
        return lastRetryTime;
    }

    /**
     * Setter method for property <tt>lastRetryTime</tt>.
     *
     * @param lastRetryTime  value to be assigned to property lastRetryTime
     */
    public void setLastRetryTime(Date lastRetryTime) {
        this.lastRetryTime = lastRetryTime;
    }

    /**
     * Getter method for property <tt>nextRetryTime</tt>.
     *
     * @return property value of nextRetryTime
     */
    public Date getNextRetryTime() {
        return nextRetryTime;
    }

    /**
     * Setter method for property <tt>nextRetryTime</tt>.
     *
     * @param nextRetryTime  value to be assigned to property nextRetryTime
     */
    public void setNextRetryTime(Date nextRetryTime) {
        this.nextRetryTime = nextRetryTime;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}