package org.walkerljl.boss.sdk.auth.support;

import java.util.List;
import java.util.Set;

import org.walkerljl.boss.sdk.auth.annotation.Authentication;
import org.walkerljl.boss.sdk.auth.enums.AuthCondition;
import org.walkerljl.boss.sdk.auth.enums.AuthType;

/**
 * 权限验支撑类
 *
 * @author xingxun
 */
public interface AuthenticationSupport {

    /**
     * 获取鉴权条件
     *
     * @param clazzAuthentication
     * @param methodAuthentication
     * @return
     */
    AuthCondition getAuthCondition(Authentication clazzAuthentication, Authentication methodAuthentication);

    /**
     * 获取授权类型
     *
     * @param clazzAuthentication
     * @param methodAuthentication
     * @return
     */
    AuthType getType(Authentication clazzAuthentication, Authentication methodAuthentication);

    /**
     * 获取授权码列表
     *
     * @param clazzAuthentication
     * @param methodAuthentication
     * @return
     */
    String[] getAuthCodes(Authentication clazzAuthentication, Authentication methodAuthentication);

    /**
     * 鉴权
     *
     * @param authCodes 需要被鉴权的权限码列表
     * @param userAuthCodes 用户权限码列表
     * @param authCondition 鉴权条件
     * @return
     */
    boolean authenticate(List<String> authCodes, Set<String> userAuthCodes,
                         AuthCondition authCondition);

    /**
     * 获取拒绝访问提示消息
     *
     * @param clazzAuthentication
     * @param methodAuthentication
     * @return
     */
    String getPermissionDeniedMessage(Authentication clazzAuthentication, Authentication methodAuthentication);
}