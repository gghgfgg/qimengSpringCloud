/*
Navicat MySQL Data Transfer

Source Server         : php
Source Server Version : 50553
Source Host           : localhost:3306
Source Database       : usermanage

Target Server Type    : MYSQL
Target Server Version : 50553
File Encoding         : 65001

Date: 2019-08-31 18:31:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for app
-- ----------------------------
DROP TABLE IF EXISTS `app`;
CREATE TABLE `app` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `appid` varchar(50) DEFAULT NULL,
  `secret` varchar(50) DEFAULT NULL,
  `apptype` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of app
-- ----------------------------
INSERT INTO `app` VALUES ('1', 'wx204802db85a9fb3c', '7088426ea2e5b73ad58817b3ce57e29f', 'wechat');

-- ----------------------------
-- Table structure for main_stu
-- ----------------------------
DROP TABLE IF EXISTS `main_stu`;
CREATE TABLE `main_stu` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `uuid` varchar(32) DEFAULT NULL,
  `stuinfo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of main_stu
-- ----------------------------
INSERT INTO `main_stu` VALUES ('1', '1ddabcc6fa994bddb6665e7099975a3d', '[{\"unit\":0,\"bind\":1,\"wasteType\":0,\"qrCode\":\"http://www.qimenghb.com?code=9892fd67a6ee28ea&card=ab6fc4d9dba14edf\",\"name\":\"黄俊星\",\"active\":0,\"stuCard\":\"ab6fc4d9dba14edf\",\"point\":0,\"stuCode\":\"9892fd67a6ee28ea\"}]');

-- ----------------------------
-- Table structure for main_user
-- ----------------------------
DROP TABLE IF EXISTS `main_user`;
CREATE TABLE `main_user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `uuid` varchar(32) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `mail` varchar(50) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  `salt` varchar(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uuid` (`uuid`) USING HASH,
  UNIQUE KEY `phone` (`phone`) USING HASH,
  UNIQUE KEY `mail` (`mail`) USING HASH,
  UNIQUE KEY `username` (`username`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of main_user
-- ----------------------------
INSERT INTO `main_user` VALUES ('2', '1ddabcc6fa994bddb6665e7099975a3d', '15920198991', null, null, null, null, '2019-08-31 11:11:22', '2019-08-31 11:11:22');

-- ----------------------------
-- Table structure for wechat_main
-- ----------------------------
DROP TABLE IF EXISTS `wechat_main`;
CREATE TABLE `wechat_main` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `openid` varchar(32) DEFAULT NULL,
  `unionid` varchar(32) DEFAULT NULL,
  `uuid` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `un` (`unionid`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of wechat_main
-- ----------------------------
INSERT INTO `wechat_main` VALUES ('1', 'osHiB4qSHEv9cqS8dhOoagmmZPmo', null, '1ddabcc6fa994bddb6665e7099975a3d');

-- ----------------------------
-- Table structure for wechat_user
-- ----------------------------
DROP TABLE IF EXISTS `wechat_user`;
CREATE TABLE `wechat_user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `openid` varchar(32) DEFAULT NULL,
  `unionid` varchar(32) DEFAULT NULL,
  `nick_name` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `un` (`openid`,`unionid`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of wechat_user
-- ----------------------------
INSERT INTO `wechat_user` VALUES ('1', null, null, null, '2019-08-30 22:46:15', '2019-08-30 22:46:15');
INSERT INTO `wechat_user` VALUES ('2', null, null, null, '2019-08-31 09:23:32', '2019-08-31 09:23:32');
INSERT INTO `wechat_user` VALUES ('3', 'osHiB4qSHEv9cqS8dhOoagmmZPmo', null, null, '2019-08-31 09:24:37', '2019-08-31 09:24:37');
