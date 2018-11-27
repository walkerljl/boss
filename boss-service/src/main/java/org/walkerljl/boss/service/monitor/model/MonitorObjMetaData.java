package org.walkerljl.boss.service.monitor.model;

import java.util.List;

import org.walkerljl.boss.service.monitor.model.base.BaseStdMonitorModel;

/**
 * 监控对象元数据
 *
 * @author xingxun
 */
public class MonitorObjMetaData extends BaseStdMonitorModel {

    private static final long serialVersionUID = 854690573491903428L;

    /** 接入业务编码*/
    private String       bizCode;
    /** 接入业务名称*/
    private String       bizName;
    /** 对象ID*/
    private String       objId;
    /** 对象名称*/
    private String       objName;
    /** 预警对象列表*/
    private List<String> alarmReceivers;
    /** 业务负责人*/
    private String       bizOwner;

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
     * Getter method for property <tt>bizName</tt>.
     *
     * @return property value of bizName
     */
    public String getBizName() {
        return bizName;
    }

    /**
     * Setter method for property <tt>bizName</tt>.
     *
     * @param bizName  value to be assigned to property bizName
     */
    public void setBizName(String bizName) {
        this.bizName = bizName;
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
     * Getter method for property <tt>objName</tt>.
     *
     * @return property value of objName
     */
    public String getObjName() {
        return objName;
    }

    /**
     * Setter method for property <tt>objName</tt>.
     *
     * @param objName  value to be assigned to property objName
     */
    public void setObjName(String objName) {
        this.objName = objName;
    }

    /**
     * Getter method for property <tt>alarmReceivers</tt>.
     *
     * @return property value of alarmReceivers
     */
    public List<String> getAlarmReceivers() {
        return alarmReceivers;
    }

    /**
     * Setter method for property <tt>alarmReceivers</tt>.
     *
     * @param alarmReceivers  value to be assigned to property alarmReceivers
     */
    public void setAlarmReceivers(List<String> alarmReceivers) {
        this.alarmReceivers = alarmReceivers;
    }

    /**
     * Getter method for property <tt>bizOwner</tt>.
     *
     * @return property value of bizOwner
     */
    public String getBizOwner() {
        return bizOwner;
    }

    /**
     * Setter method for property <tt>bizOwner</tt>.
     *
     * @param bizOwner  value to be assigned to property bizOwner
     */
    public void setBizOwner(String bizOwner) {
        this.bizOwner = bizOwner;
    }
}