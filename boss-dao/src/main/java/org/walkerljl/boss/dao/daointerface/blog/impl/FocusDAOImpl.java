package org.walkerljl.boss.dao.daointerface.blog.impl;

import org.springframework.stereotype.Repository;
import org.walkerljl.boss.dao.daointerface.blog.FocusDAO;
import org.walkerljl.boss.dao.dataobject.blog.FocusDO;
import org.walkerljl.boss.support.dao.impl.BaseDAOImpl;

/**
 * FocusDaoImpl
 *
 * @author lijunlin
 */
@Repository("focusDAO")
public class FocusDAOImpl extends BaseDAOImpl<Long, FocusDO> implements FocusDAO {

}
