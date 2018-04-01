package org.walkerljl.boss.dao.daointerface.sys.impl;

import org.springframework.stereotype.Component;
import org.walkerljl.boss.dao.daointerface.sys.AppConfigDAO;
import org.walkerljl.boss.dao.dataobject.sys.AppConfigDO;
import org.walkerljl.boss.support.dao.impl.BaseDAOImpl;

/**
 * AppConfigDaoImpl
 *
 * @author lijunlin
 */
@Component("appConfigDAO")
public class AppConfigDAOImpl extends BaseDAOImpl<Long, AppConfigDO> implements AppConfigDAO {

    public AppConfigDAOImpl() {
        super.baseNameSpace = "org.walkerljl.boss.dao.sys.ConfigDAO";
    }

    public int updateStatusByKey2(String key) {
        return update(getNameSpace("updateStatusByKey"), key);
    }
}
