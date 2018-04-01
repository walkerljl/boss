package org.walkerljl.boss.support.dao.dataobject;

import java.io.Serializable;
import java.util.Date;

import org.walkerljl.toolkit.db.api.annotation.Column;

/**
 * BaseDO
 *
 * @author lijunlin
 */
public class BaseDO implements Serializable {

    private static final long serialVersionUID = 8709480122725265761L;

    /**
     * 编号
     */
    @Column(key = true, value = "id")
    private Long id;

    /**
     * 唯一编号
     */
    private transient String uuid;

    /**
     * 备注
     */
    @Column("remark")
    private String remark;

    /**
     * 状态
     */
    @Column("status")
    private transient String status;

    /**
     * 创建者
     */
    @Column("creator")
    private String creator;

    /**
     * 创建时间
     */
    @Column("created_time")
    private Date createdTime;

    /**
     * 修改者
     */
    @Column("modifier")
    private String modifier;

    /**
     * 修改时间
     */
    @Column("modified_time")
    private Date modifiedTime;

    /**
     * 默认构造函数
     */
    public BaseDO() {}

    public boolean isEnabled() {
        return true;
    }

    public boolean isDisabled() {
        return false;
    }

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
    public Date getModifiedTime() {
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
}