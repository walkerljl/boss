package org.walkerljl.boss.service.monitor.analyze.alarm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.walkerljl.boss.common.util.CollectionUtil;
import org.walkerljl.boss.common.util.DateUtil;
import org.walkerljl.boss.service.monitor.MonitorService;
import org.walkerljl.boss.service.monitor.analyze.AnalyzeConfig;
import org.walkerljl.boss.service.monitor.analyze.AnalyzeContext;
import org.walkerljl.boss.service.monitor.analyze.Analyzer;
import org.walkerljl.boss.service.monitor.analyze.abstracts.AbstractAnalyzer;
import org.walkerljl.boss.service.monitor.analyze.alarm.impl.DefaultAlarmer;
import org.walkerljl.boss.service.monitor.analyze.handler.alarm.AlarmAnalyzeHandler;
import org.walkerljl.boss.service.monitor.analyze.handler.alarm.impl.AlarmAnalyzeHandlerRepositoryFactory;
import org.walkerljl.boss.service.monitor.model.MonitorData;
import org.walkerljl.boss.service.monitor.model.MonitorObjMetaData;
import org.walkerljl.boss.service.monitor.model.alarm.AlarmRecord;
import org.walkerljl.boss.service.monitor.model.alarm.AlarmRule;
import org.walkerljl.boss.service.monitor.model.alarm.enums.AlarmRuleTypeEnum;
import org.walkerljl.boss.service.task.TaskBroker;
import org.walkerljl.boss.service.task.impl.TaskExecutionContext;
import org.walkerljl.boss.service.task.impl.util.TaskBuilder;
import org.walkerljl.boss.service.task.model.Task;
import org.walkerljl.boss.service.task.model.enums.TaskPriorityEnum;

/**
 * AlarmAnalyzer
 *
 * @author xingxun
 */
public class AlarmAnalyzer extends AbstractAnalyzer implements Analyzer {

    private static final String BIZ_ID_SEPARATOR = "@@";
    private static final String TASK_CONTEXT_KEY_ALARM_RECORDS = "task.monitor.alarm.records";

    private MonitorService monitorService;
    private AnalyzeConfig  config;
    private TaskBroker taskBroker;

    public AlarmAnalyzer(MonitorService monitorService, AnalyzeConfig config, TaskBroker taskBroker) {
        this.monitorService = monitorService;
        this.config = config;
        this.taskBroker = taskBroker;
    }

    @Override
    public void handle0(TaskExecutionContext context) {
        Task task = (Task) context.getAttribute(TaskExecutionContext.TASK);
        if (task == null) {
            return;
        }
        String[] bizIdItems = task.getBizId().split(BIZ_ID_SEPARATOR);
        String bizId = bizIdItems[0];

        Date prodcutTime = DateUtil.dataFormat2Date(bizIdItems[1], DateUtil.FORMAT_YYYY_MM_DD);
        //组装
        MonitorData monitorData = monitorService.getMonitorData(task.getBizCode(), bizId, prodcutTime);
        if (monitorData == null) {
            return;
        }

        //查询监控对象元数据
        MonitorObjMetaData monitorObjMetaData = monitorService.getMonitorObjMetaData(monitorData.getBizCode(),
                monitorData.getObjId());
        if (monitorObjMetaData == null) {
            return;
        }
        monitorData.setMonitorObjMetaData(monitorObjMetaData);

        //查询预警规则
        List<AlarmRule> alarmRules = monitorService.listAlarmRules(monitorData.getBizCode(), monitorData.getObjId());
        if (CollectionUtil.isEmpty(alarmRules)) {
            return;
        }
        monitorData.setAlarmRules(alarmRules);

        AnalyzeContext analyzeContext = new AnalyzeContext();
        analyzeContext.setAttribute(AnalyzeContext.CONFIG, config);
        analyzeContext.setAttribute(AnalyzeContext.MONITOR_DATA, monitorData);
        analyzeContext.setAttribute(AnalyzeContext.TASK_CONTEXT, context);

        analyze(analyzeContext);
    }

    @Override
    public void analyze(AnalyzeContext context) {
        MonitorData monitorData = (MonitorData) context.getAttribute(AnalyzeContext.MONITOR_DATA);

        //预警分析
        List<AlarmRecord> alarmRecords = analyze0(monitorData, monitorData.getAlarmRules(), context);
        TaskExecutionContext taskContext = (TaskExecutionContext)context.getAttribute(AnalyzeContext.TASK_CONTEXT);
        if (taskContext != null) {
            taskContext.setAttribute(TASK_CONTEXT_KEY_ALARM_RECORDS, alarmRecords);
        }
    }

    private List<AlarmRecord> analyze0(MonitorData monitorData, List<AlarmRule> alarmRules, AnalyzeContext context) {
        //预警分析
        List<AlarmRecord> alarmRecords = new ArrayList<>(alarmRules.size());
        for (AlarmRule alarmRule : alarmRules) {
            if (alarmRule == null) {
                continue;
            }
            AlarmRuleTypeEnum alarmRuleType = alarmRule.getType();
            if (alarmRuleType == null) {
                continue;
            }
            AlarmAnalyzeHandler alarmAnalyzeHandler = AlarmAnalyzeHandlerRepositoryFactory.getDefaultRepository()
                    .lookup(alarmRuleType.getCode());
            if (alarmAnalyzeHandler == null) {
                continue;
            }
            AlarmRecord alarmRecord = alarmAnalyzeHandler.handle(monitorData, alarmRule, context);
            if (alarmRecord != null) {
                alarmRecords.add(alarmRecord);
            }
        }

        return alarmRecords;
    }

    @Override
    public void handleInTransactionAfterRun(TaskExecutionContext context) {
        List<AlarmRecord> alarmRecords = (List<AlarmRecord>)context.getAttribute(TASK_CONTEXT_KEY_ALARM_RECORDS);
        if (CollectionUtil.isEmpty(alarmRecords)) {
            return;
        }
        for (AlarmRecord alarmRecord : alarmRecords) {
            if (alarmRecord == null) {
                continue;
            }
            //添加预警记录流水
            monitorService.saveAlarmRecord(alarmRecord);

            //提交任务
            Task task = TaskBuilder.buildTask(alarmRecord.buildTaskBizCode(),
                    alarmRecord.buildIdentityId(),
                    DefaultAlarmer.class.getSimpleName(),
                    TaskPriorityEnum.HIGH,
                    alarmRecord.getCreator());
            taskBroker.submit(task);

        }
    }
}