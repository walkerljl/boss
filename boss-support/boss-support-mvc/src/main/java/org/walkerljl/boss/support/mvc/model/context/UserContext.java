package org.walkerljl.boss.support.mvc.model.context;

import java.util.Set;

import org.walkerljl.boss.support.UserInfo;
import org.walkerljl.toolkit.standard.model.BaseEntity;

/**
 * 用户上下文
 *
 * @author xingxun
 */
public class UserContext extends BaseEntity {

    /**
     * 用户信息
     */
    private UserInfo    user;
    /**
     * 用户权限码列表
     */
    private Set<String> authCodes;

    /**
     * 默认构造函数
     */
    public UserContext() {
    }

    /**
     * 构造函数
     *
     * @param user 用户信息
     */
    public UserContext(UserInfo user) {
        this(user, null);
    }

    /**
     * 构造函数
     *
     * @param user      用户信息
     * @param authCodes 用户权限码列表
     */
    public UserContext(UserInfo user, Set<String> authCodes) {
        this.user = user;
        this.authCodes = authCodes;
    }

    /**
     * Getter method for property <tt>user</tt>.
     *
     * @return property value of user
     */
    public UserInfo getUser() {
        return user;
    }

    /**
     * Setter method for property <tt>user</tt>.
     *
     * @param user value to be assigned to property user
     */
    public void setUser(UserInfo user) {
        this.user = user;
    }

    /**
     * Getter method for property <tt>authCodes</tt>.
     *
     * @return property value of authCodes
     */
    public Set<String> getAuthCodes() {
        return authCodes;
    }

    /**
     * Setter method for property <tt>authCodes</tt>.
     *
     * @param authCodes value to be assigned to property authCodes
     */
    public void setAuthCodes(Set<String> authCodes) {
        this.authCodes = authCodes;
    }
}