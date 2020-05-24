
SET FOREIGN_KEY_CHECKS = 0;

-- DROP TABLE IF EXISTS `record`;

CREATE TABLE `record` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(16) COMMENT '姓名',
  `sex` varchar(16) COMMENT '性别',
  `birthday` varchar(16) COMMENT '出生年月',
  `nation` varchar(16) COMMENT '民族',
  `politics` varchar(16) COMMENT '政治面貌',
  `enrollment_time` varchar(16) COMMENT '入学时间',
  `id_card` varchar(30) COMMENT '身份证号码',
  `phone` varchar(16) COMMENT '联系电话',
  `university` varchar(16) COMMENT '大学',
  `college` varchar(16) COMMENT '学院',
  `dept` varchar(16) COMMENT '系',
  `grade` varchar(16) COMMENT '班级',
  `residence` varchar(16) COMMENT '家庭户口',
  `home_num` varchar(16) COMMENT '家庭人口总数',
  `home_income` varchar(16) COMMENT '家庭总收入',
  `month_income` varchar(16) COMMENT '人均月收入',
  `source_income` varchar(16) COMMENT '收入来源',
  `addr` varchar(16) COMMENT '家庭住址',
  `postal_code` varchar(16) COMMENT '邮政编码',
  `achievement` varchar(255) COMMENT '学习成绩',
  `reason` varchar(255) COMMENT '申请理由',
  `member` int(20) COMMENT '家庭成员id',
  `content` varchar(255) COMMENT '审批意见',
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;