package org.walkerljl.boss.service.identity;

import java.util.List;

import org.walkerljl.boss.dao.dataobject.identity.AppDO;
import org.walkerljl.boss.support.service.JqueryDatatableBaseService;

/**
 * 应用业务逻辑接口
 *
 * @author lijunlin
 */
public interface AppService extends JqueryDatatableBaseService<Long, AppDO> {

    /**
     * 获取用户可访问的应用
     *
     * @param userId
     * @return
     */
    List<AppDO> selectAccessibleApps(String userId);

    /**
     * 获取前100个应用
     *
     * @return
     */
    List<AppDO> select100Apps();
}
