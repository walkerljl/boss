package org.walkerljl.boss.service.monitor.model.alarm;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.walkerljl.boss.common.util.ArrayUtil;
import org.walkerljl.boss.common.util.StringUtil;
import org.walkerljl.boss.service.monitor.model.alarm.enums.AlarmChannelEnum;
import org.walkerljl.boss.service.monitor.model.alarm.enums.AlarmLevelEnum;
import org.walkerljl.boss.service.monitor.model.base.BaseStdMonitorModel;

/**
 * 预警记录
 *
 * @author xingxun
 */
public class AlarmRecord extends BaseStdMonitorModel {

    private static final long serialVersionUID = -1543995044946872977L;

    private static final String IDENTITY_ID_SEPARATOR = "@@";
    private static final int IDENTITY_ID_ITEMS_LENGTH = 3;
    private static final String TASK_CODE_PREFIX = "alarm";

    /** 接入业务编码*/
    private String                 bizCode;
    /** 接入业务名称*/
    private String                 bizName;
    /** 监控对象ID*/
    private String                 objId;
    /** 监控对象名称*/
    private String                 objName;
    /** 预警数据*/
    private BigDecimal             data;
    /** 预警数据产生时间*/
    private Date                   dataTime;
    /** 内容*/
    private String                 content;
    /** 预警时间*/
    private Date                   time;
    /** 预警等级*/
    private AlarmLevelEnum         level;
    /** 预警渠道列表*/
    private List<AlarmChannelEnum> channels;
    /** 预警对象列表*/
    private List<String>           receivers;

    /**
     * 构建任务业务编码
     *
     * @return
     */
    public String buildTaskBizCode() {
        return String.format("%s%s%s", bizCode, IDENTITY_ID_SEPARATOR, TASK_CODE_PREFIX);
    }

    /**
     * 构建身份ID
     *
     * @return
     */
    public String buildIdentityId() {
        return String.format("%s%s%s%s%s", bizCode, IDENTITY_ID_SEPARATOR, objId, IDENTITY_ID_SEPARATOR, getId());
    }

    /**
     * 解析身份ID
     *
     * @param identityId
     * @return
     */
    public static AlarmRecord parseIdentityId(String identityId) {
        String[] identityIdItems = StringUtil.split(identityId, IDENTITY_ID_SEPARATOR);
        if (ArrayUtil.length(identityIdItems) != IDENTITY_ID_ITEMS_LENGTH) {
            return null;
        }
        if (StringUtil.isEmpty(identityIdItems[0])) {
            return null;
        }
        if (StringUtil.isEmpty(identityIdItems[1])) {
            return null;
        }
        if (StringUtil.isEmpty(identityIdItems[2])) {
            return null;
        }
        AlarmRecord alarmRecord = new AlarmRecord();
        alarmRecord.setBizCode(identityIdItems[0]);
        alarmRecord.setObjId(identityIdItems[1]);
        alarmRecord.setId(identityIdItems[2]);
        return alarmRecord;
    }

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
     * Getter method for property <tt>data</tt>.
     *
     * @return property value of data
     */
    public BigDecimal getData() {
        return data;
    }

    /**
     * Setter method for property <tt>data</tt>.
     *
     * @param data  value to be assigned to property data
     */
    public void setData(BigDecimal data) {
        this.data = data;
    }

    /**
     * Getter method for property <tt>dataTime</tt>.
     *
     * @return property value of dataTime
     */
    public Date getDataTime() {
        return dataTime;
    }

    /**
     * Setter method for property <tt>dataTime</tt>.
     *
     * @param dataTime  value to be assigned to property dataTime
     */
    public void setDataTime(Date dataTime) {
        this.dataTime = dataTime;
    }

    /**
     * Getter method for property <tt>content</tt>.
     *
     * @return property value of content
     */
    public String getContent() {
        return content;
    }

    /**
     * Setter method for property <tt>content</tt>.
     *
     * @param content  value to be assigned to property content
     */
    public void setContent(String content) {
        this.content = content;
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
     * Getter method for property <tt>level</tt>.
     *
     * @return property value of level
     */
    public AlarmLevelEnum getLevel() {
        return level;
    }

    /**
     * Setter method for property <tt>level</tt>.
     *
     * @param level  value to be assigned to property level
     */
    public void setLevel(AlarmLevelEnum level) {
        this.level = level;
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

    /**
     * Getter method for property <tt>receivers</tt>.
     *
     * @return property value of receivers
     */
    public List<String> getReceivers() {
        return receivers;
    }

    /**
     * Setter method for property <tt>receivers</tt>.
     *
     * @param receivers  value to be assigned to property receivers
     */
    public void setReceivers(List<String> receivers) {
        this.receivers = receivers;
    }
}