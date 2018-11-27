package org.walkerljl.boss.service.task.impl;

/**
 * LoggerNames
 *
 * @author xingxun
 */
public class TaskLoggerNames {

    /**DEFAULT*/
    public static final String DEFAULT = "TASK_DEFAULT";

    /** 服务接入*/
    public static final String SAL_DIGEST = "TASK_SAL_DIGEST";
    public static final String SAL_DETAIL = "TASK_SAL_DETAIL";

    /*** BROKER*/
    public static final String BROKER_DIGEST = "TASK_BROKER_DIGEST";
    public static final String BROKER_DETAIL = "TASK_BROKER_DETAIL";

    /** 任务分发-拆分*/
    public static final String TASK_DISPATCH_SPLIT_DIGEST = "TASK_DISPATCH_SPLIT_DIGEST";
    public static final String TASK_DISPATCH_SPLIT_DETAIL = "TASK_DISPATCH_SPLIT_DETAIL";

    /** 任务分发-加载*/
    public static final String TASK_DISPATCH_LOAD_DIGEST = "TASK_DISPATCH_LOAD_DIGEST";
    public static final String TASK_DISPATCH_LOAD_DETAIL = "TASK_DISPATCH_LOAD_DETAIL";

    /** 任务分发-执行*/
    public static final String TASK_DISPATCH_EXECUTE_DIGEST = "TASK_DISPATCH_EXECUTE_DIGEST";
    public static final String TASK_DISPATCH_EXECUTE_DETAIL = "TASK_DISPATCH_EXECUTE_DETAIL";

    /*** 任务执行*/
    public static final String EXECUTE_DIGEST = "TASK_EXECUTE_DIGEST";
    public static final String EXECUTE_DETAIL = "TASK_EXECUTE_DETAIL";

    /** 预警*/
    public static final String ALARMER_DIGEST = "TASK_ALARMER_DIGEST";
    public static final String ALARMER_DETAIL = "TASK_ALARMER_DETAIL";
}