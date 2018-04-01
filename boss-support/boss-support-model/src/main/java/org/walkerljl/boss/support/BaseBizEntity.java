package org.walkerljl.boss.support;

/**
 * 基础业务实体
 *
 * @author xingxun
 */
public class BaseBizEntity extends BaseEntity {

    /** 操作者*/
    private UserInfo operator;

    /**
     * Getter method for property <tt>operator</tt>.
     *
     * @return property value of operator
     */
    public UserInfo getOperator() {
        return operator;
    }

    /**
     * Setter method for property <tt>operator</tt>.
     *
     * @param operator  value to be assigned to property operator
     */
    public void setOperator(UserInfo operator) {
        this.operator = operator;
    }
}