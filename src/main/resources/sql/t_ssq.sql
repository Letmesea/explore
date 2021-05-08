/*
Navicat MySQL Data Transfer

Source Server         : 本地mysql
Source Server Version : 50733
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50733
File Encoding         : 65001

Date: 2021-05-08 17:32:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_ssq
-- ----------------------------
DROP TABLE IF EXISTS `t_ssq`;
CREATE TABLE `t_ssq` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `number` varchar(10) NOT NULL COMMENT '期号',
  `win_number` varchar(20) NOT NULL COMMENT '开奖号码',
  `sq_number` varchar(20) NOT NULL COMMENT '出球顺序',
  `open_dtm` datetime NOT NULL COMMENT '开奖日期',
  `create_dtm` datetime NOT NULL COMMENT '创建时间',
  `update_dtm` datetime DEFAULT NULL COMMENT '更新时间',
  `first_prize_num` varchar(5) NOT NULL COMMENT '一等注数',
  `total_amount` varchar(20) NOT NULL COMMENT '总销额',
  `second_prize_num` varchar(5) NOT NULL COMMENT '二等奖数',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ssq_number` (`number`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4949 DEFAULT CHARSET=utf8mb4;
