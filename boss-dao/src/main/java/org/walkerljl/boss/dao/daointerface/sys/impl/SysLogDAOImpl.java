package org.walkerljl.boss.dao.daointerface.sys.impl;

import org.springframework.stereotype.Component;
import org.walkerljl.boss.dao.daointerface.sys.SysLogDAO;
import org.walkerljl.boss.dao.dataobject.sys.SysLogDO;
import org.walkerljl.boss.support.dao.impl.BaseDAOImpl;

/**
 * SysLogDaoImpl
 *
 * @author lijunlin
 */
@Component("sysLogDAO")
public class SysLogDAOImpl extends BaseDAOImpl<Long, SysLogDO> implements SysLogDAO {

    public SysLogDAOImpl() {
        super.baseNameSpace = "SysLogDAO";
    }

}
