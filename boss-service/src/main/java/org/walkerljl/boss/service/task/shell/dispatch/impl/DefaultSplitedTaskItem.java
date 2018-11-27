package org.walkerljl.boss.service.task.shell.dispatch.impl;

import org.walkerljl.boss.common.util.ArrayUtil;
import org.walkerljl.boss.service.task.shell.dispatch.SplitedTaskItem;
import org.walkerljl.boss.support.common.util.StringUtil;

/**
 * 监控被拆分的任务项
 *
 * @author xingxun
 */
public class DefaultSplitedTaskItem implements SplitedTaskItem {

    private static final long serialVersionUID = -6199290569594511251L;

    /** 身份项目分隔符*/
    private static final String IDENTITY_ITEM_SEPARATOR = ",";
    /** 身份项目长度*/
    private static final int    IDENTITY_ITEM_LEGNTH    = 4;

    /** 业务编码*/
    private String bizCode;
    /** 业务ID*/
    private String bizId;
    /** 任务ID*/
    private String taskId;
    /** 任务状态*/
    private String taskStatus;

    public DefaultSplitedTaskItem() {}

    public DefaultSplitedTaskItem(String bizCode, String bizId, String taskId, String taskStatus) {
        this.bizCode = bizCode;
        this.bizId = bizId;
        this.taskId = taskId;
        this.taskStatus = taskStatus;
    }

    /**
     * 解析
     *
     * @param serializedObject
     * @return
     */
    public static DefaultSplitedTaskItem parse(String serializedObject) {
        if (StringUtil.isEmpty(serializedObject)) {
            return null;
        }
        String[] serializedObjectItems = serializedObject.split(IDENTITY_ITEM_SEPARATOR);
        if (ArrayUtil.length(serializedObjectItems) != IDENTITY_ITEM_LEGNTH) {
            return null;
        }
        for (String item : serializedObjectItems) {
            if (StringUtil.isEmpty(item)) {
                return null;
            }
        }
        return new DefaultSplitedTaskItem(serializedObjectItems[0], serializedObjectItems[1], serializedObjectItems[2],
                serializedObjectItems[3]);
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
     * Getter method for property <tt>taskId</tt>.
     *
     * @return property value of taskId
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * Setter method for property <tt>taskId</tt>.
     *
     * @param taskId  value to be assigned to property taskId
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    /**
     * Getter method for property <tt>taskStatus</tt>.
     *
     * @return property value of taskStatus
     */
    public String getTaskStatus() {
        return taskStatus;
    }

    /**
     * Setter method for property <tt>taskStatus</tt>.
     *
     * @param taskStatus  value to be assigned to property taskStatus
     */
    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    @Override
    public String toString() {
        return String.format("%s%s%s%s%s%s%s",
                bizCode,
                IDENTITY_ITEM_SEPARATOR,
                bizId,
                IDENTITY_ITEM_SEPARATOR,
                taskId,
                IDENTITY_ITEM_SEPARATOR,
                taskStatus);
    }
}