package org.walkerljl.boss.dao.dataobject.task;

import org.walkerljl.boss.dao.dataobject.task.base.BaseTaskDO;

/**
 * 任务执行日志
 *
 * @author xingxun
 */
public class TaskLogDO extends BaseTaskDO {

    private static final long serialVersionUID = -5026448492122383200L;

    /** 业务编码*/
    private String  bizCode;
    /** 业务ID*/
    private String  bizId;
    /** 任务ID*/
    private String  taskId;
    /** 当前重试次数*/
    private Integer attempts;
    /** 描述*/
    private String  description;

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
     * Getter method for property <tt>description</tt>.
     *
     * @return property value of description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter method for property <tt>description</tt>.
     *
     * @param description  value to be assigned to property description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}