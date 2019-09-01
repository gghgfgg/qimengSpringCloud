/*
Navicat MySQL Data Transfer

Source Server         : php
Source Server Version : 50553
Source Host           : localhost:3306
Source Database       : newstudent

Target Server Type    : MYSQL
Target Server Version : 50553
File Encoding         : 65001

Date: 2019-08-20 09:05:31
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for auxiliary_recycle_type
-- ----------------------------
DROP TABLE IF EXISTS `auxiliary_recycle_type`;
CREATE TABLE `auxiliary_recycle_type` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `type` tinyint(1) DEFAULT NULL,
  `mark` varchar(255) DEFAULT NULL,
  `uint` varchar(20) DEFAULT NULL,
  `factor` int(11) unsigned DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `type` (`type`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for auxiliary_school_contacts_type
-- ----------------------------
DROP TABLE IF EXISTS `auxiliary_school_contacts_type`;
CREATE TABLE `auxiliary_school_contacts_type` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `type` tinyint(1) unsigned DEFAULT NULL,
  `position` varchar(20) DEFAULT NULL,
  `weight` tinyint(1) unsigned DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `type` (`type`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for auxiliary_school_id
-- ----------------------------
DROP TABLE IF EXISTS `auxiliary_school_id`;
CREATE TABLE `auxiliary_school_id` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `school_id` char(10) DEFAULT NULL,
  `school_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `school_id` (`school_id`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for city
-- ----------------------------
DROP TABLE IF EXISTS `city`;
CREATE TABLE `city` (
  `city_id` int(10) NOT NULL,
  `city_name` varchar(50) NOT NULL,
  `province_id` varchar(20) NOT NULL,
  `first_letter` varchar(20) DEFAULT NULL,
  `is_hot` int(10) NOT NULL DEFAULT '0',
  `state` int(10) NOT NULL DEFAULT '1',
  PRIMARY KEY (`city_id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Table structure for global_data
-- ----------------------------
DROP TABLE IF EXISTS `global_data`;
CREATE TABLE `global_data` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `global_key` varchar(255) DEFAULT NULL,
  `global_value` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for kernel_points_manage_log
-- ----------------------------
DROP TABLE IF EXISTS `kernel_points_manage_log`;
CREATE TABLE `kernel_points_manage_log` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `uuid` char(32) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `app_id` varchar(20) DEFAULT NULL,
  `operator` varchar(20) DEFAULT NULL,
  `points` int(11) DEFAULT NULL,
  `mark` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `uuid` (`uuid`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for kernel_points_used_log
-- ----------------------------
DROP TABLE IF EXISTS `kernel_points_used_log`;
CREATE TABLE `kernel_points_used_log` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `uuid` char(32) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `app_id` varchar(20) DEFAULT NULL,
  `points` int(11) unsigned DEFAULT NULL,
  `mark` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `uuid` (`uuid`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for kernel_school_auto_count
-- ----------------------------
DROP TABLE IF EXISTS `kernel_school_auto_count`;
CREATE TABLE `kernel_school_auto_count` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `school_code` char(12) DEFAULT NULL,
  `type` tinyint(1) unsigned DEFAULT NULL,
  `count` int(11) unsigned DEFAULT NULL,
  `points` int(11) unsigned DEFAULT NULL,
  `remainder` int(11) unsigned DEFAULT NULL,
  `activity_count` int(11) unsigned DEFAULT NULL,
  `count_type` tinyint(1) unsigned DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `school_code` (`school_code`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for kernel_school_recycle_count
-- ----------------------------
DROP TABLE IF EXISTS `kernel_school_recycle_count`;
CREATE TABLE `kernel_school_recycle_count` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `school_code` char(12) DEFAULT NULL,
  `type` tinyint(1) unsigned DEFAULT NULL,
  `count` int(11) unsigned DEFAULT NULL,
  `points` int(11) unsigned DEFAULT NULL,
  `remainder` int(11) unsigned DEFAULT NULL,
  `activity_count` int(11) unsigned DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `school_code` (`school_code`,`type`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for kernel_student_data
-- ----------------------------
DROP TABLE IF EXISTS `kernel_student_data`;
CREATE TABLE `kernel_student_data` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `uuid` char(32) NOT NULL,
  `identity_card` char(18) DEFAULT NULL,
  `student_code` char(19) DEFAULT NULL,
  `card` varchar(40) DEFAULT NULL,
  `code` varchar(40) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `type` tinyint(1) unsigned zerofill NOT NULL DEFAULT '0',
  `school_code` char(12) NOT NULL,
  `binding` tinyint(1) unsigned zerofill NOT NULL,
  `active` tinyint(1) unsigned zerofill NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `first_time` datetime DEFAULT NULL,
  `last_time` datetime DEFAULT NULL,
  `activity_count` int(11) unsigned zerofill NOT NULL,
  `total_points` int(11) unsigned zerofill NOT NULL,
  `used_points` int(11) unsigned zerofill NOT NULL,
  `deduct_points` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uuid` (`uuid`) USING HASH,
  UNIQUE KEY `identity_card` (`identity_card`) USING HASH,
  UNIQUE KEY `student_code` (`student_code`) USING HASH,
  UNIQUE KEY `card_code` (`card`,`code`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for kernel_student_inform
-- ----------------------------
DROP TABLE IF EXISTS `kernel_student_inform`;
CREATE TABLE `kernel_student_inform` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `sex` char(4) DEFAULT NULL,
  `student_code` char(20) DEFAULT NULL,
  `identity_card` char(19) DEFAULT NULL,
  `native_place` varchar(20) DEFAULT NULL,
  `nation` varchar(20) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `school_name` varchar(50) DEFAULT NULL,
  `school_id` char(11) DEFAULT NULL,
  `birthday` varchar(20) DEFAULT NULL,
  `birthplace` varchar(20) DEFAULT NULL,
  `nationality` varchar(50) DEFAULT NULL,
  `identity_type` varchar(20) DEFAULT NULL,
  `grade` varchar(4) DEFAULT NULL,
  `classS` varchar(4) DEFAULT NULL,
  `overseas_chinese` char(4) DEFAULT NULL,
  `politics_status` varchar(5) DEFAULT NULL,
  `health` varchar(10) DEFAULT NULL,
  `census_register` varchar(20) DEFAULT NULL,
  `census_register_type` varchar(20) DEFAULT NULL,
  `enrollment_time` varchar(20) DEFAULT NULL,
  `entrance_way` varchar(10) DEFAULT NULL,
  `studying_way` varchar(10) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `contact_address` varchar(255) DEFAULT NULL,
  `residence` varchar(255) DEFAULT NULL,
  `postal_code` char(12) DEFAULT NULL,
  `workers_children` char(4) DEFAULT NULL,
  `only_child` char(4) DEFAULT NULL,
  `preschool_education` char(4) DEFAULT NULL,
  `leftover_children` char(4) DEFAULT NULL,
  `orphan` char(4) DEFAULT NULL,
  `martyr_children` char(4) CHARACTER SET utf8mb4 DEFAULT NULL,
  `funding` char(4) DEFAULT NULL,
  `boarder_alimony` char(4) DEFAULT NULL,
  `auxiliary_number` varchar(20) DEFAULT NULL,
  `student_number` varchar(20) DEFAULT NULL,
  `student_source` varchar(20) DEFAULT NULL,
  `learning_class` char(4) DEFAULT NULL,
  `disability_types` varchar(50) DEFAULT '',
  `family_name` varchar(20) DEFAULT NULL,
  `family_relationship` varchar(20) DEFAULT NULL,
  `family_work` varchar(20) DEFAULT NULL,
  `family_residence` varchar(255) DEFAULT NULL,
  `family_census_register` varchar(255) DEFAULT NULL,
  `family_phone` varchar(50) DEFAULT NULL,
  `teacher_phone` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `student_code` (`student_code`) USING HASH,
  UNIQUE KEY `identity_card` (`identity_card`) USING HASH,
UNIQUE KEY `teacher_phone` (`teacher_phone`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for kernel_student_recycle_count
-- ----------------------------
DROP TABLE IF EXISTS `kernel_student_recycle_count`;
CREATE TABLE `kernel_student_recycle_count` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `uuid` char(32) NOT NULL,
  `type` tinyint(1) unsigned NOT NULL,
  `count` int(11) unsigned NOT NULL,
  `points` int(11) unsigned NOT NULL,
  `remainder` int(11) unsigned NOT NULL,
  `activity_count` int(11) unsigned NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uuid` (`uuid`,`type`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for machine_device_management
-- ----------------------------
DROP TABLE IF EXISTS `machine_device_management`;
CREATE TABLE `machine_device_management` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `machine_id` char(14) DEFAULT NULL,
  `serial_number` varchar(50) DEFAULT NULL,
  `school_code` char(12) DEFAULT NULL,
  `postal_code` char(6) DEFAULT NULL,
  `active` tinyint(1) unsigned DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `machine_id` (`machine_id`) USING HASH,
  KEY `serial_number` (`serial_number`) USING HASH,
  KEY `school_code` (`school_code`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for machine_device_recycle_count
-- ----------------------------
DROP TABLE IF EXISTS `machine_device_recycle_count`;
CREATE TABLE `machine_device_recycle_count` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `machine_id` char(14) DEFAULT NULL,
  `type` tinyint(1) unsigned DEFAULT NULL,
  `count` int(11) unsigned DEFAULT NULL,
  `points` int(11) unsigned DEFAULT NULL,
  `remainder` int(11) unsigned DEFAULT NULL,
  `activity_count` int(11) unsigned DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `machine_id` (`machine_id`,`type`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for machine_device_recycle_log
-- ----------------------------
DROP TABLE IF EXISTS `machine_device_recycle_log`;
CREATE TABLE `machine_device_recycle_log` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `machine_id` char(14) NOT NULL,
  `schoolid_of_device` char(12) NOT NULL,
  `uuid` char(32) NOT NULL,
  `schoolid_of_student` char(12) NOT NULL,
  `recycle_type` tinyint(1) unsigned NOT NULL,
  `identity_type` tinyint(1) unsigned NOT NULL,
  `count` int(11) unsigned NOT NULL,
  `last_remainder` int(11) unsigned NOT NULL,
  `factor` int(11) unsigned NOT NULL,
  `points` int(11) unsigned NOT NULL,
  `remainder` int(11) unsigned NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `machine_id` (`machine_id`) USING HASH,
  KEY `schoolid_of_device` (`schoolid_of_device`) USING HASH,
  KEY `uuid` (`uuid`) USING HASH,
  KEY `schoolid_of_student` (`schoolid_of_student`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for machine_device_state
-- ----------------------------
DROP TABLE IF EXISTS `machine_device_state`;
CREATE TABLE `machine_device_state` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `serial_number` varchar(50) DEFAULT NULL,
  `status` tinyint(1) unsigned zerofill DEFAULT NULL,
  `status_type` tinyint(1) unsigned DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `serial_number` (`serial_number`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for machine_device_state_log
-- ----------------------------
DROP TABLE IF EXISTS `machine_device_state_log`;
CREATE TABLE `machine_device_state_log` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `serial_number` varchar(50) DEFAULT NULL,
  `status` tinyint(1) unsigned zerofill DEFAULT NULL,
  `status_type` tinyint(1) unsigned DEFAULT NULL,
  `mark` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for management_application_management
-- ----------------------------
DROP TABLE IF EXISTS `management_application_management`;
CREATE TABLE `management_application_management` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `app_id` varchar(20) DEFAULT NULL,
  `deskey` char(16) DEFAULT NULL,
  `ivkey` char(8) DEFAULT NULL,
  `app_type` tinyint(1) unsigned zerofill NOT NULL DEFAULT '0',
  `app_name` varchar(20) DEFAULT NULL,
  `active` tinyint(1) unsigned zerofill NOT NULL DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `app_id` (`app_id`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for province
-- ----------------------------
DROP TABLE IF EXISTS `province`;
CREATE TABLE `province` (
  `province_id` varchar(20) NOT NULL,
  `province_name` varchar(50) NOT NULL,
  PRIMARY KEY (`province_id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Table structure for region
-- ----------------------------
DROP TABLE IF EXISTS `region`;
CREATE TABLE `region` (
  `region_id` int(10) NOT NULL,
  `region_name` varchar(50) NOT NULL COMMENT '地区名称',
  `city_id` int(10) NOT NULL DEFAULT '0' COMMENT '父地区ID',
  PRIMARY KEY (`region_id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Table structure for upload_log
-- ----------------------------
DROP TABLE IF EXISTS `upload_log`;
CREATE TABLE `upload_log` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `file` varchar(255) NOT NULL,
  `app_id` varchar(255) NOT NULL,
  `operator` varchar(255) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8;
