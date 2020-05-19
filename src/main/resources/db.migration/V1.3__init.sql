
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `ask_leave`;

CREATE TABLE `ask_leave` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `title` varchar(16) DEFAULT NULL COMMENT '标题',
  `description` varchar(16) DEFAULT NULL COMMENT '描述',
  `in_time` datetime DEFAULT NULL COMMENT '提交时间',
  `day` smallint DEFAULT NULL,
  `status` varchar(16) DEFAULT NULL,
  `user_id` int(20) comment '用户'
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

