package org.walkerljl.boss.util;

import org.junit.Assert;
import org.junit.Test;
import org.walkerljl.boss.support.common.util.NumUtil;

/**
 * NumUtilTest
 *
 * @author xingxun
 * @Date 2018/5/19
 */
public class NumUtilTest {

    @Test
    public void intValue() {

        int actual = NumUtil.intValue(null);
        Assert.assertEquals(actual, 0);

        actual = NumUtil.intValue(new Integer(1));
        Assert.assertEquals(actual, 1);
    }

    @Test
    public void longValue() {

        long actual = NumUtil.longValue(null);
        Assert.assertEquals(actual, 0L);

        actual = NumUtil.intValue(new Long(1));
        Assert.assertEquals(actual, 1L);
    }
}
