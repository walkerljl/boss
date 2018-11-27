/**
* 监控-监控对象元数据
*/
CREATE TABLE `monitor_obj_meta_data`(
	`id` BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
	`biz_code` VARCHAR(64) NOT NULL COMMENT '接入业务编码',
	`biz_name` VARCHAR(128) NOT NULL COMMENT '接入业务名称',
	`obj_id` VARCHAR(128) NOT NULL COMMENT '监控对象ID',
	`obj_name` VARCHAR(128) NOT NULL COMMENT '监控对象名称',
	`alarm_receivers` VARCHAR(1024) NOT NULL COMMENT '预警对象列表',
	`biz_owner` VARCHAR(128) NOT NULL COMMENT '业务负责人。可以采用格式<${ID}>${name}，如：<xingxun>行寻。',
	`remark` VARCHAR(256) DEFAULT '' COMMENT '备注',
	`ext_info` VARCHAR(1024) DEFAULT '' COMMENT '扩展信息',
	`status` CHAR(2) NOT NULL COMMENT '状态。10：正常；20：已删除；30：暂停预警；',
	`creator` VARCHAR(64) NOT NULL COMMENT '创建者',
	`gmt_create` DATETIME NOT NULL COMMENT '创建时间',
	`modifier` VARCHAR(64) NOT NULL COMMENT '修改者',
	`gmt_modified` DATETIME NOT NULL COMMENT '修改时间'
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT '监控对象元数据';
/** 添加索引、约束等*/
ALTER TABLE `monitor_obj_meta_data` ADD UNIQUE KEY uk_biz_code_obj_id(`biz_code`,`obj_id`);

/**
* 监控-监控数据
*/
CREATE TABLE `monitor_data`(
`id` BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
`biz_code` VARCHAR(64) NOT NULL COMMENT '接入业务编码',
`obj_id` VARCHAR(128) NOT NULL COMMENT '监控对象ID',
`value` DECIMAL(19,4) NOT NULL COMMENT '监控数据',
`time` DATETIME NOT NULL COMMENT '数据产生时间',
`remark` VARCHAR(256) DEFAULT '' COMMENT '备注',
`ext_info` VARCHAR(1024) DEFAULT '' COMMENT '扩展信息',
`status` CHAR(2) NOT NULL COMMENT '状态。10：正常；20：已删除；',
`creator` VARCHAR(64) NOT NULL COMMENT '创建者',
`gmt_create` DATETIME NOT NULL COMMENT '创建时间',
`modifier` VARCHAR(64) NOT NULL COMMENT '修改者',
`gmt_modified` DATETIME NOT NULL COMMENT '修改时间'
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT '监控数据';
/** 添加索引、约束等*/
ALTER TABLE `monitor_data` ADD INDEX idx_biz_code_obj_id_time(`biz_code`,`obj_id`,`time`);

/**
* 监控-预警规则
*/
CREATE TABLE `monitor_alarm_rule`(
`id` BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
`biz_code` VARCHAR(64) NOT NULL COMMENT '接入业务编码',
`obj_id` VARCHAR(128) NOT NULL COMMENT '监控对象ID',
`type` CHAR(4) NOT NULL COMMENT '规则类型',
`expression` VARCHAR(1024) NOT NULL COMMENT '规则表达式',
`channels` VARCHAR(128) DEFAULT '' COMMENT '预警渠道列表',
`remark` VARCHAR(256) DEFAULT '' COMMENT '备注',
`ext_info` VARCHAR(1024) DEFAULT '' COMMENT '扩展信息',
`status` CHAR(2) NOT NULL COMMENT '状态。10：正常；20：已删除；30：暂停预警；',
`creator` VARCHAR(64) NOT NULL COMMENT '创建者',
`gmt_create` DATETIME NOT NULL COMMENT '创建时间',
`modifier` VARCHAR(64) NOT NULL COMMENT '修改者',
`gmt_modified` DATETIME NOT NULL COMMENT '修改时间'
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT '预警规则';
/** 添加索引、约束等*/
ALTER TABLE `monitor_alarm_rule` ADD INDEX idx_biz_code_obj_id(`biz_code`,`obj_id`);

/**
* 监控-预警记录
*/
CREATE TABLE `monitor_alarm_record`(
`id` BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
`biz_code` VARCHAR(64) NOT NULL COMMENT '接入业务编码',
`biz_name` VARCHAR(128) NOT NULL COMMENT '接入业务名称',
`obj_id` VARCHAR(128) NOT NULL COMMENT '监控对象ID',
`obj_name` VARCHAR(128) NOT NULL COMMENT '监控对象名称',
`data` DECIMAL(19,4) NOT NULL COMMENT '预警数据',
`data_time` DATETIME NOT NULL COMMENT '预警数据产生时间',
`level` CHAR(2) NOT NULL COMMENT '预警级别。	10：绿（提示信息）；20：黄；30：橙；40：红；',
`content` VARCHAR(1024) NOT NULL COMMENT '内容',
`time` DATETIME NOT NULL COMMENT '预警时间',
`channels` VARCHAR(128) DEFAULT '' COMMENT '预警渠道列表',
`receivers` VARCHAR(1024) DEFAULT '' COMMENT '预警对象列表',
`remark` VARCHAR(256) DEFAULT '' COMMENT '备注',
`ext_info` VARCHAR(1024) DEFAULT '' COMMENT '扩展信息',
`status` CHAR(2) NOT NULL COMMENT '状态。	20：已删除；30：成功；31：失败；',
`creator` VARCHAR(64) NOT NULL COMMENT '创建者',
`gmt_create` DATETIME NOT NULL COMMENT '创建时间',
`modifier` VARCHAR(64) NOT NULL COMMENT '修改者',
`gmt_modified` DATETIME NOT NULL COMMENT '修改时间'
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT '预警记录';
/** 添加索引、约束等*/
ALTER TABLE `monitor_alarm_record` ADD INDEX idx_biz_code_obj_id(`biz_code`,`obj_id`);


