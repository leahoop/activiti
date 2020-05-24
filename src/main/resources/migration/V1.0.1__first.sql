
SET FOREIGN_KEY_CHECKS = 0;

-- DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `password` varchar(16) NOT NULL COMMENT '密码',
  `rank` varchar(16) DEFAULT NULL COMMENT '级别',
  `username` varchar(24) NOT NULL COMMENT '用户名',
  `leader_id` smallint (2) DEFAULT NULL,
  `in_time` datetime DEFAULT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;



INSERT INTO `user` (`id`, `password`, `rank`, `username`, `leader_id`, `in_time`)
VALUES
	(1, '123', '管理员', 'admin', NULL, '2019-04-22 14:19:23'),
	(5, '123', '员工', 'user3', 7, '2019-04-22 14:21:38'),
	(6, '123', '员工', 'user4', 7, '2019-04-22 14:22:56'),
	(7, '123', '辅导员', 'school', 1, '2019-04-22 14:22:56');
