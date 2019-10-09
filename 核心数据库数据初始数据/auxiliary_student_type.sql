/*
Navicat MySQL Data Transfer

Source Server         : php
Source Server Version : 50553
Source Host           : localhost:3306
Source Database       : studentinform

Target Server Type    : MYSQL
Target Server Version : 50553
File Encoding         : 65001

Date: 2019-09-24 16:06:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for auxiliary_student_type
-- ----------------------------
DROP TABLE IF EXISTS `auxiliary_student_type`;
CREATE TABLE `auxiliary_student_type` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `type` tinyint(1) unsigned zerofill DEFAULT NULL,
  `b_phone` tinyint(1) unsigned zerofill DEFAULT NULL,
  `b_teacher` tinyint(1) unsigned zerofill DEFAULT NULL,
  `mark` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `type` (`type`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auxiliary_student_type
-- ----------------------------
INSERT INTO `auxiliary_student_type` VALUES ('1', '0', '0', '0', '普通学生', '2019-09-07 23:20:36', '2019-09-07 23:20:36');
INSERT INTO `auxiliary_student_type` VALUES ('2', '2', '1', '0', '带手机认证学生', '2019-09-07 23:21:41', '2019-09-07 23:21:41');
INSERT INTO `auxiliary_student_type` VALUES ('3', '1', '0', '1', '普通教师', '2019-09-07 23:28:53', '2019-09-07 23:28:53');
INSERT INTO `auxiliary_student_type` VALUES ('4', '3', '1', '1', '带手机认证教师', '2019-09-07 23:29:23', '2019-09-07 23:29:23');
INSERT INTO `auxiliary_student_type` VALUES ('5', '5', '0', '1', '校长', '2019-09-24 10:58:17', '2019-09-24 10:58:17');
INSERT INTO `auxiliary_student_type` VALUES ('6', '9', '0', '1', '副校长', '2019-09-24 10:59:21', '2019-09-24 10:59:21');
INSERT INTO `auxiliary_student_type` VALUES ('7', '13', '0', '1', '主任', '2019-09-24 10:59:36', '2019-09-24 10:59:36');
INSERT INTO `auxiliary_student_type` VALUES ('8', '17', '0', '1', '副主任', '2019-09-24 11:00:05', '2019-09-24 11:00:05');
INSERT INTO `auxiliary_student_type` VALUES ('9', '21', '0', '1', '总务处主任', '2019-09-24 11:07:31', '2019-09-24 11:07:31');
INSERT INTO `auxiliary_student_type` VALUES ('10', '25', '0', '1', '总务处副主任', '2019-09-24 11:07:39', '2019-09-24 11:07:39');
INSERT INTO `auxiliary_student_type` VALUES ('11', '29', '0', '1', '教务处主任', '2019-09-24 11:08:34', '2019-09-24 11:08:34');
INSERT INTO `auxiliary_student_type` VALUES ('12', '33', '0', '1', '教务处副主任', '2019-09-24 11:08:41', '2019-09-24 11:08:41');
INSERT INTO `auxiliary_student_type` VALUES ('13', '37', '0', '1', '体卫处主任', '2019-09-24 11:16:17', '2019-09-24 11:16:17');
INSERT INTO `auxiliary_student_type` VALUES ('14', '41', '0', '1', '体卫处副主任', '2019-09-24 11:16:30', '2019-09-24 11:16:30');
INSERT INTO `auxiliary_student_type` VALUES ('15', '45', '0', '1', '团支书', '2019-09-24 11:17:08', '2019-09-24 11:17:08');
INSERT INTO `auxiliary_student_type` VALUES ('16', '49', '0', '1', '大队辅导员', '2019-09-24 11:17:15', '2019-09-24 11:17:15');
