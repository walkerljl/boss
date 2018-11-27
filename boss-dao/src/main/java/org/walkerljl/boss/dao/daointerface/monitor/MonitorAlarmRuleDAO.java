package org.walkerljl.boss.dao.daointerface.monitor;

import java.util.List;

import org.walkerljl.boss.dao.dataobject.monitor.AlarmRuleDO;

/**
 * 预警规则数据访问接口
 *
 * @author xingxun
 */
public interface MonitorAlarmRuleDAO {

    /**
     * 根据业务编码和监控对象ID查询预警规则
     *
     * @param bizCode 接入业务编码
     * @param objId 监控对象ID
     * @return
     */
    List<AlarmRuleDO> listByBizCodeAndObjId(String bizCode, String objId);
}