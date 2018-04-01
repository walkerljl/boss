package org.walkerljl.boss.dao.daointerface.sso.impl;

import org.junit.Test;
import org.walkerljl.boss.dao.BaseUnitTest;
import org.walkerljl.boss.dao.daointerface.sso.UserDAO;
import org.walkerljl.toolkit.lang.ReflectUtils;

/**
 * UserDaoImplTest
 *
 * @author lijunlin
 */
public class UserDaoImplTest extends BaseUnitTest {

    @Test
    public void test() {
        UserDAO userDao = new UserDAOImpl();

        try {
            LOGGER.debug(UserDAOImpl.class.getSimpleName());
            ReflectUtils.invokeDeclared(userDao, "selectByKey", new Class<?>[]{Long.class}, new Object[]{1L});
        } catch (Throwable e) {
            LOGGER.error(e);
        }
    }
}
