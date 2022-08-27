/*
 Navicat Premium Data Transfer

 Source Server         : mysqltest
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : localhost:3306
 Source Schema         : pm_system

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 27/08/2022 16:11:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for pn_admin_menu
-- ----------------------------
DROP TABLE IF EXISTS `pn_admin_menu`;
CREATE TABLE `pn_admin_menu`  (
  `mid` bigint(0) NOT NULL,
  `menuname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名',
  `pid` bigint(0) NULL DEFAULT NULL COMMENT '父id',
  `url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '跳转链接',
  `show` int(0) NULL DEFAULT 1 COMMENT '是否显示在导航,1显示,2不显示',
  `glyphicon` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `versionid` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`mid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '数据分析菜单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pn_admin_menu
-- ----------------------------
INSERT INTO `pn_admin_menu` VALUES (11, '系统管理', 0, '#', 1, 'el-icon-user', 1);
INSERT INTO `pn_admin_menu` VALUES (12, '独角兽管理', 0, '#', 1, 'el-icon-s-order', 1);
INSERT INTO `pn_admin_menu` VALUES (13, '产品管理', 0, '#', 1, 'el-icon-data-line', 1);
INSERT INTO `pn_admin_menu` VALUES (14, '客户管理', 0, '#', 1, 'el-icon-timer', 1);
INSERT INTO `pn_admin_menu` VALUES (15, '会员管理', 0, '#', 1, 'el-icon-ship', 1);
INSERT INTO `pn_admin_menu` VALUES (16, 'IPO管理', 0, '#', 1, 'el-icon-setting', 1);
INSERT INTO `pn_admin_menu` VALUES (17, '共同基金', 0, '#', 1, 'el-icon-setting', 1);
INSERT INTO `pn_admin_menu` VALUES (18, '报表统计', 0, '#', 1, 'el-icon-setting', 1);
INSERT INTO `pn_admin_menu` VALUES (11001, '人员管理', 11, '/users', 1, 'glyphicon glyphicon-lock', 1);
INSERT INTO `pn_admin_menu` VALUES (11002, '菜单管理', 11, '/menus', 1, 'glyphicon glyphicon-lock', 2);
INSERT INTO `pn_admin_menu` VALUES (12001, '用户资产', 12, '/company/asset', 1, '', 2);
INSERT INTO `pn_admin_menu` VALUES (12002, '企业信息管理', 12, '/company/administration', 1, '', 2);
INSERT INTO `pn_admin_menu` VALUES (12003, '充值提现信息', 12, '/company/withdrawal', 1, NULL, 2);
INSERT INTO `pn_admin_menu` VALUES (13001, '产品系列管理', 13, '/company/series', 1, '', 3);
INSERT INTO `pn_admin_menu` VALUES (13002, '产品基础信息', 13, '/company/basic', 1, '', 3);
INSERT INTO `pn_admin_menu` VALUES (13003, '产品推荐管理', 13, '/company/recommend', 1, 'glyphicon glyphicon-hdd', 3);
INSERT INTO `pn_admin_menu` VALUES (13004, '产品审核', 13, '/company/review', 1, 'glyphicon glyphicon-eye-close', 3);

-- ----------------------------
-- Table structure for pn_admin_user
-- ----------------------------
DROP TABLE IF EXISTS `pn_admin_user`;
CREATE TABLE `pn_admin_user`  (
  `uid` bigint(0) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `role_id` bigint(0) NULL DEFAULT NULL COMMENT '角色id -1超级管理员 1管理员2普通用户3渠道',
  `reg_time` datetime(0) NULL DEFAULT NULL COMMENT '注册时间',
  `login_time` datetime(0) NULL DEFAULT NULL COMMENT '登录时间',
  `isvalid` int(0) NULL DEFAULT 1 COMMENT '=1有效 =0无效',
  `create_uid` bigint(0) NULL DEFAULT NULL COMMENT '创建人',
  `menu_id` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单数组',
  `channels` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '渠道数组',
  `partners` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注',
  `head_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5560 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pn_admin_user
-- ----------------------------
INSERT INTO `pn_admin_user` VALUES (1, '超管', 'aaa', NULL, '2020-11-12 17:42:16', NULL, 2, 7, NULL, NULL, NULL, 'ccc发发发', 'http://localhost:8080/img/01.jpg');
INSERT INTO `pn_admin_user` VALUES (3, 'wangshaocheng', 'e10adc3949ba59abbe56e057f20f883e', 1, '2016-07-22 15:38:19', '2016-07-25 19:06:24', 0, 9, '11001,12001,13001,20,20001', NULL, NULL, '该账号停用', 'http://localhost:8080/img/02.jpg');
INSERT INTO `pn_admin_user` VALUES (7, 'admin', 'e10adc3949ba59abbe56e057f20f883e', -1, '2016-07-22 10:03:52', '2020-07-16 14:26:32', 1, NULL, NULL, NULL, NULL, NULL, 'http://localhost:8080/img/03.jpg');
INSERT INTO `pn_admin_user` VALUES (8, 'ceshi001', 'd41d8cd98f00b204e9800998ecf8427e', 3, '2016-11-04 15:42:42', '2019-01-09 13:42:13', 2, 7, '11,11001,11002,12,12001,12002,13,13001,13002,15002', NULL, '1000067', '', 'http://localhost:8080/img/04.jpg');
INSERT INTO `pn_admin_user` VALUES (9, '测试1', 'abc123', 3, '2016-11-21 14:47:21', NULL, 1, 7, '11,11001,11002,12,12001,12002,12003,13,13001,13002,13003,13004,14,15,16,17,18', NULL, '1000067', '', 'http://localhost:8080/img/05.jpg');
INSERT INTO `pn_admin_user` VALUES (123, '3333', 'e10adc3949ba59abbe56e057f20f883e', NULL, '2020-11-12 17:57:23', NULL, 1, 9, NULL, NULL, NULL, '法尔', 'http://localhost:8080/img/06.jpg');
INSERT INTO `pn_admin_user` VALUES (1235, '444', 'e10adc3949ba59abbe56e057f20f883e', NULL, '2020-11-12 18:04:00', NULL, 0, 9, NULL, NULL, NULL, '啊啊啊啊', 'http://localhost:8080/img/06.jpg');
INSERT INTO `pn_admin_user` VALUES (5555, '6666', 'e10adc3949ba59abbe56e057f20f883e', NULL, '2020-11-12 18:00:56', NULL, 1, 9, NULL, NULL, NULL, 'test', 'http://localhost:8080/img/01.jpg');
INSERT INTO `pn_admin_user` VALUES (5560, '哈哈test', NULL, 1, '2022-08-19 20:35:22', NULL, 0, NULL, NULL, NULL, NULL, '123', 'http://localhost:8080/upload/01.jpg');

-- ----------------------------
-- Table structure for product_basic
-- ----------------------------
DROP TABLE IF EXISTS `product_basic`;
CREATE TABLE `product_basic`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `pname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品名称',
  `series` int(0) NULL DEFAULT NULL COMMENT '产品系列 0代表测试产品 1代表中金收益宝 2安顺收益',
  `classification` int(0) NULL DEFAULT NULL COMMENT '二级分类 0代表对冲基金 1代表其他基金 2 阳光私募 3 储蓄险',
  `mechanism_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机构名称',
  `open_time` datetime(0) NULL DEFAULT NULL,
  `currency` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '币种',
  `status` int(0) NULL DEFAULT 1 COMMENT '审核状态',
  `yield` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收益率',
  `review_person` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审核人',
  `cycle` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '认购周期',
  `management_rate` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '基金管理费率',
  `subscription_rate` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '认购费率',
  `amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '起投金额',
  `method` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '认购费收取方式',
  `redemption_period` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '赎回周期',
  `starting_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '赎回起始金额',
  `redemption_fee` decimal(10, 2) NULL DEFAULT NULL COMMENT '赎回费',
  `lock_period` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '锁定期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_basic
-- ----------------------------
INSERT INTO `product_basic` VALUES (1, 'QFII基金', 1, 1, '悦淘金信息技术有限公司', '2022-08-31 09:29:42', 'CNY', 1, '1.43%', '王五', '1', '1.23%', '1.63%', 200.03, '并购', '一周', 100.07, 300.00, '一周');
INSERT INTO `product_basic` VALUES (2, '哈hello基金', 0, 1, '尚马资管', '2022-08-02 16:07:53', 'USD', 1, '1111', '1111', '1111', '1111', '11000', 1111.00, '1000.0', '111', 1000.00, 111.00, '111');
INSERT INTO `product_basic` VALUES (3, 'REITS基金', 2, 2, '友邦', '2022-08-27 16:09:33', 'USD', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `product_basic` VALUES (4, '测试的基金', 0, 3, '尚马资管', '2022-07-22 16:10:24', 'CNY', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `product_basic` VALUES (5, '蚂蚁基金', 2, 0, '友邦', '2022-08-12 17:16:47', 'CNY', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `product_basic` VALUES (6, '哈哈基金', 1, 1, '1111', '2022-08-04 00:00:00', '1111', 1, '1.11%', NULL, NULL, '2.43%', '5.93%', 1.00, NULL, NULL, 1.00, 1.00, NULL);
INSERT INTO `product_basic` VALUES (8, '测试基金', 1, 1, '尚马机构', '2022-08-24 20:14:23', NULL, 0, '1.39%', '尚雯婕', NULL, '2.32%', '5.66%', 100.00, NULL, NULL, 1000.00, 100.00, NULL);
INSERT INTO `product_basic` VALUES (9, '债券型基金', 0, 0, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `product_basic` VALUES (10, '阿里基金', 2, 2, '投资机构', '2022-08-27 16:09:33', 'CNY', 1, '1.42%', 'coco', '三周', '2.42%', '1.42%', 200.01, '并购', '四周', 100.90, 200.00, '一周');
INSERT INTO `product_basic` VALUES (15, '腾讯基金', 2, 2, '投资机构', '2022-08-27 16:09:33', 'CNY', 1, '1.42%', 'coco', '三周', '2.42%', '1.42%', 200.01, '并购', '四周', 100.90, 200.00, '一周');
INSERT INTO `product_basic` VALUES (16, '腾讯基金', 2, 2, '投资机构', '2022-08-27 16:09:33', 'CNY', 1, '1.42%', 'coco', '三周', '2.42%%', '1.42%%', 200.01, '并购', '四周', 100.80, 200.00, '一周');
INSERT INTO `product_basic` VALUES (20, '腾讯基金', 2, 2, '投资机构', '2022-08-27 16:09:33', 'CNY', 1, '1.42%', 'coco', '三周', '2.42%', '1.42%', 200.01, '并购', '四周', 100.90, 200.00, '一周');
INSERT INTO `product_basic` VALUES (21, '123', 0, 0, '1111', '2022-08-09 00:00:00', 'CNY', 1, '111%', 'sunshine', '1住', '1.23%', '1%', 100.00, '1', '7周', 1.00, 1000.00, '哈哈');
INSERT INTO `product_basic` VALUES (22, '1111', 0, 1, '1', '2022-08-25 00:00:00', NULL, 1, '1%', '哈哈', '1', '1%', '1%', 0.00, '90.00', '20.00', 60.00, 90.00, '周六');
INSERT INTO `product_basic` VALUES (23, 'QFII基金', 1, 0, '123', '2022-08-12 00:00:00', 'CNY', 1, '123%', 'sunshine', '1', '1%', '1%', 90.00, '谁呀', '1', 90.00, 1000.00, '1周');
INSERT INTO `product_basic` VALUES (24, '1', 0, 0, '1', '2022-08-03 00:00:00', 'CNY', 1, '1%', '哈哈是我呀', '1', '1%', '%', 0.00, '', '', 0.00, 0.00, '');
INSERT INTO `product_basic` VALUES (25, 'REITS基金', 0, 1, '', '2022-08-17 00:00:00', 'CNY', 1, '2.3%', '哈哈哈', '11', '%', '111%', 0.01, '', '', 0.00, 0.09, '');
INSERT INTO `product_basic` VALUES (26, '美哦与', 0, 0, '1', '2022-08-26 00:00:00', 'CNY', 1, '123', '蔡健雅', '1', '1.24%', '1.34%', 90.00, '没有', '1', 20.00, 90.00, '1');
INSERT INTO `product_basic` VALUES (27, '', NULL, 1, '', '2022-08-04 00:00:00', 'CNY', 1, '', '', '', '', '', 0.00, '', '', 0.00, 0.00, '');
INSERT INTO `product_basic` VALUES (28, '112', 0, 0, '111', '2022-08-18 00:00:00', 'CNY', 1, '111', '', '', '111', '', 0.00, '111', '1111', 0.00, 0.00, '');

-- ----------------------------
-- Table structure for product_increase
-- ----------------------------
DROP TABLE IF EXISTS `product_increase`;
CREATE TABLE `product_increase`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `basic_id` int(0) NULL DEFAULT NULL COMMENT '关联的产品管理表id',
  `net_worth` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位净值',
  `growth_rate` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '累计增长率',
  `base_time` datetime(0) NULL DEFAULT NULL COMMENT '净值基准日',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_increase
-- ----------------------------
INSERT INTO `product_increase` VALUES (2, NULL, '', NULL, '2022-08-19 21:15:12');
INSERT INTO `product_increase` VALUES (4, 2, '1.24', '1.90', '2022-09-02 18:00:00');
INSERT INTO `product_increase` VALUES (5, 9, '13', '123', '2022-08-25 00:00:00');
INSERT INTO `product_increase` VALUES (6, 16, '1111', '111111', '2022-08-25 00:00:00');
INSERT INTO `product_increase` VALUES (7, 5, '10', '999', '2022-08-27 00:00:00');
INSERT INTO `product_increase` VALUES (8, 6, '111', '1111', '2022-08-18 00:00:00');

-- ----------------------------
-- Table structure for product_recommend
-- ----------------------------
DROP TABLE IF EXISTS `product_recommend`;
CREATE TABLE `product_recommend`  (
  `pb_id` int(0) NULL DEFAULT NULL COMMENT '外键 ==basic.id',
  `finer` int(0) NULL DEFAULT NULL COMMENT '推荐度  0普通推荐  1热门推荐',
  `issue` int(0) NULL DEFAULT NULL COMMENT '是否首发  0否  1是',
  `subscribe` int(0) NULL DEFAULT NULL COMMENT '是否线上申购 0否  1是',
  `sort` int(0) NULL DEFAULT NULL COMMENT '排序',
  `visible` int(0) NULL DEFAULT NULL COMMENT '是否投顾端可见 0否  1是',
  `explain` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '推荐理由',
  `is_finer` int(0) NULL DEFAULT NULL COMMENT '是否已推荐',
  `is_status` int(0) NULL DEFAULT NULL COMMENT '是否审核   0否  1是'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_recommend
-- ----------------------------
INSERT INTO `product_recommend` VALUES (1, NULL, NULL, NULL, 1, NULL, NULL, 1, 1);
INSERT INTO `product_recommend` VALUES (2, 1, 1, 1, 2, 1, '2222222', 0, 1);
INSERT INTO `product_recommend` VALUES (4, 1, 0, 1, 3, 0, NULL, 0, 1);
INSERT INTO `product_recommend` VALUES (5, 1, 1, 1, 4, 1, NULL, 1, 1);
INSERT INTO `product_recommend` VALUES (6, 0, 0, 1, 5, 1, NULL, 0, 1);
INSERT INTO `product_recommend` VALUES (7, 1, 0, 1, 6, 1, NULL, 1, 1);
INSERT INTO `product_recommend` VALUES (8, 1, 1, 1, 7, 1, '11111', 0, 1);
INSERT INTO `product_recommend` VALUES (9, 1, 1, 1, 8, 1, NULL, 0, 1);
INSERT INTO `product_recommend` VALUES (10, 0, 0, 1, 9, 0, NULL, 1, 1);
INSERT INTO `product_recommend` VALUES (11, 1, 1, 1, 10, NULL, NULL, 1, 1);
INSERT INTO `product_recommend` VALUES (12, 1, 0, 1, 11, NULL, NULL, 0, 1);
INSERT INTO `product_recommend` VALUES (13, 0, 1, 0, 12, NULL, NULL, 0, 1);
INSERT INTO `product_recommend` VALUES (14, 1, 0, 1, 13, NULL, NULL, 0, 1);
INSERT INTO `product_recommend` VALUES (15, 0, 1, 0, 14, NULL, NULL, 1, 1);
INSERT INTO `product_recommend` VALUES (16, 1, 1, 1, 15, NULL, NULL, 0, 1);
INSERT INTO `product_recommend` VALUES (8, 1, 1, 1, NULL, 0, 'aaa', NULL, 1);
INSERT INTO `product_recommend` VALUES (8, 1, 1, 1, NULL, 0, 'aaa', NULL, 1);
INSERT INTO `product_recommend` VALUES (8, 0, 0, 0, NULL, 0, '1111', NULL, 1);
INSERT INTO `product_recommend` VALUES (6, 0, 0, 1, NULL, 1, '123123123', NULL, 1);
INSERT INTO `product_recommend` VALUES (4, 1, 0, 1, NULL, 0, NULL, NULL, 1);
INSERT INTO `product_recommend` VALUES (9, 1, 0, 0, NULL, 1, '111', NULL, NULL);
INSERT INTO `product_recommend` VALUES (6, 1, 0, 0, NULL, 1, '稍等给你发你会非常', NULL, NULL);
INSERT INTO `product_recommend` VALUES (4, 1, 1, 1, NULL, 0, '11111111', NULL, NULL);
INSERT INTO `product_recommend` VALUES (6, 0, 1, 0, NULL, 1, '123', NULL, NULL);
INSERT INTO `product_recommend` VALUES (4, 0, 0, 1, NULL, 1, 'dsad ', NULL, NULL);
INSERT INTO `product_recommend` VALUES (16, 1, 1, 1, NULL, 1, 'dsadsad', NULL, NULL);
INSERT INTO `product_recommend` VALUES (0, 0, 1, 1, NULL, 1, '123', NULL, NULL);

-- ----------------------------
-- Table structure for product_review
-- ----------------------------
DROP TABLE IF EXISTS `product_review`;
CREATE TABLE `product_review`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `pname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品名称',
  `business_type` int(0) NULL DEFAULT NULL COMMENT '业务类型',
  `create_person` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `basic_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_review
-- ----------------------------
INSERT INTO `product_review` VALUES (1, '测试基金', 1, 'coco', '2022-08-03 22:20:37', '2022-08-03 22:20:40', 8);
INSERT INTO `product_review` VALUES (2, '债券型基金', 0, '哈哈', '2022-03-24 22:50:25', '2022-08-01 22:50:31', 9);
INSERT INTO `product_review` VALUES (3, '债券型基金', 2, '好', '2022-08-01 22:51:14', '2022-08-15 22:51:18', 1);
INSERT INTO `product_review` VALUES (4, 'REITS基金', 3, '快乐', '2022-08-05 22:51:33', '2022-08-13 22:51:37', 3);
INSERT INTO `product_review` VALUES (5, 'QFII基金', 2, '周华健', '2022-08-12 22:52:14', '2022-08-07 22:52:17', 23);

-- ----------------------------
-- Table structure for product_series
-- ----------------------------
DROP TABLE IF EXISTS `product_series`;
CREATE TABLE `product_series`  (
  `sid` int(0) NOT NULL AUTO_INCREMENT COMMENT '\r\n产品系列id',
  `sname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品中文名',
  `sename` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品英文名',
  `remittance` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '汇款信息概括',
  `bank_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收款银行名称',
  `bank_swift` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '银行SWIFT码',
  `bank_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'BANK CODE',
  `cnaps_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'CNAPS编号',
  `bank_area` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收款银行地区',
  `bank_city` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收款银行城市',
  `payee_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收款人户名',
  `payee_account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收款人账号',
  `payee_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收款人地址',
  `Information_organization` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资管机构',
  PRIMARY KEY (`sid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_series
-- ----------------------------
INSERT INTO `product_series` VALUES (1, '私募股权A(香港)111111', 'HK Private FundA11111111', '悦淘金信息技术有限公司111111', '染色奥若色绕所', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `product_series` VALUES (2, '中金收益宝', 'China Gold portfolio', '尚马资管', '海狮银行', NULL, NULL, NULL, '河南', '郑州', NULL, '大萨达', NULL, NULL);
INSERT INTO `product_series` VALUES (3, '测试基金产品', 'Unicorn Equity Investment Portfolio', '尚马资管', '郑州银行', 'DSFASF2FDS2F', 'FDSGSG156S1G', 'SF156S41DF651S68F1S', '郑州', '高新区', '林海', '54651616818615', '公园茂', '123');
INSERT INTO `product_series` VALUES (4, '安顺收益', 'Short DateD Liquid Portfolio', '友邦', '海狮银行', 'MFLDMGKLS', 'GFSDHSGHDSH', '1DFH53F1DH321FD56D', '河南', '郑州', '林海', '516586484615186165', '自知则知之做做做做做', '撒大声地撒');
INSERT INTO `product_series` VALUES (5, '中芯国际金融', 'Smic Financial', '中芯', '尚马银行', '1D3SA1F3A1F', '0SF1DFG61SAG651S05G1SD', '15G6S4D6G51F61G65S1G', '湖北', '武汉', '借鉴借鉴', '1f31s3ad1f2sd', 'dfsafsadfsadf', '佛挡杀佛');
INSERT INTO `product_series` VALUES (6, '汇淘金', 'HUITAOJIN', '汇淘金融', '是的法师打发', '士大夫撒都士大夫撒阿', ' 的说法', '的说法阿萨德', '度数发啊是否 ', '的说法阿萨德发', '士大夫暗室逢灯', '大师傅', NULL, 'sdfsafdsa ');
INSERT INTO `product_series` VALUES (7, '萨达萨达所多', 'fdggh', 'gfdghdfhdf', 'fdgfdgdfg', 'dfgdfgdf', 'gfdgfdg', 'dfgdf', 'gfdgfd', 'gfdgfdg', '第三方第三方', '范德萨发生打发个梵蒂冈', NULL, NULL);
INSERT INTO `product_series` VALUES (8, '海王金融', 'haiwang', '海王信息', '海王银行', 'ABCDEFG123', 'LH123LH', 'ZXCVBNM', '河南', '郑州', '林海', '42568123413ACDS', '河南省郑州市高新区公园茂', NULL);
INSERT INTO `product_series` VALUES (9, '海王', 'haiwang', '海王信息', 'aaa', 'swiftma123', 'bankdai123', 'cnpssd', '河南', '郑州', '林海', '收款人账号', '收款人地址', '分为非违法');
INSERT INTO `product_series` VALUES (10, '热帖沃尔特而额头纹', '退热贴', '是的撒', '山东银行', 'GREHERHERH', 'REGRGWEHWEG', 'G31THG35ER1H3ER10H2', '山东', '菏泽', '牛牪犇', '6666', '曹县', NULL);
INSERT INTO `product_series` VALUES (11, '林林林', 'linlinlin', '信息信息', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `product_series` VALUES (12, 'dsdsadad', 'sadasfas', 'dsaddasf', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `product_series` VALUES (13, '海王金融', 'haiwang', '海王信息', '海王银行', 'ABCDEFG123', 'LH123LH', 'ZXCVBNM', '河南', '郑州', '林海', '42568123413ACDS', '河南省郑州市高新区公园茂', NULL);
INSERT INTO `product_series` VALUES (14, '测试名', 'ceshi', 'SDASDA ', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `product_series` VALUES (19, 'adasdasd', 'fdsdafsdafsd', NULL, '海狮银行', '公司的感受到', NULL, NULL, '河南', '郑州', NULL, NULL, NULL, '热帖热特润特让他热');
INSERT INTO `product_series` VALUES (20, 'gfdgdfg', 'fdgdfg', 'fdgfdgfdg', '第三方士大夫是', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `product_series` VALUES (21, '海王金融', 'haiwang', '海王信息', '海王银行', 'ABCDEFG123', 'LH123LH123', 'ZXCVBNM', '河南', '郑州', '林海', '42568123413ACDS', '河南省郑州市高新区公园茂', NULL);
INSERT INTO `product_series` VALUES (22, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `product_series` VALUES (23, '打算', '大萨达撒大', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `product_series` VALUES (24, '萨达萨达所', ' 撒大声地撒', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `product_series` VALUES (25, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `product_series` VALUES (26, '海', 'jnfghgdh', 'fdgdfgdf', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `product_series` VALUES (27, '大', '佛挡杀佛', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `product_series` VALUES (28, '产品名称', 'name', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `product_series` VALUES (29, '123', '123', NULL, '丰富的发生的', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `product_series` VALUES (30, '123', '123', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for product_type
-- ----------------------------
DROP TABLE IF EXISTS `product_type`;
CREATE TABLE `product_type`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `classification` int(0) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_type
-- ----------------------------
INSERT INTO `product_type` VALUES (1, 0, '对冲基金');
INSERT INTO `product_type` VALUES (2, 1, '其他基金');
INSERT INTO `product_type` VALUES (3, 2, '阳光私募');
INSERT INTO `product_type` VALUES (4, 3, '储蓄险');

-- ----------------------------
-- Table structure for tb_area
-- ----------------------------
DROP TABLE IF EXISTS `tb_area`;
CREATE TABLE `tb_area`  (
  `area_id` int(0) NOT NULL,
  `area_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `parent_id` int(0) NOT NULL,
  PRIMARY KEY (`area_id`, `parent_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_area
-- ----------------------------
INSERT INTO `tb_area` VALUES (110000, '河南', 0);
INSERT INTO `tb_area` VALUES (110100, '郑州', 110000);
INSERT INTO `tb_area` VALUES (110101, '金水区', 110100);
INSERT INTO `tb_area` VALUES (110102, '高新区', 110100);
INSERT INTO `tb_area` VALUES (110200, '开封', 110000);
INSERT INTO `tb_area` VALUES (120000, '河北', 0);
INSERT INTO `tb_area` VALUES (120100, '石家庄', 120000);
INSERT INTO `tb_area` VALUES (120101, '石家庄区', 120100);

SET FOREIGN_KEY_CHECKS = 1;
