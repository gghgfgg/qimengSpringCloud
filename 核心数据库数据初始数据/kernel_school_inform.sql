/*
Navicat MySQL Data Transfer

Source Server         : php
Source Server Version : 50553
Source Host           : localhost:3306
Source Database       : studentinform

Target Server Type    : MYSQL
Target Server Version : 50553
File Encoding         : 65001

Date: 2019-10-17 19:35:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for kernel_school_inform
-- ----------------------------
DROP TABLE IF EXISTS `kernel_school_inform`;
CREATE TABLE `kernel_school_inform` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `school_name` varchar(50) DEFAULT NULL,
  `school_id` char(10) DEFAULT NULL,
  `school_code` char(12) DEFAULT NULL,
  `postal_code` char(6) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `contacts` varchar(255) DEFAULT NULL,
  `active` tinyint(1) unsigned zerofill NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `school_code` (`school_code`) USING HASH,
  KEY `school_id` (`school_id`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of kernel_school_inform
-- ----------------------------
INSERT INTO `kernel_school_inform` VALUES ('1', '汕头市新乡小学', '2144003701', '2144003701', '440511', '广东省汕头市金平区金湖路32号', null, '1', '2019-09-07 14:38:18', '2019-09-07 14:38:18');
INSERT INTO `kernel_school_inform` VALUES ('2', '汕头市桂花小学', '2144003680', '2144003680', '440511', '广东省汕头市金平区广厦新城桂花园1幢', null, '1', '2019-09-07 14:39:50', '2019-09-07 14:39:50');
INSERT INTO `kernel_school_inform` VALUES ('3', '汕头市金砂小学', '2144003473', '2144003473', '440511', '广东省汕头市金平区汕樟路86号', null, '1', '2019-09-07 14:41:59', '2019-09-07 14:41:59');
INSERT INTO `kernel_school_inform` VALUES ('4', '汕头市外马路第三小学', '2144003686', '2144003686', '440511', '广东省汕头市金平区外马路129号', null, '1', '2019-09-07 14:42:24', '2019-09-07 14:42:24');
INSERT INTO `kernel_school_inform` VALUES ('5', '汕头市外马路第四小学', '2144003655', '2144003655', '440511', '广东省汕头市金平区联兴里左巷1号', null, '1', '2019-09-07 14:43:03', '2019-09-07 14:43:03');
INSERT INTO `kernel_school_inform` VALUES ('6', '汕头市东厦小学', '2144003475', '2144003475', '440511', '广东省汕头市金平区东厦路42号', null, '1', '2019-09-07 14:44:28', '2019-09-07 14:44:28');
INSERT INTO `kernel_school_inform` VALUES ('7', '汕头市金龙小学', '2144003477', '2144003477', '440511', '广东省汕头市金平区龙眼路108号', null, '1', '2019-09-07 14:44:53', '2019-09-07 14:44:53');
INSERT INTO `kernel_school_inform` VALUES ('8', '汕头市龙湖区金涛小学', '2144003406', '2144003406', '440507', '广东省汕头市龙湖区嘉福大厦西北角', null, '1', '2019-09-07 14:54:24', '2019-09-07 14:54:24');
INSERT INTO `kernel_school_inform` VALUES ('9', '汕头市东厦小学北校区', '2144003475', '214400347501', '440511', '广东省汕头市金平区金湖路126号', null, '1', '2019-09-07 14:54:50', '2019-09-07 14:55:16');
INSERT INTO `kernel_school_inform` VALUES ('10', '汕头市大华路第二小学', '2144003654', '2144003654', '440511', '广东省汕头市金平区大华路43号', null, '0', '2019-09-23 18:13:32', '2019-09-23 18:13:32');
INSERT INTO `kernel_school_inform` VALUES ('11', '启盟小学', '1000000001', '1000000001', '440511', '广东汕头市', null, '1', '2019-09-28 11:08:44', '2019-09-28 11:08:44');
INSERT INTO `kernel_school_inform` VALUES ('12', '上海市长宁实验小学', '9131012510', '9131012510', '310105', '上海市长宁区茅台路625号', null, '1', '2019-10-07 16:29:43', '2019-10-07 16:29:43');
INSERT INTO `kernel_school_inform` VALUES ('13', '汕头市大华路第一小学', '2144003653', '2144003653', '440511', '汕头市金平区大华路24号', null, '0', '2019-10-17 09:58:29', '2019-10-17 09:58:29');
INSERT INTO `kernel_school_inform` VALUES ('14', '汕头市华坞路小学', '2144003657', '2144003657', '440511', '汕头市金平区华坞路10号', null, '0', '2019-10-17 10:01:07', '2019-10-17 10:01:07');
INSERT INTO `kernel_school_inform` VALUES ('15', '汕头市北墩小学', '2144003650', '2144003650', '440511', '汕头市金平区金环北路金砂中学旁边', null, '0', '2019-10-17 10:02:43', '2019-10-17 10:02:43');
INSERT INTO `kernel_school_inform` VALUES ('16', '汕头市南墩小学', '2144003649', '2144003649', '440511', '汕头市金平区东湖路1号', null, '0', '2019-10-17 10:04:06', '2019-10-17 10:04:06');
INSERT INTO `kernel_school_inform` VALUES ('17', '汕头市浮西小学', '2144003648', '2144003648', '440511', '汕头市金平区金禧花园金枫苑', null, '0', '2019-10-17 10:11:34', '2019-10-17 10:11:34');
INSERT INTO `kernel_school_inform` VALUES ('18', '汕头市红领巾路小学', '2144003656', '2144003656', '440511', '汕头市金平区饶平路2号', null, '0', '2019-10-17 10:14:28', '2019-10-17 10:14:28');
INSERT INTO `kernel_school_inform` VALUES ('19', '汕头市镇邦路第一小学', '2144003688', '2144003688', '440511', '汕头市金平区海安街道万安花园10栋', null, '0', '2019-10-17 10:15:07', '2019-10-17 10:15:07');
INSERT INTO `kernel_school_inform` VALUES ('20', '汕头市鮀浦中心学校', '3144002725', '3144002725', '440511', '汕头市金平区蓬洲学校路15号', null, '0', '2019-10-17 10:15:35', '2019-10-17 10:15:35');
INSERT INTO `kernel_school_inform` VALUES ('21', '汕头市赖厝小学', '2144003709', '2144003709', '440511', '汕头市金平区鮀江街道赖厝村', null, '0', '2019-10-17 10:16:07', '2019-10-17 10:16:07');
INSERT INTO `kernel_school_inform` VALUES ('22', '汕头市月季小学', '2144003660', '2144003660', '440511', '汕头市金平区月季园21栋', null, '0', '2019-10-17 10:16:28', '2019-10-17 10:16:28');
