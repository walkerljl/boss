package org.walkerljl.boss.support.mvc.auth;

import java.io.Serializable;
import java.util.List;

/**
 * 按钮条
 *
 * @author xingxun
 */
public interface ButtonBar extends Serializable {

    /**
     * 获取按钮列表
     *
     * @param userId
     * @param isAdmin
     * @param authCodePrefix
     * @return
     */
    List<Button> getButtons(String userId, boolean isAdmin, String authCodePrefix);
}
