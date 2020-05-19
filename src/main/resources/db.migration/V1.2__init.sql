
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `password` varchar(16) NOT NULL COMMENT '密码',
  `rank` varchar(16) DEFAULT NULL COMMENT '级别',
  `username` varchar(24) NOT NULL COMMENT '用户名',
  `leader_id` bit(2) DEFAULT NULL,
  `in_time` datetime DEFAULT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;



INSERT INTO `user` (`id`, `password`, `rank`, `username`, `leader_id`, `in_time`)
VALUES
	(1, '123', '管理员', 'admin', NULL, '2019-04-22 14:19:23'),
	(2, '123', '总经理', 'user2', NULL, '2019-04-22 14:19:23'),
	(3, '123', '部门经理', 'user1', 2, '2019-04-22 14:19:23'),
	(5, '123', '员工', 'user3', 3, '2019-04-22 14:21:38'),
	(6, '123', '员工', 'user4', 3, '2019-04-22 14:22:56');