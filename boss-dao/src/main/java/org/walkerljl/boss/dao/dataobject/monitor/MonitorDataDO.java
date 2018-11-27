package org.walkerljl.boss.dao.dataobject.monitor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.walkerljl.boss.dao.dataobject.monitor.base.BaseMonitorDO;

/**
 * 监控数据
 *
 * @author xingxun
 */
public class MonitorDataDO extends BaseMonitorDO {

    private static final long serialVersionUID = -2303712375447847250L;

    /** 接入业务编码*/
    private String     bizCode;
    /** 监控对象ID*/
    private String     objId;
    /** 监控数据*/
    private BigDecimal value;
    /** 产生时间*/
    private Date       time;

    /** 监控对象元数据*/
    private MonitorObjMetaDataDO monitorObjMetaData;
    /** 预警规则列表*/
    private List<AlarmRuleDO>    alarmRules;

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
     * Getter method for property <tt>value</tt>.
     *
     * @return property value of value
     */
    public BigDecimal getValue() {
        return value;
    }

    /**
     * Setter method for property <tt>value</tt>.
     *
     * @param value  value to be assigned to property value
     */
    public void setValue(BigDecimal value) {
        this.value = value;
    }

    /**
     * Getter method for property <tt>time</tt>.
     *
     * @return property value of time
     */
    public Date getTime() {
        return time;
    }

    /**
     * Setter method for property <tt>time</tt>.
     *
     * @param time  value to be assigned to property time
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * Getter method for property <tt>monitorObjMetaData</tt>.
     *
     * @return property value of monitorObjMetaData
     */
    public MonitorObjMetaDataDO getMonitorObjMetaData() {
        return monitorObjMetaData;
    }

    /**
     * Setter method for property <tt>monitorObjMetaData</tt>.
     *
     * @param monitorObjMetaData  value to be assigned to property monitorObjMetaData
     */
    public void setMonitorObjMetaData(MonitorObjMetaDataDO monitorObjMetaData) {
        this.monitorObjMetaData = monitorObjMetaData;
    }

    /**
     * Getter method for property <tt>alarmRules</tt>.
     *
     * @return property value of alarmRules
     */
    public List<AlarmRuleDO> getAlarmRules() {
        return alarmRules;
    }

    /**
     * Setter method for property <tt>alarmRules</tt>.
     *
     * @param alarmRules  value to be assigned to property alarmRules
     */
    public void setAlarmRules(List<AlarmRuleDO> alarmRules) {
        this.alarmRules = alarmRules;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}