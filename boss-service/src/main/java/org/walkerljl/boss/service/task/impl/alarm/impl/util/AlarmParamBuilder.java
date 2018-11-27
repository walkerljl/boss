package org.walkerljl.boss.service.task.impl.alarm.impl.util;

import org.walkerljl.boss.service.task.impl.TaskExecutionContext;
import org.walkerljl.boss.service.task.impl.alarm.AlarmInfo;
import org.walkerljl.boss.service.task.model.Task;
import org.walkerljl.boss.support.common.util.ThrowableUtil;

/**
 * AlarmParamBuilder
 *
 * @author xingxun
 */
public class AlarmParamBuilder {

    /**
     * 构建报警信息
     *
     * @param context
     * @return
     */
    public static AlarmInfo buildAlarmInfo(TaskExecutionContext context) {
        if (context == null) {
            return null;
        }
        Task task = (Task) context.getAttribute(TaskExecutionContext.TASK);
        if (task == null) {
            return null;
        }
        String alarmKey = String.format("%s:%s:%s", task.getBizCode(), task.getBizId(), task.getId());
        Throwable e = (Throwable) context.getAttribute(TaskExecutionContext.EXECUTION_RESULT_THROABLE);
        AlarmInfo alarmInfo = new AlarmInfo(alarmKey, ThrowableUtil.getMessage(e));

        return alarmInfo;
    }
}