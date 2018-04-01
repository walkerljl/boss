package org.walkerljl.boss.service.sso;

import org.walkerljl.boss.dao.dataobject.sso.UserDO;
import org.walkerljl.boss.model.sso.LoginCommand;
import org.walkerljl.boss.support.sdk.Message;
import org.walkerljl.boss.support.service.JqueryDatatableBaseService;

/**
 * UserService
 *
 * @author lijunlin
 */
public interface UserService extends JqueryDatatableBaseService<Long, UserDO> {

    /**
     * 登录验证
     *
     * @param command
     * @return
     */
    Message<Object> login(LoginCommand command);

    /**
     * 通过登录账号获取用户信息
     *
     * @param userId
     * @return
     */
    UserDO getUserByUserId(String userId);

    /**
     * 注册
     *
     * @param user
     * @return
     */
    Message<Object> register(UserDO user);

    /**
     * 确认注册
     *
     * @param userId
     * @return
     */
    Message<Object> confirmRegister(String userId);

    /**
     * 修改密码
     *
     * @param user
     * @return
     */
    Message<Object> updatePassword(UserDO user);

    /**
     * 重置密码
     *
     * @param user
     * @return
     */
    Message<Object> resetPassword(UserDO user);

    /**
     * 登录账号是否存在
     *
     * @param userId
     * @return
     */
    boolean userIdIsExists(String userId);

    /**
     * 登录名是否存在
     *
     * @param userName
     * @return
     */
    boolean userNameIsExists(String userName);

    /**
     * 邮箱是否存在
     *
     * @param email
     * @return
     */
    boolean emailIsExists(String email);

    /**
     * 手机是否存在
     *
     * @param mobile
     * @return
     */
    boolean mobileIsExists(String mobile);
}