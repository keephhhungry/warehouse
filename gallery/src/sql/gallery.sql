/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : localhost:3306
 Source Schema         : gallery

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 06/12/2020 21:26:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_art_owner
-- ----------------------------
DROP TABLE IF EXISTS `t_art_owner`;
CREATE TABLE `t_art_owner`  (
  `owner_id` int(0) NOT NULL AUTO_INCREMENT,
  `artwork_id` int(0) NULL DEFAULT NULL,
  `owner_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `owners_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`owner_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_art_owner
-- ----------------------------
INSERT INTO `t_art_owner` VALUES (1, 1, 'artist', 1);
INSERT INTO `t_art_owner` VALUES (2, 2, 'artist', 2);
INSERT INTO `t_art_owner` VALUES (3, 3, 'artist', 3);
INSERT INTO `t_art_owner` VALUES (4, 6, 'collector', 1);
INSERT INTO `t_art_owner` VALUES (5, 7, 'collector', 1);
INSERT INTO `t_art_owner` VALUES (6, 8, 'collector', 1);

-- ----------------------------
-- Table structure for t_artist
-- ----------------------------
DROP TABLE IF EXISTS `t_artist`;
CREATE TABLE `t_artist`  (
  `artist_id` int(0) NOT NULL AUTO_INCREMENT,
  `artist_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `artist_phone_number` varchar(11) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `artist_gender` varchar(4) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`artist_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_artist
-- ----------------------------
INSERT INTO `t_artist` VALUES (1, 'Da Vinci', '13003956566', 'male');
INSERT INTO `t_artist` VALUES (2, 'Van Gogh', '13003956566', 'male');
INSERT INTO `t_artist` VALUES (3, 'Monet', '13003956566', 'male');
INSERT INTO `t_artist` VALUES (4, 'Xu Beihong', '13003956566', 'male');
INSERT INTO `t_artist` VALUES (9, 'Cao Cao', '13008775422', 'male');

-- ----------------------------
-- Table structure for t_artwork
-- ----------------------------
DROP TABLE IF EXISTS `t_artwork`;
CREATE TABLE `t_artwork`  (
  `artwork_id` int(0) NOT NULL AUTO_INCREMENT,
  `artwork_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `price` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `date_received` datetime(0) NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`artwork_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_artwork
-- ----------------------------
INSERT INTO `t_artwork` VALUES (1, 'Mona Lisa smile', '1000', 'Mona Lisa smile', 'painting', '2020-12-05 15:12:21', 'not_sold');
INSERT INTO `t_artwork` VALUES (2, 'The Last Supper', '1000', 'The Last Supper', 'painting', '2020-12-05 00:00:00', 'not_sold');
INSERT INTO `t_artwork` VALUES (3, 'Eight Horses', '2000', 'Eight Horses', 'painting', '2020-12-05 00:00:00', 'not_sold');
INSERT INTO `t_artwork` VALUES (6, 'Hammer Manuscript', '2000', 'Hammer Manuscript', 'painting', '2020-12-06 00:00:00', 'not_for_sell');
INSERT INTO `t_artwork` VALUES (7, 'Starry night', '3000', 'Starry night', 'painting', '2020-12-06 00:00:00', 'not_for_sell');
INSERT INTO `t_artwork` VALUES (8, 'sunflower', '4000', 'sunflower', 'painting', '2020-12-06 00:00:00', 'not_for_sell');

-- ----------------------------
-- Table structure for t_bill
-- ----------------------------
DROP TABLE IF EXISTS `t_bill`;
CREATE TABLE `t_bill`  (
  `bill_id` int(0) NOT NULL AUTO_INCREMENT,
  `customer_id` int(0) NULL DEFAULT NULL,
  `artist_id` int(0) NULL DEFAULT NULL,
  `artwork_id` int(0) NULL DEFAULT NULL,
  `pay_money` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `creat_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`bill_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_bill
-- ----------------------------
INSERT INTO `t_bill` VALUES (2, 1, 3, 3, '2000.0', '2020-12-06 00:00:00');
INSERT INTO `t_bill` VALUES (3, 1, 3, 3, '2000.0', '2020-12-06 00:00:00');

-- ----------------------------
-- Table structure for t_collector
-- ----------------------------
DROP TABLE IF EXISTS `t_collector`;
CREATE TABLE `t_collector`  (
  `collector_id` int(0) NOT NULL AUTO_INCREMENT,
  `collector_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `collector_phone_number` varchar(11) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `collector_gender` varchar(8) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`collector_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_collector
-- ----------------------------
INSERT INTO `t_collector` VALUES (1, 'collectorA', '13003956566', 'male');
INSERT INTO `t_collector` VALUES (2, 'collectorB', '13003956566', 'Female');

-- ----------------------------
-- Table structure for t_customer
-- ----------------------------
DROP TABLE IF EXISTS `t_customer`;
CREATE TABLE `t_customer`  (
  `customer_id` int(0) NOT NULL AUTO_INCREMENT,
  `customer_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `customer_gender` varchar(8) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `customer_phone_number` varchar(11) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `customer_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`customer_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_customer
-- ----------------------------
INSERT INTO `t_customer` VALUES (1, 'customerA', 'male', '13003956566', 'Haiding district, Beijing');
INSERT INTO `t_customer` VALUES (2, 'customerB', 'male', '13003956566', 'Haiding district, Beijing');
INSERT INTO `t_customer` VALUES (3, 'customerC', 'male', '13003956566', 'Haiding district, Beijing');
INSERT INTO `t_customer` VALUES (4, 'customerD', 'male', '13003956566', 'Haiding district, Beijing');
INSERT INTO `t_customer` VALUES (5, 'customerE', 'male', '13003956566', 'Haiding district, Beijing');
INSERT INTO `t_customer` VALUES (7, 'customerF', 'male', '1230123001', 'Haiding district, Beijing');

-- ----------------------------
-- Table structure for t_employee
-- ----------------------------
DROP TABLE IF EXISTS `t_employee`;
CREATE TABLE `t_employee`  (
  `employee_id` int(0) NOT NULL,
  `employee_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `employee_gender` varchar(8) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `employee_phone_number` varchar(11) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `employee_level` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`employee_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_employee
-- ----------------------------
INSERT INTO `t_employee` VALUES (1, 'boss', 'male', '13911111111', 'Gallery owner');
INSERT INTO `t_employee` VALUES (2, 'saleA', 'male', '13922222222', 'Sales Assistant');
INSERT INTO `t_employee` VALUES (3, 'saleB', 'male', '13933333333', 'Sales Assistant');
INSERT INTO `t_employee` VALUES (4, 'saleC', 'male', '13944444444', 'Sales Assistant');
INSERT INTO `t_employee` VALUES (5, 'saleD', 'male', '13955555555', 'Sales Assistant');
INSERT INTO `t_employee` VALUES (6, 'staffE', 'male', '13966666666', 'Office staff');
INSERT INTO `t_employee` VALUES (7, 'staffF', 'male', '13977777777', 'Office staff');

-- ----------------------------
-- Table structure for t_exhibit
-- ----------------------------
DROP TABLE IF EXISTS `t_exhibit`;
CREATE TABLE `t_exhibit`  (
  `exhibit_id` int(0) NOT NULL,
  `exhibit_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `exhibit_theme` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `exhibit_begin_date` date NULL DEFAULT NULL,
  `exhibit_time` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`exhibit_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_income
-- ----------------------------
DROP TABLE IF EXISTS `t_income`;
CREATE TABLE `t_income`  (
  `income_id` int(0) NOT NULL AUTO_INCREMENT,
  `customer_id` int(0) NULL DEFAULT NULL,
  `artist_id` int(0) NULL DEFAULT NULL,
  `artwork_id` int(0) NULL DEFAULT NULL,
  `employee_id` int(0) NULL DEFAULT NULL,
  `receive_money` double(255, 0) NULL DEFAULT NULL,
  `creat_time` datetime(0) NULL DEFAULT NULL,
  `income_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`income_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
