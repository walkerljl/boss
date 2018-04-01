package org.walkerljl.boss.sdk.auth.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.walkerljl.boss.sdk.auth.enums.AuthCondition;
import org.walkerljl.boss.sdk.auth.enums.AuthType;

/**
 * 权限注解
 *
 * @author xingxun
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface Authentication {

    /**
     * 权限验证类型,默认为登录验证
     *
     * @return
     */
    AuthType type() default AuthType.LOGIN;

    /**
     * 权限码列表
     *
     * @return
     */
    String[] codes() default "";

    /**
     * 描述
     *
     * @return
     */
    String description() default "";

    /**
     * 多个权限之间的鉴权关系
     *
     * <ul>
     *     <li>AND表示所有权限校验通过才通过,OR表示只需要一个权限校验通过就通过</li>
     *     <li>默认为OR</li>
     * </ul>
     * @return
     */
    AuthCondition condition() default AuthCondition.OR;
}