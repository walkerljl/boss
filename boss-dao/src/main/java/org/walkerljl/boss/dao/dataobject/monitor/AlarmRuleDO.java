package org.walkerljl.boss.dao.dataobject.monitor;

import org.walkerljl.boss.dao.dataobject.monitor.base.BaseMonitorDO;

/**
 * 预警规则
 *
 * @author xingxun
 */
public class AlarmRuleDO extends BaseMonitorDO {

    private static final long serialVersionUID = 3018390311962096358L;

    /** 接入业务编码*/
    private String bizCode;
    /** 监控对象ID*/
    private String objId;
    /** 类型*/
    private String type;
    /** 规则表达式*/
    private String expression;
    /** 预警渠道列表*/
    private String channels;

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
     * Getter method for property <tt>objId</tt>.
     *
     * @return property value of objId
     */
    public String getObjId() {
        return objId;
    }

    /**
     * Setter method for property <tt>objId</tt>.
     *
     * @param objId  value to be assigned to property objId
     */
    public void setObjId(String objId) {
        this.objId = objId;
    }

    /**
     * Getter method for property <tt>type</tt>.
     *
     * @return property value of type
     */
    public String getType() {
        return type;
    }

    /**
     * Setter method for property <tt>type</tt>.
     *
     * @param type  value to be assigned to property type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Getter method for property <tt>expression</tt>.
     *
     * @return property value of expression
     */
    public String getExpression() {
        return expression;
    }

    /**
     * Setter method for property <tt>expression</tt>.
     *
     * @param expression  value to be assigned to property expression
     */
    public void setExpression(String expression) {
        this.expression = expression;
    }

    /**
     * Getter method for property <tt>channels</tt>.
     *
     * @return property value of channels
     */
    public String getChannels() {
        return channels;
    }

    /**
     * Setter method for property <tt>channels</tt>.
     *
     * @param channels  value to be assigned to property channels
     */
    public void setChannels(String channels) {
        this.channels = channels;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}