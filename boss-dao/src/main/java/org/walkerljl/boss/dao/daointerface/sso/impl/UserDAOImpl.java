package org.walkerljl.boss.dao.daointerface.sso.impl;

import org.springframework.stereotype.Repository;
import org.walkerljl.boss.dao.daointerface.sso.UserDAO;
import org.walkerljl.boss.dao.dataobject.sso.UserDO;
import org.walkerljl.boss.support.dao.impl.BaseDAOImpl;

/**
 * UserDaoImpl
 *
 * @author lijunlin
 */
@Repository("userDAO")
public class UserDAOImpl extends BaseDAOImpl<Long, UserDO> implements UserDAO {

    public UserDAOImpl() {
        super.baseNameSpace = "org.walkerljl.boss.sso.dao.UserDAO";
    }

    @Override
    public UserDO selectByKey(Long key) {
        return super.selectByKey(key);
    }
}