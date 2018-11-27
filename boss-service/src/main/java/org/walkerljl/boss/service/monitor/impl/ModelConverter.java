package org.walkerljl.boss.service.monitor.impl;

import java.util.ArrayList;
import java.util.List;

import org.walkerljl.boss.common.util.CollectionUtil;
import org.walkerljl.boss.dao.dataobject.monitor.AlarmRecordDO;
import org.walkerljl.boss.dao.dataobject.monitor.AlarmRuleDO;
import org.walkerljl.boss.dao.dataobject.monitor.MonitorDataDO;
import org.walkerljl.boss.dao.dataobject.monitor.MonitorObjMetaDataDO;
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
import org.walkerljl.boss.service.monitor.model.enums.status.MonitorDataStatusEnum;
import org.walkerljl.boss.service.monitor.model.enums.status.MonitorObjMetaDataStatusEnum;

/**
 * 核心模型与数据访问模型转换器
 *
 * @author xingxun
 */
public class ModelConverter {

    /**
     * toAlarmRecordDO
     *
     * @param inputModel
     * @return
     */
    public static AlarmRecordDO toAlarmRecordDO(AlarmRecord inputModel) {
        if (inputModel == null) {
            return null;
        }

        AlarmRecordDO resultModel = new AlarmRecordDO();
        inputModel.initBaseDOInfo(resultModel);
        resultModel.setBizCode(inputModel.getBizCode());
        resultModel.setBizName(inputModel.getBizName());
        resultModel.setObjId(inputModel.getObjId());
        resultModel.setObjName(inputModel.getObjName());
        resultModel.setData(inputModel.getData());
        resultModel.setDataTime(inputModel.getDataTime());
        resultModel.setLevel(inputModel.getLevel() == null ? null : inputModel.getLevel().getCode());
        resultModel.setContent(inputModel.getContent());
        resultModel.setTime(inputModel.getTime());
        resultModel.setChannels(inputModel.toJSONString(inputModel.getChannels()));
        resultModel.setReceivers(inputModel.toJSONString(inputModel.getReceivers()));

        return resultModel;
    }

    /**
     * toAlarmRecord
     *
     * @param inputModel
     * @return
     */
    public static AlarmRecord toAlarmRecord(AlarmRecordDO inputModel) {
        if (inputModel == null) {
            return null;
        }

        AlarmRecord resultModel = new AlarmRecord();
        resultModel.initBaseInfo(inputModel, AlarmRecordStatusEnum.values()[0].getEnumObject(inputModel.getStatus()));
        resultModel.setBizCode(inputModel.getBizCode());
        resultModel.setBizName(inputModel.getBizName());
        resultModel.setObjId(inputModel.getObjId());
        resultModel.setObjName(inputModel.getObjName());
        resultModel.setData(inputModel.getData());
        resultModel.setDataTime(inputModel.getDataTime());
        resultModel.setLevel((AlarmLevelEnum)AlarmLevelEnum.values()[0].getEnumObject(inputModel.getLevel()));
        resultModel.setContent(inputModel.getContent());
        resultModel.setTime(inputModel.getTime());
        resultModel.setChannels(resultModel.parseList(inputModel.getChannels(), AlarmChannelEnum.class));
        resultModel.setReceivers(resultModel.parseList(inputModel.getReceivers(), String.class));

        return resultModel;
    }

    /**
     * toMonitorObjMetaData
     *
     * @param inputModel
     * @return
     */
    public static MonitorObjMetaData toMonitorObjMetaData(MonitorObjMetaDataDO inputModel) {
        if (inputModel == null) {
            return null;
        }
        MonitorObjMetaData resultModel = new MonitorObjMetaData();
        resultModel.initBaseInfo(inputModel, MonitorObjMetaDataStatusEnum.values()[0].getEnumObject(inputModel.getStatus()));
        resultModel.setBizCode(inputModel.getBizCode());
        resultModel.setBizName(inputModel.getBizName());
        resultModel.setObjId(inputModel.getObjId());
        resultModel.setObjName(inputModel.getObjName());
        resultModel.setAlarmReceivers(BaseMonitorModel.parseList(inputModel.getAlarmReceivers(), String.class));
        resultModel.setBizOwner(inputModel.getBizOwner());

        return resultModel;
    }

    /**
     * toAlarmRule
     *
     * @param inputModel
     * @return
     */
    public static AlarmRule toAlarmRule(AlarmRuleDO inputModel) {
        if (inputModel == null) {
            return null;
        }
        AlarmRule resultModel = new AlarmRule();
        resultModel.initBaseInfo(inputModel, AlarmRuleStatusEnum.values()[0].getEnumObject(inputModel.getStatus()));
        resultModel.setBizCode(inputModel.getBizCode());
        resultModel.setObjId(inputModel.getObjId());
        resultModel.setType((AlarmRuleTypeEnum) AlarmRuleTypeEnum.values()[0].getEnumObject(inputModel.getType()));
        resultModel.setExpression(inputModel.getExpression());

        List<String> channelCodes = BaseMonitorModel.parseList(inputModel.getChannels(), String.class);
        if (CollectionUtil.isEmpty(channelCodes)) {
            return resultModel;
        }
        List<AlarmChannelEnum> channels = new ArrayList<>(channelCodes.size());
        for (String channelCode : channelCodes) {
            AlarmChannelEnum channel = (AlarmChannelEnum) AlarmChannelEnum.values()[0].getEnumObject(channelCode);
            if (channel == null) {
                continue;
            }
            channels.add(channel);
        }
        resultModel.setChannels(channels);

        return resultModel;
    }

    /**
     * toAlarmRules
     *
     * @param inputModels
     * @return
     */
    public static List<AlarmRule> toAlarmRules(List<AlarmRuleDO> inputModels) {
        if (CollectionUtil.isEmpty(inputModels)) {
            return null;
        }
        List<AlarmRule> resultModels = new ArrayList<>(inputModels.size());
        for (AlarmRuleDO inputModel : inputModels) {
            AlarmRule resultModel = toAlarmRule(inputModel);
            if (resultModel == null) {
                continue;
            }
            resultModels.add(resultModel);
        }

        return resultModels;
    }

    /**
     * toMonitorData
     *
     * @param inputModel
     * @return
     */
    public static MonitorData toMonitorData(MonitorDataDO inputModel) {
        if (inputModel == null) {
            return null;
        }
        MonitorData resultModel = new MonitorData();
        resultModel.initBaseInfo(inputModel, MonitorDataStatusEnum.values()[0].getEnumObject(inputModel.getStatus()));
        resultModel.setBizCode(inputModel.getBizCode());
        resultModel.setObjId(inputModel.getObjId());
        resultModel.setValue(inputModel.getValue());
        resultModel.setTime(inputModel.getTime());

        return resultModel;
    }

    /**
     * toMonitorDatas
     *
     * @param inputModels
     * @return
     */
    public static List<MonitorData> toMonitorDatas(List<MonitorDataDO> inputModels) {
        if (CollectionUtil.isEmpty(inputModels)) {
            return null;
        }
        List<MonitorData> resultModels = new ArrayList<>(inputModels.size());
        for (MonitorDataDO inputModel : inputModels) {
            MonitorData resultModel = toMonitorData(inputModel);
            if (resultModel == null) {
                continue;
            }
            resultModels.add(resultModel);
        }

        return resultModels;
    }
}