package org.walkerljl.boss.util;

import org.junit.Assert;
import org.junit.Test;
import org.walkerljl.boss.support.common.util.ThrowableUtil;

/**
 * ThrowableUtilTest
 *
 * @author xingxun
 * @Date 2018/5/19
 */
public class ThrowableUtilTest {

    @Test
    public void getMessage() {

        String actual = ThrowableUtil.getMessage(null);
        Assert.assertEquals(actual, null);

        String expected = "errorMsg";
        Throwable throwable = new RuntimeException(expected);
        actual = ThrowableUtil.getMessage(throwable);
        Assert.assertEquals(actual, expected);
    }
}
