package org.walkerljl.boss.support;


import org.walkerljl.toolkit.standard.model.paging.Paginator;

/**
 * 业务分页器
 *
 * @author xingxun
 */
public class BizPaginator<T> extends Paginator<T> {

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