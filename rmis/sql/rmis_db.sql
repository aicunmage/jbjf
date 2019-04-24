/*
Navicat MySQL Data Transfer

Source Server         : 阿里云-rmis
Source Server Version : 50670
Source Host           : rm-uf60965662rf7p9lwwo.mysql.rds.aliyuncs.com:3306
Source Database       : rmis_db

Target Server Type    : MYSQL
Target Server Version : 50670
File Encoding         : 65001

Date: 2019-04-24 17:43:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for apply_image
-- ----------------------------
DROP TABLE IF EXISTS `apply_image`;
CREATE TABLE `apply_image` (
  `id` varchar(50) DEFAULT NULL COMMENT 'id',
  `instance_uid` varchar(250) NOT NULL COMMENT '影像唯一号码',
  `series_uid` varchar(250) DEFAULT NULL COMMENT '序列唯一',
  `image_number` varchar(64) DEFAULT NULL COMMENT '影像序号',
  `image_file` text COMMENT '影像存储路径',
  `transfer_uid` varchar(64) DEFAULT NULL COMMENT '传输语法识别号',
  `class_uid` varchar(250) DEFAULT NULL COMMENT '影像格式ID',
  `backup_label` varchar(64) DEFAULT NULL COMMENT '影像备份标签',
  `source_ae` varchar(64) DEFAULT NULL COMMENT '上传仪器的AE-Title',
  `rcvd_date` varchar(64) DEFAULT NULL COMMENT '影像上传日期',
  `rcvd_time` varchar(64) DEFAULT NULL COMMENT '影像上传时间',
  `file_size` int(11) DEFAULT NULL COMMENT '影像大小',
  `transaction_uid` varchar(64) DEFAULT NULL COMMENT '存储协议交易唯一号',
  `imgae_deleted` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  `key_image` tinyint(1) DEFAULT NULL COMMENT '是否关键影像',
  `n_rows` int(11) DEFAULT NULL COMMENT '影像的水平像素',
  `n_columns` int(11) DEFAULT NULL COMMENT '影像的垂直像素',
  `bits_allocated` int(11) DEFAULT NULL COMMENT '影像的灰阶',
  `n_frames` int(11) DEFAULT NULL COMMENT '动态影像的影像格数',
  `pr_label` varchar(64) DEFAULT NULL COMMENT '影像呈现状态卷标',
  `pr_desc` varchar(64) DEFAULT NULL COMMENT '影像呈现状态说明',
  `pr_time` varchar(64) DEFAULT NULL COMMENT '影像呈现状态制作时间或影像生成的时间',
  `pr_date` varchar(64) DEFAULT NULL COMMENT '影像呈现状态制作日期或影像生成的日期',
  `pr_creator` varchar(64) DEFAULT NULL COMMENT '影像呈现状态制作人',
  `UPD_USER` varchar(30) DEFAULT NULL COMMENT '更新者',
  `UPD_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  `CRT_USER` varchar(30) DEFAULT NULL COMMENT '创建者',
  `CRT_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`instance_uid`),
  KEY `FK_Reference_30` (`series_uid`),
  CONSTRAINT `FK_Reference_30` FOREIGN KEY (`series_uid`) REFERENCES `apply_series` (`series_uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for apply_image_1113
-- ----------------------------
DROP TABLE IF EXISTS `apply_image_1113`;
CREATE TABLE `apply_image_1113` (
  `id` varchar(50) DEFAULT NULL COMMENT 'id',
  `instance_uid` varchar(250) NOT NULL COMMENT '影像唯一号码',
  `series_uid` varchar(250) DEFAULT NULL COMMENT '序列唯一',
  `image_number` varchar(64) DEFAULT NULL COMMENT '影像序号',
  `image_file` text COMMENT '影像存储路径',
  `transfer_uid` varchar(64) DEFAULT NULL COMMENT '传输语法识别号',
  `class_uid` varchar(250) DEFAULT NULL COMMENT '影像格式ID',
  `backup_label` varchar(64) DEFAULT NULL COMMENT '影像备份标签',
  `source_ae` varchar(64) DEFAULT NULL COMMENT '上传仪器的AE-Title',
  `rcvd_date` varchar(64) DEFAULT NULL COMMENT '影像上传日期',
  `rcvd_time` varchar(64) DEFAULT NULL COMMENT '影像上传时间',
  `file_size` int(11) DEFAULT NULL COMMENT '影像大小',
  `transaction_uid` varchar(64) DEFAULT NULL COMMENT '存储协议交易唯一号',
  `imgae_deleted` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  `key_image` tinyint(1) DEFAULT NULL COMMENT '是否关键影像',
  `n_rows` int(11) DEFAULT NULL COMMENT '影像的水平像素',
  `n_columns` int(11) DEFAULT NULL COMMENT '影像的垂直像素',
  `bits_allocated` int(11) DEFAULT NULL COMMENT '影像的灰阶',
  `n_frames` int(11) DEFAULT NULL COMMENT '动态影像的影像格数',
  `pr_label` varchar(64) DEFAULT NULL COMMENT '影像呈现状态卷标',
  `pr_desc` varchar(64) DEFAULT NULL COMMENT '影像呈现状态说明',
  `pr_time` varchar(64) DEFAULT NULL COMMENT '影像呈现状态制作时间或影像生成的时间',
  `pr_date` varchar(64) DEFAULT NULL COMMENT '影像呈现状态制作日期或影像生成的日期',
  `pr_creator` varchar(64) DEFAULT NULL COMMENT '影像呈现状态制作人',
  `UPD_USER` varchar(30) DEFAULT NULL COMMENT '更新者',
  `UPD_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  `CRT_USER` varchar(30) DEFAULT NULL COMMENT '创建者',
  `CRT_TIME` datetime DEFAULT NULL COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for apply_image_his
-- ----------------------------
DROP TABLE IF EXISTS `apply_image_his`;
CREATE TABLE `apply_image_his` (
  `id` varchar(50) DEFAULT NULL COMMENT 'id',
  `instance_uid` varchar(250) NOT NULL COMMENT '影像唯一号码',
  `series_uid` varchar(250) DEFAULT NULL COMMENT '序列唯一',
  `image_number` varchar(64) DEFAULT NULL COMMENT '影像序号',
  `image_file` text COMMENT '影像存储路径',
  `transfer_uid` varchar(64) DEFAULT NULL COMMENT '传输语法识别号',
  `class_uid` varchar(250) DEFAULT NULL COMMENT '影像格式ID',
  `backup_label` varchar(64) DEFAULT NULL COMMENT '影像备份标签',
  `source_ae` varchar(64) DEFAULT NULL COMMENT '上传仪器的AE-Title',
  `rcvd_date` varchar(64) DEFAULT NULL COMMENT '影像上传日期',
  `rcvd_time` varchar(64) DEFAULT NULL COMMENT '影像上传时间',
  `file_size` int(11) DEFAULT NULL COMMENT '影像大小',
  `transaction_uid` varchar(64) DEFAULT NULL COMMENT '存储协议交易唯一号',
  `imgae_deleted` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  `key_image` tinyint(1) DEFAULT NULL COMMENT '是否关键影像',
  `n_rows` int(11) DEFAULT NULL COMMENT '影像的水平像素',
  `n_columns` int(11) DEFAULT NULL COMMENT '影像的垂直像素',
  `bits_allocated` int(11) DEFAULT NULL COMMENT '影像的灰阶',
  `n_frames` int(11) DEFAULT NULL COMMENT '动态影像的影像格数',
  `pr_label` varchar(64) DEFAULT NULL COMMENT '影像呈现状态卷标',
  `pr_desc` varchar(64) DEFAULT NULL COMMENT '影像呈现状态说明',
  `pr_time` varchar(64) DEFAULT NULL COMMENT '影像呈现状态制作时间或影像生成的时间',
  `pr_date` varchar(64) DEFAULT NULL COMMENT '影像呈现状态制作日期或影像生成的日期',
  `pr_creator` varchar(64) DEFAULT NULL COMMENT '影像呈现状态制作人',
  `UPD_USER` varchar(30) DEFAULT NULL COMMENT '更新者',
  `UPD_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  `CRT_USER` varchar(30) DEFAULT NULL COMMENT '创建者',
  `CRT_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `transfer_date` datetime DEFAULT NULL,
  PRIMARY KEY (`instance_uid`),
  KEY `series_uid` (`series_uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for apply_patient
-- ----------------------------
DROP TABLE IF EXISTS `apply_patient`;
CREATE TABLE `apply_patient` (
  `id` varchar(50) DEFAULT NULL COMMENT 'ID',
  `ptn_id` varchar(64) NOT NULL COMMENT '患者Id',
  `ptn_name` varchar(64) DEFAULT NULL COMMENT '患者姓名',
  `study_uid` varchar(250) NOT NULL COMMENT '检查唯一',
  `sex` varchar(32) DEFAULT NULL COMMENT '患者性别',
  `birth_date` varchar(64) DEFAULT NULL COMMENT '患者出生日期',
  `ptn_size` varchar(64) DEFAULT NULL COMMENT '身高',
  `ptn_weight` varchar(64) DEFAULT NULL COMMENT '体重',
  `ptn_age` varchar(32) DEFAULT NULL COMMENT '患者年龄',
  `UPD_USER` varchar(30) DEFAULT NULL COMMENT '更新者',
  `UPD_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  `CRT_USER` varchar(30) DEFAULT NULL COMMENT '创建者',
  `CRT_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `ptn_id_dcm` text COMMENT 'dcm原ptnid',
  PRIMARY KEY (`ptn_id`),
  KEY `FK_Reference_31` (`study_uid`),
  CONSTRAINT `FK_Reference_31` FOREIGN KEY (`study_uid`) REFERENCES `apply_study` (`study_uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for apply_patient_his
-- ----------------------------
DROP TABLE IF EXISTS `apply_patient_his`;
CREATE TABLE `apply_patient_his` (
  `id` varchar(50) DEFAULT NULL COMMENT 'ID',
  `ptn_id` varchar(64) NOT NULL COMMENT '患者Id',
  `ptn_name` varchar(64) DEFAULT NULL COMMENT '患者姓名',
  `study_uid` varchar(250) NOT NULL COMMENT '检查唯一',
  `sex` varchar(32) DEFAULT NULL COMMENT '患者性别',
  `birth_date` varchar(64) DEFAULT NULL COMMENT '患者出生日期',
  `ptn_size` varchar(64) DEFAULT NULL COMMENT '身高',
  `ptn_weight` varchar(64) DEFAULT NULL COMMENT '体重',
  `ptn_age` varchar(32) DEFAULT NULL COMMENT '患者年龄',
  `UPD_USER` varchar(30) DEFAULT NULL COMMENT '更新者',
  `UPD_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  `CRT_USER` varchar(30) DEFAULT NULL COMMENT '创建者',
  `CRT_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `ptn_id_dcm` text COMMENT 'dcm原ptnid',
  `transfer_date` datetime DEFAULT NULL,
  PRIMARY KEY (`ptn_id`),
  KEY `study_uid` (`study_uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for apply_series
-- ----------------------------
DROP TABLE IF EXISTS `apply_series`;
CREATE TABLE `apply_series` (
  `id` varchar(50) DEFAULT NULL COMMENT 'id',
  `series_uid` varchar(250) NOT NULL COMMENT '序列唯一号码',
  `series_number` varchar(64) DEFAULT NULL COMMENT '序列序号',
  `study_uid` varchar(250) NOT NULL COMMENT '检查唯一',
  `modality` varchar(64) DEFAULT NULL COMMENT '仪器种类',
  `body_part` varchar(64) DEFAULT NULL COMMENT '检查部位',
  `series_date` varchar(64) DEFAULT NULL COMMENT '序列生成日期',
  `series_time` varchar(64) DEFAULT NULL COMMENT '序列生成时间',
  `series_desc` varchar(64) DEFAULT NULL COMMENT '序列描述',
  `req_proc_id` varchar(64) DEFAULT NULL COMMENT '开单项目检查号',
  `sched_proc_id` varchar(64) DEFAULT NULL COMMENT '排程项目检查号',
  `operator_name` varchar(64) DEFAULT NULL COMMENT '检查技师',
  `model_name` varchar(64) DEFAULT NULL COMMENT '仪器型号',
  `UPD_USER` varchar(30) DEFAULT NULL COMMENT '更新者',
  `UPD_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  `CRT_USER` varchar(30) DEFAULT NULL COMMENT '创建者',
  `CRT_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`series_uid`),
  KEY `FK_Reference_32` (`study_uid`),
  CONSTRAINT `FK_Reference_32` FOREIGN KEY (`study_uid`) REFERENCES `apply_study` (`study_uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for apply_series_his
-- ----------------------------
DROP TABLE IF EXISTS `apply_series_his`;
CREATE TABLE `apply_series_his` (
  `id` varchar(50) DEFAULT NULL COMMENT 'id',
  `series_uid` varchar(250) NOT NULL COMMENT '序列唯一号码',
  `series_number` varchar(64) DEFAULT NULL COMMENT '序列序号',
  `study_uid` varchar(250) NOT NULL COMMENT '检查唯一',
  `modality` varchar(64) DEFAULT NULL COMMENT '仪器种类',
  `body_part` varchar(64) DEFAULT NULL COMMENT '检查部位',
  `series_date` varchar(64) DEFAULT NULL COMMENT '序列生成日期',
  `series_time` varchar(64) DEFAULT NULL COMMENT '序列生成时间',
  `series_desc` varchar(64) DEFAULT NULL COMMENT '序列描述',
  `req_proc_id` varchar(64) DEFAULT NULL COMMENT '开单项目检查号',
  `sched_proc_id` varchar(64) DEFAULT NULL COMMENT '排程项目检查号',
  `operator_name` varchar(64) DEFAULT NULL COMMENT '检查技师',
  `model_name` varchar(64) DEFAULT NULL COMMENT '仪器型号',
  `UPD_USER` varchar(30) DEFAULT NULL COMMENT '更新者',
  `UPD_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  `CRT_USER` varchar(30) DEFAULT NULL COMMENT '创建者',
  `CRT_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `transfer_date` datetime DEFAULT NULL,
  PRIMARY KEY (`series_uid`),
  KEY `study_uid` (`study_uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for apply_study
-- ----------------------------
DROP TABLE IF EXISTS `apply_study`;
CREATE TABLE `apply_study` (
  `id` varchar(50) DEFAULT NULL COMMENT 'id',
  `study_uid` varchar(250) NOT NULL COMMENT '检查唯一号码',
  `study_date` varchar(64) DEFAULT NULL COMMENT '检查日期',
  `study_time` varchar(64) DEFAULT NULL COMMENT '检查时间',
  `study_id` varchar(250) DEFAULT NULL COMMENT '检查号',
  `accession_number` varchar(64) DEFAULT NULL COMMENT '检查流水号',
  `physician_name` varchar(64) DEFAULT NULL COMMENT '主治医生姓名',
  `check_describe` varchar(250) DEFAULT NULL COMMENT '检查描述',
  `user_group` varchar(64) DEFAULT NULL COMMENT '使用者群组',
  `UPD_USER` varchar(30) DEFAULT NULL COMMENT '创建者',
  `UPD_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  `CRT_USER` varchar(30) DEFAULT NULL COMMENT '创建者',
  `CRT_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`study_uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for apply_study_his
-- ----------------------------
DROP TABLE IF EXISTS `apply_study_his`;
CREATE TABLE `apply_study_his` (
  `id` varchar(50) DEFAULT NULL COMMENT 'id',
  `study_uid` varchar(250) NOT NULL COMMENT '检查唯一号码',
  `study_date` varchar(64) DEFAULT NULL COMMENT '检查日期',
  `study_time` varchar(64) DEFAULT NULL COMMENT '检查时间',
  `study_id` varchar(250) DEFAULT NULL COMMENT '检查号',
  `accession_number` varchar(64) DEFAULT NULL COMMENT '检查流水号',
  `physician_name` varchar(64) DEFAULT NULL COMMENT '主治医生姓名',
  `check_describe` varchar(250) DEFAULT NULL COMMENT '检查描述',
  `user_group` varchar(64) DEFAULT NULL COMMENT '使用者群组',
  `UPD_USER` varchar(30) DEFAULT NULL COMMENT '创建者',
  `UPD_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  `CRT_USER` varchar(30) DEFAULT NULL COMMENT '创建者',
  `CRT_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `transfer_date` datetime DEFAULT NULL,
  PRIMARY KEY (`study_uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for apply_worklist
-- ----------------------------
DROP TABLE IF EXISTS `apply_worklist`;
CREATE TABLE `apply_worklist` (
  `id` varchar(50) DEFAULT NULL COMMENT 'id',
  `ptn_id` varchar(64) DEFAULT NULL COMMENT '患者ID',
  `check_num` varchar(64) NOT NULL COMMENT '检查单号',
  `ptn_name` varchar(64) DEFAULT NULL COMMENT '患者姓名',
  `birth_date` date DEFAULT NULL COMMENT '出生日期',
  `sex` varchar(16) DEFAULT NULL COMMENT '患者性别',
  `sex_code` varchar(16) DEFAULT NULL COMMENT '性别code',
  `IS_NORMAL_IMAGE` tinyint(1) DEFAULT NULL COMMENT '是否有正常的影像',
  `hight` varchar(64) DEFAULT NULL COMMENT '患者身高',
  `weight` varchar(64) DEFAULT NULL COMMENT '患者体重',
  `announcements` varchar(128) DEFAULT NULL COMMENT '医疗注意事项',
  `allergy` varchar(128) DEFAULT NULL COMMENT '显影剂过敏项目',
  `occupation` varchar(64) DEFAULT NULL COMMENT '患者职业',
  `pgy_status` varchar(2) DEFAULT NULL COMMENT '患者怀孕状态',
  `modality` varchar(4) DEFAULT NULL COMMENT '仪器种类',
  `ae_title` varchar(64) DEFAULT NULL COMMENT '仪器AE_title',
  `study_time` datetime DEFAULT NULL COMMENT '排检时间',
  `physician_name` varchar(128) DEFAULT NULL COMMENT '检查医生',
  `exam` varchar(128) DEFAULT NULL COMMENT '检查项目',
  `body_part` varchar(128) DEFAULT NULL COMMENT '检查部位',
  `summary` varchar(128) DEFAULT NULL COMMENT '检查方法',
  `costs` decimal(10,0) DEFAULT NULL COMMENT '金额',
  `apply_doc` varchar(64) DEFAULT NULL COMMENT '上传医生账号',
  `apply_org` varchar(64) DEFAULT NULL COMMENT '上传机构',
  `apply_status` varchar(64) DEFAULT NULL COMMENT '当前状态',
  `study_uid` varchar(250) NOT NULL COMMENT '检查唯一',
  `apply_status_code` varchar(64) DEFAULT NULL COMMENT '申请状态code',
  `ptn_age` varchar(32) DEFAULT NULL COMMENT '患者年龄',
  `ptn_age_unit_code` varchar(32) DEFAULT NULL COMMENT '年龄单位code',
  `ptn_age_unit` varchar(32) DEFAULT NULL COMMENT '年龄单位',
  `UPD_USER` varchar(30) DEFAULT NULL COMMENT '更新者',
  `UPD_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  `CRT_USER` varchar(30) DEFAULT NULL COMMENT '创建者',
  `CRT_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `ptn_id_dcm` text COMMENT 'dcm原ptnid',
  `accession_number` varchar(64) DEFAULT NULL COMMENT 'dcm原检查流水号',
  PRIMARY KEY (`check_num`),
  KEY `FK_Reference_33` (`study_uid`),
  KEY `rds_idx_0` (`apply_org`,`study_time`),
  CONSTRAINT `FK_Reference_33` FOREIGN KEY (`study_uid`) REFERENCES `apply_study` (`study_uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for apply_worklist_his
-- ----------------------------
DROP TABLE IF EXISTS `apply_worklist_his`;
CREATE TABLE `apply_worklist_his` (
  `id` varchar(50) DEFAULT NULL COMMENT 'id',
  `ptn_id` varchar(64) DEFAULT NULL COMMENT '患者ID',
  `check_num` varchar(64) NOT NULL COMMENT '检查单号',
  `ptn_name` varchar(64) DEFAULT NULL COMMENT '患者姓名',
  `birth_date` date DEFAULT NULL COMMENT '出生日期',
  `sex` varchar(16) DEFAULT NULL COMMENT '患者性别',
  `sex_code` varchar(16) DEFAULT NULL COMMENT '性别code',
  `hight` varchar(64) DEFAULT NULL COMMENT '患者身高',
  `weight` varchar(64) DEFAULT NULL COMMENT '患者体重',
  `announcements` varchar(128) DEFAULT NULL COMMENT '医疗注意事项',
  `allergy` varchar(128) DEFAULT NULL COMMENT '显影剂过敏项目',
  `occupation` varchar(64) DEFAULT NULL COMMENT '患者职业',
  `pgy_status` varchar(2) DEFAULT NULL COMMENT '患者怀孕状态',
  `modality` varchar(4) DEFAULT NULL COMMENT '仪器种类',
  `ae_title` varchar(64) DEFAULT NULL COMMENT '仪器AE_title',
  `study_time` datetime DEFAULT NULL COMMENT '排检时间',
  `physician_name` varchar(128) DEFAULT NULL COMMENT '检查医生',
  `exam` varchar(128) DEFAULT NULL COMMENT '检查项目',
  `body_part` varchar(128) DEFAULT NULL COMMENT '检查部位',
  `summary` varchar(128) DEFAULT NULL COMMENT '检查方法',
  `costs` decimal(10,0) DEFAULT NULL COMMENT '金额',
  `apply_doc` varchar(64) DEFAULT NULL COMMENT '上传医生账号',
  `apply_org` varchar(64) DEFAULT NULL COMMENT '上传机构',
  `apply_status` varchar(64) DEFAULT NULL COMMENT '当前状态',
  `study_uid` varchar(250) NOT NULL COMMENT '检查唯一',
  `apply_status_code` varchar(64) DEFAULT NULL COMMENT '申请状态code',
  `ptn_age` varchar(32) DEFAULT NULL COMMENT '患者年龄',
  `ptn_age_unit_code` varchar(32) DEFAULT NULL COMMENT '年龄单位code',
  `ptn_age_unit` varchar(32) DEFAULT NULL COMMENT '年龄单位',
  `UPD_USER` varchar(30) DEFAULT NULL COMMENT '更新者',
  `UPD_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  `CRT_USER` varchar(30) DEFAULT NULL COMMENT '创建者',
  `CRT_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `IS_NORMAL_IMAGE` tinyint(1) DEFAULT NULL COMMENT '是否有正常的影像',
  `ptn_id_dcm` text COMMENT 'dcm原ptnid',
  `accession_number` varchar(64) DEFAULT NULL COMMENT 'dcm原检查流水号',
  `transfer_date` datetime DEFAULT NULL,
  PRIMARY KEY (`check_num`),
  KEY `study_uid` (`study_uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for business_series
-- ----------------------------
DROP TABLE IF EXISTS `business_series`;
CREATE TABLE `business_series` (
  `id` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '主键',
  `series_uid` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `laterality` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '体位',
  `body_part_examed` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '检查部位',
  `study_uid` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `modality` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '模态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间，采集得到',
  `status` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `study_uid` (`study_uid`(255)),
  KEY `series_uid` (`series_uid`(255))
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='检查表';

-- ----------------------------
-- Table structure for business_sop
-- ----------------------------
DROP TABLE IF EXISTS `business_sop`;
CREATE TABLE `business_sop` (
  `id` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '主键',
  `sop_uid` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `manufacurer` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '制造商',
  `file_path` varchar(512) COLLATE utf8_bin DEFAULT NULL COMMENT '图像文件路径',
  `series_uid` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间，采集得到',
  `status` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `series_uid` (`series_uid`(255)),
  KEY `sop_uid` (`sop_uid`(255))
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='图像表';

-- ----------------------------
-- Table structure for business_study
-- ----------------------------
DROP TABLE IF EXISTS `business_study`;
CREATE TABLE `business_study` (
  `id` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '主键',
  `study_uid` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `patient_id` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '病人ID',
  `study_id` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间，采集得到',
  `status` tinyint(1) DEFAULT '0' COMMENT '-1:删除 0：失效 1：生效',
  `institution_id` bigint(20) DEFAULT '0' COMMENT '关联机构Id',
  `institution_name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '医疗机构名称',
  PRIMARY KEY (`id`),
  KEY `study_uid` (`study_uid`(255))
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='检查表';

-- ----------------------------
-- Table structure for busin_checklist_accessory
-- ----------------------------
DROP TABLE IF EXISTS `busin_checklist_accessory`;
CREATE TABLE `busin_checklist_accessory` (
  `ID` varchar(50) DEFAULT NULL COMMENT 'ID',
  `NUM_ID` int(11) DEFAULT NULL COMMENT '序号',
  `FILE` text COMMENT '附件地址',
  `UPD_USER` varchar(64) DEFAULT NULL COMMENT '修改人',
  `UPD_TIME` datetime DEFAULT NULL COMMENT '修改时间',
  `CRT_USER` varchar(64) DEFAULT NULL COMMENT '创建人',
  `CRT_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `REMARK` varchar(128) DEFAULT NULL COMMENT '备注',
  KEY `FK_Reference_27` (`ID`),
  CONSTRAINT `FK_Reference_27` FOREIGN KEY (`ID`) REFERENCES `busin_patient_info` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='附件';

-- ----------------------------
-- Table structure for busin_checklist_index
-- ----------------------------
DROP TABLE IF EXISTS `busin_checklist_index`;
CREATE TABLE `busin_checklist_index` (
  `ID` varchar(50) DEFAULT NULL COMMENT 'ID',
  `ACCESSION_NUM` varchar(64) NOT NULL COMMENT '检查流水号',
  `CHECK_NUM` varchar(64) DEFAULT NULL COMMENT '检查单号',
  `PARTS_CODE` varchar(64) DEFAULT NULL COMMENT '检查部位Code',
  `PARTS` varchar(64) DEFAULT NULL COMMENT '检查部位',
  `SUMMARY_CODE` varchar(64) DEFAULT NULL COMMENT '检查方法Code',
  `SUMMARY` varchar(64) DEFAULT NULL COMMENT '检查方法',
  `EXAM_CODE` varchar(64) DEFAULT NULL COMMENT '检查项目Code',
  `EXAM` varchar(64) DEFAULT NULL COMMENT '检查项目',
  `COSTS` decimal(10,0) DEFAULT NULL COMMENT '金额',
  `STATUS_CODE` varchar(64) DEFAULT NULL COMMENT '当前状态Code',
  `STATUS` varchar(64) DEFAULT NULL COMMENT '当前状态',
  `STATUS_AI_CODE` varchar(64) DEFAULT NULL COMMENT 'AI状态Code',
  `STATUS_AI` varchar(64) DEFAULT NULL COMMENT 'AI状态',
  `PARTS_GROUP_CODE` varchar(64) DEFAULT NULL COMMENT '检查部位分组Code',
  `PARTS_GROUP` varchar(64) DEFAULT NULL COMMENT '检查部位分组',
  `TYPE_CODE` varchar(64) DEFAULT NULL COMMENT '检查类型code',
  `TYPE` varchar(64) DEFAULT NULL COMMENT '检查类型',
  `ISDELETE` tinyint(1) DEFAULT NULL COMMENT '逻辑删除',
  `UPD_USER` varchar(64) DEFAULT NULL COMMENT '修改人',
  `UPD_TIME` datetime DEFAULT NULL COMMENT '修改时间',
  `CRT_USER` varchar(64) DEFAULT NULL COMMENT '创建人',
  `CRT_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `remark` text,
  `DESCRIBE_BQ` text COMMENT '病情描述',
  `APPLY_TIME` datetime DEFAULT NULL COMMENT '申请时间',
  `IMG_NUM` int(128) DEFAULT NULL COMMENT '影像张数',
  `IMG_RECEIVE_TIME` datetime DEFAULT NULL COMMENT '图像接收时间',
  `APPLY_DOC_CODE` varchar(64) DEFAULT NULL COMMENT '申请医生Code',
  `APPLY_DOC` varchar(64) DEFAULT NULL COMMENT '申请医生',
  `APPLY_HOSP_CODE` varchar(64) DEFAULT NULL COMMENT '申请医院Code',
  `APPLY_HOSP` varchar(64) DEFAULT NULL COMMENT '申请医院',
  `REP_GROUP_ID` varchar(50) DEFAULT NULL COMMENT '报告分组ID',
  `JZ_FLAG` tinyint(1) DEFAULT '0' COMMENT '急诊',
  `VJ_FLAG` tinyint(1) DEFAULT '0' COMMENT '危急',
  `REPORT_DR` varchar(64) DEFAULT NULL COMMENT '报告医生',
  `REPORT_TIME` datetime DEFAULT NULL COMMENT '报告世间',
  `AUDIT_DR` varchar(64) DEFAULT NULL COMMENT '审核医生',
  `AUDIT_TIME` datetime DEFAULT NULL COMMENT '审核世间',
  `PRINT_DR` varchar(64) DEFAULT NULL COMMENT '打印医生',
  `PRINT_TIME` datetime DEFAULT NULL COMMENT '打印世间',
  `REFUSE_CODE` varchar(64) DEFAULT NULL COMMENT '拒绝字典code',
  `REFUSE_NAME` varchar(256) DEFAULT NULL COMMENT '拒绝name',
  `PTN_SOURCE` varchar(1) DEFAULT NULL COMMENT '患者来源 1：门诊   2：住院',
  `PAST_ILLNESS` text COMMENT '既往史',
  `accession_number_dcm` varchar(64) DEFAULT NULL COMMENT 'dcm原检查流水号',
  `DOC_LEVEL` int(11) DEFAULT '0' COMMENT '0--默认，代表普通报告医生处理；1--转报告医生处理，只有报告医生分组可以处理',
  `IS_REPULSE` tinyint(1) DEFAULT '0' COMMENT '是否由审核医生退回重写处理，0-否，1-是',
  PRIMARY KEY (`ACCESSION_NUM`),
  KEY `CHECK_NUM_PK` (`CHECK_NUM`) USING BTREE,
  KEY `index_name` (`APPLY_TIME`),
  KEY `idx_hospital` (`APPLY_HOSP_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='检查信息_记录';

-- ----------------------------
-- Table structure for busin_checklist_index_1112
-- ----------------------------
DROP TABLE IF EXISTS `busin_checklist_index_1112`;
CREATE TABLE `busin_checklist_index_1112` (
  `ID` varchar(50) DEFAULT NULL COMMENT 'ID',
  `ACCESSION_NUM` varchar(64) NOT NULL COMMENT '检查流水号',
  `CHECK_NUM` varchar(64) DEFAULT NULL COMMENT '检查单号',
  `PARTS_CODE` varchar(64) DEFAULT NULL COMMENT '检查部位Code',
  `PARTS` varchar(64) DEFAULT NULL COMMENT '检查部位',
  `SUMMARY_CODE` varchar(64) DEFAULT NULL COMMENT '检查方法Code',
  `SUMMARY` varchar(64) DEFAULT NULL COMMENT '检查方法',
  `EXAM_CODE` varchar(64) DEFAULT NULL COMMENT '检查项目Code',
  `EXAM` varchar(64) DEFAULT NULL COMMENT '检查项目',
  `COSTS` decimal(10,0) DEFAULT NULL COMMENT '金额',
  `STATUS_CODE` varchar(64) DEFAULT NULL COMMENT '当前状态Code',
  `STATUS` varchar(64) DEFAULT NULL COMMENT '当前状态',
  `STATUS_AI_CODE` varchar(64) DEFAULT NULL COMMENT 'AI状态Code',
  `STATUS_AI` varchar(64) DEFAULT NULL COMMENT 'AI状态',
  `PARTS_GROUP_CODE` varchar(64) DEFAULT NULL COMMENT '检查部位分组Code',
  `PARTS_GROUP` varchar(64) DEFAULT NULL COMMENT '检查部位分组',
  `TYPE_CODE` varchar(64) DEFAULT NULL COMMENT '检查类型code',
  `TYPE` varchar(64) DEFAULT NULL COMMENT '检查类型',
  `ISDELETE` tinyint(1) DEFAULT NULL COMMENT '逻辑删除',
  `UPD_USER` varchar(64) DEFAULT NULL COMMENT '修改人',
  `UPD_TIME` datetime DEFAULT NULL COMMENT '修改时间',
  `CRT_USER` varchar(64) DEFAULT NULL COMMENT '创建人',
  `CRT_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `remark` text,
  `DESCRIBE_BQ` text COMMENT '病情描述',
  `APPLY_TIME` datetime DEFAULT NULL COMMENT '申请时间',
  `IMG_NUM` int(128) DEFAULT NULL COMMENT '影像张数',
  `IMG_RECEIVE_TIME` datetime DEFAULT NULL COMMENT '图像接收时间',
  `APPLY_DOC_CODE` varchar(64) DEFAULT NULL COMMENT '申请医生Code',
  `APPLY_DOC` varchar(64) DEFAULT NULL COMMENT '申请医生',
  `APPLY_HOSP_CODE` varchar(64) DEFAULT NULL COMMENT '申请医院Code',
  `APPLY_HOSP` varchar(64) DEFAULT NULL COMMENT '申请医院',
  `REP_GROUP_ID` varchar(50) DEFAULT NULL COMMENT '报告分组ID',
  `JZ_FLAG` tinyint(1) DEFAULT '0' COMMENT '急诊',
  `VJ_FLAG` tinyint(1) DEFAULT '0' COMMENT '危急',
  `REPORT_DR` varchar(64) DEFAULT NULL COMMENT '报告医生',
  `REPORT_TIME` datetime DEFAULT NULL COMMENT '报告世间',
  `AUDIT_DR` varchar(64) DEFAULT NULL COMMENT '审核医生',
  `AUDIT_TIME` datetime DEFAULT NULL COMMENT '审核世间',
  `PRINT_DR` varchar(64) DEFAULT NULL COMMENT '打印医生',
  `PRINT_TIME` datetime DEFAULT NULL COMMENT '打印世间',
  `REFUSE_CODE` varchar(64) DEFAULT NULL COMMENT '拒绝字典code',
  `REFUSE_NAME` varchar(256) DEFAULT NULL COMMENT '拒绝name',
  `PTN_SOURCE` varchar(1) DEFAULT NULL COMMENT '患者来源 1：门诊   2：住院',
  `PAST_ILLNESS` text COMMENT '既往史',
  `accession_number_dcm` varchar(64) DEFAULT NULL COMMENT 'dcm原检查流水号',
  `DOC_LEVEL` int(11) DEFAULT '0' COMMENT '0--默认，代表普通报告医生处理；1--转报告医生处理，只有报告医生分组可以处理',
  `IS_REPULSE` tinyint(1) DEFAULT '0' COMMENT '是否由审核医生退回重写处理，0-否，1-是'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for busin_checklist_index_his
-- ----------------------------
DROP TABLE IF EXISTS `busin_checklist_index_his`;
CREATE TABLE `busin_checklist_index_his` (
  `ID` varchar(50) DEFAULT NULL COMMENT 'ID',
  `ACCESSION_NUM` varchar(64) NOT NULL COMMENT '检查流水号',
  `CHECK_NUM` varchar(64) DEFAULT NULL COMMENT '检查单号',
  `PARTS_CODE` varchar(64) DEFAULT NULL COMMENT '检查部位Code',
  `PARTS` varchar(64) DEFAULT NULL COMMENT '检查部位',
  `SUMMARY_CODE` varchar(64) DEFAULT NULL COMMENT '检查方法Code',
  `SUMMARY` varchar(64) DEFAULT NULL COMMENT '检查方法',
  `EXAM_CODE` varchar(64) DEFAULT NULL COMMENT '检查项目Code',
  `EXAM` varchar(64) DEFAULT NULL COMMENT '检查项目',
  `COSTS` decimal(10,0) DEFAULT NULL COMMENT '金额',
  `STATUS_CODE` varchar(64) DEFAULT NULL COMMENT '当前状态Code',
  `STATUS` varchar(64) DEFAULT NULL COMMENT '当前状态',
  `STATUS_AI_CODE` varchar(64) DEFAULT NULL COMMENT 'AI状态Code',
  `STATUS_AI` varchar(64) DEFAULT NULL COMMENT 'AI状态',
  `PARTS_GROUP_CODE` varchar(64) DEFAULT NULL COMMENT '检查部位分组Code',
  `PARTS_GROUP` varchar(64) DEFAULT NULL COMMENT '检查部位分组',
  `TYPE_CODE` varchar(64) DEFAULT NULL COMMENT '检查类型code',
  `TYPE` varchar(64) DEFAULT NULL COMMENT '检查类型',
  `ISDELETE` tinyint(1) DEFAULT NULL COMMENT '逻辑删除',
  `UPD_USER` varchar(64) DEFAULT NULL COMMENT '修改人',
  `UPD_TIME` datetime DEFAULT NULL COMMENT '修改时间',
  `CRT_USER` varchar(64) DEFAULT NULL COMMENT '创建人',
  `CRT_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `REMARK` varchar(2048) DEFAULT NULL COMMENT '备注',
  `DESCRIBE_BQ` text COMMENT '病情描述',
  `APPLY_TIME` datetime DEFAULT NULL COMMENT '申请时间',
  `IMG_NUM` int(128) DEFAULT NULL COMMENT '影像张数',
  `IMG_RECEIVE_TIME` datetime DEFAULT NULL COMMENT '图像接收时间',
  `APPLY_DOC_CODE` varchar(64) DEFAULT NULL COMMENT '申请医生Code',
  `APPLY_DOC` varchar(64) DEFAULT NULL COMMENT '申请医生',
  `APPLY_HOSP_CODE` varchar(64) DEFAULT NULL COMMENT '申请医院Code',
  `APPLY_HOSP` varchar(64) DEFAULT NULL COMMENT '申请医院',
  `REP_GROUP_ID` varchar(50) DEFAULT NULL COMMENT '报告分组ID',
  `JZ_FLAG` tinyint(1) DEFAULT '0' COMMENT '急诊',
  `VJ_FLAG` tinyint(1) DEFAULT '0' COMMENT '危急',
  `REPORT_DR` varchar(64) DEFAULT NULL COMMENT '报告医生',
  `REPORT_TIME` datetime DEFAULT NULL COMMENT '报告世间',
  `AUDIT_DR` varchar(64) DEFAULT NULL COMMENT '审核医生',
  `AUDIT_TIME` datetime DEFAULT NULL COMMENT '审核世间',
  `PRINT_DR` varchar(64) DEFAULT NULL COMMENT '打印医生',
  `PRINT_TIME` datetime DEFAULT NULL COMMENT '打印世间',
  `REFUSE_CODE` varchar(64) DEFAULT NULL COMMENT '拒绝字典code',
  `REFUSE_NAME` varchar(256) DEFAULT NULL COMMENT '拒绝name',
  `PTN_SOURCE` varchar(1) DEFAULT NULL COMMENT '患者来源 1：门诊   2：住院',
  `PAST_ILLNESS` text COMMENT '既往史',
  `accession_number_dcm` varchar(64) DEFAULT NULL COMMENT 'dcm原检查流水号',
  `DOC_LEVEL` int(11) DEFAULT '0' COMMENT '0--默认，代表普通报告医生处理；1--转报告医生处理，只有报告医生分组可以处理',
  `transfer_date` datetime DEFAULT NULL,
  `IS_REPULSE` tinyint(1) DEFAULT '0' COMMENT '是否由审核医生退回重写处理，0-否，1-是',
  PRIMARY KEY (`ACCESSION_NUM`),
  KEY `CHECK_NUM` (`CHECK_NUM`),
  KEY `APPLY_TIME` (`APPLY_TIME`),
  KEY `APPLY_HOSP_CODE` (`APPLY_HOSP_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for busin_checklist_items
-- ----------------------------
DROP TABLE IF EXISTS `busin_checklist_items`;
CREATE TABLE `busin_checklist_items` (
  `ID` varchar(50) NOT NULL COMMENT 'ID',
  `NUMBER_ID` int(11) DEFAULT NULL COMMENT '序号',
  `ACCESSION_NUM` varchar(64) DEFAULT NULL COMMENT '流水号',
  `PARTS_CODE` varchar(64) DEFAULT NULL COMMENT '检查部位Code',
  `PARTS` varchar(64) DEFAULT NULL COMMENT '检查部位',
  `PARTS_EN` varchar(64) DEFAULT NULL COMMENT '检查部位EN',
  `SUMMARY_CODE` varchar(64) DEFAULT NULL COMMENT '检查方法Code',
  `SUMMARY` varchar(64) DEFAULT NULL COMMENT '检查方法',
  `SUMMARY_EN` varchar(64) DEFAULT NULL COMMENT '检查方法EN',
  `BODY_ALIGN_CODE` varchar(64) DEFAULT NULL COMMENT '部位左右标识CODE',
  `BODY_ALIGN` varchar(64) DEFAULT NULL COMMENT '部位左右标识',
  `COSTS` decimal(10,0) DEFAULT NULL COMMENT '金额',
  `EXAM_ITEM_CODE` varchar(64) DEFAULT NULL COMMENT '检查项目Code',
  `EXAM_ITEM` varchar(64) DEFAULT NULL COMMENT '检查项目',
  `EXAM_ITEM_EN` varchar(64) DEFAULT NULL COMMENT '检查项目EN',
  `PARTSGROUP_CODE` varchar(64) DEFAULT NULL COMMENT '部位分组Code',
  `PARTSGROUP` varchar(64) DEFAULT NULL COMMENT '部位分组',
  `ISDELETE` tinyint(1) DEFAULT NULL COMMENT '逻辑删除',
  `UPD_USER` varchar(64) DEFAULT NULL COMMENT '修改人',
  `UPD_TIME` datetime DEFAULT NULL COMMENT '修改时间',
  `CRT_USER` varchar(64) DEFAULT NULL COMMENT '创建人',
  `CRT_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`ID`),
  KEY `FK_Reference_17` (`ACCESSION_NUM`),
  CONSTRAINT `FK_Reference_17` FOREIGN KEY (`ACCESSION_NUM`) REFERENCES `busin_checklist_index` (`ACCESSION_NUM`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='检查信息_明细';

-- ----------------------------
-- Table structure for busin_checklist_items_his
-- ----------------------------
DROP TABLE IF EXISTS `busin_checklist_items_his`;
CREATE TABLE `busin_checklist_items_his` (
  `ID` varchar(50) NOT NULL COMMENT 'ID',
  `NUMBER_ID` int(11) DEFAULT NULL COMMENT '序号',
  `ACCESSION_NUM` varchar(64) DEFAULT NULL COMMENT '流水号',
  `PARTS_CODE` varchar(64) DEFAULT NULL COMMENT '检查部位Code',
  `PARTS` varchar(64) DEFAULT NULL COMMENT '检查部位',
  `PARTS_EN` varchar(64) DEFAULT NULL COMMENT '检查部位EN',
  `SUMMARY_CODE` varchar(64) DEFAULT NULL COMMENT '检查方法Code',
  `SUMMARY` varchar(64) DEFAULT NULL COMMENT '检查方法',
  `SUMMARY_EN` varchar(64) DEFAULT NULL COMMENT '检查方法EN',
  `BODY_ALIGN_CODE` varchar(64) DEFAULT NULL COMMENT '部位左右标识CODE',
  `BODY_ALIGN` varchar(64) DEFAULT NULL COMMENT '部位左右标识',
  `COSTS` decimal(10,0) DEFAULT NULL COMMENT '金额',
  `EXAM_ITEM_CODE` varchar(64) DEFAULT NULL COMMENT '检查项目Code',
  `EXAM_ITEM` varchar(64) DEFAULT NULL COMMENT '检查项目',
  `EXAM_ITEM_EN` varchar(64) DEFAULT NULL COMMENT '检查项目EN',
  `PARTSGROUP_CODE` varchar(64) DEFAULT NULL COMMENT '部位分组Code',
  `PARTSGROUP` varchar(64) DEFAULT NULL COMMENT '部位分组',
  `ISDELETE` tinyint(1) DEFAULT NULL COMMENT '逻辑删除',
  `UPD_USER` varchar(64) DEFAULT NULL COMMENT '修改人',
  `UPD_TIME` datetime DEFAULT NULL COMMENT '修改时间',
  `CRT_USER` varchar(64) DEFAULT NULL COMMENT '创建人',
  `CRT_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `transfer_date` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ACCESSION_NUM` (`ACCESSION_NUM`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for busin_check_dmc_record
-- ----------------------------
DROP TABLE IF EXISTS `busin_check_dmc_record`;
CREATE TABLE `busin_check_dmc_record` (
  `ID` varchar(50) NOT NULL COMMENT 'ID',
  `ACCESSION_NUM` varchar(128) DEFAULT NULL COMMENT '流水号',
  `ACCESSION_NUM_IMG` text COMMENT '影像ACC',
  `UID_IMG` varchar(128) DEFAULT NULL COMMENT '影像UID',
  PRIMARY KEY (`ID`),
  KEY `FK_Reference_20` (`ACCESSION_NUM`),
  CONSTRAINT `FK_Reference_20` FOREIGN KEY (`ACCESSION_NUM`) REFERENCES `busin_checklist_index` (`ACCESSION_NUM`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='检查影像_绑定';

-- ----------------------------
-- Table structure for busin_check_flow_state
-- ----------------------------
DROP TABLE IF EXISTS `busin_check_flow_state`;
CREATE TABLE `busin_check_flow_state` (
  `ID` varchar(64) NOT NULL COMMENT '主键',
  `ACCESSION_NUM` varchar(64) DEFAULT NULL COMMENT '流水号',
  `NUMBER` int(11) DEFAULT NULL COMMENT '编号',
  `STATUS_CODE` varchar(50) DEFAULT NULL COMMENT '状态CODE',
  `STATUS` varchar(16) DEFAULT NULL COMMENT '状态',
  `OPERATION_USER` varchar(64) DEFAULT NULL COMMENT '操作人',
  `OPERATION_TIME` datetime DEFAULT NULL COMMENT '操作时间',
  `REMARK` varchar(128) DEFAULT NULL COMMENT '备注',
  `FINDING` text,
  `IMPRESSION` text COMMENT '诊断建议',
  `HP` varchar(6) DEFAULT NULL COMMENT '阴性阳性',
  PRIMARY KEY (`ID`),
  KEY `FK_Reference_21` (`ACCESSION_NUM`),
  CONSTRAINT `FK_Reference_21` FOREIGN KEY (`ACCESSION_NUM`) REFERENCES `busin_checklist_index` (`ACCESSION_NUM`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='检查流程状态_时间';

-- ----------------------------
-- Table structure for busin_check_flow_state_1112
-- ----------------------------
DROP TABLE IF EXISTS `busin_check_flow_state_1112`;
CREATE TABLE `busin_check_flow_state_1112` (
  `ID` varchar(64) NOT NULL COMMENT '主键',
  `ACCESSION_NUM` varchar(64) DEFAULT NULL COMMENT '流水号',
  `NUMBER` int(11) DEFAULT NULL COMMENT '编号',
  `STATUS_CODE` varchar(50) DEFAULT NULL COMMENT '状态CODE',
  `STATUS` varchar(16) DEFAULT NULL COMMENT '状态',
  `OPERATION_USER` varchar(64) DEFAULT NULL COMMENT '操作人',
  `OPERATION_TIME` datetime DEFAULT NULL COMMENT '操作时间',
  `REMARK` varchar(128) DEFAULT NULL COMMENT '备注',
  `FINDING` text,
  `IMPRESSION` text COMMENT '诊断建议',
  `HP` varchar(6) DEFAULT NULL COMMENT '阴性阳性'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for busin_check_flow_state_his
-- ----------------------------
DROP TABLE IF EXISTS `busin_check_flow_state_his`;
CREATE TABLE `busin_check_flow_state_his` (
  `ID` varchar(64) NOT NULL COMMENT '主键',
  `ACCESSION_NUM` varchar(64) DEFAULT NULL COMMENT '流水号',
  `NUMBER` int(11) DEFAULT NULL COMMENT '编号',
  `STATUS_CODE` varchar(50) DEFAULT NULL COMMENT '状态CODE',
  `STATUS` varchar(16) DEFAULT NULL COMMENT '状态',
  `OPERATION_USER` varchar(64) DEFAULT NULL COMMENT '操作人',
  `OPERATION_TIME` datetime DEFAULT NULL COMMENT '操作时间',
  `REMARK` varchar(128) DEFAULT NULL COMMENT '备注',
  `FINDING` text,
  `IMPRESSION` text COMMENT '诊断建议',
  `HP` varchar(6) DEFAULT NULL COMMENT '阴性阳性',
  `transfer_date` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ACCESSION_NUM` (`ACCESSION_NUM`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for busin_patient
-- ----------------------------
DROP TABLE IF EXISTS `busin_patient`;
CREATE TABLE `busin_patient` (
  `ID` varchar(50) DEFAULT NULL COMMENT 'ID',
  `PAT_ID` varchar(64) NOT NULL COMMENT '患者唯一标识',
  `NAME` varchar(64) DEFAULT NULL COMMENT '患者姓名',
  `NAME_PY` varchar(64) DEFAULT NULL COMMENT '患者姓名_拼音',
  `SEX_CODE` varchar(64) DEFAULT NULL COMMENT '性别Code',
  `SEX` varchar(64) DEFAULT NULL COMMENT '性别',
  `BITTH` datetime DEFAULT NULL COMMENT '出生日期',
  `ID_SH` varchar(64) DEFAULT NULL COMMENT '身份证号',
  `SOCIALSECURITY` varchar(64) DEFAULT NULL COMMENT '社保号',
  `NATION_CODE` varchar(64) DEFAULT NULL COMMENT '民族Code',
  `NATION` varchar(64) DEFAULT NULL COMMENT '民族',
  `BLOODTYPE_CODE` varchar(64) DEFAULT NULL COMMENT '血型Code',
  `BLOODTYPE` varchar(64) DEFAULT NULL COMMENT '血型',
  `PHON` varchar(64) DEFAULT NULL COMMENT '联系电话',
  `ADDRESS` varchar(64) DEFAULT NULL COMMENT '家庭住址',
  `Email` varchar(64) DEFAULT NULL COMMENT 'Email',
  `IDENTITY_CODE` varchar(64) DEFAULT NULL COMMENT '患者身份Code',
  `IDENTITY` varchar(64) DEFAULT NULL COMMENT '患者身份',
  `ISDELETE` tinyint(1) unsigned zerofill DEFAULT NULL COMMENT '逻辑删除',
  `UPD_USER` varchar(16) DEFAULT NULL COMMENT '修改人',
  `UPD_TIME` datetime DEFAULT NULL COMMENT '修改时间',
  `CRT_USER` varchar(16) DEFAULT NULL COMMENT '创建人',
  `CRT_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `ptn_id_dcm` text COMMENT 'dcm原ptnid',
  PRIMARY KEY (`PAT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='患者信息';

-- ----------------------------
-- Table structure for busin_patient_info
-- ----------------------------
DROP TABLE IF EXISTS `busin_patient_info`;
CREATE TABLE `busin_patient_info` (
  `ID` varchar(50) NOT NULL COMMENT 'ID',
  `PAT_ID` varchar(64) DEFAULT NULL COMMENT '患者唯一标识',
  `CARD_NO` varchar(64) DEFAULT NULL COMMENT '就诊卡号',
  `IHP_NO` varchar(64) DEFAULT NULL COMMENT '住院号',
  `VISID` int(11) DEFAULT NULL COMMENT '住院次数',
  `CHECKLIST_ID` varchar(50) DEFAULT NULL COMMENT '检查单号',
  PRIMARY KEY (`ID`),
  KEY `FK_Reference_15` (`PAT_ID`),
  CONSTRAINT `FK_Reference_15` FOREIGN KEY (`PAT_ID`) REFERENCES `busin_patient` (`PAT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='患者就诊信息';

-- ----------------------------
-- Table structure for rep_group
-- ----------------------------
DROP TABLE IF EXISTS `rep_group`;
CREATE TABLE `rep_group` (
  `ID` varchar(50) NOT NULL COMMENT '分组编号',
  `NAME` varchar(30) DEFAULT NULL COMMENT '分组名称',
  `UPD_USER` varchar(30) DEFAULT NULL,
  `UPD_TIME` datetime DEFAULT NULL,
  `CRT_USER` varchar(30) DEFAULT NULL,
  `CRT_TIME` datetime DEFAULT NULL,
  `NAMEPY` varchar(32) DEFAULT NULL,
  `NAMEWB` varchar(32) DEFAULT NULL,
  `STATUS` varchar(16) DEFAULT '0' COMMENT '分组属性',
  `NINDEX` int(32) DEFAULT NULL COMMENT '分组排序',
  `image_center_id` varchar(64) DEFAULT '0' COMMENT '所属影像中心id',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for rep_image
-- ----------------------------
DROP TABLE IF EXISTS `rep_image`;
CREATE TABLE `rep_image` (
  `ID` varchar(50) NOT NULL COMMENT 'ID',
  `REP_UID` varchar(128) DEFAULT NULL COMMENT '报告UID',
  `NUMBER` int(11) DEFAULT NULL COMMENT '编号',
  `IMG_PAGE` text COMMENT '图像路径',
  `IMG_LABEL_CODE` varchar(16) DEFAULT NULL COMMENT '图像下标Code',
  `IMG_LABEL` varchar(16) DEFAULT NULL COMMENT '图像下标',
  `ISDELETE` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`ID`),
  KEY `FK_Reference_24` (`REP_UID`),
  CONSTRAINT `FK_Reference_24` FOREIGN KEY (`REP_UID`) REFERENCES `rep_record` (`REP_UID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='报告贴图';

-- ----------------------------
-- Table structure for rep_image_1112
-- ----------------------------
DROP TABLE IF EXISTS `rep_image_1112`;
CREATE TABLE `rep_image_1112` (
  `ID` varchar(50) NOT NULL COMMENT 'ID',
  `REP_UID` varchar(128) DEFAULT NULL COMMENT '报告UID',
  `NUMBER` int(11) DEFAULT NULL COMMENT '编号',
  `IMG_PAGE` text COMMENT '图像路径',
  `IMG_LABEL_CODE` varchar(16) DEFAULT NULL COMMENT '图像下标Code',
  `IMG_LABEL` varchar(16) DEFAULT NULL COMMENT '图像下标',
  `ISDELETE` tinyint(1) DEFAULT '0' COMMENT '逻辑删除'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for rep_image_his
-- ----------------------------
DROP TABLE IF EXISTS `rep_image_his`;
CREATE TABLE `rep_image_his` (
  `ID` varchar(50) NOT NULL COMMENT 'ID',
  `REP_UID` varchar(128) DEFAULT NULL COMMENT '报告UID',
  `NUMBER` int(11) DEFAULT NULL COMMENT '编号',
  `IMG_PAGE` text COMMENT '图像路径',
  `IMG_LABEL_CODE` varchar(16) DEFAULT NULL COMMENT '图像下标Code',
  `IMG_LABEL` varchar(16) DEFAULT NULL COMMENT '图像下标',
  `ISDELETE` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  `transfer_date` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `REP_UID` (`REP_UID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for rep_record
-- ----------------------------
DROP TABLE IF EXISTS `rep_record`;
CREATE TABLE `rep_record` (
  `ID` varchar(50) DEFAULT NULL COMMENT 'ID',
  `REP_UID` varchar(128) NOT NULL COMMENT '报告UID',
  `ACCESSION_NUM` varchar(64) DEFAULT NULL COMMENT '检查流水号',
  `PAT_ID` varchar(64) DEFAULT NULL COMMENT '患者唯一标识',
  `FINDING1` text COMMENT '影像所见1',
  `FINDING2` text COMMENT '影像所见2',
  `FINDING3` text COMMENT '影像所见3',
  `IMPRESSION1` text COMMENT '诊断建议1',
  `IMPRESSION2` text COMMENT '诊断建议2',
  `IMPRESSION3` text COMMENT '诊断建议3',
  `PAT_AGE` varchar(16) DEFAULT NULL COMMENT '患者年龄',
  `AGE_UNIT_CODE` varchar(16) DEFAULT NULL COMMENT '年龄单位Code',
  `AGE_UNIT` varchar(16) DEFAULT NULL COMMENT '年龄单位',
  `HP` varchar(6) DEFAULT NULL COMMENT '阴性阳性',
  `rate` varchar(4) DEFAULT NULL COMMENT '报告评级',
  `suggest` text COMMENT '报告建议',
  PRIMARY KEY (`REP_UID`),
  KEY `FK_Reference_18` (`ACCESSION_NUM`),
  KEY `FK_Reference_19` (`PAT_ID`),
  CONSTRAINT `FK_Reference_18` FOREIGN KEY (`ACCESSION_NUM`) REFERENCES `busin_checklist_index` (`ACCESSION_NUM`),
  CONSTRAINT `FK_Reference_19` FOREIGN KEY (`PAT_ID`) REFERENCES `busin_patient` (`PAT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='诊断报告_记录';

-- ----------------------------
-- Table structure for rep_record_1112
-- ----------------------------
DROP TABLE IF EXISTS `rep_record_1112`;
CREATE TABLE `rep_record_1112` (
  `ID` varchar(50) DEFAULT NULL COMMENT 'ID',
  `REP_UID` varchar(128) NOT NULL COMMENT '报告UID',
  `ACCESSION_NUM` varchar(64) DEFAULT NULL COMMENT '检查流水号',
  `PAT_ID` varchar(64) DEFAULT NULL COMMENT '患者唯一标识',
  `FINDING1` text COMMENT '影像所见1',
  `FINDING2` text COMMENT '影像所见2',
  `FINDING3` text COMMENT '影像所见3',
  `IMPRESSION1` text COMMENT '诊断建议1',
  `IMPRESSION2` text COMMENT '诊断建议2',
  `IMPRESSION3` text COMMENT '诊断建议3',
  `PAT_AGE` varchar(16) DEFAULT NULL COMMENT '患者年龄',
  `AGE_UNIT_CODE` varchar(16) DEFAULT NULL COMMENT '年龄单位Code',
  `AGE_UNIT` varchar(16) DEFAULT NULL COMMENT '年龄单位',
  `HP` varchar(6) DEFAULT NULL COMMENT '阴性阳性',
  `rate` varchar(4) DEFAULT NULL COMMENT '报告评级',
  `suggest` text COMMENT '报告建议'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for rep_record_his
-- ----------------------------
DROP TABLE IF EXISTS `rep_record_his`;
CREATE TABLE `rep_record_his` (
  `ID` varchar(50) DEFAULT NULL COMMENT 'ID',
  `REP_UID` varchar(128) NOT NULL COMMENT '报告UID',
  `ACCESSION_NUM` varchar(64) DEFAULT NULL COMMENT '检查流水号',
  `PAT_ID` varchar(64) DEFAULT NULL COMMENT '患者唯一标识',
  `FINDING1` text COMMENT '影像所见1',
  `FINDING2` text COMMENT '影像所见2',
  `FINDING3` text COMMENT '影像所见3',
  `IMPRESSION1` text COMMENT '诊断建议1',
  `IMPRESSION2` text COMMENT '诊断建议2',
  `IMPRESSION3` text COMMENT '诊断建议3',
  `PAT_AGE` varchar(16) DEFAULT NULL COMMENT '患者年龄',
  `AGE_UNIT_CODE` varchar(16) DEFAULT NULL COMMENT '年龄单位Code',
  `AGE_UNIT` varchar(16) DEFAULT NULL COMMENT '年龄单位',
  `HP` varchar(6) DEFAULT NULL COMMENT '阴性阳性',
  `transfer_date` datetime DEFAULT NULL,
  `rate` varchar(4) DEFAULT NULL COMMENT '报告评级',
  `suggest` text COMMENT '报告建议',
  PRIMARY KEY (`REP_UID`),
  KEY `ACCESSION_NUM` (`ACCESSION_NUM`),
  KEY `PAT_ID` (`PAT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for set_report_db
-- ----------------------------
DROP TABLE IF EXISTS `set_report_db`;
CREATE TABLE `set_report_db` (
  `ID` varchar(50) NOT NULL COMMENT 'ID',
  `NAME` varchar(64) DEFAULT NULL COMMENT '名称',
  `UPPER` varchar(50) DEFAULT NULL COMMENT '上级',
  `NINDEX` varchar(16) DEFAULT NULL COMMENT '排序',
  `TYPE_CODE` varchar(16) DEFAULT NULL COMMENT '类型Code',
  `TYPE` varchar(16) DEFAULT NULL COMMENT '类型',
  `FINDING` text COMMENT '影像所见',
  `IMPRESSION` text COMMENT '诊断建议',
  `NAMEPY` varchar(64) DEFAULT NULL COMMENT '拼音简码',
  `NAMEWB` varchar(64) DEFAULT NULL COMMENT '五笔简码',
  `UPD_USER` varchar(16) DEFAULT NULL COMMENT '修改人',
  `UPD_TIME` datetime DEFAULT NULL COMMENT '修改时间',
  `CRT_USER` varchar(16) DEFAULT NULL COMMENT '创建人',
  `CRT_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `REMARK` varchar(128) DEFAULT NULL COMMENT '备注',
  `ISDELETE` tinyint(1) DEFAULT NULL COMMENT '逻辑删除',
  `FILEFLAG` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='报告知识库';

-- ----------------------------
-- Table structure for set_report_db_0801
-- ----------------------------
DROP TABLE IF EXISTS `set_report_db_0801`;
CREATE TABLE `set_report_db_0801` (
  `ID` varchar(50) NOT NULL COMMENT 'ID',
  `NAME` varchar(64) DEFAULT NULL COMMENT '名称',
  `UPPER` varchar(50) DEFAULT NULL COMMENT '上级',
  `NINDEX` varchar(16) DEFAULT NULL COMMENT '排序',
  `TYPE_CODE` varchar(16) DEFAULT NULL COMMENT '类型Code',
  `TYPE` varchar(16) DEFAULT NULL COMMENT '类型',
  `FINDING` text COMMENT '影像所见',
  `IMPRESSION` text COMMENT '诊断建议',
  `NAMEPY` varchar(64) DEFAULT NULL COMMENT '拼音简码',
  `NAMEWB` varchar(64) DEFAULT NULL COMMENT '五笔简码',
  `UPD_USER` varchar(16) DEFAULT NULL COMMENT '修改人',
  `UPD_TIME` datetime DEFAULT NULL COMMENT '修改时间',
  `CRT_USER` varchar(16) DEFAULT NULL COMMENT '创建人',
  `CRT_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `REMARK` varchar(128) DEFAULT NULL COMMENT '备注',
  `ISDELETE` tinyint(1) DEFAULT NULL COMMENT '逻辑删除',
  `FILEFLAG` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for set_report_private
-- ----------------------------
DROP TABLE IF EXISTS `set_report_private`;
CREATE TABLE `set_report_private` (
  `ID` varchar(50) NOT NULL COMMENT 'ID',
  `NAME` varchar(32) DEFAULT NULL COMMENT '名称',
  `UPPER` varchar(50) DEFAULT NULL COMMENT '上级',
  `NINDEX` varchar(16) DEFAULT NULL COMMENT '排序',
  `TYPE_CODE` varchar(16) DEFAULT NULL COMMENT '类型Code',
  `TYPE` varchar(16) DEFAULT NULL COMMENT '类型',
  `FINDING` text COMMENT '影像所见',
  `IMPRESSION` text COMMENT '诊断建议',
  `NAMEPY` varchar(64) DEFAULT NULL COMMENT '拼音简码',
  `NAMEWB` varchar(64) DEFAULT NULL COMMENT '五笔简码',
  `UPD_USER` varchar(16) DEFAULT NULL COMMENT '修改人',
  `UPD_TIME` datetime DEFAULT NULL COMMENT '修改时间',
  `CRT_USER` varchar(16) DEFAULT NULL COMMENT '创建人',
  `CRT_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `REMARK` varchar(128) DEFAULT NULL COMMENT '备注',
  `ISDELETE` tinyint(1) DEFAULT NULL COMMENT '逻辑删除',
  `LOGINCODE` varchar(50) DEFAULT NULL COMMENT '登录号',
  `FILEFLAG` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='报告知识库_私有';

-- ----------------------------
-- Table structure for set_usedtemplate
-- ----------------------------
DROP TABLE IF EXISTS `set_usedtemplate`;
CREATE TABLE `set_usedtemplate` (
  `ID` varchar(50) NOT NULL COMMENT 'ID',
  `LOGINCODE` varchar(16) DEFAULT NULL COMMENT '登录号',
  `EXAMTECH` text COMMENT '描述',
  `NINDEX` varchar(16) DEFAULT NULL COMMENT '排序',
  `TYPE_CODE` varchar(16) DEFAULT NULL COMMENT '类型Code',
  `TYPE` varchar(16) DEFAULT NULL COMMENT '类型',
  `NAME` varchar(16) DEFAULT NULL COMMENT '名称',
  `NAMEPY` varchar(16) DEFAULT NULL COMMENT '拼音简码',
  `NAMEWB` varchar(16) DEFAULT NULL COMMENT '五笔简码',
  `UPD_USER` varchar(16) DEFAULT NULL COMMENT '修改人',
  `UPD_TIME` datetime DEFAULT NULL COMMENT '修改时间',
  `CRT_USER` varchar(16) DEFAULT NULL COMMENT '创建人',
  `CRT_TIEM` datetime DEFAULT NULL COMMENT '创建时间',
  `REMARK` varchar(128) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='常用语';

-- ----------------------------
-- Table structure for sys_armarium_oper
-- ----------------------------
DROP TABLE IF EXISTS `sys_armarium_oper`;
CREATE TABLE `sys_armarium_oper` (
  `ID` varchar(50) NOT NULL,
  `LOGINCODE` varchar(50) DEFAULT NULL COMMENT '登陆名',
  `STATUS` tinyint(1) DEFAULT NULL COMMENT '启用状态',
  `ARMARIUM` varchar(50) DEFAULT NULL COMMENT '医疗设备名称',
  `ARMARIUM_ID` varchar(50) DEFAULT NULL COMMENT '医疗设备编号',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_department
-- ----------------------------
DROP TABLE IF EXISTS `sys_department`;
CREATE TABLE `sys_department` (
  `ID` varchar(50) NOT NULL COMMENT '主键',
  `DEP_NAME` varchar(30) DEFAULT NULL COMMENT '部门编号',
  `ORG_ID` varchar(50) DEFAULT NULL COMMENT '机构编号',
  `UPPERID` varchar(50) DEFAULT NULL COMMENT '上级',
  `NLEVEL` int(11) DEFAULT NULL COMMENT '层级',
  `PHONE` varchar(32) DEFAULT NULL COMMENT '联系电话',
  `FAX` varchar(32) DEFAULT NULL COMMENT '传真',
  `EMAIL` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `DEP_TYPE_CODE` varchar(16) DEFAULT NULL COMMENT '部门类型CODE',
  `DEP_TYPE` varchar(16) DEFAULT NULL COMMENT '部门类型',
  `NINDEX` varchar(16) DEFAULT NULL COMMENT '排序值',
  `CHARGE_PERSON` varchar(16) DEFAULT NULL COMMENT '负责人',
  `STATUS` tinyint(1) DEFAULT NULL COMMENT '状态',
  `ISDELETE` tinyint(1) DEFAULT NULL COMMENT '逻辑删除',
  `REMARK` varchar(128) DEFAULT NULL COMMENT '备注',
  `UPD_USER` varchar(30) DEFAULT NULL COMMENT '更改人',
  `UPD_TIME` datetime DEFAULT NULL COMMENT '更改时间',
  `CRT_USER` varchar(30) DEFAULT NULL COMMENT '创建人',
  `CRT_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `NAMEPY` varchar(32) DEFAULT NULL,
  `NAMEWB` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_Reference_1` (`ORG_ID`),
  CONSTRAINT `FK_Reference_1` FOREIGN KEY (`ORG_ID`) REFERENCES `sys_organization` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `ID` varchar(50) NOT NULL COMMENT '目录编号',
  `DNAME` varchar(30) DEFAULT NULL COMMENT '目录名称',
  `CLS_ID` varchar(50) DEFAULT NULL COMMENT '分类编号',
  `CLS_NAME` varchar(30) DEFAULT NULL COMMENT '分类名称',
  `ISMODIFY` tinyint(1) DEFAULT NULL COMMENT '锁定状态',
  `STATUS` tinyint(1) DEFAULT NULL COMMENT '启用状态',
  `NAMEPY` varchar(16) DEFAULT NULL COMMENT '拼音简码',
  `NAMEWB` varchar(16) DEFAULT NULL COMMENT '五笔简码',
  `ISDELETE` tinyint(1) DEFAULT NULL COMMENT '逻辑删除',
  `REMARK` varchar(128) DEFAULT NULL COMMENT '备注',
  `UPD_USER` varchar(30) DEFAULT NULL COMMENT '更新人',
  `UPD_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  `CRT_USER` varchar(30) DEFAULT NULL COMMENT '创建人',
  `CRT_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`ID`),
  KEY `FK_Reference_2` (`CLS_ID`),
  CONSTRAINT `FK_Reference_2` FOREIGN KEY (`CLS_ID`) REFERENCES `sys_dict_cls` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_dict_bodypart
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_bodypart`;
CREATE TABLE `sys_dict_bodypart` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '部位主键ID',
  `CHECKTYPE_CODE` varchar(64) DEFAULT NULL COMMENT '检查类型Code',
  `CHECKTYPE_NAME` varchar(64) DEFAULT NULL COMMENT '检查类型Name',
  `NAME` varchar(64) DEFAULT NULL COMMENT '名称',
  `EN_NAME` varchar(64) DEFAULT NULL COMMENT '英文名称',
  `LEFT_RIGHT` int(2) DEFAULT '0' COMMENT '是否左右',
  `RADIOGRAPHY` int(2) DEFAULT '0' COMMENT '是否造影',
  `OTHERVALUE` varchar(64) DEFAULT NULL COMMENT '辅助值1',
  `OTHERVALUE2` varchar(64) DEFAULT NULL COMMENT '辅助值2',
  `CRT_USER` varchar(64) DEFAULT NULL COMMENT '创建人',
  `CRT_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPD_USER` varchar(64) DEFAULT NULL COMMENT '修改人',
  `UPD_TIME` datetime DEFAULT NULL COMMENT '修改时间',
  `REMARK` varchar(64) DEFAULT NULL COMMENT '备注',
  `STATUS` tinyint(1) DEFAULT NULL COMMENT '启用状态',
  `NINDEX` int(11) DEFAULT NULL COMMENT '排序值',
  `NAMEPY` varchar(64) DEFAULT NULL COMMENT '拼音简码',
  `NAMEWB` varchar(64) DEFAULT NULL COMMENT '五笔简码',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10030 DEFAULT CHARSET=utf8 COMMENT='检查部位_字典表';

-- ----------------------------
-- Table structure for sys_dict_city
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_city`;
CREATE TABLE `sys_dict_city` (
  `ID` int(11) NOT NULL,
  `AREACODE` varchar(32) DEFAULT NULL,
  `AREANAME` varchar(32) DEFAULT NULL,
  `UPPCODE` int(11) DEFAULT NULL,
  `SHORTNAME` varchar(32) DEFAULT NULL,
  `LEVEL` int(11) DEFAULT NULL,
  `LEVELTYPE` varchar(32) DEFAULT NULL,
  `CITYCODE` varchar(32) DEFAULT NULL,
  `ZIPCODE` varchar(32) DEFAULT NULL,
  `MERGERNAME` varchar(128) DEFAULT NULL,
  `LNG` varchar(32) DEFAULT NULL,
  `LAT` varchar(32) DEFAULT NULL,
  `PINYIN` varchar(32) DEFAULT NULL,
  `NAMEPY` varchar(32) DEFAULT NULL,
  `NAMEWB` varchar(32) DEFAULT NULL,
  `CRT_USER` varchar(32) DEFAULT NULL,
  `CRT_TIME` datetime DEFAULT NULL,
  `UPD_USER` varchar(32) DEFAULT NULL,
  `UPD_TIME` datetime DEFAULT NULL,
  `REMARK` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_dict_cls
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_cls`;
CREATE TABLE `sys_dict_cls` (
  `ID` varchar(50) NOT NULL COMMENT '分类编号',
  `CLS_NAME` varchar(30) DEFAULT NULL COMMENT '分类名称',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_dict_dtl
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_dtl`;
CREATE TABLE `sys_dict_dtl` (
  `ID` varchar(50) NOT NULL COMMENT '字典编号',
  `DICT_ID` varchar(50) DEFAULT NULL COMMENT '目录编号',
  `CODE` varchar(64) DEFAULT NULL COMMENT '数据详情表的code',
  `NAME` varchar(64) DEFAULT NULL COMMENT '辅助值',
  `OTHERVALUE` varchar(128) DEFAULT NULL COMMENT '数据值',
  `OTHERVALUE2` varchar(128) DEFAULT NULL COMMENT '辅助值2',
  `NINDEX` varchar(16) DEFAULT NULL COMMENT '排序值',
  `ISMODIFY` tinyint(1) DEFAULT NULL COMMENT '锁定状态',
  `NAMEPY` varchar(128) DEFAULT NULL COMMENT '拼音简码',
  `NAMEWB` varchar(128) DEFAULT NULL COMMENT '五笔简码',
  `ISDELETE` tinyint(1) DEFAULT NULL COMMENT '逻辑删除',
  `REMARK` varchar(128) DEFAULT NULL COMMENT '备注',
  `UPD_USER` varchar(30) DEFAULT NULL COMMENT '修改人',
  `UPD_TIME` datetime DEFAULT NULL COMMENT '修改时间',
  `CRT_USER` varchar(30) DEFAULT NULL COMMENT '创建人',
  `CRT_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`ID`),
  KEY `FK_Reference_3` (`DICT_ID`),
  KEY `FK_Reference_40` (`CODE`) USING BTREE,
  CONSTRAINT `FK_Reference_3` FOREIGN KEY (`DICT_ID`) REFERENCES `sys_dict` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_dict_scanmethod
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_scanmethod`;
CREATE TABLE `sys_dict_scanmethod` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `CHECKTYPE_CODE` varchar(64) DEFAULT NULL COMMENT '检查类型Code',
  `CHECKTYPE_NAME` varchar(64) DEFAULT NULL COMMENT '检查类型Name',
  `NAME` varchar(64) DEFAULT NULL COMMENT '名称',
  `OTHERVALUE` varchar(64) DEFAULT NULL COMMENT '辅助值1',
  `OTHERVALUE2` varchar(64) DEFAULT NULL COMMENT '辅助值2',
  `CRT_USER` varchar(64) DEFAULT NULL COMMENT '创建人',
  `CRT_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPD_USER` varchar(64) DEFAULT NULL COMMENT '修改人',
  `UPD_TIME` datetime DEFAULT NULL COMMENT '修改时间',
  `REMARK` varchar(64) DEFAULT NULL COMMENT '备注',
  `STATUS` tinyint(1) DEFAULT NULL COMMENT '启用状态',
  `NINDEX` int(11) DEFAULT NULL COMMENT '排序值',
  `NAMEPY` varchar(64) DEFAULT NULL COMMENT '拼音简码',
  `NAMEWB` varchar(64) DEFAULT NULL COMMENT '五笔简码',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10025 DEFAULT CHARSET=utf8 COMMENT='检查方法(扫描方式)_字典表';

-- ----------------------------
-- Table structure for sys_hx_org
-- ----------------------------
DROP TABLE IF EXISTS `sys_hx_org`;
CREATE TABLE `sys_hx_org` (
  `create_date` char(16) DEFAULT NULL,
  `pro_name` char(32) DEFAULT NULL,
  `sales_name` char(32) DEFAULT NULL,
  `org_name` char(128) DEFAULT NULL,
  `cost_mark` char(128) DEFAULT NULL,
  `remark` char(128) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `ID` varchar(50) NOT NULL COMMENT 'id',
  `LOGINCODE` varchar(50) DEFAULT NULL COMMENT '账号',
  `OPERATION` varchar(50) DEFAULT NULL COMMENT '操作',
  `METHOD` varchar(200) DEFAULT NULL COMMENT '方法',
  `PARAMS` varchar(5000) DEFAULT NULL COMMENT '参数',
  `IP` varchar(64) DEFAULT NULL COMMENT 'ip',
  `UPD_USER` varchar(30) DEFAULT NULL COMMENT '更新人',
  `UPD_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  `CRT_USER` varchar(30) DEFAULT NULL COMMENT '创建人',
  `CRT_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`ID`),
  KEY `ID` (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `ID` varchar(50) NOT NULL COMMENT '菜单编号',
  `MNAME` varchar(30) DEFAULT NULL COMMENT '菜单名',
  `UPPDER_ID` varchar(50) DEFAULT NULL COMMENT '上级菜单',
  `NLEVEL` int(11) DEFAULT NULL COMMENT '层级',
  `SYSTEM_ID` varchar(50) DEFAULT NULL COMMENT '所属系统',
  `NINDEX` int(32) DEFAULT NULL COMMENT '排序',
  `BC_FLAG` int(11) DEFAULT NULL COMMENT 'BC_FLAG 页面或者C端展示',
  `PAGE` varchar(128) DEFAULT NULL COMMENT '页面地址',
  `PIC` varchar(128) DEFAULT NULL COMMENT '图标',
  `NAMEPY` varchar(32) DEFAULT NULL COMMENT '拼音简码',
  `NAMEWB` varchar(32) DEFAULT NULL COMMENT '五笔简码',
  `REMARK` varchar(128) DEFAULT NULL COMMENT '备注',
  `UPD_USER` varchar(30) DEFAULT NULL COMMENT '修改人',
  `UPD_TIME` datetime DEFAULT NULL COMMENT '修改时间',
  `CRT_USER` varchar(30) DEFAULT NULL COMMENT '创建人',
  `CRT_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_operator
-- ----------------------------
DROP TABLE IF EXISTS `sys_operator`;
CREATE TABLE `sys_operator` (
  `ID` varchar(50) DEFAULT NULL COMMENT '用户编号',
  `LOGINCODE` varchar(50) NOT NULL COMMENT '登陆名',
  `PASSWORD` varchar(50) DEFAULT NULL COMMENT '密码',
  `ADMIN_CODE` varchar(16) DEFAULT NULL COMMENT '审核Code',
  `ADMIN` varchar(16) DEFAULT NULL COMMENT '审核',
  `SIGNATURE` text COMMENT '签名',
  `HOME_PAGE` varchar(50) DEFAULT NULL COMMENT '自定义主页',
  `STATUS` tinyint(1) DEFAULT NULL COMMENT '启用状态',
  `ISDELETE` tinyint(1) DEFAULT NULL COMMENT '逻辑删除',
  `REMARK` varchar(128) DEFAULT NULL COMMENT '备注',
  `UPD_USER` varchar(50) DEFAULT NULL COMMENT '更新人',
  `UPD_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  `CRT_USER` varchar(50) DEFAULT NULL COMMENT '创建人',
  `CRT_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`LOGINCODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_operator_dtl
-- ----------------------------
DROP TABLE IF EXISTS `sys_operator_dtl`;
CREATE TABLE `sys_operator_dtl` (
  `ID` varchar(50) NOT NULL COMMENT '主键',
  `ARCHIVES_NUM` varchar(100) DEFAULT NULL,
  `BEFORE_NAME` varchar(30) DEFAULT NULL,
  `ID_NUM` varchar(50) DEFAULT NULL,
  `BIRTHDAY` datetime DEFAULT NULL,
  `BIRTHPLACE` varchar(100) DEFAULT NULL,
  `FAMILY_NAME` varchar(50) DEFAULT NULL,
  `NATIVE_PLACE` varchar(30) DEFAULT NULL,
  `HEALTH` varchar(10) DEFAULT NULL,
  `POLITICAL_OUTLOOK` varchar(10) DEFAULT NULL,
  `MARTIAL_STATUS` varchar(10) DEFAULT NULL,
  `EDUCATION` varchar(10) DEFAULT NULL,
  `ACADEMIC_DEGREE` varchar(10) DEFAULT NULL,
  `BLOOD_TYPE` varchar(10) DEFAULT NULL,
  `HEIGHT` varchar(10) DEFAULT NULL,
  `LEFT_VERSION` varchar(10) DEFAULT NULL,
  `RIGHT_VERSION` varchar(10) DEFAULT NULL,
  `MAJOR` varchar(30) DEFAULT NULL,
  `LANGUAGE` varchar(10) DEFAULT NULL,
  `LANGUAGE_LEVEL` varchar(10) DEFAULT NULL,
  `POST_CODE` varchar(10) DEFAULT NULL,
  `HOME_TEL` varchar(30) DEFAULT NULL,
  `HOME_MAIL` varchar(30) DEFAULT NULL,
  `OFFICE_TEL` varchar(30) DEFAULT NULL,
  `RESIDENCE_ADDRESS` varchar(100) DEFAULT NULL,
  `HOME_ADDRESS` varchar(100) DEFAULT NULL,
  `RESIDENCE_DIFFERENCE` varchar(30) DEFAULT NULL,
  `EFLAG_CODE` varchar(50) DEFAULT NULL,
  `EFLAG` varchar(50) DEFAULT NULL,
  `MANAGE_CODE` varchar(50) DEFAULT NULL,
  `MANAGE` varchar(50) DEFAULT NULL,
  `PROFESSIONAL_LEVEL` varchar(50) DEFAULT NULL,
  `PROFESSIONAL` varchar(50) DEFAULT NULL,
  `TECHNICAL_LEVEL` varchar(50) DEFAULT NULL,
  `TECHNICAL` varchar(50) DEFAULT NULL,
  `UPD_USER` varchar(16) DEFAULT NULL,
  `UPD_TIME` datetime DEFAULT NULL,
  `CRT_USER` varchar(16) DEFAULT NULL,
  `CRT_TIME` datetime DEFAULT NULL,
  `SEX` varchar(50) DEFAULT NULL,
  `DEP_ID` varchar(50) DEFAULT NULL,
  `ORG_ID` varchar(50) DEFAULT NULL,
  `LOGINCODE` varchar(50) DEFAULT NULL,
  `NAMEPY` varchar(32) DEFAULT NULL,
  `NAMEWB` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_Reference_6` (`DEP_ID`),
  KEY `FK_Reference_7` (`ORG_ID`),
  KEY `FK_Reference_8` (`LOGINCODE`),
  CONSTRAINT `FK_Reference_6` FOREIGN KEY (`DEP_ID`) REFERENCES `sys_department` (`ID`),
  CONSTRAINT `FK_Reference_7` FOREIGN KEY (`ORG_ID`) REFERENCES `sys_organization` (`ID`),
  CONSTRAINT `FK_Reference_8` FOREIGN KEY (`LOGINCODE`) REFERENCES `sys_operator` (`LOGINCODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_oper_org_rel
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_org_rel`;
CREATE TABLE `sys_oper_org_rel` (
  `relation_id` int(11) NOT NULL AUTO_INCREMENT,
  `oper_group_id` varchar(64) DEFAULT NULL,
  `org_group_id` int(11) DEFAULT NULL,
  `is_valid` int(11) DEFAULT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `expire_date` timestamp NOT NULL DEFAULT '2038-01-01 00:00:00',
  `extend1` varchar(64) DEFAULT NULL,
  `extend2` varchar(64) DEFAULT NULL,
  `extend3` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`relation_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_oper_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_role`;
CREATE TABLE `sys_oper_role` (
  `ID` varchar(50) NOT NULL COMMENT '主键',
  `ROLE_ID` varchar(50) DEFAULT NULL COMMENT '角色编码',
  `LOGINCODE` varchar(50) DEFAULT NULL COMMENT '登录号',
  PRIMARY KEY (`ID`),
  KEY `FK_Reference_4` (`LOGINCODE`),
  KEY `FK_Reference_5` (`ROLE_ID`),
  CONSTRAINT `FK_Reference_4` FOREIGN KEY (`LOGINCODE`) REFERENCES `sys_operator` (`LOGINCODE`),
  CONSTRAINT `FK_Reference_5` FOREIGN KEY (`ROLE_ID`) REFERENCES `sys_role` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_organization
-- ----------------------------
DROP TABLE IF EXISTS `sys_organization`;
CREATE TABLE `sys_organization` (
  `ID` varchar(50) NOT NULL COMMENT '机构编号',
  `CODE` varchar(64) DEFAULT NULL COMMENT '机构编号',
  `NAME` varchar(30) DEFAULT NULL COMMENT '机构名称',
  `SHORTNAME` varchar(30) DEFAULT NULL COMMENT '机构简称',
  `PHONE` varchar(32) DEFAULT NULL COMMENT '联系电话',
  `ORG_HEADER` varchar(32) DEFAULT NULL COMMENT '机构负责人',
  `ADDRESS` varchar(128) DEFAULT NULL COMMENT '地址',
  `EMAIL` varchar(128) DEFAULT NULL COMMENT '邮箱',
  `NOTATION` varchar(256) DEFAULT NULL COMMENT '简介',
  `STATUS` tinyint(1) DEFAULT NULL COMMENT '状态',
  `ISDELETE` tinyint(1) DEFAULT NULL COMMENT '逻辑删除',
  `CLS_ID` varchar(50) DEFAULT NULL COMMENT '分类编号',
  `REMARK` varchar(128) DEFAULT NULL COMMENT '备注',
  `UPD_USER` varchar(30) DEFAULT NULL COMMENT '更新人',
  `UPD_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  `CRT_USER` varchar(30) DEFAULT NULL COMMENT '创建人',
  `CRT_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `NAMEPY` varchar(32) DEFAULT NULL,
  `NAMEWB` varchar(32) DEFAULT NULL,
  `CITY_NO` int(11) DEFAULT NULL COMMENT '城市编码',
  `PROVINCE_NO` int(11) DEFAULT NULL COMMENT '省份编码',
  `COUNTY_NO` int(11) DEFAULT NULL COMMENT '县\\区编码',
  `ORG_LEVEL` varchar(64) DEFAULT NULL COMMENT '机构级别',
  `ORG_TYPE` varchar(64) DEFAULT NULL COMMENT '客户等级',
  `IF_CHARGE` tinyint(1) DEFAULT NULL COMMENT '是否收费客户',
  `CHARGE_DATE_BEGIN` datetime DEFAULT NULL COMMENT '收费开始日期',
  `CHARGE_DATE_END` datetime DEFAULT NULL COMMENT '收费结束日期',
  `CHARGE_STANDARD` varchar(16) DEFAULT NULL COMMENT '收费标准',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `co` (`CODE`),
  KEY `FK_Reference_9` (`CLS_ID`),
  CONSTRAINT `FK_Reference_9` FOREIGN KEY (`CLS_ID`) REFERENCES `sys_org_cls` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_org_classify
-- ----------------------------
DROP TABLE IF EXISTS `sys_org_classify`;
CREATE TABLE `sys_org_classify` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) DEFAULT NULL COMMENT '分类名称',
  `attribute` varchar(64) DEFAULT NULL COMMENT '分类属性',
  `weight` int(11) DEFAULT NULL COMMENT '排序权重',
  `colour` varchar(64) DEFAULT NULL COMMENT '分类颜色',
  `nindex` varchar(64) DEFAULT NULL COMMENT '排序(显示)',
  `org_code` varchar(64) DEFAULT NULL COMMENT '所属影像中心code',
  `org_name` varchar(64) DEFAULT NULL COMMENT '所属影像中心name',
  `namepy` varchar(64) DEFAULT NULL COMMENT '拼音简码',
  `namewb` varchar(64) DEFAULT NULL COMMENT '五笔简码',
  `upd_user` varchar(64) DEFAULT NULL COMMENT '修改人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `crt_user` varchar(64) DEFAULT NULL COMMENT '创建人',
  `crt_time` datetime DEFAULT NULL COMMENT '创建时间',
  `remark` varchar(128) DEFAULT NULL COMMENT '备注',
  `extend1` varchar(64) DEFAULT NULL COMMENT '预留1',
  `extend2` varchar(64) DEFAULT NULL COMMENT '预留2',
  `extend3` varchar(64) DEFAULT NULL COMMENT '预留3',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_org_cls
-- ----------------------------
DROP TABLE IF EXISTS `sys_org_cls`;
CREATE TABLE `sys_org_cls` (
  `ID` varchar(50) NOT NULL COMMENT '分类编号',
  `CLS_NAME` varchar(30) DEFAULT NULL COMMENT '分类名称',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_org_group_def
-- ----------------------------
DROP TABLE IF EXISTS `sys_org_group_def`;
CREATE TABLE `sys_org_group_def` (
  `group_id` int(11) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(64) DEFAULT NULL,
  `group_pro` varchar(64) DEFAULT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `extend1` varchar(64) DEFAULT NULL,
  `extend2` varchar(64) DEFAULT NULL,
  `extend3` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10007 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_org_group_rel
-- ----------------------------
DROP TABLE IF EXISTS `sys_org_group_rel`;
CREATE TABLE `sys_org_group_rel` (
  `relation_id` int(11) NOT NULL AUTO_INCREMENT,
  `image_id` varchar(64) DEFAULT NULL,
  `hospital_id` varchar(64) DEFAULT NULL,
  `group_id` int(11) DEFAULT NULL,
  `is_valid` int(11) DEFAULT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `expire_date` timestamp NOT NULL DEFAULT '2038-01-01 00:00:00',
  `extend1` varchar(64) DEFAULT NULL,
  `extend2` varchar(64) DEFAULT NULL,
  `extend3` varchar(64) DEFAULT NULL,
  `classify_id` int(11) DEFAULT NULL COMMENT '机构分类ID',
  PRIMARY KEY (`relation_id`)
) ENGINE=InnoDB AUTO_INCREMENT=602 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_rep_group
-- ----------------------------
DROP TABLE IF EXISTS `sys_rep_group`;
CREATE TABLE `sys_rep_group` (
  `ID` varchar(50) NOT NULL COMMENT '主键',
  `GROUP_ID` varchar(50) DEFAULT NULL COMMENT '分组编号',
  `LOGINCODE` varchar(50) DEFAULT NULL,
  `image_center_id` varchar(64) DEFAULT '0' COMMENT '所属影像中心id',
  PRIMARY KEY (`ID`),
  KEY `FK_Reference_10` (`GROUP_ID`),
  KEY `FK_Reference_11` (`LOGINCODE`),
  CONSTRAINT `FK_Reference_10` FOREIGN KEY (`GROUP_ID`) REFERENCES `rep_group` (`ID`),
  CONSTRAINT `FK_Reference_11` FOREIGN KEY (`LOGINCODE`) REFERENCES `sys_operator` (`LOGINCODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_right_modset
-- ----------------------------
DROP TABLE IF EXISTS `sys_right_modset`;
CREATE TABLE `sys_right_modset` (
  `ID` varchar(50) NOT NULL COMMENT '主键',
  `MENU_ID` varchar(50) DEFAULT NULL COMMENT '菜单编号',
  `LOGINCODE` varchar(50) DEFAULT NULL COMMENT '登录号/角色编号',
  `OBJFLAG` int(11) DEFAULT NULL COMMENT '标识',
  PRIMARY KEY (`ID`),
  KEY `FK_Reference_13` (`MENU_ID`),
  KEY `FK_Reference_14` (`LOGINCODE`),
  CONSTRAINT `FK_Reference_13` FOREIGN KEY (`MENU_ID`) REFERENCES `sys_menu` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_right_module
-- ----------------------------
DROP TABLE IF EXISTS `sys_right_module`;
CREATE TABLE `sys_right_module` (
  `ID` varchar(50) NOT NULL COMMENT '主键',
  `MENU_ID` varchar(50) DEFAULT NULL COMMENT '菜单编号',
  `LOGINCODE` varchar(50) DEFAULT NULL COMMENT '登录号',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `ID` varchar(50) NOT NULL COMMENT '角色编号',
  `NAME` varchar(30) DEFAULT NULL COMMENT '角色名称',
  `NAMEPY` varchar(16) DEFAULT NULL COMMENT '拼音简码',
  `NAMEWB` varchar(16) DEFAULT NULL COMMENT '五笔简码',
  `REMARK` varchar(128) DEFAULT NULL COMMENT '备注',
  `STATUS` tinyint(1) DEFAULT NULL COMMENT '状态',
  `ISDELETE` tinyint(1) DEFAULT NULL COMMENT '逻辑删除',
  `UPD_USER` varchar(30) DEFAULT NULL COMMENT '更新人',
  `UPD_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  `CRT_USER` varchar(30) DEFAULT NULL COMMENT '创建人',
  `CRT_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_token
-- ----------------------------
DROP TABLE IF EXISTS `sys_token`;
CREATE TABLE `sys_token` (
  `ID` varchar(50) NOT NULL COMMENT '主键',
  `LOGINCODE` varchar(50) NOT NULL COMMENT '账号',
  `TOKEN` varchar(100) NOT NULL COMMENT 'token',
  `EXPIRE_TIME` datetime DEFAULT NULL COMMENT '过期时间',
  `UPD_USER` varchar(30) DEFAULT NULL COMMENT '更新人',
  `UPD_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  `CRT_USER` varchar(30) DEFAULT NULL COMMENT '创建人',
  `CRT_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`ID`),
  KEY `LOGINCODE` (`LOGINCODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户Token';

-- ----------------------------
-- Table structure for sys_word
-- ----------------------------
DROP TABLE IF EXISTS `sys_word`;
CREATE TABLE `sys_word` (
  `CWORD` varchar(10) DEFAULT NULL COMMENT '中文',
  `PYCODE` varchar(10) DEFAULT NULL COMMENT '拼音',
  `WBCODE` varchar(10) DEFAULT NULL COMMENT '五笔',
  `SJCODE` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for teach_takephotos_index
-- ----------------------------
DROP TABLE IF EXISTS `teach_takephotos_index`;
CREATE TABLE `teach_takephotos_index` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(64) DEFAULT NULL COMMENT '名称',
  `upper` int(11) DEFAULT NULL COMMENT '上级',
  `nindex` varchar(64) DEFAULT NULL COMMENT '排序',
  `type_code` varchar(64) DEFAULT NULL COMMENT '类型CODE',
  `type` varchar(64) DEFAULT NULL COMMENT '类型',
  `namepy` varchar(64) DEFAULT NULL,
  `namewb` varchar(64) DEFAULT NULL,
  `upd_user` varchar(64) DEFAULT NULL,
  `upd_time` datetime DEFAULT NULL,
  `crt_user` varchar(64) DEFAULT NULL,
  `crt_time` datetime DEFAULT NULL,
  `remark` varchar(128) DEFAULT NULL,
  `isdelete` tinyint(1) unsigned DEFAULT '0' COMMENT '是否删除',
  `extend1` varchar(64) DEFAULT NULL COMMENT '预留',
  `extend2` varchar(64) DEFAULT NULL COMMENT '预留',
  `extend3` varchar(64) DEFAULT NULL COMMENT '预留',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for teach_takephotos_items
-- ----------------------------
DROP TABLE IF EXISTS `teach_takephotos_items`;
CREATE TABLE `teach_takephotos_items` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `indexID` int(11) NOT NULL COMMENT '主表外键',
  `path` varchar(128) DEFAULT NULL COMMENT '图片路径',
  `nindex` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`ID`),
  KEY `FK_indexID` (`indexID`),
  CONSTRAINT `FK_indexID` FOREIGN KEY (`indexID`) REFERENCES `teach_takephotos_index` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=187 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tem_report_zhengtw0829
-- ----------------------------
DROP TABLE IF EXISTS `tem_report_zhengtw0829`;
CREATE TABLE `tem_report_zhengtw0829` (
  `ACCESSION_NUM` varchar(64) DEFAULT NULL COMMENT '流水号',
  `OPERATION_USER` varchar(64) DEFAULT NULL COMMENT '操作人',
  `finding` text,
  `impression` text COMMENT '诊断建议',
  `CHECK_APPLY_TIME` datetime DEFAULT NULL COMMENT '申请时间',
  `CHECK_REPORT_TIME` datetime DEFAULT NULL COMMENT '报告世间',
  `subtime` bigint(21) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for z_databi
-- ----------------------------
DROP TABLE IF EXISTS `z_databi`;
CREATE TABLE `z_databi` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `VALUE1` varchar(2000) DEFAULT NULL,
  `VALUE2` int(11) DEFAULT NULL,
  `VALUE3` datetime DEFAULT NULL,
  `VALUE4` double DEFAULT NULL,
  `NINDEX` varchar(64) DEFAULT NULL,
  `REMARK` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `INDEX_VALUE1` (`VALUE1`(255)) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- View structure for patient_data
-- ----------------------------
DROP VIEW IF EXISTS `patient_data`;
CREATE ALGORITHM=UNDEFINED DEFINER=`rmisu`@`%` SQL SECURITY DEFINER VIEW `patient_data` AS select `pat`.`PAT_ID` AS `PAT_ONLY_ID`,`pat`.`ID` AS `PAT_ID`,`pat`.`NAME` AS `PAT_NAME`,`pat`.`NAME_PY` AS `PAT_NAME_PY`,`pat`.`SEX_CODE` AS `PAT_SEX_CODE`,`pat`.`SEX` AS `PAT_SEX`,`pat`.`BITTH` AS `PAT_BITTH`,`pat`.`ID_SH` AS `PAT_ID_SH`,`pat`.`SOCIALSECURITY` AS `PAT_SOCIALSECURITY`,`pat`.`NATION_CODE` AS `PAT_NATION_CODE`,`pat`.`NATION` AS `PAT_NATION`,`pat`.`BLOODTYPE_CODE` AS `PAT_BLOODTYPE_CODE`,`pat`.`BLOODTYPE` AS `PAT_BLOODTYPE`,`pat`.`PHON` AS `PAT_PHON`,`pat`.`ADDRESS` AS `PAT_ADDRESS`,`pat`.`Email` AS `PAT_Email`,`pat`.`IDENTITY_CODE` AS `PAT_IDENTITY_CODE`,`pat`.`IDENTITY` AS `PAT_IDENTITY`,`pat`.`ptn_id_dcm` AS `PAT_ID_DCM`,`patinfo`.`ID` AS `PATINFO_ID`,`patinfo`.`PAT_ID` AS `PATINFO_ONLY_ID`,`patinfo`.`CHECKLIST_ID` AS `PATINFO_CHECKLIST_ID`,`patinfo`.`CARD_NO` AS `PATINFO_CARD_NO`,`patinfo`.`IHP_NO` AS `PATINFO_IHP_NO`,`patinfo`.`VISID` AS `PATINFO_VISID` from (`busin_patient` `pat` left join `busin_patient_info` `patinfo` on((`pat`.`PAT_ID` = `patinfo`.`PAT_ID`))) where (`pat`.`ISDELETE` = 0) ;

-- ----------------------------
-- View structure for rep_dcm
-- ----------------------------
DROP VIEW IF EXISTS `rep_dcm`;
CREATE ALGORITHM=UNDEFINED DEFINER=`rmisu`@`%` SQL SECURITY DEFINER VIEW `rep_dcm` AS select `indexcl`.`ID` AS `CHECK_ID`,`indexcl`.`CHECK_NUM` AS `CHECK_NUM`,`indexcl`.`ACCESSION_NUM` AS `CHECK_ACCESSION_NUM`,`indexcl`.`PARTS_CODE` AS `CHECK_PARTS_CODE`,`indexcl`.`PARTS` AS `CHECK_PARTS`,`indexcl`.`SUMMARY_CODE` AS `CHECK_SUMMARY_CODE`,`indexcl`.`SUMMARY` AS `CHECK_SUMMARY`,`indexcl`.`EXAM_CODE` AS `CHECK_EXAM_CODE`,`indexcl`.`EXAM` AS `CHECK_EXAM`,`indexcl`.`COSTS` AS `CHECK_COSTS`,`indexcl`.`STATUS_CODE` AS `CHECK_STATUS_CODE`,`indexcl`.`STATUS` AS `CHECK_STATUS`,`indexcl`.`STATUS_AI_CODE` AS `CHECK_STATUS_AI_CODE`,`indexcl`.`STATUS_AI` AS `CHECK_STATUS_AI`,`indexcl`.`PARTS_GROUP_CODE` AS `CHECK_PARTS_GROUP_CODE`,`indexcl`.`PARTS_GROUP` AS `CHECK_PARTS_GROUP`,`indexcl`.`TYPE_CODE` AS `CHECK_TYPE_CODE`,`indexcl`.`TYPE` AS `CHECK_TYPE`,`indexcl`.`ISDELETE` AS `CHECK_ISDELETE`,`indexcl`.`UPD_USER` AS `CHECK_UPD_USER`,`indexcl`.`UPD_TIME` AS `CHECK_UPD_TIME`,`indexcl`.`CRT_USER` AS `CHECK_CRT_USER`,`indexcl`.`CRT_TIME` AS `CHECK_CRT_TIME`,`indexcl`.`remark` AS `CHECK_REMARK`,`indexcl`.`DESCRIBE_BQ` AS `CHECK_DESCRIBE_BQ`,`indexcl`.`APPLY_TIME` AS `CHECK_APPLY_TIME`,`indexcl`.`IMG_NUM` AS `CHECK_IMG_NUM`,`indexcl`.`IMG_RECEIVE_TIME` AS `CHECK_IMG_RECEIVE_TIME`,`indexcl`.`APPLY_DOC_CODE` AS `CHECK_APPLY_DOC_CODE`,`indexcl`.`APPLY_DOC` AS `CHECK_APPLY_DOC`,`indexcl`.`APPLY_HOSP_CODE` AS `CHECK_APPLY_HOSP_CODE`,`indexcl`.`APPLY_HOSP` AS `CHECK_APPLY_HOSP`,`indexcl`.`REP_GROUP_ID` AS `CHECK_REP_GROUP_ID`,`indexcl`.`JZ_FLAG` AS `CHECK_JZ_FLAG`,`indexcl`.`VJ_FLAG` AS `CHECK_VJ_FLAG`,`indexcl`.`REPORT_DR` AS `CHECK_REPORT_DR`,`indexcl`.`REPORT_TIME` AS `CHECK_REPORT_TIME`,`indexcl`.`AUDIT_DR` AS `CHECK_AUDIT_DR`,`indexcl`.`AUDIT_TIME` AS `CHECK_AUDIT_TIME`,`indexcl`.`PRINT_DR` AS `CHECK_PRINT_DR`,`indexcl`.`PRINT_TIME` AS `CHECK_PRINT_TIME`,`indexcl`.`REFUSE_CODE` AS `CHECK_REFUSE_CODE`,`indexcl`.`REFUSE_NAME` AS `CHECK_REFUSE_NAME`,`indexcl`.`PTN_SOURCE` AS `CHECK_PTN_SOURCE`,`indexcl`.`PAST_ILLNESS` AS `CHECK_PAST_ILLNESS`,`indexcl`.`accession_number_dcm` AS `CHECK_ACCESSION_NUM_DCM`,`indexcl`.`DOC_LEVEL` AS `DOC_LEVEL`,`reprc`.`ID` AS `REPRCD_ID`,`reprc`.`ACCESSION_NUM` AS `REPRCD_ACCESSION_NUM`,`reprc`.`PAT_ID` AS `REPRCD_PAT_ONLY_ID`,`reprc`.`REP_UID` AS `REPRCD_REP_UID`,`reprc`.`FINDING1` AS `REPRCD_FINDING1`,`reprc`.`FINDING2` AS `REPRCD_FINDING2`,`reprc`.`FINDING3` AS `REPRCD_FINDING3`,`reprc`.`IMPRESSION1` AS `REPRCD_IMPRESSION1`,`reprc`.`IMPRESSION2` AS `REPRCD_IMPRESSION2`,`reprc`.`IMPRESSION3` AS `REPRCD_IMPRESSION3`,`reprc`.`PAT_AGE` AS `REPRCD_PAT_AGE`,`reprc`.`AGE_UNIT_CODE` AS `REPRCD_AGE_UNIT_CODE`,`reprc`.`AGE_UNIT` AS `REPRCD_AGE_UNIT`,`reprc`.`HP` AS `REPRCD_HP`,`reprc`.`rate` AS `rate`,`reprc`.`suggest` AS `suggest` from (`busin_checklist_index` `indexcl` join `rep_record` `reprc`) where ((`indexcl`.`ISDELETE` = 0) and (`indexcl`.`ACCESSION_NUM` = `reprc`.`ACCESSION_NUM`)) ;

-- ----------------------------
-- View structure for rep_dcm_his
-- ----------------------------
DROP VIEW IF EXISTS `rep_dcm_his`;
CREATE ALGORITHM=UNDEFINED DEFINER=`rmisu`@`%` SQL SECURITY DEFINER VIEW `rep_dcm_his` AS select `indexcl`.`ID` AS `CHECK_ID`,`indexcl`.`CHECK_NUM` AS `CHECK_NUM`,`indexcl`.`ACCESSION_NUM` AS `CHECK_ACCESSION_NUM`,`indexcl`.`PARTS_CODE` AS `CHECK_PARTS_CODE`,`indexcl`.`PARTS` AS `CHECK_PARTS`,`indexcl`.`SUMMARY_CODE` AS `CHECK_SUMMARY_CODE`,`indexcl`.`SUMMARY` AS `CHECK_SUMMARY`,`indexcl`.`EXAM_CODE` AS `CHECK_EXAM_CODE`,`indexcl`.`EXAM` AS `CHECK_EXAM`,`indexcl`.`COSTS` AS `CHECK_COSTS`,`indexcl`.`STATUS_CODE` AS `CHECK_STATUS_CODE`,`indexcl`.`STATUS` AS `CHECK_STATUS`,`indexcl`.`STATUS_AI_CODE` AS `CHECK_STATUS_AI_CODE`,`indexcl`.`STATUS_AI` AS `CHECK_STATUS_AI`,`indexcl`.`PARTS_GROUP_CODE` AS `CHECK_PARTS_GROUP_CODE`,`indexcl`.`PARTS_GROUP` AS `CHECK_PARTS_GROUP`,`indexcl`.`TYPE_CODE` AS `CHECK_TYPE_CODE`,`indexcl`.`TYPE` AS `CHECK_TYPE`,`indexcl`.`ISDELETE` AS `CHECK_ISDELETE`,`indexcl`.`UPD_USER` AS `CHECK_UPD_USER`,`indexcl`.`UPD_TIME` AS `CHECK_UPD_TIME`,`indexcl`.`CRT_USER` AS `CHECK_CRT_USER`,`indexcl`.`CRT_TIME` AS `CHECK_CRT_TIME`,`indexcl`.`REMARK` AS `CHECK_REMARK`,`indexcl`.`DESCRIBE_BQ` AS `CHECK_DESCRIBE_BQ`,`indexcl`.`APPLY_TIME` AS `CHECK_APPLY_TIME`,`indexcl`.`IMG_NUM` AS `CHECK_IMG_NUM`,`indexcl`.`IMG_RECEIVE_TIME` AS `CHECK_IMG_RECEIVE_TIME`,`indexcl`.`APPLY_DOC_CODE` AS `CHECK_APPLY_DOC_CODE`,`indexcl`.`APPLY_DOC` AS `CHECK_APPLY_DOC`,`indexcl`.`APPLY_HOSP_CODE` AS `CHECK_APPLY_HOSP_CODE`,`indexcl`.`APPLY_HOSP` AS `CHECK_APPLY_HOSP`,`indexcl`.`REP_GROUP_ID` AS `CHECK_REP_GROUP_ID`,`indexcl`.`JZ_FLAG` AS `CHECK_JZ_FLAG`,`indexcl`.`VJ_FLAG` AS `CHECK_VJ_FLAG`,`indexcl`.`REPORT_DR` AS `CHECK_REPORT_DR`,`indexcl`.`REPORT_TIME` AS `CHECK_REPORT_TIME`,`indexcl`.`AUDIT_DR` AS `CHECK_AUDIT_DR`,`indexcl`.`AUDIT_TIME` AS `CHECK_AUDIT_TIME`,`indexcl`.`PRINT_DR` AS `CHECK_PRINT_DR`,`indexcl`.`PRINT_TIME` AS `CHECK_PRINT_TIME`,`indexcl`.`REFUSE_CODE` AS `CHECK_REFUSE_CODE`,`indexcl`.`REFUSE_NAME` AS `CHECK_REFUSE_NAME`,`indexcl`.`PTN_SOURCE` AS `CHECK_PTN_SOURCE`,`indexcl`.`PAST_ILLNESS` AS `CHECK_PAST_ILLNESS`,`indexcl`.`accession_number_dcm` AS `CHECK_ACCESSION_NUM_DCM`,`indexcl`.`DOC_LEVEL` AS `DOC_LEVEL`,`reprc`.`ID` AS `REPRCD_ID`,`reprc`.`ACCESSION_NUM` AS `REPRCD_ACCESSION_NUM`,`reprc`.`PAT_ID` AS `REPRCD_PAT_ONLY_ID`,`reprc`.`REP_UID` AS `REPRCD_REP_UID`,`reprc`.`FINDING1` AS `REPRCD_FINDING1`,`reprc`.`FINDING2` AS `REPRCD_FINDING2`,`reprc`.`FINDING3` AS `REPRCD_FINDING3`,`reprc`.`IMPRESSION1` AS `REPRCD_IMPRESSION1`,`reprc`.`IMPRESSION2` AS `REPRCD_IMPRESSION2`,`reprc`.`IMPRESSION3` AS `REPRCD_IMPRESSION3`,`reprc`.`PAT_AGE` AS `REPRCD_PAT_AGE`,`reprc`.`AGE_UNIT_CODE` AS `REPRCD_AGE_UNIT_CODE`,`reprc`.`AGE_UNIT` AS `REPRCD_AGE_UNIT`,`reprc`.`HP` AS `REPRCD_HP`,`reprc`.`rate` AS `rate`,`reprc`.`suggest` AS `suggest` from (`busin_checklist_index_his` `indexcl` join `rep_record_his` `reprc`) where ((`indexcl`.`ISDELETE` = 0) and (`indexcl`.`ACCESSION_NUM` = `reprc`.`ACCESSION_NUM`)) ;

-- ----------------------------
-- View structure for view_worklist
-- ----------------------------
DROP VIEW IF EXISTS `view_worklist`;
CREATE ALGORITHM=UNDEFINED DEFINER=`rmisu`@`%` SQL SECURITY DEFINER VIEW `view_worklist` AS select `patient_data`.`PAT_ONLY_ID` AS `PAT_ONLY_ID`,`patient_data`.`PAT_ID` AS `PAT_ID`,`patient_data`.`PAT_NAME` AS `PAT_NAME`,`patient_data`.`PAT_NAME_PY` AS `PAT_NAME_PY`,`patient_data`.`PAT_SEX_CODE` AS `PAT_SEX_CODE`,`patient_data`.`PAT_SEX` AS `PAT_SEX`,`patient_data`.`PAT_BITTH` AS `PAT_BITTH`,`patient_data`.`PAT_ID_SH` AS `PAT_ID_SH`,`patient_data`.`PAT_SOCIALSECURITY` AS `PAT_SOCIALSECURITY`,`patient_data`.`PAT_NATION_CODE` AS `PAT_NATION_CODE`,`patient_data`.`PAT_NATION` AS `PAT_NATION`,`patient_data`.`PAT_BLOODTYPE_CODE` AS `PAT_BLOODTYPE_CODE`,`patient_data`.`PAT_BLOODTYPE` AS `PAT_BLOODTYPE`,`patient_data`.`PAT_PHON` AS `PAT_PHON`,`patient_data`.`PAT_ADDRESS` AS `PAT_ADDRESS`,`patient_data`.`PAT_Email` AS `PAT_Email`,`patient_data`.`PAT_IDENTITY_CODE` AS `PAT_IDENTITY_CODE`,`patient_data`.`PAT_IDENTITY` AS `PAT_IDENTITY`,`patient_data`.`PATINFO_ID` AS `PATINFO_ID`,`patient_data`.`PATINFO_ONLY_ID` AS `PATINFO_ONLY_ID`,`patient_data`.`PATINFO_CHECKLIST_ID` AS `PATINFO_CHECKLIST_ID`,`patient_data`.`PATINFO_CARD_NO` AS `PATINFO_CARD_NO`,`patient_data`.`PATINFO_IHP_NO` AS `PATINFO_IHP_NO`,`patient_data`.`PATINFO_VISID` AS `PATINFO_VISID`,`patient_data`.`PAT_ID_DCM` AS `PAT_ID_DCM`,`rep_dcm`.`CHECK_ACCESSION_NUM_DCM` AS `CHECK_ACCESSION_NUM_DCM`,`rep_dcm`.`CHECK_ID` AS `CHECK_ID`,`rep_dcm`.`CHECK_NUM` AS `CHECK_NUM`,`rep_dcm`.`CHECK_ACCESSION_NUM` AS `CHECK_ACCESSION_NUM`,`rep_dcm`.`CHECK_PARTS_CODE` AS `CHECK_PARTS_CODE`,`rep_dcm`.`CHECK_PARTS` AS `CHECK_PARTS`,`rep_dcm`.`CHECK_SUMMARY_CODE` AS `CHECK_SUMMARY_CODE`,`rep_dcm`.`CHECK_SUMMARY` AS `CHECK_SUMMARY`,`rep_dcm`.`CHECK_EXAM_CODE` AS `CHECK_EXAM_CODE`,`rep_dcm`.`CHECK_EXAM` AS `CHECK_EXAM`,`rep_dcm`.`CHECK_COSTS` AS `CHECK_COSTS`,`rep_dcm`.`CHECK_STATUS_CODE` AS `CHECK_STATUS_CODE`,`rep_dcm`.`CHECK_STATUS` AS `CHECK_STATUS`,`rep_dcm`.`CHECK_STATUS_AI_CODE` AS `CHECK_STATUS_AI_CODE`,`rep_dcm`.`CHECK_STATUS_AI` AS `CHECK_STATUS_AI`,`rep_dcm`.`CHECK_TYPE_CODE` AS `CHECK_TYPE_CODE`,`rep_dcm`.`CHECK_TYPE` AS `CHECK_TYPE`,`rep_dcm`.`CHECK_PARTS_GROUP_CODE` AS `CHECK_PARTS_GROUP_CODE`,`rep_dcm`.`CHECK_PARTS_GROUP` AS `CHECK_PARTS_GROUP`,`rep_dcm`.`CHECK_ISDELETE` AS `CHECK_ISDELETE`,`rep_dcm`.`CHECK_UPD_USER` AS `CHECK_UPD_USER`,`rep_dcm`.`CHECK_UPD_TIME` AS `CHECK_UPD_TIME`,`rep_dcm`.`CHECK_CRT_USER` AS `CHECK_CRT_USER`,`rep_dcm`.`CHECK_CRT_TIME` AS `CHECK_CRT_TIME`,`rep_dcm`.`CHECK_REMARK` AS `CHECK_REMARK`,`rep_dcm`.`CHECK_DESCRIBE_BQ` AS `CHECK_DESCRIBE_BQ`,`rep_dcm`.`CHECK_APPLY_TIME` AS `CHECK_APPLY_TIME`,`rep_dcm`.`CHECK_IMG_NUM` AS `CHECK_IMG_NUM`,`rep_dcm`.`CHECK_IMG_RECEIVE_TIME` AS `CHECK_IMG_RECEIVE_TIME`,`rep_dcm`.`CHECK_APPLY_DOC_CODE` AS `CHECK_APPLY_DOC_CODE`,`rep_dcm`.`CHECK_APPLY_DOC` AS `CHECK_APPLY_DOC`,`rep_dcm`.`CHECK_APPLY_HOSP_CODE` AS `CHECK_APPLY_HOSP_CODE`,`rep_dcm`.`CHECK_APPLY_HOSP` AS `CHECK_APPLY_HOSP`,`rep_dcm`.`CHECK_REP_GROUP_ID` AS `CHECK_REP_GROUP_ID`,`rep_dcm`.`CHECK_JZ_FLAG` AS `CHECK_JZ_FLAG`,`rep_dcm`.`CHECK_VJ_FLAG` AS `CHECK_VJ_FLAG`,`rep_dcm`.`CHECK_REPORT_DR` AS `CHECK_REPORT_DR`,`rep_dcm`.`CHECK_REPORT_TIME` AS `CHECK_REPORT_TIME`,`rep_dcm`.`CHECK_AUDIT_DR` AS `CHECK_AUDIT_DR`,`rep_dcm`.`CHECK_AUDIT_TIME` AS `CHECK_AUDIT_TIME`,`rep_dcm`.`CHECK_PRINT_DR` AS `CHECK_PRINT_DR`,`rep_dcm`.`CHECK_PRINT_TIME` AS `CHECK_PRINT_TIME`,`rep_dcm`.`CHECK_REFUSE_CODE` AS `CHECK_REFUSE_CODE`,`rep_dcm`.`CHECK_REFUSE_NAME` AS `CHECK_REFUSE_NAME`,`rep_dcm`.`REPRCD_ID` AS `REPRCD_ID`,`rep_dcm`.`REPRCD_ACCESSION_NUM` AS `REPRCD_ACCESSION_NUM`,`rep_dcm`.`REPRCD_PAT_ONLY_ID` AS `REPRCD_PAT_ONLY_ID`,`rep_dcm`.`REPRCD_REP_UID` AS `REPRCD_REP_UID`,`rep_dcm`.`REPRCD_FINDING1` AS `REPRCD_FINDING1`,`rep_dcm`.`REPRCD_FINDING2` AS `REPRCD_FINDING2`,`rep_dcm`.`REPRCD_FINDING3` AS `REPRCD_FINDING3`,`rep_dcm`.`REPRCD_IMPRESSION1` AS `REPRCD_IMPRESSION1`,`rep_dcm`.`REPRCD_IMPRESSION2` AS `REPRCD_IMPRESSION2`,`rep_dcm`.`REPRCD_IMPRESSION3` AS `REPRCD_IMPRESSION3`,`rep_dcm`.`REPRCD_PAT_AGE` AS `REPRCD_PAT_AGE`,`rep_dcm`.`REPRCD_AGE_UNIT_CODE` AS `REPRCD_AGE_UNIT_CODE`,`rep_dcm`.`REPRCD_AGE_UNIT` AS `REPRCD_AGE_UNIT`,`rep_dcm`.`REPRCD_HP` AS `REPRCD_HP`,`rep_dcm`.`CHECK_PTN_SOURCE` AS `CHECK_PTN_SOURCE`,`rep_dcm`.`CHECK_PAST_ILLNESS` AS `CHECK_PAST_ILLNESS`,`rep_dcm`.`DOC_LEVEL` AS `DOC_LEVEL`,`rep_dcm`.`rate` AS `rate`,`rep_dcm`.`suggest` AS `suggest` from (`patient_data` join `rep_dcm` on((`rep_dcm`.`REPRCD_PAT_ONLY_ID` = `patient_data`.`PAT_ONLY_ID`))) order by `rep_dcm`.`CHECK_APPLY_TIME` desc ;

-- ----------------------------
-- View structure for view_worklist_his
-- ----------------------------
DROP VIEW IF EXISTS `view_worklist_his`;
CREATE ALGORITHM=UNDEFINED DEFINER=`rmisu`@`%` SQL SECURITY DEFINER VIEW `view_worklist_his` AS select `patient_data`.`PAT_ONLY_ID` AS `PAT_ONLY_ID`,`patient_data`.`PAT_ID` AS `PAT_ID`,`patient_data`.`PAT_NAME` AS `PAT_NAME`,`patient_data`.`PAT_NAME_PY` AS `PAT_NAME_PY`,`patient_data`.`PAT_SEX_CODE` AS `PAT_SEX_CODE`,`patient_data`.`PAT_SEX` AS `PAT_SEX`,`patient_data`.`PAT_BITTH` AS `PAT_BITTH`,`patient_data`.`PAT_ID_SH` AS `PAT_ID_SH`,`patient_data`.`PAT_SOCIALSECURITY` AS `PAT_SOCIALSECURITY`,`patient_data`.`PAT_NATION_CODE` AS `PAT_NATION_CODE`,`patient_data`.`PAT_NATION` AS `PAT_NATION`,`patient_data`.`PAT_BLOODTYPE_CODE` AS `PAT_BLOODTYPE_CODE`,`patient_data`.`PAT_BLOODTYPE` AS `PAT_BLOODTYPE`,`patient_data`.`PAT_PHON` AS `PAT_PHON`,`patient_data`.`PAT_ADDRESS` AS `PAT_ADDRESS`,`patient_data`.`PAT_Email` AS `PAT_Email`,`patient_data`.`PAT_IDENTITY_CODE` AS `PAT_IDENTITY_CODE`,`patient_data`.`PAT_IDENTITY` AS `PAT_IDENTITY`,`patient_data`.`PATINFO_ID` AS `PATINFO_ID`,`patient_data`.`PATINFO_ONLY_ID` AS `PATINFO_ONLY_ID`,`patient_data`.`PATINFO_CHECKLIST_ID` AS `PATINFO_CHECKLIST_ID`,`patient_data`.`PATINFO_CARD_NO` AS `PATINFO_CARD_NO`,`patient_data`.`PATINFO_IHP_NO` AS `PATINFO_IHP_NO`,`patient_data`.`PATINFO_VISID` AS `PATINFO_VISID`,`patient_data`.`PAT_ID_DCM` AS `PAT_ID_DCM`,`rep_dcm_his`.`CHECK_ACCESSION_NUM_DCM` AS `CHECK_ACCESSION_NUM_DCM`,`rep_dcm_his`.`CHECK_ID` AS `CHECK_ID`,`rep_dcm_his`.`CHECK_NUM` AS `CHECK_NUM`,`rep_dcm_his`.`CHECK_ACCESSION_NUM` AS `CHECK_ACCESSION_NUM`,`rep_dcm_his`.`CHECK_PARTS_CODE` AS `CHECK_PARTS_CODE`,`rep_dcm_his`.`CHECK_PARTS` AS `CHECK_PARTS`,`rep_dcm_his`.`CHECK_SUMMARY_CODE` AS `CHECK_SUMMARY_CODE`,`rep_dcm_his`.`CHECK_SUMMARY` AS `CHECK_SUMMARY`,`rep_dcm_his`.`CHECK_EXAM_CODE` AS `CHECK_EXAM_CODE`,`rep_dcm_his`.`CHECK_EXAM` AS `CHECK_EXAM`,`rep_dcm_his`.`CHECK_COSTS` AS `CHECK_COSTS`,`rep_dcm_his`.`CHECK_STATUS_CODE` AS `CHECK_STATUS_CODE`,`rep_dcm_his`.`CHECK_STATUS` AS `CHECK_STATUS`,`rep_dcm_his`.`CHECK_STATUS_AI_CODE` AS `CHECK_STATUS_AI_CODE`,`rep_dcm_his`.`CHECK_STATUS_AI` AS `CHECK_STATUS_AI`,`rep_dcm_his`.`CHECK_TYPE_CODE` AS `CHECK_TYPE_CODE`,`rep_dcm_his`.`CHECK_TYPE` AS `CHECK_TYPE`,`rep_dcm_his`.`CHECK_PARTS_GROUP_CODE` AS `CHECK_PARTS_GROUP_CODE`,`rep_dcm_his`.`CHECK_PARTS_GROUP` AS `CHECK_PARTS_GROUP`,`rep_dcm_his`.`CHECK_ISDELETE` AS `CHECK_ISDELETE`,`rep_dcm_his`.`CHECK_UPD_USER` AS `CHECK_UPD_USER`,`rep_dcm_his`.`CHECK_UPD_TIME` AS `CHECK_UPD_TIME`,`rep_dcm_his`.`CHECK_CRT_USER` AS `CHECK_CRT_USER`,`rep_dcm_his`.`CHECK_CRT_TIME` AS `CHECK_CRT_TIME`,`rep_dcm_his`.`CHECK_REMARK` AS `CHECK_REMARK`,`rep_dcm_his`.`CHECK_DESCRIBE_BQ` AS `CHECK_DESCRIBE_BQ`,`rep_dcm_his`.`CHECK_APPLY_TIME` AS `CHECK_APPLY_TIME`,`rep_dcm_his`.`CHECK_IMG_NUM` AS `CHECK_IMG_NUM`,`rep_dcm_his`.`CHECK_IMG_RECEIVE_TIME` AS `CHECK_IMG_RECEIVE_TIME`,`rep_dcm_his`.`CHECK_APPLY_DOC_CODE` AS `CHECK_APPLY_DOC_CODE`,`rep_dcm_his`.`CHECK_APPLY_DOC` AS `CHECK_APPLY_DOC`,`rep_dcm_his`.`CHECK_APPLY_HOSP_CODE` AS `CHECK_APPLY_HOSP_CODE`,`rep_dcm_his`.`CHECK_APPLY_HOSP` AS `CHECK_APPLY_HOSP`,`rep_dcm_his`.`CHECK_REP_GROUP_ID` AS `CHECK_REP_GROUP_ID`,`rep_dcm_his`.`CHECK_JZ_FLAG` AS `CHECK_JZ_FLAG`,`rep_dcm_his`.`CHECK_VJ_FLAG` AS `CHECK_VJ_FLAG`,`rep_dcm_his`.`CHECK_REPORT_DR` AS `CHECK_REPORT_DR`,`rep_dcm_his`.`CHECK_REPORT_TIME` AS `CHECK_REPORT_TIME`,`rep_dcm_his`.`CHECK_AUDIT_DR` AS `CHECK_AUDIT_DR`,`rep_dcm_his`.`CHECK_AUDIT_TIME` AS `CHECK_AUDIT_TIME`,`rep_dcm_his`.`CHECK_PRINT_DR` AS `CHECK_PRINT_DR`,`rep_dcm_his`.`CHECK_PRINT_TIME` AS `CHECK_PRINT_TIME`,`rep_dcm_his`.`CHECK_REFUSE_CODE` AS `CHECK_REFUSE_CODE`,`rep_dcm_his`.`CHECK_REFUSE_NAME` AS `CHECK_REFUSE_NAME`,`rep_dcm_his`.`REPRCD_ID` AS `REPRCD_ID`,`rep_dcm_his`.`REPRCD_ACCESSION_NUM` AS `REPRCD_ACCESSION_NUM`,`rep_dcm_his`.`REPRCD_PAT_ONLY_ID` AS `REPRCD_PAT_ONLY_ID`,`rep_dcm_his`.`REPRCD_REP_UID` AS `REPRCD_REP_UID`,`rep_dcm_his`.`REPRCD_FINDING1` AS `REPRCD_FINDING1`,`rep_dcm_his`.`REPRCD_FINDING2` AS `REPRCD_FINDING2`,`rep_dcm_his`.`REPRCD_FINDING3` AS `REPRCD_FINDING3`,`rep_dcm_his`.`REPRCD_IMPRESSION1` AS `REPRCD_IMPRESSION1`,`rep_dcm_his`.`REPRCD_IMPRESSION2` AS `REPRCD_IMPRESSION2`,`rep_dcm_his`.`REPRCD_IMPRESSION3` AS `REPRCD_IMPRESSION3`,`rep_dcm_his`.`REPRCD_PAT_AGE` AS `REPRCD_PAT_AGE`,`rep_dcm_his`.`REPRCD_AGE_UNIT_CODE` AS `REPRCD_AGE_UNIT_CODE`,`rep_dcm_his`.`REPRCD_AGE_UNIT` AS `REPRCD_AGE_UNIT`,`rep_dcm_his`.`REPRCD_HP` AS `REPRCD_HP`,`rep_dcm_his`.`CHECK_PTN_SOURCE` AS `CHECK_PTN_SOURCE`,`rep_dcm_his`.`CHECK_PAST_ILLNESS` AS `CHECK_PAST_ILLNESS`,`rep_dcm_his`.`DOC_LEVEL` AS `DOC_LEVEL`,`rep_dcm_his`.`rate` AS `rate`,`rep_dcm_his`.`suggest` AS `suggest` from (`patient_data` join `rep_dcm_his` on((`rep_dcm_his`.`REPRCD_PAT_ONLY_ID` = `patient_data`.`PAT_ONLY_ID`))) order by `rep_dcm_his`.`CHECK_APPLY_TIME` desc ;
