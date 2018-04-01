package org.walkerljl.boss.support;

import java.io.Serializable;
import java.util.Date;

import org.walkerljl.boss.support.enums.StatusEnum;

/**
 * BaseModel
 *
 * @author xingxun
 */
public class BaseModel implements Serializable {

    /**
     * 编号
     */
    private Long id;

    /**
     * 唯一编号
     */
    private transient String uuid;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态
     */
    private transient String status;

    /**
     * 创建者
     */
    private String creator;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 修改者
     */
    private String modifier;

    /**
     * 修改时间
     */
    private Date modifiedTime;

    /**
     * 默认构造函数
     */
    public BaseModel() {}

    //================ getter and setter

    /**
     * 获取编号
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置编号
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取唯一编号
     *
     * @return
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * 设置唯一编号
     *
     * @param uuid
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * 获取备注
     *
     * @return
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取状态值
     *
     * @return
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * 设置状态值
     *
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取创建者
     *
     * @return
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 设置创建者
     *
     * @param creator
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * 获取创建时间
     *
     * @return
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * 设置创建日期
     *
     * @param createdTime
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * 获取修改者
     *
     * @return
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * 设置修改者
     *
     * @param modifier
     */
    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    /**
     * 获取修改时间
     *
     * @return
     */
    public Date getModifiedTimed() {
        return modifiedTime;
    }

    /**
     * 设置修改时间
     *
     * @param modifiedTime
     */
    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    //============== 自定义方法

    /**
     * 是否启用
     *
     * @return
     */
    public boolean isEnabled() {
        return getStatusType() == StatusEnum.ENABLED;
    }

    /**
     * 是否停用
     *
     * @return
     */
    public boolean isDisabled() {
        return getStatusType() == StatusEnum.DISABLED;
    }

    /**
     * 是否删除
     *
     * @return
     */
    public boolean isDeleted() {
        return getStatusType() == StatusEnum.DELETED;
    }

    /**
     * 获取状态类型
     *
     * @return
     */
    public StatusEnum getStatusType() {
        return StatusEnum.getByCode((status == null) ? "" : status);
    }

    /**
     * 设置状态类型
     *
     * @param status
     */
    public void setStatusType(StatusEnum status) {
        this.status = status.getCode();
    }

    /**
     * 获取状态名称
     *
     * @return
     */
    public String getStatusName() {
        StatusEnum status = getStatusType();
        return status == null ? "未知状态" : status.getDescription();
    }

    /**
     * 初始化基础字段值
     *
     * @param creator 创建者
     */
    public void initBaseFieldValues(String creator) {
        setStatus(StatusEnum.ENABLED.getCode());
        setCreatedTime(new Date());
        setModifiedTime(getCreatedTime());
        setCreator(creator);
        setModifier(getCreator());
    }
}