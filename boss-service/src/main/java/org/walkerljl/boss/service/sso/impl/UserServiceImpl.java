package org.walkerljl.boss.service.sso.impl;

import java.util.Date;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.walkerljl.boss.dao.daointerface.sso.LoginInfoDAO;
import org.walkerljl.boss.dao.daointerface.sso.UserDAO;
import org.walkerljl.boss.dao.dataobject.sso.LoginInfoDO;
import org.walkerljl.boss.dao.dataobject.sso.UserDO;
import org.walkerljl.boss.model.sso.LoginCommand;
import org.walkerljl.boss.service.sso.UserService;
import org.walkerljl.boss.support.dao.daointerface.BaseDAO;
import org.walkerljl.boss.support.enums.StatusEnum;
import org.walkerljl.boss.support.sdk.Message;
import org.walkerljl.boss.support.service.impl.JqueryDatatableBaseServiceImpl;
import org.walkerljl.toolkit.lang.StringUtils;

/**
 * UserServiceImpl
 *
 * @author lijunlin
 */
@Service("userService")
public class UserServiceImpl extends JqueryDatatableBaseServiceImpl<Long, UserDO> implements UserService {

    @Resource
    private UserDAO      userDAO;
    @Resource
    private LoginInfoDAO loginInfoDAO;

    @Override
    public BaseDAO<Long, UserDO> getDAO() {
        return userDAO;
    }

    @Override
    public UserDO getUserByUserId(String userId) {
        UserDO condition = new UserDO();
        condition.setUserId(userId);
        return userDAO.selectOne(condition);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Message<Object> login(LoginCommand command) {
        if (command == null) {
            LOGGER.warn("登录命令为空");
            return Message.failure();
        }

        if (StringUtils.isBlank(command.getUserId())) {
            LOGGER.warn("用户ID为空");
            return Message.failure();
        }

        if (StringUtils.isBlank(command.getPassword())) {
            LOGGER.warn("登录密码为空");
            return Message.failure();
        }

        UserDO dbUser = getUserByUserId(command.getUserId());
        if (dbUser == null || !dbUser.isEnabled()) {
            LOGGER.warn(String.format("用户信息不存在, userId:%s", command.getUserId()));
            return Message.failure();
        }

        //随机盐+三次MD5加密
        String expectedPassword = getEncryptedPassword(command.getPassword(), dbUser.getSalt() + "");
        if (expectedPassword.equals(dbUser.getPassword())) {
            command.setUserName(dbUser.getUserName());
            //更新最新登录状态
            updateLastLoginStatus(dbUser.getId(), command);
            //记录登录信息
            addLoginInfo(command);
            return Message.success();
        }
        return Message.failure();
    }

    /**
     * 获取加密的密码
     *
     * @param inputPassword
     * @param salt
     * @return
     */
    private String getEncryptedPassword(String inputPassword, String salt) {
        //return EncryptUtils.encryptByMD5(
        //        EncryptUtils.encryptByMD5(
        //                EncryptUtils.encryptByMD5(inputPassword + salt)));
        return null;
    }

    /**
     * 更新最新登录状态
     *
     * @param key
     * @param command
     */
    private void updateLastLoginStatus(Long key, LoginCommand command) {
        UserDO userStatus = new UserDO();
        userStatus.setLastLoginTime(new Date());
        userStatus.setLastLoginIp(command.getLoginIp());
        userStatus.setLastLoginAgent(command.getLoginAgent().getValue());
        userDAO.updateByKey(userStatus, key);
    }

    /**
     * 添加登录信息
     *
     * @param command
     */
    private void addLoginInfo(LoginCommand command) {
        LoginInfoDO loginInfo = new LoginInfoDO();
        loginInfo.setUserId(command.getUserId());
        loginInfo.setUserName(command.getUserName());
        loginInfo.setLoginIp(command.getLoginIp());
        loginInfo.setLoginTime(new Date());
        loginInfo.setLogoutTime(loginInfo.getLoginTime());
        loginInfo.setLoginAgent(command.getLoginAgent().getValue());
        loginInfoDAO.insert(loginInfo);
    }

    @Override
    public boolean userIdIsExists(String userId) {
        return getUserByUserId(userId) != null;
    }

    @Override
    public boolean userNameIsExists(String userName) {
        UserDO condition = new UserDO();
        condition.setUserName(userName);
        return userDAO.selectOne(condition) != null;
    }

    @Override
    public boolean emailIsExists(String email) {
        UserDO condition = new UserDO();
        condition.setEmail(email);
        return userDAO.selectOne(condition) != null;
    }

    @Override
    public boolean mobileIsExists(String mobile) {
        UserDO condition = new UserDO();
        condition.setMobile(mobile);
        return userDAO.selectOne(condition) != null;
    }

    @Override
    public Message<Object> register(UserDO user) {
        if (StringUtils.isEmpty(user.getUserId())) {
            return Message.failure("用户ID为空");
        } else if (StringUtils.isEmpty(user.getUserName())) {
            return Message.failure("用户名为空");
        } else if (StringUtils.isEmpty(user.getPassword())) {
            return Message.failure("登录密码为空");
        } else if (userIdIsExists(user.getUserId())) {
            return Message.failure("用户ID已存在");
        } else if (userNameIsExists(user.getUserName())) {
            return Message.failure("用户名已存在");
        }
        if (StringUtils.isNotEmpty(user.getEmail()) && emailIsExists(user.getEmail())) {
            return Message.failure("邮箱已被账号绑定");
        }
        if (StringUtils.isNotEmpty(user.getMobile()) && mobileIsExists(user.getMobile())) {
            return Message.failure("手机已被账号绑定");
        }
        //生成随机盐
        int max = 99999999;
        int min = 10000000;
        Random random = new Random();
        int salt = random.nextInt(max) % (max - min + 1) + min;
        user.setSalt(salt + "");

        //设置密码
        String encryptedPassword = getEncryptedPassword(user.getPassword(), user.getSalt());
        user.setPassword(encryptedPassword);

        return Message.create(userDAO.insert(user) > 0);
    }

    @Override
    public Message<Object> confirmRegister(String userId) {
        UserDO user = getUserByUserId(userId);
        if (user == null) {
            return Message.failure("用户不存在");
        } else if (!user.isDisabled()) {
            return Message.failure("用户已经确认，不能重新确认");
        }
        user.setStatus(StatusEnum.ENABLED.getCode());
        user.setModifiedTime(new Date());
        return Message.create(userDAO.updateByKey(user, user.getId()) > 0);
    }

    @Override
    public Message<Object> updatePassword(UserDO user) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Message<Object> resetPassword(UserDO user) {
        // TODO Auto-generated method stub
        return null;
    }
}