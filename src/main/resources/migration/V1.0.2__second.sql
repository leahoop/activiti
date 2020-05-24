
SET FOREIGN_KEY_CHECKS = 0;

-- DROP TABLE IF EXISTS `ask_leave`;

CREATE TABLE `ask_leave` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `title` varchar(16) DEFAULT NULL COMMENT '标题',
  `description` varchar(16) DEFAULT NULL COMMENT '描述',
  `in_time` datetime DEFAULT NULL COMMENT '提交时间',
  `day` smallint DEFAULT NULL,
  `type` smallint DEFAULT NULL COMMENT '申请类型',
  `status` varchar(16) DEFAULT NULL,
  `user_id` int(20) comment '上级用户',
  `task_id` varchar(16) comment '任务的id',
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;


alter table ask_leave add COLUMN record_id int(20) comment '记录的id';

