package org.walkerljl.boss.support;

import org.walkerljl.boss.support.enums.UserTypeEnum;

/**
 * 用户信息
 *
 * @author xingxun
 */
public class UserInfo extends BaseEntity {

    private static final long serialVersionUID = -6488451179137743120L;

    /** ID*/
    private String       id;
    /** 名称*/
    private String       name;
    /** 类型*/
    private UserTypeEnum type;

    /**
     * 默认构造函数
     */
    public UserInfo() {}

    /**
     * 构造函数
     *
     * @param id ID
     * @param name 名称
     * @param type 类型
     */
    public UserInfo(String id, String name, UserTypeEnum type) {
        this.id = id;
        this.name = name;
        this.type = type;
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
     * Getter method for property <tt>name</tt>.
     *
     * @return property value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for property <tt>name</tt>.
     *
     * @param name  value to be assigned to property name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter method for property <tt>type</tt>.
     *
     * @return property value of type
     */
    public UserTypeEnum getType() {
        return type;
    }

    /**
     * Setter method for property <tt>type</tt>.
     *
     * @param type  value to be assigned to property type
     */
    public void setType(UserTypeEnum type) {
        this.type = type;
    }
}