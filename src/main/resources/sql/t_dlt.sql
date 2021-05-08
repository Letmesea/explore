/*
Navicat MySQL Data Transfer

Source Server         : 本地mysql
Source Server Version : 50733
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50733
File Encoding         : 65001

Date: 2021-05-08 17:32:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_dlt
-- ----------------------------
DROP TABLE IF EXISTS `t_dlt`;
CREATE TABLE `t_dlt` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` varchar(10) NOT NULL COMMENT '期号',
  `win_number` varchar(20) NOT NULL COMMENT '开奖号码',
  `sq_number` varchar(20) NOT NULL COMMENT '出球顺序',
  `open_dtm` datetime NOT NULL COMMENT '开奖日期',
  `create_dtm` datetime NOT NULL,
  `update_dtm` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `dlt_number` (`number`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
