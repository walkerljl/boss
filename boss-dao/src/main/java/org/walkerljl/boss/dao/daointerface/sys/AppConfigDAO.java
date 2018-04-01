package org.walkerljl.boss.dao.daointerface.sys;

import org.walkerljl.boss.dao.dataobject.sys.AppConfigDO;
import org.walkerljl.boss.support.dao.daointerface.BaseDAO;

/**
 * 配置信息数据访问接口
 *
 * @author lijunlin
 */
public interface AppConfigDAO extends BaseDAO<Long, AppConfigDO> {

    int updateStatusByKey2(String key);
}
