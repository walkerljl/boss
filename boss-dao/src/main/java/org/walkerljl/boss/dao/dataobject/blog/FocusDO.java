package org.walkerljl.boss.dao.dataobject.blog;

import org.walkerljl.boss.support.dao.dataobject.BaseDO;

/**
 * 关注
 *
 * @author lijunlin
 */
public class FocusDO extends BaseDO {

    private static final long serialVersionUID = 1L;

    /**
     * 被关注用户Id
     */
    private String userId;
    /**
     * 被关注用户姓名
     */
    private String userName;

    public FocusDO() {}

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}