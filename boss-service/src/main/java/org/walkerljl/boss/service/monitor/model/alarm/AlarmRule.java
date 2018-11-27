package org.walkerljl.boss.service.monitor.model.alarm;

import java.util.List;

import org.walkerljl.boss.service.monitor.model.alarm.enums.AlarmChannelEnum;
import org.walkerljl.boss.service.monitor.model.alarm.enums.AlarmRuleTypeEnum;
import org.walkerljl.boss.service.monitor.model.base.BaseStdMonitorModel;

/**
 * 预警规则
 *
 * @author xingxun
 */
public class AlarmRule extends BaseStdMonitorModel {

    private static final long serialVersionUID = 3018390311962096358L;

    /** 接入业务编码*/
    private String                 bizCode;
    /** 监控对象ID*/
    private String                 objId;
    /** 类型*/
    private AlarmRuleTypeEnum      type;
    /** 规则表达式*/
    private String                 expression;
    /** 预警渠道列表*/
    private List<AlarmChannelEnum> channels;

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
    public AlarmRuleTypeEnum getType() {
        return type;
    }

    /**
     * Setter method for property <tt>type</tt>.
     *
     * @param type  value to be assigned to property type
     */
    public void setType(AlarmRuleTypeEnum type) {
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
    public List<AlarmChannelEnum> getChannels() {
        return channels;
    }

    /**
     * Setter method for property <tt>channels</tt>.
     *
     * @param channels  value to be assigned to property channels
     */
    public void setChannels(List<AlarmChannelEnum> channels) {
        this.channels = channels;
    }
}