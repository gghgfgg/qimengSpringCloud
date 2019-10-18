/*
Navicat MySQL Data Transfer

Source Server         : php
Source Server Version : 50553
Source Host           : localhost:3306
Source Database       : studentinform

Target Server Type    : MYSQL
Target Server Version : 50553
File Encoding         : 65001

Date: 2019-10-17 19:35:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for auxiliary_school_id
-- ----------------------------
DROP TABLE IF EXISTS `auxiliary_school_id`;
CREATE TABLE `auxiliary_school_id` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `school_id` char(10) DEFAULT NULL,
  `school_name` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `school_id` (`school_id`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auxiliary_school_id
-- ----------------------------
INSERT INTO `auxiliary_school_id` VALUES ('1', '2144003701', '汕头市新乡小学', '2019-09-05 19:56:32', '2019-09-05 19:56:32');
INSERT INTO `auxiliary_school_id` VALUES ('2', '2144003680', '汕头市桂花小学', '2019-09-05 20:05:55', '2019-09-05 20:05:55');
INSERT INTO `auxiliary_school_id` VALUES ('3', '2144003473', '汕头市金砂小学', '2019-09-05 20:06:14', '2019-09-05 20:06:14');
INSERT INTO `auxiliary_school_id` VALUES ('4', '2144003686', '汕头市外马路第三小学', '2019-09-05 20:06:34', '2019-09-05 20:06:34');
INSERT INTO `auxiliary_school_id` VALUES ('5', '2144003655', '汕头市外马路第四小学', '2019-09-05 20:06:52', '2019-09-05 20:06:52');
INSERT INTO `auxiliary_school_id` VALUES ('6', '2144003475', '汕头市东厦小学', '2019-09-05 20:07:18', '2019-09-05 20:07:18');
INSERT INTO `auxiliary_school_id` VALUES ('7', '2144003477', '汕头市金龙小学', '2019-09-05 20:07:36', '2019-09-05 20:07:36');
INSERT INTO `auxiliary_school_id` VALUES ('8', '2144003406', '汕头市龙湖区金涛小学', '2019-09-05 20:08:02', '2019-09-05 20:37:54');
INSERT INTO `auxiliary_school_id` VALUES ('9', '2144003654', '汕头市金平区大华路第二小学', '2019-09-19 15:09:31', '2019-09-19 15:09:31');
INSERT INTO `auxiliary_school_id` VALUES ('10', '3444000518', '汕头市第二中学', '2019-09-19 15:10:17', '2019-09-19 15:10:17');
INSERT INTO `auxiliary_school_id` VALUES ('11', '9131012510', '上海市长宁实验小学', '2019-10-07 16:27:16', '2019-10-07 16:27:16');
INSERT INTO `auxiliary_school_id` VALUES ('12', '2144003653', '汕头市大华路第一小学', '2019-10-17 09:50:23', '2019-10-17 09:50:23');
INSERT INTO `auxiliary_school_id` VALUES ('13', '2144003657', '汕头市华坞路小学', '2019-10-17 09:50:44', '2019-10-17 09:50:44');
INSERT INTO `auxiliary_school_id` VALUES ('14', '2144003650', '汕头市北墩小学', '2019-10-17 09:50:55', '2019-10-17 09:50:55');
INSERT INTO `auxiliary_school_id` VALUES ('15', '2144003649', '汕头市南墩小学', '2019-10-17 09:51:11', '2019-10-17 09:51:11');
INSERT INTO `auxiliary_school_id` VALUES ('16', '2144003648', '汕头市浮西小学', '2019-10-17 09:51:35', '2019-10-17 09:51:35');
INSERT INTO `auxiliary_school_id` VALUES ('17', '2144003656', '汕头市红领巾路小学', '2019-10-17 09:51:45', '2019-10-17 09:51:45');
INSERT INTO `auxiliary_school_id` VALUES ('18', '2144003688', '汕头市镇邦路第一小学', '2019-10-17 09:51:56', '2019-10-17 09:51:56');
INSERT INTO `auxiliary_school_id` VALUES ('19', '3144002725', '汕头市鮀浦中心学校', '2019-10-17 09:52:10', '2019-10-17 09:52:10');
INSERT INTO `auxiliary_school_id` VALUES ('20', '2144003709', '汕头市赖厝小学', '2019-10-17 09:52:23', '2019-10-17 09:52:23');
INSERT INTO `auxiliary_school_id` VALUES ('21', '2144003660', '汕头市月季小学', '2019-10-17 09:52:33', '2019-10-17 09:52:33');
