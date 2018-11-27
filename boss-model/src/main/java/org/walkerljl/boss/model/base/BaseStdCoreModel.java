package org.walkerljl.boss.model.base;

import java.util.Date;
import java.util.Map;

import org.walkerljl.boss.dao.dataobject.base.StdBaseDO;
import org.walkerljl.boss.model.enums.base.StatusIEnum;

/**
 * 基础核心模型
 *
 * @author xingxun
 */
public class BaseStdCoreModel extends BaseCoreModel {

    private static final long serialVersionUID = -3631715832151460408L;

    /** ID*/
    private String              id;
    /** 状态*/
    private StatusIEnum         status;
    /** 备注*/
    private String              remark;
    /** 扩展信息*/
    private Map<String, String> extInfo;
    /** 创建者*/
    private String              creator;
    /** 创建时间*/
    private Date                createdTime;
    /** 最近修改者*/
    private String              modifier;
    /** 最近更新时间*/
    private Date                modifiedTime;

    /**
     * 初始化基础信息
     *
     * @param destModel 目标模型
     */
    public void initBaseDOInfo(StdBaseDO destModel) {
        if (destModel == null) {
            return;
        }
        destModel.setId(this.id == null ? 0L : Long.valueOf(this.id));
        destModel.setRemark(this.remark);
        destModel.setExtInfo(toJSONString(this.extInfo));
        destModel.setStatus(this.status == null ? null : this.status.getCode());
        destModel.setCreator(this.creator);
        destModel.setCreatedTime(this.createdTime);
        destModel.setModifier(this.modifier);
        destModel.setModifiedTime(this.modifiedTime);
    }

    /**
     * 初始化基础信息
     *
     * @param baseDO 基础数据模型
     * @param status 状态对象
     */
    public void initBaseInfo(StdBaseDO baseDO, StatusIEnum status) {
        if (baseDO == null) {
            return;
        }
        this.setId(String.valueOf(baseDO.getId()));
        this.setRemark(baseDO.getRemark());
        this.setExtInfo((Map)parseObject(baseDO.getExtInfo()));
        this.setStatus(status);
        this.setCreator(baseDO.getCreator());
        this.setCreatedTime(baseDO.getCreatedTime());
        this.setModifier(baseDO.getModifier());
        this.setModifiedTime(baseDO.getModifiedTime());
    }

    /**
     * Getter method for property <tt>id</tt>.
     *
     * @return property value of id
     */
    public String getId() {
        return id;
    }

    /**
     * Setter method for property <tt>id</tt>.
     *
     * @param id  value to be assigned to property id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter method for property <tt>status</tt>.
     *
     * @return property value of status
     */
    public StatusIEnum getStatus() {
        return status;
    }

    /**
     * Setter method for property <tt>status</tt>.
     *
     * @param status  value to be assigned to property status
     */
    public void setStatus(StatusIEnum status) {
        this.status = status;
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
    public Map<String, String> getExtInfo() {
        return extInfo;
    }

    /**
     * Setter method for property <tt>extInfo</tt>.
     *
     * @param extInfo  value to be assigned to property extInfo
     */
    public void setExtInfo(Map<String, String> extInfo) {
        this.extInfo = extInfo;
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
     * Getter method for property <tt>createdTime</tt>.
     *
     * @return property value of createdTime
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * Setter method for property <tt>createdTime</tt>.
     *
     * @param createdTime  value to be assigned to property createdTime
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
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
     * Getter method for property <tt>modifiedTime</tt>.
     *
     * @return property value of modifiedTime
     */
    public Date getModifiedTime() {
        return modifiedTime;
    }

    /**
     * Setter method for property <tt>modifiedTime</tt>.
     *
     * @param modifiedTime  value to be assigned to property modifiedTime
     */
    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    @Override
    public String toString() {
        return toJSONString(this);
    }
}