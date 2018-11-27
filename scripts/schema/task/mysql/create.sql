/**
 * 定义表结构
 */

/**
 * 任务表
 */
CREATE TABLE `task`(
	`id` BIGINT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '主键Id',
	`biz_code` VARCHAR(64) NOT NULL COMMENT '业务编码',
	`biz_id` VARCHAR(128) NOT NULL COMMENT '业务ID',
	`priority` TINYINT(1) NOT NULL COMMENT '优先级。1:高；5:中；9:低；',
	`handler_id` VARCHAR(256) NOT NULL COMMENT '处理器ID',
	`attempts` INT NOT NULL COMMENT '当前重试次数',
	`max_attempts` INT NOT NULL COMMENT '最大重试次数',
	`retry_rule` VARCHAR(128) NOT NULL COMMENT '重试规则',
	`last_retry_time` DATETIME NOT NULL COMMENT '上次重试时间',
	`next_retry_time` DATETIME NOT NULL COMMENT '下次重试时间',
	`remark` VARCHAR(256) DEFAULT '' COMMENT '备注',
	`ext_info` VARCHAR(1024) DEFAULT '' COMMENT '扩展信息',
	`status` CHAR(2) NOT NULL COMMENT '状态。20：已删除；30：待处理；31：处理中；32：已处理；33：处理失败；',
	`creator` VARCHAR(64) NOT NULL COMMENT '创建者',
	`gmt_create` DATETIME NOT NULL COMMENT '创建时间',
	`modifier` VARCHAR(64) NOT NULL COMMENT '修改者',
	`gmt_modified` DATETIME NOT NULL COMMENT '修改时间'
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT '重试任务表';
/** 添加索引、约束等*/
ALTER TABLE `task` ADD UNIQUE KEY uk_biz_code_biz_id(`biz_code`,`biz_id`);

/**
 * 任务执行参数表
 */
CREATE TABLE `task_param`(
	`id` BIGINT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '主键Id',
	`biz_code` VARCHAR(64) NOT NULL COMMENT '业务类型',
	`biz_id` VARCHAR(128) NOT NULL COMMENT '业务ID',
	`task_id` BIGINT UNSIGNED NOT NULL COMMENT '任务ID',
	`value` VARCHAR(4096) NOT NULL COMMENT '参数值',
	`remark` VARCHAR(256) DEFAULT '' COMMENT '备注',
	`ext_info` VARCHAR(1024) DEFAULT '' COMMENT '扩展信息',
	`status` CHAR(2) NOT NULL COMMENT '状态。10：正常；20：已删除；',
	`creator` VARCHAR(64) NOT NULL COMMENT '创建者',
	`gmt_create` DATETIME NOT NULL COMMENT '创建时间',
	`modifier` VARCHAR(64) NOT NULL COMMENT '修改者',
	`gmt_modified` DATETIME NOT NULL COMMENT '修改时间'
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT '任务执行参数表';
/** 添加索引、约束等*/
ALTER TABLE `task_param` ADD INDEX idx_biz_code_biz_id_task_id(`biz_code`,`biz_id`,`task_id`);

/**
 * 任务执行日志表
 */
CREATE TABLE `task_log`(
	`id` BIGINT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '主键Id',
	`biz_code` VARCHAR(64) NOT NULL COMMENT '业务类型',
	`biz_id` VARCHAR(128) NOT NULL COMMENT '业务ID',
	`task_id` BIGINT UNSIGNED NOT NULL COMMENT '任务ID',
	`attempts` INT NOT NULL COMMENT '当前重试次数',
	`description` VARCHAR(1024) DEFAULT '' COMMENT '描述',
	`remark` VARCHAR(256) DEFAULT '' COMMENT '备注',
	`ext_info` VARCHAR(1024) DEFAULT '' COMMENT '扩展信息',
	`status` CHAR(2) NOT NULL COMMENT '状态。20：已删除；30：成功；31：失败；',
	`creator` VARCHAR(64) NOT NULL COMMENT '创建者',
	`gmt_create` DATETIME NOT NULL COMMENT '创建时间',
	`modifier` VARCHAR(64) NOT NULL COMMENT '修改者',
	`gmt_modified` DATETIME NOT NULL COMMENT '修改时间'
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT '任务执行日志表';
ALTER TABLE `task_log` ADD INDEX idx_biz_code_biz_id_task_id(`biz_code`,`biz_id`,`task_id`);

