
package org.walkerljl.boss.common.model.dal;

import java.util.Date;

import org.walkerljl.toolkit.db.api.annotation.Column;

/**
 * BaseStdDO
 *
 * @author xingxun
 */
public class BaseStdDO extends BaseDO {

    private static final long serialVersionUID = -6660163293501373937L;

    /** ID*/
    @Column(value = "id", key = true)
    private Long              id;
    /** 备注（可选，不超过512个字符）*/
    @Column("remark")
    private String              remark;
    /** 扩展信息（可选，不超过2048个字符）**/
    @Column("extInfo")
    private String extInfo;
    /** 状态（必选，不超过2个字符）**/
    @Column("status")
    private String              status;
    /** 创建者（必选，不超过64个字符）**/
    @Column("creator")
    private String              creator;
    /** 创建时间（必选，不超过512个字符）**/
    @Column("gmtCreate")
    private Date                gmtCreate;
    /** 最近修改者（必选，不超过64个字符）**/
    @Column("modifier")
    private String              modifier;
    /** 最近更新时间（必选）**/
    @Column("gmtModified")
    private Date                gmtModified;

    public <T extends BaseStdDO> T init(String operatorId, String statusCode) {
        this.setStatus(statusCode);
        this.setCreator(operatorId);
        this.setGmtCreate(new Date());
        this.setModifier(this.getCreator());
        this.setGmtModified(this.getGmtCreate());
        return (T) this;
    }

    /**
     * Getter method for property <tt>id</tt>.
     *
     * @return property value of id
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter method for property <tt>id</tt>.
     *
     * @param id  value to be assigned to property id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter method for property <tt>remark</tt>.
     *
     * @return property value of remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * Setter method for property <tt>remark</tt>.
     *
     * @param remark  value to be assigned to property remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * Getter method for property <tt>extInfo</tt>.
     *
     * @return property value of extInfo
     */
    public String getExtInfo() {
        return extInfo;
    }

    /**
     * Setter method for property <tt>extInfo</tt>.
     *
     * @param extInfo  value to be assigned to property extInfo
     */
    public void setExtInfo(String extInfo) {
        this.extInfo = extInfo;
    }

    /**
     * Getter method for property <tt>status</tt>.
     *
     * @return property value of status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Setter method for property <tt>status</tt>.
     *
     * @param status  value to be assigned to property status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Getter method for property <tt>creator</tt>.
     *
     * @return property value of creator
     */
    public String getCreator() {
        return creator;
    }

    /**
     * Setter method for property <tt>creator</tt>.
     *
     * @param creator  value to be assigned to property creator
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * Getter method for property <tt>gmtCreate</tt>.
     *
     * @return property value of gmtCreate
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * Setter method for property <tt>gmtCreate</tt>.
     *
     * @param gmtCreate  value to be assigned to property gmtCreate
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * Getter method for property <tt>modifier</tt>.
     *
     * @return property value of modifier
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * Setter method for property <tt>modifier</tt>.
     *
     * @param modifier  value to be assigned to property modifier
     */
    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    /**
     * Getter method for property <tt>gmtModified</tt>.
     *
     * @return property value of gmtModified
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * Setter method for property <tt>gmtModified</tt>.
     *
     * @param gmtModified  value to be assigned to property gmtModified
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}