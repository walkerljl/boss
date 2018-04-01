package org.walkerljl.boss.service.auth.res.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.walkerljl.boss.dao.daointerface.auth.res.ButtonDAO;
import org.walkerljl.boss.dao.dataobject.auth.res.ButtonDO;
import org.walkerljl.boss.service.auth.res.ButtonService;
import org.walkerljl.boss.support.dao.daointerface.BaseDAO;
import org.walkerljl.boss.support.service.impl.JqueryDatatableBaseServiceImpl;

/**
 * ButtonServiceImpl
 *
 * @author lijunlin
 */
@Service("buttonService")
public class ButtonServiceImpl extends JqueryDatatableBaseServiceImpl<Long, ButtonDO> implements ButtonService {

    @Resource
    private ButtonDAO buttonDAO;

    @Override
    public BaseDAO<Long, ButtonDO> getDAO() {
        return buttonDAO;
    }
}