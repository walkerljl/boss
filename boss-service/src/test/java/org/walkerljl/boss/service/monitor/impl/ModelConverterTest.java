package org.walkerljl.boss.service.monitor.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.walkerljl.boss.dao.dataobject.monitor.AlarmRecordDO;
import org.walkerljl.boss.dao.dataobject.monitor.AlarmRuleDO;
import org.walkerljl.boss.dao.dataobject.monitor.MonitorDataDO;
import org.walkerljl.boss.dao.dataobject.monitor.MonitorObjMetaDataDO;
import org.walkerljl.boss.dao.dataobject.monitor.base.BaseMonitorDO;
import org.walkerljl.boss.model.enums.base.StatusIEnum;
import org.walkerljl.boss.service.monitor.BaseMonitorUnitTest;
import org.walkerljl.boss.common.util.StringUtil;
import org.walkerljl.boss.service.monitor.model.MonitorData;
import org.walkerljl.boss.service.monitor.model.MonitorObjMetaData;
import org.walkerljl.boss.service.monitor.model.alarm.AlarmRecord;
import org.walkerljl.boss.service.monitor.model.alarm.AlarmRule;
import org.walkerljl.boss.service.monitor.model.alarm.enums.AlarmChannelEnum;
import org.walkerljl.boss.service.monitor.model.alarm.enums.AlarmLevelEnum;
import org.walkerljl.boss.service.monitor.model.alarm.enums.status.AlarmRecordStatusEnum;
import org.walkerljl.boss.service.monitor.model.alarm.enums.status.AlarmRuleStatusEnum;
import org.walkerljl.boss.service.monitor.model.alarm.enums.AlarmRuleTypeEnum;
import org.walkerljl.boss.service.monitor.model.base.BaseMonitorModel;
import org.walkerljl.boss.service.monitor.model.base.BaseStdMonitorModel;
import org.walkerljl.boss.service.monitor.model.enums.status.MonitorDataStatusEnum;
import org.walkerljl.boss.service.monitor.model.enums.status.MonitorObjMetaDataStatusEnum;

/**
 * ModelConverterTest
 *
 * @author xingxun
 */
public class ModelConverterTest extends BaseMonitorUnitTest {

    private void fillBaseInfo(BaseStdMonitorModel expected, StatusIEnum status) {
        expected.setId(String.valueOf(1L));
        expected.setRemark("remark");
        Map<String, String> extInfo = new HashMap<>(2);
        extInfo.put("key1", "value1");
        extInfo.put("key2", "value2");
        expected.setExtInfo(extInfo);
        expected.setStatus(status);
        expected.setCreator("xingxun");
        expected.setCreatedTime(new Date());
        expected.setModifier(expected.getCreator());
        expected.setModifiedTime(expected.getCreatedTime());
    }

    private void fillBaseInfo(BaseMonitorDO expected, String status) {
        expected.setId(1L);
        expected.setRemark("remark");
        Map<String, String> extInfo = new HashMap<>(2);
        extInfo.put("key1", "value1");
        extInfo.put("key2", "value2");
        expected.setExtInfo(BaseMonitorModel.toJSONString(extInfo));
        expected.setStatus(status);
        expected.setCreator("xingxun");
        expected.setCreatedTime(new Date());
        expected.setModifier(expected.getCreator());
        expected.setModifiedTime(expected.getCreatedTime());
    }

    @Test
    public void toAlarmRecordDO() {
        AlarmRecord expected = null;
        AlarmRecordDO actual = ModelConverter.toAlarmRecordDO(expected);
        Assert.assertNull(actual);

        expected = new AlarmRecord();
        expected.setId("id");
        expected.setBizCode("bizCode");
        expected.setBizName("bizName");
        expected.setObjId("objId");
        expected.setObjName("objName");
        expected.setData(new BigDecimal(10));
        expected.setDataTime(new Date());
        expected.setLevel(null);
        expected.setContent("content");
        expected.setTime(new Date());
        expected.setChannels(Arrays.asList(AlarmChannelEnum.LOG));
        expected.setReceivers(Arrays.asList("xingxun", "walkerljl"));
        fillBaseInfo(expected, AlarmRecordStatusEnum.UNALARM);

        actual = ModelConverter.toAlarmRecordDO(expected);
        asserts(expected, actual);

        expected.setLevel(AlarmLevelEnum.GREEN);
        actual = ModelConverter.toAlarmRecordDO(expected);
        asserts(expected, actual);
    }

    private void asserts(AlarmRecord expected, AlarmRecordDO actual) {
        Assert.assertEquals(expected.getBizCode(), actual.getBizCode());
        Assert.assertEquals(expected.getBizName(), actual.getBizName());
        Assert.assertEquals(expected.getObjId(), actual.getObjId());
        Assert.assertEquals(expected.getObjName(), actual.getObjName());
        Assert.assertEquals(expected.getData(), actual.getData());
        Assert.assertEquals(expected.getDataTime(), actual.getDataTime());
        if (expected.getLevel() != null) {
            Assert.assertEquals(expected.getLevel().getCode(), actual.getLevel());
        }
        Assert.assertEquals(expected.getContent(), actual.getContent());
        Assert.assertEquals(expected.getTime(), actual.getTime());
        Assert.assertEquals(BaseMonitorModel.toJSONString(expected.getChannels()), actual.getChannels());
        Assert.assertEquals(BaseMonitorModel.toJSONString(expected.getReceivers()), actual.getReceivers());

        assertBaseInfo(expected, actual);
    }

    private void assertBaseInfo(BaseStdMonitorModel expected, BaseMonitorDO actual) {
        Assert.assertEquals(expected.getId(), String.valueOf(actual.getId()));
        Assert.assertEquals(expected.getRemark(), actual.getRemark());
        Assert.assertEquals(BaseMonitorModel.toJSONString(expected.getExtInfo()), actual.getExtInfo());
        Assert.assertEquals(expected.getStatus().getCode(), actual.getStatus());
        Assert.assertEquals(expected.getCreator(), actual.getCreator());
        Assert.assertEquals(expected.getCreatedTime(), actual.getCreatedTime());
        Assert.assertEquals(expected.getModifier(), actual.getModifier());
        Assert.assertEquals(expected.getModifiedTime(), actual.getModifiedTime());
    }

    private void assertBaseInfo(BaseMonitorDO expected, BaseStdMonitorModel actual) {
        Assert.assertEquals(String.valueOf(expected.getId()), actual.getId());
        Assert.assertEquals(expected.getRemark(), actual.getRemark());
        Assert.assertEquals(expected.getExtInfo(), BaseMonitorModel.toJSONString(actual.getExtInfo()));
        Assert.assertEquals(expected.getStatus(), actual.getStatus().getCode());
        Assert.assertEquals(expected.getCreator(), actual.getCreator());
        Assert.assertEquals(expected.getCreatedTime(), actual.getCreatedTime());
        Assert.assertEquals(expected.getModifier(), actual.getModifier());
        Assert.assertEquals(expected.getModifiedTime(), actual.getModifiedTime());
    }

    @Test
    public void toMonitorObjMetaData() {

        MonitorObjMetaDataDO expected = null;
        MonitorObjMetaData actual = ModelConverter.toMonitorObjMetaData(expected);
        Assert.assertNull(actual);

        expected = new MonitorObjMetaDataDO();
        expected.setBizCode("bizCode");
        expected.setBizName("bizName");
        expected.setObjId("objId");
        expected.setObjName("objName");
        expected.setAlarmReceivers(BaseMonitorModel.toJSONString(Arrays.asList("xingxun")));
        expected.setBizOwner("bizOwner");
        fillBaseInfo(expected, MonitorObjMetaDataStatusEnum.NORMAL.getCode());

        actual = ModelConverter.toMonitorObjMetaData(expected);
        asserts(expected, actual);
    }

    private void asserts(MonitorObjMetaDataDO expected, MonitorObjMetaData actual) {
        Assert.assertEquals(expected.getBizCode(), actual.getBizCode());
        Assert.assertEquals(expected.getBizName(), actual.getBizName());
        Assert.assertEquals(expected.getObjId(), actual.getObjId());
        Assert.assertEquals(expected.getObjName(), actual.getObjName());
        Assert.assertEquals(BaseMonitorModel.parseList(expected.getAlarmReceivers(), String.class), actual.getAlarmReceivers());
        Assert.assertEquals(expected.getBizOwner(), actual.getBizOwner());

        assertBaseInfo(expected, actual);
    }

    @Test
    public void toMonitorData() {

        MonitorDataDO expected = null;
        MonitorData actual = ModelConverter.toMonitorData(expected);
        Assert.assertNull(actual);

        expected = newMonitorDataDO(1);

        actual = ModelConverter.toMonitorData(expected);
        asserts(expected, actual);
    }

    private MonitorDataDO newMonitorDataDO(int index) {
        MonitorDataDO expected = new MonitorDataDO();
        expected.setBizCode("bizCode" + index);
        expected.setObjId("objId" + index);
        expected.setValue(new BigDecimal(100 + index));
        expected.setTime(new Date());
        fillBaseInfo(expected, MonitorDataStatusEnum.NORMAL.getCode());
        return expected;
    }

    private void asserts(MonitorDataDO expected, MonitorData actual) {
        Assert.assertEquals(expected.getBizCode(), actual.getBizCode());
        Assert.assertEquals(expected.getObjId(), actual.getObjId());
        Assert.assertEquals(expected.getValue(), actual.getValue());
        Assert.assertEquals(expected.getTime(), actual.getTime());

        assertBaseInfo(expected, actual);
    }

    @Test
    public void toMonitorDatas() {

        List<MonitorDataDO> expecteds = null;
        List<MonitorData> actuals = ModelConverter.toMonitorDatas(expecteds);
        Assert.assertNull(actuals);

        expecteds = new ArrayList<>(3);
        expecteds.add(newMonitorDataDO(1));
        expecteds.add(newMonitorDataDO(2));
        expecteds.add(null);

        actuals = ModelConverter.toMonitorDatas(expecteds);
        asserts(expecteds.get(0), actuals.get(0));
        asserts(expecteds.get(1), actuals.get(1));
    }

    private AlarmRuleDO newAlarmRule(int index, String type) {
        AlarmRuleDO expected = new AlarmRuleDO();
        expected.setBizCode("bizCode" +  index);
        expected.setObjId("objId" + index);
        expected.setType(type);
        expected.setExpression("expression" + index);
        fillBaseInfo(expected, AlarmRuleStatusEnum.NORMAL.getCode());
        return expected;
    }

    @Test
    public void toAlarmRule() {

        AlarmRuleDO expected = null;
        AlarmRule actual = ModelConverter.toAlarmRule(expected);
        Assert.assertNull(actual);

        expected = newAlarmRule(1, AlarmRuleTypeEnum.MULTI_DAYS_CHANGE_EXCEED_PERCENT.getCode());
        actual = ModelConverter.toAlarmRule(expected);
        asserts(expected, actual);

        expected.setChannels(BaseMonitorModel.toJSONString(Arrays.asList(AlarmChannelEnum.LOG.getCode())));
        actual = ModelConverter.toAlarmRule(expected);
        asserts(expected, actual);

        expected.setChannels(BaseMonitorModel.toJSONString(Arrays.asList(AlarmChannelEnum.LOG.getCode(), null)));
        actual = ModelConverter.toAlarmRule(expected);
        asserts(expected, actual);
    }

    private void asserts(AlarmRuleDO expected, AlarmRule actual) {
        Assert.assertEquals(expected.getBizCode(), actual.getBizCode());
        Assert.assertEquals(expected.getObjId(), actual.getObjId());
        Assert.assertEquals(expected.getType(), actual.getType().getCode());
        Assert.assertEquals(expected.getExpression(), actual.getExpression());

        assertBaseInfo(expected, actual);

        if (StringUtil.isEmpty(expected.getChannels())) {
            return;
        }
        List<AlarmChannelEnum> channels = new ArrayList<>();
        for (String channelCode : BaseMonitorModel.parseList(expected.getChannels(), String.class)) {
            AlarmChannelEnum channel = (AlarmChannelEnum)AlarmChannelEnum.values()[0].getEnumObject(channelCode);
            if (channel != null) {
                channels.add(channel);
            }
        }
        Assert.assertEquals(channels, actual.getChannels());
    }

    @Test
    public void toAlarmRules() {

        List<AlarmRuleDO> expecteds = null;
        List<AlarmRule> actuals = ModelConverter.toAlarmRules(expecteds);
        Assert.assertNull(actuals);

        expecteds = new ArrayList<>(3);
        expecteds.add(newAlarmRule(1, AlarmRuleTypeEnum.MULTI_DAYS_CHANGE_EXCEED_PERCENT.getCode()));
        expecteds.add(newAlarmRule(2, AlarmRuleTypeEnum.SINGLE_DAY_MAX_IN_N_DAYS.getCode()));
        expecteds.add(null);
        actuals = ModelConverter.toAlarmRules(expecteds);
        asserts(expecteds.get(0), actuals.get(0));
        asserts(expecteds.get(1), actuals.get(1));
    }
}