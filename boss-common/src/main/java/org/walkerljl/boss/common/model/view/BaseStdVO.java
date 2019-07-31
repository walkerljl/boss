package org.walkerljl.boss.common.model.view;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.walkerljl.boss.common.model.core.enums.StatusIEnum;
import org.walkerljl.boss.common.util.CollectionUtil;

/**
 * 基础标准VO
 *
 * @author xingxun
 */
public class BaseStdVO extends BaseVO {

    private static final long   serialVersionUID = 8397639739144921250L;

    /** ID*/
    private String              id;
    /** 备注（可选，不超过512个字符）*/
    private String              remark;
    /** 扩展信息（可选，不超过2048个字符）**/
    private Map<String, String> extInfo;
    /** 状态（必选，不超过2个字符）**/
    private String              status;
    /** 创建者（必选，不超过64个字符）**/
    private String              creator;
    /** 创建时间（必选，不超过512个字符）**/
    private Date                createdTime;
    /** 最近修改者（必选，不超过64个字符）**/
    private String              modifier;
    /** 最近更新时间（必选）**/
    private Date                modifiedTime;

    public <T extends BaseStdVO> T init(String operatorId, StatusIEnum status) {
        this.setStatus(status.getCode());
        this.setCreator(operatorId);
        this.setCreatedTime(new Date());
        this.setModifier(this.getCreator());
        this.setModifiedTime(this.getCreatedTime());
        return (T) this;
    }

    public static <T extends BaseStdVO >void initBaseInfo(List<T> list, String operatorId, StatusIEnum status) {
        if (CollectionUtil.isEmpty(list)) {
            return;
        }
        for (T item : list) {
            if (item == null) {
                return;
            }
            item.init(operatorId, status);
        }
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
        return super.toString();
    }
}