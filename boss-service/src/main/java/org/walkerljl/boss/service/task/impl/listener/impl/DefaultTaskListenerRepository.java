package org.walkerljl.boss.service.task.impl.listener.impl;

import org.walkerljl.boss.service.task.TaskListener;
import org.walkerljl.boss.service.task.impl.listener.TaskListenerRepository;
import org.walkerljl.toolkit.standard.repository.abstracts.AbstractObjectRepository;

/**
 * 默认任务监听器仓储
 *
 * @author xingxun
 */
public class DefaultTaskListenerRepository extends AbstractObjectRepository<String, TaskListener> implements TaskListenerRepository {

}