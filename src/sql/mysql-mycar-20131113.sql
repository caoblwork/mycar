/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50525
Source Host           : zt-2009222:3306
Source Database       : mycar

Target Server Type    : MYSQL
Target Server Version : 50525
File Encoding         : 65001

Date: 2013-11-13 15:59:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `batch_job_execution`
-- ----------------------------
DROP TABLE IF EXISTS `batch_job_execution`;
CREATE TABLE `batch_job_execution` (
  `JOB_EXECUTION_ID` bigint(20) NOT NULL,
  `VERSION` bigint(20) DEFAULT NULL,
  `JOB_INSTANCE_ID` bigint(20) NOT NULL,
  `CREATE_TIME` datetime NOT NULL,
  `START_TIME` datetime DEFAULT NULL,
  `END_TIME` datetime DEFAULT NULL,
  `STATUS` varchar(10) DEFAULT NULL,
  `EXIT_CODE` varchar(100) DEFAULT NULL,
  `EXIT_MESSAGE` varchar(2500) DEFAULT NULL,
  `LAST_UPDATED` datetime DEFAULT NULL,
  PRIMARY KEY (`JOB_EXECUTION_ID`),
  KEY `JOB_INST_EXEC_FK` (`JOB_INSTANCE_ID`),
  CONSTRAINT `JOB_INST_EXEC_FK` FOREIGN KEY (`JOB_INSTANCE_ID`) REFERENCES `batch_job_instance` (`JOB_INSTANCE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of batch_job_execution
-- ----------------------------
INSERT INTO `batch_job_execution` VALUES ('1', '2', '1', '2013-11-11 02:35:23', '2013-11-11 02:35:23', '2013-11-11 02:35:24', 'COMPLETED', 'COMPLETED', '', '2013-11-11 02:35:24');
INSERT INTO `batch_job_execution` VALUES ('2', '2', '2', '2013-11-11 02:38:55', '2013-11-11 02:38:55', '2013-11-11 02:38:56', 'COMPLETED', 'COMPLETED', '', '2013-11-11 02:38:56');
INSERT INTO `batch_job_execution` VALUES ('3', '2', '3', '2013-11-11 02:40:58', '2013-11-11 02:40:59', '2013-11-11 02:40:59', 'COMPLETED', 'COMPLETED', '', '2013-11-11 02:40:59');
INSERT INTO `batch_job_execution` VALUES ('4', '2', '4', '2013-11-11 02:42:37', '2013-11-11 02:42:37', '2013-11-11 02:42:38', 'COMPLETED', 'COMPLETED', '', '2013-11-11 02:42:38');
INSERT INTO `batch_job_execution` VALUES ('5', '2', '5', '2013-11-11 02:51:59', '2013-11-11 02:51:59', '2013-11-11 02:52:00', 'COMPLETED', 'COMPLETED', '', '2013-11-11 02:52:00');

-- ----------------------------
-- Table structure for `batch_job_execution_context`
-- ----------------------------
DROP TABLE IF EXISTS `batch_job_execution_context`;
CREATE TABLE `batch_job_execution_context` (
  `JOB_EXECUTION_ID` bigint(20) NOT NULL,
  `SHORT_CONTEXT` varchar(2500) NOT NULL,
  `SERIALIZED_CONTEXT` text,
  PRIMARY KEY (`JOB_EXECUTION_ID`),
  CONSTRAINT `JOB_EXEC_CTX_FK` FOREIGN KEY (`JOB_EXECUTION_ID`) REFERENCES `batch_job_execution` (`JOB_EXECUTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of batch_job_execution_context
-- ----------------------------
INSERT INTO `batch_job_execution_context` VALUES ('1', '{\"map\":[\"\"]}', null);
INSERT INTO `batch_job_execution_context` VALUES ('2', '{\"map\":[\"\"]}', null);
INSERT INTO `batch_job_execution_context` VALUES ('3', '{\"map\":[\"\"]}', null);
INSERT INTO `batch_job_execution_context` VALUES ('4', '{\"map\":[\"\"]}', null);
INSERT INTO `batch_job_execution_context` VALUES ('5', '{\"map\":[\"\"]}', null);

-- ----------------------------
-- Table structure for `batch_job_execution_params`
-- ----------------------------
DROP TABLE IF EXISTS `batch_job_execution_params`;
CREATE TABLE `batch_job_execution_params` (
  `JOB_EXECUTION_ID` bigint(20) NOT NULL,
  `TYPE_CD` varchar(6) NOT NULL,
  `KEY_NAME` varchar(100) NOT NULL,
  `STRING_VAL` varchar(250) DEFAULT NULL,
  `DATE_VAL` datetime DEFAULT NULL,
  `LONG_VAL` bigint(20) DEFAULT NULL,
  `DOUBLE_VAL` double DEFAULT NULL,
  `IDENTIFYING` char(1) NOT NULL,
  KEY `JOB_EXEC_PARAMS_FK` (`JOB_EXECUTION_ID`),
  CONSTRAINT `JOB_EXEC_PARAMS_FK` FOREIGN KEY (`JOB_EXECUTION_ID`) REFERENCES `batch_job_execution` (`JOB_EXECUTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of batch_job_execution_params
-- ----------------------------
INSERT INTO `batch_job_execution_params` VALUES ('1', 'STRING', 'random', '10802998', '1970-01-01 08:00:00', '0', '0', 'Y');
INSERT INTO `batch_job_execution_params` VALUES ('1', 'STRING', 'targetFile', 'E:/Users/ying/.mycar_log/batch/active-users/active-users.2013-11-10.csv', '1970-01-01 08:00:00', '0', '0', 'Y');
INSERT INTO `batch_job_execution_params` VALUES ('2', 'STRING', 'random', '22055359', '1970-01-01 08:00:00', '0', '0', 'Y');
INSERT INTO `batch_job_execution_params` VALUES ('2', 'STRING', 'targetFile', 'E:/Users/ying/.mycar_log/batch/active-users/active-users.2013-11-10.csv', '1970-01-01 08:00:00', '0', '0', 'Y');
INSERT INTO `batch_job_execution_params` VALUES ('3', 'STRING', 'random', '17520325', '1970-01-01 08:00:00', '0', '0', 'Y');
INSERT INTO `batch_job_execution_params` VALUES ('3', 'STRING', 'targetFile', 'E:/Users/ying/.mycar_log/batch/active-users/active-users.2013-11-10.csv', '1970-01-01 08:00:00', '0', '0', 'Y');
INSERT INTO `batch_job_execution_params` VALUES ('4', 'STRING', 'random', '80588878', '1970-01-01 08:00:00', '0', '0', 'Y');
INSERT INTO `batch_job_execution_params` VALUES ('4', 'STRING', 'targetFile', 'E:/Users/ying/.mycar_log/batch/active-users/active-users.2013-11-10.csv', '1970-01-01 08:00:00', '0', '0', 'Y');
INSERT INTO `batch_job_execution_params` VALUES ('5', 'LONG', 'timestamp', '', '1970-01-01 08:00:00', '1384109519576', '0', 'Y');

-- ----------------------------
-- Table structure for `batch_job_execution_seq`
-- ----------------------------
DROP TABLE IF EXISTS `batch_job_execution_seq`;
CREATE TABLE `batch_job_execution_seq` (
  `ID` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of batch_job_execution_seq
-- ----------------------------
INSERT INTO `batch_job_execution_seq` VALUES ('5');

-- ----------------------------
-- Table structure for `batch_job_instance`
-- ----------------------------
DROP TABLE IF EXISTS `batch_job_instance`;
CREATE TABLE `batch_job_instance` (
  `JOB_INSTANCE_ID` bigint(20) NOT NULL,
  `VERSION` bigint(20) DEFAULT NULL,
  `JOB_NAME` varchar(100) NOT NULL,
  `JOB_KEY` varchar(32) NOT NULL,
  PRIMARY KEY (`JOB_INSTANCE_ID`),
  UNIQUE KEY `JOB_INST_UN` (`JOB_NAME`,`JOB_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of batch_job_instance
-- ----------------------------
INSERT INTO `batch_job_instance` VALUES ('1', '0', 'importActiveUsersRecord', 'b5772d458d680532b2851c5ef3d2299b');
INSERT INTO `batch_job_instance` VALUES ('2', '0', 'importActiveUsersRecord', 'e68b4b03c84033351725db8bae557ffd');
INSERT INTO `batch_job_instance` VALUES ('3', '0', 'importActiveUsersRecord', '4ac4cf0e117e4bb05ab93334dfbb45e1');
INSERT INTO `batch_job_instance` VALUES ('4', '0', 'importActiveUsersRecord', 'f96966fb32ce9f8f20892f31eb98efe9');
INSERT INTO `batch_job_instance` VALUES ('5', '0', 'grantBadgeJob', 'f750a8abad22583e58a51cee1bc35d68');

-- ----------------------------
-- Table structure for `batch_job_seq`
-- ----------------------------
DROP TABLE IF EXISTS `batch_job_seq`;
CREATE TABLE `batch_job_seq` (
  `ID` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of batch_job_seq
-- ----------------------------
INSERT INTO `batch_job_seq` VALUES ('5');

-- ----------------------------
-- Table structure for `batch_step_execution`
-- ----------------------------
DROP TABLE IF EXISTS `batch_step_execution`;
CREATE TABLE `batch_step_execution` (
  `STEP_EXECUTION_ID` bigint(20) NOT NULL,
  `VERSION` bigint(20) NOT NULL,
  `STEP_NAME` varchar(100) NOT NULL,
  `JOB_EXECUTION_ID` bigint(20) NOT NULL,
  `START_TIME` datetime NOT NULL,
  `END_TIME` datetime DEFAULT NULL,
  `STATUS` varchar(10) DEFAULT NULL,
  `COMMIT_COUNT` bigint(20) DEFAULT NULL,
  `READ_COUNT` bigint(20) DEFAULT NULL,
  `FILTER_COUNT` bigint(20) DEFAULT NULL,
  `WRITE_COUNT` bigint(20) DEFAULT NULL,
  `READ_SKIP_COUNT` bigint(20) DEFAULT NULL,
  `WRITE_SKIP_COUNT` bigint(20) DEFAULT NULL,
  `PROCESS_SKIP_COUNT` bigint(20) DEFAULT NULL,
  `ROLLBACK_COUNT` bigint(20) DEFAULT NULL,
  `EXIT_CODE` varchar(100) DEFAULT NULL,
  `EXIT_MESSAGE` varchar(2500) DEFAULT NULL,
  `LAST_UPDATED` datetime DEFAULT NULL,
  PRIMARY KEY (`STEP_EXECUTION_ID`),
  KEY `JOB_EXEC_STEP_FK` (`JOB_EXECUTION_ID`),
  CONSTRAINT `JOB_EXEC_STEP_FK` FOREIGN KEY (`JOB_EXECUTION_ID`) REFERENCES `batch_job_execution` (`JOB_EXECUTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of batch_step_execution
-- ----------------------------
INSERT INTO `batch_step_execution` VALUES ('1', '3', 'workhorse', '1', '2013-11-11 02:35:23', '2013-11-11 02:35:24', 'COMPLETED', '1', '1', '0', '1', '0', '0', '0', '0', 'COMPLETED', '', '2013-11-11 02:35:24');
INSERT INTO `batch_step_execution` VALUES ('2', '3', 'clean', '1', '2013-11-11 02:35:24', '2013-11-11 02:35:24', 'COMPLETED', '1', '0', '0', '0', '0', '0', '0', '0', 'COMPLETED', '', '2013-11-11 02:35:24');
INSERT INTO `batch_step_execution` VALUES ('3', '4', 'workhorse', '2', '2013-11-11 02:38:55', '2013-11-11 02:38:56', 'COMPLETED', '2', '164', '0', '164', '0', '0', '0', '0', 'COMPLETED', '', '2013-11-11 02:38:56');
INSERT INTO `batch_step_execution` VALUES ('4', '3', 'clean', '2', '2013-11-11 02:38:56', '2013-11-11 02:38:56', 'COMPLETED', '1', '0', '0', '0', '0', '0', '0', '0', 'COMPLETED', '', '2013-11-11 02:38:56');
INSERT INTO `batch_step_execution` VALUES ('5', '3', 'workhorse', '3', '2013-11-11 02:40:59', '2013-11-11 02:40:59', 'COMPLETED', '1', '1', '0', '1', '0', '0', '0', '0', 'COMPLETED', '', '2013-11-11 02:40:59');
INSERT INTO `batch_step_execution` VALUES ('6', '3', 'clean', '3', '2013-11-11 02:40:59', '2013-11-11 02:40:59', 'COMPLETED', '1', '0', '0', '0', '0', '0', '0', '0', 'COMPLETED', '', '2013-11-11 02:40:59');
INSERT INTO `batch_step_execution` VALUES ('7', '4', 'workhorse', '4', '2013-11-11 02:42:37', '2013-11-11 02:42:38', 'COMPLETED', '2', '164', '0', '164', '0', '0', '0', '0', 'COMPLETED', '', '2013-11-11 02:42:38');
INSERT INTO `batch_step_execution` VALUES ('8', '3', 'clean', '4', '2013-11-11 02:42:38', '2013-11-11 02:42:38', 'COMPLETED', '1', '0', '0', '0', '0', '0', '0', '0', 'COMPLETED', '', '2013-11-11 02:42:38');
INSERT INTO `batch_step_execution` VALUES ('9', '3', 'workhorse', '5', '2013-11-11 02:51:59', '2013-11-11 02:52:00', 'COMPLETED', '1', '1', '0', '1', '0', '0', '0', '0', 'COMPLETED', '', '2013-11-11 02:52:00');

-- ----------------------------
-- Table structure for `batch_step_execution_context`
-- ----------------------------
DROP TABLE IF EXISTS `batch_step_execution_context`;
CREATE TABLE `batch_step_execution_context` (
  `STEP_EXECUTION_ID` bigint(20) NOT NULL,
  `SHORT_CONTEXT` varchar(2500) NOT NULL,
  `SERIALIZED_CONTEXT` text,
  PRIMARY KEY (`STEP_EXECUTION_ID`),
  CONSTRAINT `STEP_EXEC_CTX_FK` FOREIGN KEY (`STEP_EXECUTION_ID`) REFERENCES `batch_step_execution` (`STEP_EXECUTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of batch_step_execution_context
-- ----------------------------
INSERT INTO `batch_step_execution_context` VALUES ('1', '{\"map\":[{\"entry\":{\"string\":\"JpaPagingItemReader.read.count\",\"int\":2}}]}', null);
INSERT INTO `batch_step_execution_context` VALUES ('2', '{\"map\":[\"\"]}', null);
INSERT INTO `batch_step_execution_context` VALUES ('3', '{\"map\":[{\"entry\":{\"string\":\"FlatFileItemReader.read.count\",\"int\":165}}]}', null);
INSERT INTO `batch_step_execution_context` VALUES ('4', '{\"map\":[\"\"]}', null);
INSERT INTO `batch_step_execution_context` VALUES ('5', '{\"map\":[{\"entry\":{\"string\":\"JpaPagingItemReader.read.count\",\"int\":2}}]}', null);
INSERT INTO `batch_step_execution_context` VALUES ('6', '{\"map\":[\"\"]}', null);
INSERT INTO `batch_step_execution_context` VALUES ('7', '{\"map\":[{\"entry\":{\"string\":\"FlatFileItemReader.read.count\",\"int\":165}}]}', null);
INSERT INTO `batch_step_execution_context` VALUES ('8', '{\"map\":[\"\"]}', null);
INSERT INTO `batch_step_execution_context` VALUES ('9', '{\"map\":[{\"entry\":{\"string\":\"JpaPagingItemReader.read.count\",\"int\":2}}]}', null);

-- ----------------------------
-- Table structure for `batch_step_execution_seq`
-- ----------------------------
DROP TABLE IF EXISTS `batch_step_execution_seq`;
CREATE TABLE `batch_step_execution_seq` (
  `ID` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of batch_step_execution_seq
-- ----------------------------
INSERT INTO `batch_step_execution_seq` VALUES ('9');

-- ----------------------------
-- Table structure for `persistent_logins`
-- ----------------------------
DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of persistent_logins
-- ----------------------------
INSERT INTO `persistent_logins` VALUES ('yingzhor@gmail.com', 'nMseLYEjkNsRSbUT/gYNsQ==', 'WR1DYKaEu3B+SU2JyVLWlQ==', '2013-11-13 15:54:50');

-- ----------------------------
-- Table structure for `tbl_badge`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_badge`;
CREATE TABLE `tbl_badge` (
  `BADGE_ID` varchar(32) NOT NULL,
  `BADGE_NAME` varchar(100) DEFAULT NULL,
  `BADGE_DESC` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`BADGE_ID`),
  UNIQUE KEY `UK_dxntykgja8kit0g8fbolf2biy` (`BADGE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_badge
-- ----------------------------
INSERT INTO `tbl_badge` VALUES ('AE102C5803354F78AD58B67C3C7B5248', '车主', '第一次计入车辆信息时获得');
INSERT INTO `tbl_badge` VALUES ('D9EB6D643CDB4B67BA828503E60FEC2E', '开发者', '这个系统的开发人员，很牛屄的说');

-- ----------------------------
-- Table structure for `tbl_car`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_car`;
CREATE TABLE `tbl_car` (
  `CAR_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CAR_BRAND` varchar(255) DEFAULT NULL,
  `CAR_LICENSE` varchar(255) DEFAULT NULL,
  `CAR_MILEAGE` int(11) DEFAULT NULL,
  `CAR_MODEL_TYPE` varchar(255) DEFAULT NULL,
  `CAR_NAME` varchar(20) DEFAULT NULL,
  `CAR_ENGINE` varchar(255) DEFAULT NULL,
  `CAR_FRAME` varchar(255) DEFAULT NULL,
  `CAR_OWNER_ID` int(11) DEFAULT NULL,
  `CAR_TYPE` varchar(5) DEFAULT NULL,
  `CAR_LAST_MODIFIED_TIME` datetime DEFAULT NULL,
  `CAR_LAST_MODIFIED_BY` int(11) DEFAULT NULL,
  PRIMARY KEY (`CAR_ID`),
  KEY `FK_hbi6wa9ex8iefqwas9p5jrs5d` (`CAR_OWNER_ID`),
  KEY `FK_9fdxatd8hutbcadthivf1idhk` (`CAR_LAST_MODIFIED_BY`),
  CONSTRAINT `FK_9fdxatd8hutbcadthivf1idhk` FOREIGN KEY (`CAR_LAST_MODIFIED_BY`) REFERENCES `tbl_user` (`USER_ID`),
  CONSTRAINT `FK_hbi6wa9ex8iefqwas9p5jrs5d` FOREIGN KEY (`CAR_OWNER_ID`) REFERENCES `tbl_user` (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_car
-- ----------------------------
INSERT INTO `tbl_car` VALUES ('1', '大众', '湘HP3962', '5630', '帕萨特2.0T御尊', '帕萨特', '856938', 'LSVCJ6A4XDN028845', '1', 'M', '2013-11-10 21:16:17', '1');

-- ----------------------------
-- Table structure for `tbl_contact`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_contact`;
CREATE TABLE `tbl_contact` (
  `CONTACT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CONTACT_ADDRESS` varchar(255) DEFAULT NULL,
  `CONTACT_CREATED_DATE` datetime DEFAULT NULL,
  `CONTACT_NAME` varchar(255) DEFAULT NULL,
  `CONTACT_PHONE_NUMBERS` varchar(255) DEFAULT NULL,
  `CONTACT_TITLE` varchar(255) DEFAULT NULL,
  `CONTACT_OWNER_ID` int(11) DEFAULT NULL,
  `CONTACT_LAST_MODIFIED_TIME` datetime DEFAULT NULL,
  `CONTACT_LAST_MODIFIED_BY` int(11) DEFAULT NULL,
  PRIMARY KEY (`CONTACT_ID`),
  KEY `FK_l9t06vmiq426yx3gmkwkn7scb` (`CONTACT_OWNER_ID`),
  KEY `FK_kru4y4awemt1q25qw1xuo1r3o` (`CONTACT_LAST_MODIFIED_BY`),
  CONSTRAINT `FK_kru4y4awemt1q25qw1xuo1r3o` FOREIGN KEY (`CONTACT_LAST_MODIFIED_BY`) REFERENCES `tbl_user` (`USER_ID`),
  CONSTRAINT `FK_l9t06vmiq426yx3gmkwkn7scb` FOREIGN KEY (`CONTACT_OWNER_ID`) REFERENCES `tbl_user` (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_contact
-- ----------------------------
INSERT INTO `tbl_contact` VALUES ('1', '(200336)吴中路259号锦江大众4S店', '2013-09-12 11:22:38', '冯舒磊', '135-1217-9801', '销售主管', '1', '2013-10-12 10:59:36', '1');
INSERT INTO `tbl_contact` VALUES ('2', '(200000)中春路7198号B3-19号路怡汽车专营店', '2013-09-12 15:37:47', '李国华', '138-1725-7786', '业务员', '1', '2013-10-12 10:59:36', '1');
INSERT INTO `tbl_contact` VALUES ('3', '吴中路259号锦江大众4S店', '2013-11-09 06:40:05', '锦江大众保养预约', '021-64280617', '', '1', '2013-11-09 06:40:36', '1');

-- ----------------------------
-- Table structure for `tbl_cost`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_cost`;
CREATE TABLE `tbl_cost` (
  `COST_ID` int(11) NOT NULL AUTO_INCREMENT,
  `COST_VERSION` bigint(20) NOT NULL,
  `COST_TYPE` varchar(31) NOT NULL,
  `COST_PAYMENT_TYPE` varchar(20) DEFAULT NULL,
  `COST_CAR_ID` int(11) NOT NULL,
  `COST_DATE` date NOT NULL,
  `COST_LAST_MODIFIED_BY` int(11) DEFAULT NULL,
  `COST_LAST_MODIFIED_TIME` datetime DEFAULT NULL,
  `COST_RECORDING_DATE` date NOT NULL,
  `COST_SUM` double NOT NULL,
  `COST_COMMENT` varchar(50) DEFAULT NULL,
  `COST_LOC_CITY` varchar(30) DEFAULT NULL,
  `COST_LOC_STREET` varchar(200) DEFAULT NULL,
  `COST_LOC_ZIP` varchar(20) DEFAULT NULL,
  `COST_GAS_PRICE` double DEFAULT NULL,
  `COST_GAS_FILLINGTYPE` varchar(15) DEFAULT NULL,
  `COST_GAS_TYPE` varchar(15) DEFAULT NULL,
  `COST_GAS_CUBAGE` double DEFAULT NULL,
  `COST_TOLL_MILEAGE` double DEFAULT NULL,
  `COST_FINE_POINT` int(11) DEFAULT NULL,
  PRIMARY KEY (`COST_ID`),
  KEY `FK_2kfwct4haaotsib668nmqc78l` (`COST_CAR_ID`),
  KEY `FK_4x9lvurse7ir43xqe4u4yka58` (`COST_LAST_MODIFIED_BY`),
  CONSTRAINT `FK_2kfwct4haaotsib668nmqc78l` FOREIGN KEY (`COST_CAR_ID`) REFERENCES `tbl_car` (`CAR_ID`),
  CONSTRAINT `FK_4x9lvurse7ir43xqe4u4yka58` FOREIGN KEY (`COST_LAST_MODIFIED_BY`) REFERENCES `tbl_user` (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_cost
-- ----------------------------
INSERT INTO `tbl_cost` VALUES ('1', '0', 'GAS', 'CASH', '1', '2013-03-05', '1', '2013-03-05 00:00:00', '2013-03-05', '530', '提车', null, null, null, '8.5', 'FULL', '_97', '62.4', null, null);
INSERT INTO `tbl_cost` VALUES ('2', '0', 'GAS', 'CASH', '1', '2013-03-17', '1', '2013-03-17 00:00:00', '2013-03-17', '300', null, null, null, null, '8.5', 'FIXED_MONEY', '_97', '35.3', null, null);
INSERT INTO `tbl_cost` VALUES ('3', '0', 'GAS', 'CASH', '1', '2013-03-27', '1', '2013-03-27 00:00:00', '2013-03-27', '300', null, null, null, null, '8.22', 'FIXED_MONEY', '_97', '36.5', null, null);
INSERT INTO `tbl_cost` VALUES ('4', '0', 'GAS', 'CASH', '1', '2013-04-09', '1', '2013-04-09 00:00:00', '2013-04-09', '300', null, null, null, null, '8.24', 'FIXED_MONEY', '_97', '36.4', null, null);
INSERT INTO `tbl_cost` VALUES ('5', '0', 'GAS', 'PREPAYMENT_CARD', '1', '2013-04-28', '1', '2013-04-28 00:00:00', '2013-04-28', '300', '第一次使用加油卡', null, null, null, '7.9', 'FIXED_MONEY', '_97', '38', null, null);
INSERT INTO `tbl_cost` VALUES ('6', '0', 'GAS', 'PREPAYMENT_CARD', '1', '2013-05-11', '1', '2013-05-11 00:00:00', '2013-05-11', '300', null, null, null, null, '7.98', 'FIXED_MONEY', '_97', '37.6', null, null);
INSERT INTO `tbl_cost` VALUES ('7', '0', 'GAS', 'CASH', '1', '2013-05-26', '1', '2013-05-26 00:00:00', '2013-05-26', '300', null, null, null, null, '7.98', 'FIXED_MONEY', '_97', '37.6', null, null);
INSERT INTO `tbl_cost` VALUES ('8', '0', 'GAS', 'CASH', '1', '2013-06-07', '1', '2013-06-07 00:00:00', '2013-06-07', '300', null, null, null, null, '7.9', 'FIXED_MONEY', '_97', '38', null, null);
INSERT INTO `tbl_cost` VALUES ('9', '0', 'GAS', 'CASH', '1', '2013-06-18', '1', '2013-06-18 00:00:00', '2013-06-18', '300', null, null, null, null, '7.9', 'FIXED_MONEY', '_97', '38', null, null);
INSERT INTO `tbl_cost` VALUES ('10', '0', 'GAS', 'CASH', '1', '2013-07-05', '1', '2013-07-05 00:00:00', '2013-07-05', '300', null, null, null, null, '7.99', 'FIXED_MONEY', '_97', '37.6', null, null);
INSERT INTO `tbl_cost` VALUES ('11', '0', 'WASHING', 'CASH', '1', '2013-04-04', '1', '2013-04-04 00:00:00', '2013-04-04', '20', '好评', null, null, null, null, null, null, null, null, null);
INSERT INTO `tbl_cost` VALUES ('12', '0', 'WASHING', 'CASH', '1', '2013-05-14', '1', '2013-05-14 00:00:00', '2013-05-14', '20', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `tbl_cost` VALUES ('13', '0', 'FINE', 'CASH', '1', '2013-06-26', '1', '2013-06-26 00:00:00', '2013-06-26', '200', '违章停车', null, null, null, null, null, null, null, null, '0');
INSERT INTO `tbl_cost` VALUES ('14', '0', 'PARKING', 'CASH', '1', '2013-06-26', '1', '2013-06-26 00:00:00', '2013-06-26', '35', '上海市静安区', null, null, null, null, null, null, null, null, null);
INSERT INTO `tbl_cost` VALUES ('15', '0', 'GAS', 'CASH', '1', '2013-08-03', '1', '2013-08-03 00:00:00', '2013-08-03', '300', null, null, null, null, '8.19', 'FIXED_MONEY', '_97', '36.6', null, null);
INSERT INTO `tbl_cost` VALUES ('16', '0', 'WASHING', 'CASH', '1', '2013-08-17', '1', '2013-08-17 00:00:00', '2013-08-17', '20', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `tbl_cost` VALUES ('17', '0', 'GAS', 'CASH', '1', '2013-08-23', '1', '2013-08-23 00:00:00', '2013-08-24', '300', null, null, null, null, '8.19', 'FIXED_MONEY', '_97', '36.6', null, null);
INSERT INTO `tbl_cost` VALUES ('18', '0', 'GAS', 'CASH', '1', '2013-09-13', '1', '2013-09-14 16:31:04', '2013-09-14', '300', null, null, null, null, '8.19', 'FIXED_MONEY', '_97', '36.6', null, null);
INSERT INTO `tbl_cost` VALUES ('19', '0', 'GAS', 'CASH', '1', '2013-10-13', '1', '2013-10-13 07:34:49', '2013-10-13', '300', '赠送消费地用券 面值5元', null, null, null, '8.26', 'FIXED_MONEY', '_97', '36.32', null, null);
INSERT INTO `tbl_cost` VALUES ('20', '0', 'WASHING', 'CASH', '1', '2013-10-26', '1', '2013-10-27 11:53:29', '2013-10-27', '20', '航新路吴中路路口', null, null, null, null, null, null, null, null, null);
INSERT INTO `tbl_cost` VALUES ('21', '0', 'GAS', 'CASH', '1', '2013-11-02', '1', '2013-11-02 20:44:29', '2013-11-02', '300', '改称95号汽油', null, null, null, '8.24', 'FIXED_MONEY', '_97', '36.41', null, null);
INSERT INTO `tbl_cost` VALUES ('22', '0', 'PARKING', 'CASH', '1', '2013-11-02', '1', '2013-11-02 20:44:55', '2013-11-02', '5', '航东路750弄小区-1', null, null, null, null, null, null, null, null, null);
INSERT INTO `tbl_cost` VALUES ('23', '0', 'PARKING', 'CASH', '1', '2013-11-02', '1', '2013-11-02 20:45:02', '2013-11-02', '5', '航东路750弄小区-2', null, null, null, null, null, null, null, null, null);
INSERT INTO `tbl_cost` VALUES ('24', '0', 'PARKING', 'CASH', '1', '2013-11-05', '1', '2013-11-05 16:37:01', '2013-11-05', '5', '航东路750弄小区', null, null, null, null, null, null, null, null, null);
INSERT INTO `tbl_cost` VALUES ('25', '0', 'PARKING', 'CASH', '1', '2013-11-10', '1', '2013-11-10 21:15:42', '2013-11-10', '5', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `tbl_cost` VALUES ('26', '0', 'PARKING', 'CASH', '1', '2013-11-12', '1', '2013-11-12 09:38:44', '2013-11-12', '5', null, null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for `tbl_user`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user`;
CREATE TABLE `tbl_user` (
  `USER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_EMAIL` varchar(30) NOT NULL,
  `USER_ENABLED` tinyint(1) DEFAULT NULL,
  `USER_SEX` int(11) DEFAULT NULL,
  `USER_LAST_LOGIN_DT` timestamp NULL DEFAULT NULL,
  `USER_LAST_LOGIN_IP` varchar(255) DEFAULT NULL,
  `USER_MEMONIC` varchar(30) DEFAULT NULL,
  `USER_NICKNAME` varchar(30) NOT NULL,
  `USER_PWD` varchar(32) NOT NULL,
  `USER_LOCKED_TIME` bigint(20) DEFAULT NULL,
  `USER_ROLES` bigint(20) NOT NULL,
  `USER_STAND_ALONE` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`),
  UNIQUE KEY `UK_2k0esorsidhpjqc6tce5p3plv` (`USER_EMAIL`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_user
-- ----------------------------
INSERT INTO `tbl_user` VALUES ('1', 'yingzhor@gmail.com', '1', '1', '2013-10-24 16:36:39', '127.0.0.1', 'yz yingzhuo', '应卓', '9e21b083aff2ea3acdd9cf9df5a91141', null, '7', '1');

-- ----------------------------
-- Table structure for `tbl_user_badge`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user_badge`;
CREATE TABLE `tbl_user_badge` (
  `USER_ID` int(11) NOT NULL,
  `BADGE_ID` varchar(32) NOT NULL,
  PRIMARY KEY (`USER_ID`,`BADGE_ID`),
  KEY `FK_sw0xcmswfbxmf2wm2oll5knvr` (`BADGE_ID`),
  KEY `FK_tk1o2lsd7i0ojfuttrj55ib38` (`USER_ID`),
  CONSTRAINT `FK_sw0xcmswfbxmf2wm2oll5knvr` FOREIGN KEY (`BADGE_ID`) REFERENCES `tbl_badge` (`BADGE_ID`),
  CONSTRAINT `FK_tk1o2lsd7i0ojfuttrj55ib38` FOREIGN KEY (`USER_ID`) REFERENCES `tbl_user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_user_badge
-- ----------------------------
INSERT INTO `tbl_user_badge` VALUES ('1', 'AE102C5803354F78AD58B67C3C7B5248');
INSERT INTO `tbl_user_badge` VALUES ('1', 'D9EB6D643CDB4B67BA828503E60FEC2E');

-- ----------------------------
-- Table structure for `tbl_user_habit`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user_habit`;
CREATE TABLE `tbl_user_habit` (
  `HABIT_ID` int(11) NOT NULL,
  `HABIT_DEFAULT_EDIT_MODE_FOR_CAR_LIST` tinyint(1) DEFAULT NULL,
  `HABIT_LAST_TAB_INDEX_FOR_COST_LIST` int(11) DEFAULT NULL,
  `HABIT_LAST_TAB_INDEX_FOR_CHART_1` int(11) DEFAULT NULL,
  `HABIT_PAGE_SIZE_FOR_COST_LIST` int(11) DEFAULT NULL,
  `HABIT_PAGE_SORT_DIR_FOR_COST_LIST` varchar(255) DEFAULT NULL,
  `HABIT_PAGE_SORT_FOR_COST_LIST` varchar(255) DEFAULT NULL,
  `HABIT_DEFAULT_EDIT_MODE_FOR_CONTACT_LIST` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`HABIT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_user_habit
-- ----------------------------
INSERT INTO `tbl_user_habit` VALUES ('1', '1', '4', '0', '100', 'desc', 'date', '1');
INSERT INTO `tbl_user_habit` VALUES ('2', '0', '0', '0', '50', 'asc', 'date', '0');
INSERT INTO `tbl_user_habit` VALUES ('3', '0', '0', '0', '50', 'asc', 'date', '0');
