package org.walkerljl.boss.dao.daointerface.auth.impl;

import org.springframework.stereotype.Repository;
import org.walkerljl.boss.dao.daointerface.auth.DeptDAO;
import org.walkerljl.boss.dao.dataobject.auth.DeptDO;
import org.walkerljl.boss.support.dao.impl.BaseDAOImpl;

/**
 * DeptDaoImpl
 *
 * @author lijunlin
 */
@Repository("deptDAO")
public class DeptDAOImpl extends BaseDAOImpl<Long, DeptDO> implements DeptDAO {

}
