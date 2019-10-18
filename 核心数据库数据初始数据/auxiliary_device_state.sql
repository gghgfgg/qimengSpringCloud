/*
Navicat MySQL Data Transfer

Source Server         : 47.112.126.70_3333
Source Server Version : 50643
Source Host           : 47.112.126.70:3333
Source Database       : studentinform

Target Server Type    : MYSQL
Target Server Version : 50643
File Encoding         : 65001

Date: 2019-10-16 11:01:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for auxiliary_device_state
-- ----------------------------
DROP TABLE IF EXISTS `auxiliary_device_state`;
CREATE TABLE `auxiliary_device_state` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `type` tinyint(1) unsigned DEFAULT NULL,
  `status` tinyint(1) unsigned DEFAULT NULL,
  `mark` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `type` (`type`,`status`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auxiliary_device_state
-- ----------------------------
INSERT INTO `auxiliary_device_state` VALUES ('1', '0', '0', '未启动', '2019-09-05 14:02:14', '2019-09-05 14:24:22');
INSERT INTO `auxiliary_device_state` VALUES ('2', '0', '1', '正常', '2019-09-05 14:04:10', '2019-09-05 14:04:10');
INSERT INTO `auxiliary_device_state` VALUES ('3', '0', '2', '故障', '2019-09-05 14:04:54', '2019-09-05 14:15:24');
INSERT INTO `auxiliary_device_state` VALUES ('4', '0', '3', '维修', '2019-09-05 14:05:04', '2019-09-05 14:15:38');
