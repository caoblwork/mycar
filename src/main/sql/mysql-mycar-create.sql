/*
Source Server         : localhost
Source Server Version : 50525
Source Host           : localhost:3306
Source Database       : mycar

Target Server Type    : MYSQL
Target Server Version : 50525
File Encoding         : 65001
*/

SET FOREIGN_KEY_CHECKS=0;

CREATE TABLE BATCH_JOB_INSTANCE  (
	JOB_INSTANCE_ID BIGINT  NOT NULL PRIMARY KEY ,
	VERSION BIGINT ,
	JOB_NAME VARCHAR(100) NOT NULL,
	JOB_KEY VARCHAR(32) NOT NULL,
	constraint JOB_INST_UN unique (JOB_NAME, JOB_KEY)
) ENGINE=InnoDB;

CREATE TABLE BATCH_JOB_EXECUTION  (
	JOB_EXECUTION_ID BIGINT  NOT NULL PRIMARY KEY ,
	VERSION BIGINT  ,
	JOB_INSTANCE_ID BIGINT NOT NULL,
	CREATE_TIME DATETIME NOT NULL,
	START_TIME DATETIME DEFAULT NULL ,
	END_TIME DATETIME DEFAULT NULL ,
	STATUS VARCHAR(10) ,
	EXIT_CODE VARCHAR(100) ,
	EXIT_MESSAGE VARCHAR(2500) ,
	LAST_UPDATED DATETIME,
	constraint JOB_INST_EXEC_FK foreign key (JOB_INSTANCE_ID)
	references BATCH_JOB_INSTANCE(JOB_INSTANCE_ID)
) ENGINE=InnoDB;

CREATE TABLE BATCH_JOB_EXECUTION_PARAMS  (
	JOB_EXECUTION_ID BIGINT NOT NULL ,
	TYPE_CD VARCHAR(6) NOT NULL ,
	KEY_NAME VARCHAR(100) NOT NULL ,
	STRING_VAL VARCHAR(250) ,
	DATE_VAL DATETIME DEFAULT NULL ,
	LONG_VAL BIGINT ,
	DOUBLE_VAL DOUBLE PRECISION ,
	IDENTIFYING CHAR(1) NOT NULL ,
	constraint JOB_EXEC_PARAMS_FK foreign key (JOB_EXECUTION_ID)
	references BATCH_JOB_EXECUTION(JOB_EXECUTION_ID)
) ENGINE=InnoDB;

CREATE TABLE BATCH_STEP_EXECUTION  (
	STEP_EXECUTION_ID BIGINT  NOT NULL PRIMARY KEY ,
	VERSION BIGINT NOT NULL,
	STEP_NAME VARCHAR(100) NOT NULL,
	JOB_EXECUTION_ID BIGINT NOT NULL,
	START_TIME DATETIME NOT NULL ,
	END_TIME DATETIME DEFAULT NULL ,
	STATUS VARCHAR(10) ,
	COMMIT_COUNT BIGINT ,
	READ_COUNT BIGINT ,
	FILTER_COUNT BIGINT ,
	WRITE_COUNT BIGINT ,
	READ_SKIP_COUNT BIGINT ,
	WRITE_SKIP_COUNT BIGINT ,
	PROCESS_SKIP_COUNT BIGINT ,
	ROLLBACK_COUNT BIGINT ,
	EXIT_CODE VARCHAR(100) ,
	EXIT_MESSAGE VARCHAR(2500) ,
	LAST_UPDATED DATETIME,
	constraint JOB_EXEC_STEP_FK foreign key (JOB_EXECUTION_ID)
	references BATCH_JOB_EXECUTION(JOB_EXECUTION_ID)
) ENGINE=InnoDB;

CREATE TABLE BATCH_STEP_EXECUTION_CONTEXT  (
	STEP_EXECUTION_ID BIGINT NOT NULL PRIMARY KEY,
	SHORT_CONTEXT VARCHAR(2500) NOT NULL,
	SERIALIZED_CONTEXT TEXT ,
	constraint STEP_EXEC_CTX_FK foreign key (STEP_EXECUTION_ID)
	references BATCH_STEP_EXECUTION(STEP_EXECUTION_ID)
) ENGINE=InnoDB;

CREATE TABLE BATCH_JOB_EXECUTION_CONTEXT  (
	JOB_EXECUTION_ID BIGINT NOT NULL PRIMARY KEY,
	SHORT_CONTEXT VARCHAR(2500) NOT NULL,
	SERIALIZED_CONTEXT TEXT ,
	constraint JOB_EXEC_CTX_FK foreign key (JOB_EXECUTION_ID)
	references BATCH_JOB_EXECUTION(JOB_EXECUTION_ID)
) ENGINE=InnoDB;

CREATE TABLE BATCH_STEP_EXECUTION_SEQ (ID BIGINT NOT NULL) ENGINE=MYISAM;
INSERT INTO BATCH_STEP_EXECUTION_SEQ values(0);
CREATE TABLE BATCH_JOB_EXECUTION_SEQ (ID BIGINT NOT NULL) ENGINE=MYISAM;
INSERT INTO BATCH_JOB_EXECUTION_SEQ values(0);
CREATE TABLE BATCH_JOB_SEQ (ID BIGINT NOT NULL) ENGINE=MYISAM;
INSERT INTO BATCH_JOB_SEQ values(0);

DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `persistent_logins` VALUES ('yingzhor@gmail.com', 'nMseLYEjkNsRSbUT/gYNsQ==', 'WR1DYKaEu3B+SU2JyVLWlQ==', '2013-11-13 15:54:50');

DROP TABLE IF EXISTS `tbl_badge`;
CREATE TABLE `tbl_badge` (
  `BADGE_ID` varchar(32) NOT NULL,
  `BADGE_NAME` varchar(100) DEFAULT NULL,
  `BADGE_DESC` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`BADGE_ID`),
  UNIQUE KEY `UK_dxntykgja8kit0g8fbolf2biy` (`BADGE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `tbl_badge` VALUES ('AE102C5803354F78AD58B67C3C7B5248', '车主', '第一次计入车辆信息时获得');
INSERT INTO `tbl_badge` VALUES ('D9EB6D643CDB4B67BA828503E60FEC2E', '开发者', '这个系统的开发人员，很牛屄的说');

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

INSERT INTO `tbl_car` VALUES ('1', '大众', '湘HP3962', '5630', '帕萨特2.0T御尊', '帕萨特', '856938', 'LSVCJ6A4XDN028845', '1', 'M', '2013-11-10 21:16:17', '1');

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

INSERT INTO `tbl_contact` VALUES ('1', '(200336)吴中路259号锦江大众4S店', '2013-09-12 11:22:38', '冯舒磊', '135-1217-9801', '销售主管', '1', '2013-10-12 10:59:36', '1');
INSERT INTO `tbl_contact` VALUES ('2', '(200000)中春路7198号B3-19号路怡汽车专营店', '2013-09-12 15:37:47', '李国华', '138-1725-7786', '业务员', '1', '2013-10-12 10:59:36', '1');
INSERT INTO `tbl_contact` VALUES ('3', '吴中路259号锦江大众4S店', '2013-11-09 06:40:05', '锦江大众保养预约', '021-64280617', '', '1', '2013-11-09 06:40:36', '1');

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

INSERT INTO `tbl_user` VALUES ('1', 'yingzhor@gmail.com', '1', '1', '2013-10-24 16:36:39', '127.0.0.1', 'yz yingzhuo', '应卓', '9e21b083aff2ea3acdd9cf9df5a91141', null, '7', '1');

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

INSERT INTO `tbl_user_badge` VALUES ('1', 'AE102C5803354F78AD58B67C3C7B5248');
INSERT INTO `tbl_user_badge` VALUES ('1', 'D9EB6D643CDB4B67BA828503E60FEC2E');

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

INSERT INTO `tbl_user_habit` VALUES ('1', '1', '4', '0', '100', 'desc', 'date', '1');
INSERT INTO `tbl_user_habit` VALUES ('2', '0', '0', '0', '50', 'asc', 'date', '0');
INSERT INTO `tbl_user_habit` VALUES ('3', '0', '0', '0', '50', 'asc', 'date', '0');
