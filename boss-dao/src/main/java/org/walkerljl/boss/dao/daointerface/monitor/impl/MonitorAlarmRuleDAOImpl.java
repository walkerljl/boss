package org.walkerljl.boss.dao.daointerface.monitor.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.walkerljl.boss.dao.daointerface.monitor.MonitorAlarmRuleDAO;
import org.walkerljl.boss.dao.dataobject.monitor.AlarmRuleDO;
import org.walkerljl.boss.support.dao.impl.BaseDAOImpl;

/**
 * MonitorAlarmRuleDAOImpl
 *
 * @author xingxun
 */
public class MonitorAlarmRuleDAOImpl extends BaseDAOImpl<Long, AlarmRuleDO> implements MonitorAlarmRuleDAO {

    public MonitorAlarmRuleDAOImpl() {
        super.baseNameSpace = "org.walkerljl.boss.dao.daointerface.monitor.MonitorAlarmRuleDAO";
    }

    @Override
    public List<AlarmRuleDO> listByBizCodeAndObjId(String bizCode, String objId) {
        Map<String, Object> condition = new HashMap<>(2);
        condition.put("bizCode", bizCode);
        condition.put("objId", objId);

        return selectList(getNameSpace("listByBizCodeAndObjId"), condition);
    }
}