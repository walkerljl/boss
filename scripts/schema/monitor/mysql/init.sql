delete from monitor_obj_meta_data;
delete from monitor_data;
delete from monitor_alarm_rule;
delete from monitor_alarm_record;


insert into monitor_obj_meta_data(
biz_code
,biz_name
,obj_id
,obj_name
,alarm_receivers
,biz_owner
,remark
,ext_info
,status
,creator
,gmt_create
,modifier
,gmt_modified)
values
('szjys','深证证券交易所','trd_amt_1d','日交易金额','','<xingxun>行寻','','','10','xingxun',now(),'xingxun',now())
;

insert into monitor_data(
biz_code
,obj_id
,value
,time
,remark
,ext_info
,status
,creator
,gmt_create
,modifier
,gmt_modified)
values
('szjys','trd_amt_1d',100000000,date_sub(date_format(now(),'%Y-%m-%d'), interval 31 day),'','','10','xingxun',now(),'xingxun',now())
,('szjys','trd_amt_1d',100000000,date_sub(date_format(now(),'%Y-%m-%d'), interval 30 day),'','','10','xingxun',now(),'xingxun',now())
,('szjys','trd_amt_1d',100000000,date_sub(date_format(now(),'%Y-%m-%d'), interval 29 day),'','','10','xingxun',now(),'xingxun',now())
,('szjys','trd_amt_1d',100000000,date_sub(date_format(now(),'%Y-%m-%d'), interval 28 day),'','','10','xingxun',now(),'xingxun',now())
,('szjys','trd_amt_1d',100000000,date_sub(date_format(now(),'%Y-%m-%d'), interval 27 day),'','','10','xingxun',now(),'xingxun',now())
,('szjys','trd_amt_1d',100000000,date_sub(date_format(now(),'%Y-%m-%d'), interval 26 day),'','','10','xingxun',now(),'xingxun',now())
,('szjys','trd_amt_1d',100000000,date_sub(date_format(now(),'%Y-%m-%d'), interval 25 day),'','','10','xingxun',now(),'xingxun',now())
,('szjys','trd_amt_1d',100000000,date_sub(date_format(now(),'%Y-%m-%d'), interval 24 day),'','','10','xingxun',now(),'xingxun',now())
,('szjys','trd_amt_1d',100000000,date_sub(date_format(now(),'%Y-%m-%d'), interval 23 day),'','','10','xingxun',now(),'xingxun',now())
,('szjys','trd_amt_1d',100000000,date_sub(date_format(now(),'%Y-%m-%d'), interval 22 day),'','','10','xingxun',now(),'xingxun',now())
,('szjys','trd_amt_1d',100000000,date_sub(date_format(now(),'%Y-%m-%d'), interval 21 day),'','','10','xingxun',now(),'xingxun',now())

,('szjys','trd_amt_1d',100000000,date_sub(date_format(now(),'%Y-%m-%d'), interval 20 day),'','','10','xingxun',now(),'xingxun',now())
,('szjys','trd_amt_1d',100000000,date_sub(date_format(now(),'%Y-%m-%d'), interval 19 day),'','','10','xingxun',now(),'xingxun',now())
,('szjys','trd_amt_1d',100000000,date_sub(date_format(now(),'%Y-%m-%d'), interval 18 day),'','','10','xingxun',now(),'xingxun',now())
,('szjys','trd_amt_1d',100000000,date_sub(date_format(now(),'%Y-%m-%d'), interval 17 day),'','','10','xingxun',now(),'xingxun',now())
,('szjys','trd_amt_1d',100000000,date_sub(date_format(now(),'%Y-%m-%d'), interval 16 day),'','','10','xingxun',now(),'xingxun',now())
,('szjys','trd_amt_1d',100000000,date_sub(date_format(now(),'%Y-%m-%d'), interval 15 day),'','','10','xingxun',now(),'xingxun',now())
,('szjys','trd_amt_1d',100000000,date_sub(date_format(now(),'%Y-%m-%d'), interval 14 day),'','','10','xingxun',now(),'xingxun',now())
,('szjys','trd_amt_1d',100000000,date_sub(date_format(now(),'%Y-%m-%d'), interval 13 day),'','','10','xingxun',now(),'xingxun',now())
,('szjys','trd_amt_1d',100000000,date_sub(date_format(now(),'%Y-%m-%d'), interval 12 day),'','','10','xingxun',now(),'xingxun',now())
,('szjys','trd_amt_1d',100000000,date_sub(date_format(now(),'%Y-%m-%d'), interval 11 day),'','','10','xingxun',now(),'xingxun',now())

,('szjys','trd_amt_1d',100000000,date_sub(date_format(now(),'%Y-%m-%d'), interval 10 day),'','','10','xingxun',now(),'xingxun',now())
,('szjys','trd_amt_1d',100000000,date_sub(date_format(now(),'%Y-%m-%d'), interval 9 day),'','','10','xingxun',now(),'xingxun',now())
,('szjys','trd_amt_1d',100000000,date_sub(date_format(now(),'%Y-%m-%d'), interval 8 day),'','','10','xingxun',now(),'xingxun',now())
,('szjys','trd_amt_1d',100000000,date_sub(date_format(now(),'%Y-%m-%d'), interval 7 day),'','','10','xingxun',now(),'xingxun',now())
,('szjys','trd_amt_1d',100000000,date_sub(date_format(now(),'%Y-%m-%d'), interval 6 day),'','','10','xingxun',now(),'xingxun',now())
,('szjys','trd_amt_1d',100000000,date_sub(date_format(now(),'%Y-%m-%d'), interval 5 day),'','','10','xingxun',now(),'xingxun',now())
,('szjys','trd_amt_1d',100000000,date_sub(date_format(now(),'%Y-%m-%d'), interval 4 day),'','','10','xingxun',now(),'xingxun',now())
,('szjys','trd_amt_1d',80000000,date_sub(date_format(now(),'%Y-%m-%d'), interval 3 day),'','','10','xingxun',now(),'xingxun',now())
,('szjys','trd_amt_1d',72000000,date_sub(date_format(now(),'%Y-%m-%d'), interval 2 day),'','','10','xingxun',now(),'xingxun',now())
,('szjys','trd_amt_1d',68400000,date_sub(date_format(now(),'%Y-%m-%d'), interval 1 day),'','','10','xingxun',now(),'xingxun',now())
,('szjys','trd_amt_1d',200000000,date_format(now(),'%Y-%m-%d'),'','','10','xingxun',now(),'xingxun',now())
;

insert into monitor_alarm_rule(
biz_code
,obj_id
,type
,expression
,channels
,remark
,ext_info
,status
,creator
,gmt_create
,modifier
,gmt_modified)
values
('szjys','trd_amt_1d','1000','1,decline,0.2','[\'log\']','','','10','xingxun',now(),'xingxun',now())
,('szjys','trd_amt_1d','1000','2,decline,0.1','[\'log\']','','','10','xingxun',now(),'xingxun',now())
,('szjys','trd_amt_1d','1000','3,decline,0.05','[\'log\']','','','10','xingxun',now(),'xingxun',now())
,('szjys','trd_amt_1d','1002','201810,100000000;201811,120000000;','[\'log\']','','','10','xingxun',now(),'xingxun',now())
,('szjys','trd_amt_1d','1001','30','[\'log\']','','','10','xingxun',now(),'xingxun',now())
;