/**
 * 数据初始化
 */

delete from task;
delete from task_param;
delete from task_log;

/**

 * `任务信息`数据初始化脚本
 */
insert into
  `task`(`id`,`biz_code`,`biz_id`,`priority`,`handler_id`,`attempts`,`max_attempts`,`retry_rule`,`last_retry_time`,
      `next_retry_time`,`remark`,`ext_info`,`status`,`creator`,`gmt_create`,`modifier`,`gmt_modified`)
values
  (1,'task','1',5,'DummyTaskHandler',0,10,5,now(),now(),'','','30','xingxun',now(),'xingxun',now())
  ,(2,'szjys',concat('trd_amt_1d','@@',date_format(now(),'%Y-%m-%d')),5,'AlarmAnalyzer',0,10,5,now(),now(),'','','30','xingxun',now(),'xingxun',now())
  ,(3,'szjys',concat('trd_amt_1d','@@',date_sub(date_format(now(),'%Y-%m-%d'), interval 1 day)),5,'AlarmAnalyzer',0,10,5,now(),now(),'','','30','xingxun',now(),'xingxun',now())
  ,(4,'szjys',concat('trd_amt_1d','@@',date_sub(date_format(now(),'%Y-%m-%d'), interval 2 day)),5,'AlarmAnalyzer',0,10,5,now(),now(),'','','30','xingxun',now(),'xingxun',now())
  ,(5,'szjys',concat('trd_amt_1d','@@',date_sub(date_format(now(),'%Y-%m-%d'), interval 3 day)),5,'AlarmAnalyzer',0,10,5,now(),now(),'','','30','xingxun',now(),'xingxun',now())
 ;

/**
 * `任务执行参数`数据初始化脚本
 */
insert into
  `task_param`(`id`,`biz_code`,`biz_id`,`task_id`,`value`,`remark`,`ext_info`,`status`,`creator`,`gmt_create`,`modifier`,`gmt_modified`)
values
  (1,'task','1',1,'{"id":"xingxun","name":"行寻"}','','','10
  ','xingxun',now(),'xingxun',now())
;

