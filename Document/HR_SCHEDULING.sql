/*
Navicat Oracle Data Transfer
Oracle Client Version : 12.2.0.1.0

Source Server         : HR
Source Server Version : 110200
Source Host           : 127.0.0.1:1521
Source Schema         : YAMATO

Target Server Type    : ORACLE
Target Server Version : 110200
File Encoding         : 65001

Date: 2017-06-20 15:29:31
*/


-- ----------------------------
-- Table structure for HR_SCHEDULING
-- ----------------------------
DROP TABLE "YAMATO"."HR_SCHEDULING";
CREATE TABLE "YAMATO"."HR_SCHEDULING" (
"YEAR" NUMBER(4) NOT NULL ,
"MONTH" NUMBER(2) NOT NULL ,
"EMPLOYEE_ID" NUMBER(10) NOT NULL ,
"STORE_ID" VARCHAR2(6 BYTE) NOT NULL ,
"DAY_1" NUMBER(10) NULL ,
"DAY_2" NUMBER(10) NULL ,
"DAY_3" NUMBER(10) NULL ,
"DAY_4" NUMBER(10) NULL ,
"DAY_5" NUMBER(10) NULL ,
"DAY_6" NUMBER(10) NULL ,
"DAY_7" NUMBER(10) NULL ,
"DAY_8" NUMBER(10) NULL ,
"DAY_9" NUMBER(10) NULL ,
"DAY_10" NUMBER(10) NULL ,
"DAY_11" NUMBER(10) NULL ,
"DAY_12" NUMBER(10) NULL ,
"DAY_13" NUMBER(10) NULL ,
"DAY_14" NUMBER(10) NULL ,
"DAY_15" NUMBER(10) NULL ,
"DAY_16" NUMBER(10) NULL ,
"DAY_17" NUMBER(10) NULL ,
"DAY_18" NUMBER(10) NULL ,
"DAY_19" NUMBER(10) NULL ,
"DAY_20" NUMBER(10) NULL ,
"DAY_21" NUMBER(10) NULL ,
"DAY_22" NUMBER(10) NULL ,
"DAY_23" NUMBER(10) NULL ,
"DAY_24" NUMBER(10) NULL ,
"DAY_25" NUMBER(10) NULL ,
"DAY_26" NUMBER(10) NULL ,
"DAY_27" NUMBER(10) NULL ,
"DAY_28" NUMBER(10) NULL ,
"DAY_29" NUMBER(10) NULL ,
"DAY_30" NUMBER(10) NULL ,
"DAY_31" NUMBER(10) NULL ,
"BATCH_DT" DATE DEFAULT sysdate  NULL ,
"DEL_FLG" NUMBER(1) DEFAULT 0  NULL ,
"CRE_PRO_ID" VARCHAR2(100 BYTE) NULL ,
"CREATOR" NUMBER NULL ,
"CREATE_DT" DATE DEFAULT sysdate  NULL ,
"MOD_PRO_ID" VARCHAR2(100 BYTE) NULL ,
"MODIFIER" NUMBER NULL ,
"MODIFY_DT" DATE DEFAULT sysdate  NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;
COMMENT ON COLUMN "YAMATO"."HR_SCHEDULING"."YEAR" IS '排班年份';
COMMENT ON COLUMN "YAMATO"."HR_SCHEDULING"."MONTH" IS '排班月份';
COMMENT ON COLUMN "YAMATO"."HR_SCHEDULING"."EMPLOYEE_ID" IS '员工编号';
COMMENT ON COLUMN "YAMATO"."HR_SCHEDULING"."STORE_ID" IS '营业所代码';
COMMENT ON COLUMN "YAMATO"."HR_SCHEDULING"."DAY_1" IS '月份，与Tb_m_General_Code的工作类型进行关联';
COMMENT ON COLUMN "YAMATO"."HR_SCHEDULING"."BATCH_DT" IS 'Batch执行日';
COMMENT ON COLUMN "YAMATO"."HR_SCHEDULING"."DEL_FLG" IS '删除标志';
COMMENT ON COLUMN "YAMATO"."HR_SCHEDULING"."CRE_PRO_ID" IS '创建程序ID';
COMMENT ON COLUMN "YAMATO"."HR_SCHEDULING"."CREATOR" IS '创建者ID';
COMMENT ON COLUMN "YAMATO"."HR_SCHEDULING"."CREATE_DT" IS '创建日';
COMMENT ON COLUMN "YAMATO"."HR_SCHEDULING"."MOD_PRO_ID" IS '更新程序ID';
COMMENT ON COLUMN "YAMATO"."HR_SCHEDULING"."MODIFIER" IS '更新者ID';
COMMENT ON COLUMN "YAMATO"."HR_SCHEDULING"."MODIFY_DT" IS '更新日';

-- ----------------------------
-- Table structure for HR_WORKWAY
-- ----------------------------
DROP TABLE "YAMATO"."HR_WORKWAY";
CREATE TABLE "YAMATO"."HR_WORKWAY" (
"WAYID" NUMBER NOT NULL ,
"GROUPNAME" VARCHAR2(10 BYTE) NULL ,
"CONTENT" VARCHAR2(20 BYTE) NULL ,
"SORTNUM" NUMBER NULL ,
"STARTTIME" VARCHAR2(10 BYTE) NULL ,
"ENDTIME" VARCHAR2(10 BYTE) NULL ,
"RESTTIME" VARCHAR2(10 BYTE) NULL ,
"BATCH_DT" DATE DEFAULT sysdate  NOT NULL ,
"DEL_FLG" NUMBER(1) DEFAULT 0  NOT NULL ,
"CRE_PRO_ID" VARCHAR2(30 BYTE) NOT NULL ,
"CREATOR" NUMBER NOT NULL ,
"CREATE_DT" DATE DEFAULT sysdate  NOT NULL ,
"MOD_PRO_ID" VARCHAR2(30 BYTE) NULL ,
"MODIFIER" NUMBER NULL ,
"MODIFY_DT" DATE DEFAULT sysdate  NULL ,
"ISSHOW" NUMBER DEFAULT 0  NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;
COMMENT ON COLUMN "YAMATO"."HR_WORKWAY"."WAYID" IS '勤务方式代码';
COMMENT ON COLUMN "YAMATO"."HR_WORKWAY"."GROUPNAME" IS '分组名称';
COMMENT ON COLUMN "YAMATO"."HR_WORKWAY"."CONTENT" IS '勤务方式名称';
COMMENT ON COLUMN "YAMATO"."HR_WORKWAY"."SORTNUM" IS '排序号';
COMMENT ON COLUMN "YAMATO"."HR_WORKWAY"."STARTTIME" IS '工作开始时间';
COMMENT ON COLUMN "YAMATO"."HR_WORKWAY"."ENDTIME" IS '工作结束时间';
COMMENT ON COLUMN "YAMATO"."HR_WORKWAY"."RESTTIME" IS '工作休息时间';
COMMENT ON COLUMN "YAMATO"."HR_WORKWAY"."ISSHOW" IS '是否显示0:显示 1:不显示';

-- ----------------------------
-- Checks structure for table HR_SCHEDULING
-- ----------------------------
ALTER TABLE "YAMATO"."HR_SCHEDULING" ADD CHECK ("YEAR" IS NOT NULL);
ALTER TABLE "YAMATO"."HR_SCHEDULING" ADD CHECK ("MONTH" IS NOT NULL);
ALTER TABLE "YAMATO"."HR_SCHEDULING" ADD CHECK ("EMPLOYEE_ID" IS NOT NULL);
ALTER TABLE "YAMATO"."HR_SCHEDULING" ADD CHECK ("STORE_ID" IS NOT NULL);

-- ----------------------------
-- Checks structure for table HR_WORKWAY
-- ----------------------------
ALTER TABLE "YAMATO"."HR_WORKWAY" ADD CHECK ("WAYID" IS NOT NULL);
ALTER TABLE "YAMATO"."HR_WORKWAY" ADD CHECK ("BATCH_DT" IS NOT NULL);
ALTER TABLE "YAMATO"."HR_WORKWAY" ADD CHECK ("DEL_FLG" IS NOT NULL);
ALTER TABLE "YAMATO"."HR_WORKWAY" ADD CHECK ("CRE_PRO_ID" IS NOT NULL);
ALTER TABLE "YAMATO"."HR_WORKWAY" ADD CHECK ("CREATOR" IS NOT NULL);
ALTER TABLE "YAMATO"."HR_WORKWAY" ADD CHECK ("CREATE_DT" IS NOT NULL);
