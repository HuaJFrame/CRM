/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50715 (5.7.15)
 Source Host           : localhost:3306
 Source Schema         : xycrm

 Target Server Type    : MySQL
 Target Server Version : 50715 (5.7.15)
 File Encoding         : 65001

 Date: 07/05/2023 16:52:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_cus_dev_plan
-- ----------------------------
DROP TABLE IF EXISTS `sys_cus_dev_plan`;
CREATE TABLE `sys_cus_dev_plan`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `sale_chance_id` int(11) NULL DEFAULT NULL COMMENT '营销机会id',
  `plan_item` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '计划内容',
  `plan_date` datetime NULL DEFAULT NULL COMMENT '计划日期',
  `exe_affect` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '执行效果',
  `create_date` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_sys_cus_dev_plan`(`sale_chance_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_cus_dev_plan
-- ----------------------------
INSERT INTO `sys_cus_dev_plan` VALUES (1, 6, '请客吃法', '2022-12-02 16:44:11', '效果不错', '2022-12-01 16:44:33', '2022-12-01 16:44:41');
INSERT INTO `sys_cus_dev_plan` VALUES (2, 6, '吃完饭送客户回家', '2022-12-02 16:45:14', '满意', '2022-12-01 16:45:26', '2022-12-01 16:45:30');
INSERT INTO `sys_cus_dev_plan` VALUES (3, 4, '一起唱K', '2022-12-03 00:00:00', '哈哈哈', '2022-12-03 19:35:31', '2022-12-03 19:35:31');
INSERT INTO `sys_cus_dev_plan` VALUES (4, 6, '玩游戏', '2022-12-02 00:00:00', '好', '2022-12-03 19:53:13', '2022-12-03 19:53:13');
INSERT INTO `sys_cus_dev_plan` VALUES (5, 7, '一起玩游戏', '2023-02-14 00:00:00', '☆☆☆☆☆', '2023-02-15 15:35:13', '2023-05-03 20:42:38');
INSERT INTO `sys_cus_dev_plan` VALUES (11, 7, '打球', '2023-05-03 00:00:00', '', '2023-05-03 20:49:48', '2023-05-03 20:49:48');
INSERT INTO `sys_cus_dev_plan` VALUES (12, 8, '测试1', '2023-05-04 00:00:00', '☆☆☆☆☆', '2023-05-04 00:28:36', '2023-05-04 00:38:29');
INSERT INTO `sys_cus_dev_plan` VALUES (13, 8, '测试2', '2023-05-02 00:00:00', '☆☆☆☆☆', '2023-05-04 00:38:54', '2023-05-04 00:38:54');
INSERT INTO `sys_cus_dev_plan` VALUES (14, 8, '测试3', '2023-05-03 00:00:00', '☆☆☆☆', '2023-05-04 00:39:16', '2023-05-04 00:39:16');
INSERT INTO `sys_cus_dev_plan` VALUES (15, 8, '测试4', '2023-05-20 00:00:00', '', '2023-05-04 00:39:31', '2023-05-04 00:39:31');

-- ----------------------------
-- Table structure for sys_customer
-- ----------------------------
DROP TABLE IF EXISTS `sys_customer`;
CREATE TABLE `sys_customer`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `khno` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户编号',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户名称',
  `area` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地区',
  `cus_manager` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户经理',
  `level` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '级别',
  `myd` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '满意度',
  `xyd` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户信用度',
  `address` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详细地址',
  `post_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮编',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `fax` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '传真',
  `web_site` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '网址',
  `yyzzzch` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '营业执照注册号',
  `fr` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '法人代表',
  `zczj` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注册资金',
  `nyye` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '年营业额',
  `khyh` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开户银行',
  `khzh` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开户账号',
  `dsdjh` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地税登记号	',
  `gsdjh` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '国税登记号',
  `state` tinyint(1) NULL DEFAULT NULL COMMENT '流失状态 0未流失 1暂缓流失',
  `create_date` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_customer
-- ----------------------------
INSERT INTO `sys_customer` VALUES (1, 'KH21321321', '北京大牛科技', '北京', 'test', '战略合作伙伴', '☆☆☆', '☆☆☆', '北京海淀区双榆树东里15号', '100027', '010-62263393', '010-62263393', 'www.daniu.com', '420103000057404', '赵飞翔', '1000', '5000', '中国银行', '6225231243641', '4422214321321', '4104322332', 1, '2017-01-16 11:28:43', '2016-08-24 18:42:19');
INSERT INTO `sys_customer` VALUES (2, 'KH20150526073022', '风驰科技', '北京', 'test', '大客户', '☆☆☆☆', '☆☆☆☆', '321', '21', '321', '321', '321', '321', '码云', '', '21', '321', '321', '321', '3213', 1, '2017-01-16 12:15:19', '2016-11-28 11:46:24');
INSERT INTO `sys_customer` VALUES (20, 'KH201709181013450', '腾讯', '测试', 'test', '战略合作伙伴', '☆☆☆☆☆', '☆☆☆☆', '', '', '13327792156', '', '', NULL, '赵飞翔', '', '', '', '', '', '', 1, '2017-01-16 10:13:57', '2020-02-19 10:30:26');
INSERT INTO `sys_customer` VALUES (21, 'KH201709181112739', '阿里巴巴', '北京', 'test01', '战略合作伙伴', '☆☆☆☆☆', '☆☆☆☆☆', '浙江杭州', '324324', '23424324324', '2343', 'www.alibaba.com', '232432', '码云', '100', '100000', '杭州', '23432432', '4324324', '234324234', 1, '2017-01-16 11:12:16', '2017-09-18 11:25:25');
INSERT INTO `sys_customer` VALUES (22, 'KH20171021105508617', '中国工商银行', '上海', 'test', '战略合作伙伴', '☆☆☆☆☆', '☆☆☆☆☆', '浦东', '201600', '18920156732', '12312321', 'www.icbc.com', '12323', '耿鹏', '1000000', '100000', '工商', '212321', '', '', 1, '2017-01-16 10:55:09', '2017-10-21 11:00:02');
INSERT INTO `sys_customer` VALUES (23, 'KH20180115104723756', '百度', '北京', 'test', '战略合作伙伴', '☆☆☆☆', '☆☆☆☆☆', '北京西二旗', '100000', '2321321', '213123', '123213', '2321321', '李彦宏', '10000', '100000', '工商', '121321313', '', '', 0, '2018-01-16 10:47:23', '2018-01-15 10:50:00');
INSERT INTO `sys_customer` VALUES (24, 'KH20180504112003301', '小米科技', '上海', 'test', '大客户', '☆☆☆☆☆', '☆☆☆☆☆', '北京市海淀区清河中街68号华润五彩城购物中心二期13层', '1000000', '18200000520', '123123131', 'www.xiaomi.com', '110108012660422', '雷军', '185000', '5000000', '中国银行', '99999999999', '91110108551385082Q', '91110108551385082Q', 0, '2023-02-06 11:16:21', '2023-02-21 16:43:26');
INSERT INTO `sys_customer` VALUES (32, 'KH_20230205234120', 'test', '背景', '李四', '大客户', '☆☆☆☆☆', '☆☆', '长沙', '16545', '18200000520', '1564651210', '', NULL, '张三', '1000万', '', '6854646516', '4865654654', '100000', '', 0, '2023-02-05 23:41:21', '2023-05-05 18:14:31');
INSERT INTO `sys_customer` VALUES (33, 'KH_20230505014223', '测试1', '测试1', '测试1', '重点开发客户', '☆', '☆☆☆☆', '测试1', '测试1', '18220001234', '测试1', '测试1', '测试1', '测试1', '测试1', '测试1', '1564651648646', '4565746854165', '', '', 0, '2023-05-05 01:42:24', '2023-05-05 01:42:24');

-- ----------------------------
-- Table structure for sys_customer_loss
-- ----------------------------
DROP TABLE IF EXISTS `sys_customer_loss`;
CREATE TABLE `sys_customer_loss`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cus_no` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户编号',
  `cus_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户姓名',
  `cus_manager` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户经理',
  `last_order_time` date NULL DEFAULT NULL COMMENT '最后下单时间',
  `confirm_loss_time` date NULL DEFAULT NULL COMMENT '确认流失时间',
  `state` tinyint(1) NULL DEFAULT NULL COMMENT '流失状态 0未流失 1流失',
  `loss_reason` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流失原因',
  `create_date` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_customer_loss
-- ----------------------------
INSERT INTO `sys_customer_loss` VALUES (21, 'KH21321321', '北京大牛科技', 'test', '2023-05-07', '2023-02-15', 1, '公司转型', '2023-02-08 23:47:12', '2023-02-15 19:01:11');
INSERT INTO `sys_customer_loss` VALUES (22, 'KH20150526073022', '风驰科技', 'test', NULL, NULL, 0, NULL, '2023-02-08 23:47:12', '2023-02-08 23:47:12');
INSERT INTO `sys_customer_loss` VALUES (23, 'KH201709181013450', '腾讯', 'test', '2020-03-03', '2023-02-15', 0, '哈哈哈', '2023-02-08 23:47:12', '2023-02-15 20:32:30');
INSERT INTO `sys_customer_loss` VALUES (24, 'KH201709181112739', '阿里巴巴', 'test01', NULL, NULL, 0, NULL, '2023-02-08 23:47:12', '2023-02-08 23:47:12');
INSERT INTO `sys_customer_loss` VALUES (25, 'KH20171021105508617', '中国工商银行', 'test', '2023-05-07', '2023-05-07', 1, '瞧不上咱们', '2023-02-08 23:47:12', '2023-02-08 23:47:12');

-- ----------------------------
-- Table structure for sys_customer_order
-- ----------------------------
DROP TABLE IF EXISTS `sys_customer_order`;
CREATE TABLE `sys_customer_order`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cus_id` int(11) NULL DEFAULT NULL COMMENT '客户id',
  `order_no` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单编号',
  `order_date` datetime NULL DEFAULT NULL COMMENT '下单时间',
  `address` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `state` tinyint(1) NULL DEFAULT NULL COMMENT '订单状态',
  `create_date` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_customer_order
-- ----------------------------
INSERT INTO `sys_customer_order` VALUES (5, 20, '201910021001', '2020-03-03 14:56:10', '上海松江区', 1, '2016-01-29 14:56:15', '2016-11-29 14:56:17');
INSERT INTO `sys_customer_order` VALUES (6, 32, '202001022534', '2020-01-16 14:56:26', '杭州市滨江大道', 1, '2016-02-29 14:56:30', '2016-11-29 14:56:32');
INSERT INTO `sys_customer_order` VALUES (7, 24, '201911021082', '2022-11-24 17:27:31', '上海浦东', 1, '2022-11-24 17:27:31', '2022-11-25 17:27:21');
INSERT INTO `sys_customer_order` VALUES (8, 23, '201909021001', '2019-11-11 10:09:32', '背景海淀', 1, '2019-11-09 10:09:36', '2019-11-09 10:09:39');
INSERT INTO `sys_customer_order` VALUES (9, 23, '202301011111', '2023-02-06 21:44:32', '湖南', 1, '2023-02-06 21:44:41', '2023-02-06 21:44:37');

-- ----------------------------
-- Table structure for sys_customer_reprieve
-- ----------------------------
DROP TABLE IF EXISTS `sys_customer_reprieve`;
CREATE TABLE `sys_customer_reprieve`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `loss_id` int(11) NULL DEFAULT NULL COMMENT '流失表id',
  `measure` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '措施',
  `create_date` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 79 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_customer_reprieve
-- ----------------------------
INSERT INTO `sys_customer_reprieve` VALUES (44, 21, '请客户吃饭', '2023-02-06 17:06:05', '2023-02-07 11:49:37');
INSERT INTO `sys_customer_reprieve` VALUES (45, 21, '客户请客', '2017-05-25 00:00:00', '2017-09-19 11:49:36');
INSERT INTO `sys_customer_reprieve` VALUES (47, 24, '请马云吃顿饭_河马生鲜', '2017-09-19 11:17:04', '2017-09-19 11:49:26');
INSERT INTO `sys_customer_reprieve` VALUES (49, 23, '请老马喝喝茶，聊聊天', '2017-10-21 00:00:00', '2017-10-21 00:00:00');
INSERT INTO `sys_customer_reprieve` VALUES (50, 22, '请客吃饭', '2017-10-21 18:10:35', '2017-10-21 18:10:35');
INSERT INTO `sys_customer_reprieve` VALUES (67, 23, '一起打高尔夫', '2023-02-15 20:31:56', '2023-02-15 20:21:59');
INSERT INTO `sys_customer_reprieve` VALUES (73, 25, '测试1', '2023-05-06 01:15:14', '2023-05-06 01:15:14');
INSERT INTO `sys_customer_reprieve` VALUES (74, 25, '测试2', '2023-05-06 01:15:29', '2023-05-06 01:15:29');
INSERT INTO `sys_customer_reprieve` VALUES (75, 25, '测试3', '2023-05-06 01:15:34', '2023-05-06 01:15:34');
INSERT INTO `sys_customer_reprieve` VALUES (76, 25, '测试4', '2023-05-06 01:15:40', '2023-05-06 01:15:40');
INSERT INTO `sys_customer_reprieve` VALUES (77, 25, '测试5', '2023-05-06 01:15:44', '2023-05-06 01:15:44');
INSERT INTO `sys_customer_reprieve` VALUES (78, 25, '测试6', '2023-05-06 01:15:49', '2023-05-06 01:15:49');

-- ----------------------------
-- Table structure for sys_customer_serve
-- ----------------------------
DROP TABLE IF EXISTS `sys_customer_serve`;
CREATE TABLE `sys_customer_serve`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `serve_type` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '服务类型',
  `overview` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '概要',
  `customer` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户',
  `state` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '服务状态',
  `service_request` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '服务请求',
  `create_people` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '服务创建人',
  `assigner` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '服务分配人',
  `assign_time` datetime NULL DEFAULT NULL COMMENT '分配时间',
  `service_proce` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '服务处理',
  `service_proce_people` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '服务处理人',
  `service_proce_time` datetime NULL DEFAULT NULL COMMENT '服务处理时间',
  `service_proce_result` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '处理结果',
  `myd` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '满意度',
  `update_date` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `create_date` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 101 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_customer_serve
-- ----------------------------
INSERT INTO `sys_customer_serve` VALUES (74, '投诉', 'aa', '腾讯', 'fw_005', 'aaa', '张三', 'admin', '2023-02-18 18:41:19', '测试', 'admin', '2023-05-07 00:48:32', '撒发射点', '☆☆☆☆', '2023-05-07 01:07:42', '2020-01-18 09:31:53');
INSERT INTO `sys_customer_serve` VALUES (75, '投诉', 'aa', '腾讯', 'fw_005', 'aaa', '张三', 'admin', '2023-02-17 18:30:03', '测试', 'admin', '2023-05-07 00:49:07', '哈哈哈哈', '☆☆☆☆', '2023-05-07 01:07:30', '2020-01-18 10:20:10');
INSERT INTO `sys_customer_serve` VALUES (82, '投诉', '服务记录测试', '腾讯', 'fw_005', '这是服务测试', '张三', 'admin', '2020-03-22 17:17:05', '服务处理测试...', '张三', '2020-03-22 17:50:41', 'nice', '☆☆☆☆', '2023-02-21 19:13:46', '2020-02-28 09:57:18');
INSERT INTO `sys_customer_serve` VALUES (83, '咨询', '服务测试...', '腾讯', 'fw_005', '服务测试...', '张三', 'admin', '2020-03-22 17:17:42', 'dvdsfg', '张三', '2023-05-02 14:14:55', '沙发沙发', '☆', '2023-05-07 01:08:04', '2020-03-22 16:06:20');
INSERT INTO `sys_customer_serve` VALUES (84, '咨询', '提供货物配送服务', '百度', 'fw_005', '提供货物配送服务', '张三', 'admin', '2023-02-17 18:23:19', '提供货物配送服务', '张三', '2023-02-18 18:18:39', 'nice', '☆☆☆☆', '2023-02-21 19:13:40', '2023-02-17 17:16:41');
INSERT INTO `sys_customer_serve` VALUES (85, '咨询', '哈哈哈', '百度', 'fw_002', '哈哈哈', '张三', 'admin', '2023-02-17 18:24:00', NULL, NULL, NULL, NULL, NULL, '2023-02-17 18:24:00', '2023-02-17 17:22:33');
INSERT INTO `sys_customer_serve` VALUES (86, '咨询', '服务太差了', '百度', 'fw_003', '服务太差了', '张三', 'admin', '2023-02-18 19:08:37', '客户血口喷人', 'admin', '2023-05-07 01:23:08', NULL, NULL, '2023-05-07 01:23:08', '2023-02-18 19:08:08');
INSERT INTO `sys_customer_serve` VALUES (87, '咨询', '啊啊啊', '百度', 'fw_002', '啊啊啊', '张三', 'admin', '2023-02-18 19:19:17', NULL, NULL, NULL, NULL, NULL, '2023-02-18 19:19:17', '2023-02-18 19:10:09');
INSERT INTO `sys_customer_serve` VALUES (88, '咨询', 'aa', '腾讯', 'fw_005', 'aa', '张三', 'admin', '2023-02-18 19:24:03', 'hhahha', '张三', '2023-02-18 20:11:53', 'nice', '☆☆', '2023-02-21 19:12:43', '2023-02-18 19:22:31');
INSERT INTO `sys_customer_serve` VALUES (89, '咨询', 'aa', '腾讯', 'fw_005', 'aaa', '张三', 'admin', '2023-02-18 19:35:56', 'hahhahah1', '张三', '2023-02-18 20:10:30', 'nice', '☆☆☆☆☆', '2023-02-21 19:13:33', '2023-02-18 19:25:53');
INSERT INTO `sys_customer_serve` VALUES (90, '咨询', 'aa', '腾讯', 'fw_005', 'aa', '张三', 'admin', '2023-02-18 19:34:09', '不错', '张三', '2023-02-18 19:40:58', 'nice', '☆', '2023-02-21 19:12:33', '2023-02-18 19:26:21');
INSERT INTO `sys_customer_serve` VALUES (91, '咨询', 'aaa', '腾讯', 'fw_005', 'aaa', '张三', 'admin', '2023-02-18 19:45:57', '号', '张三', '2023-02-18 19:46:23', 'nice', '☆☆☆☆☆', '2023-02-18 19:46:52', '2023-02-18 19:45:40');
INSERT INTO `sys_customer_serve` VALUES (92, '咨询', 'aaaa', '腾讯', 'fw_002', 'aaaaa', '张三', 'test04', '2023-02-18 19:56:20', NULL, NULL, NULL, NULL, NULL, '2023-02-18 19:56:20', '2023-02-18 19:56:05');
INSERT INTO `sys_customer_serve` VALUES (93, '咨询', 'asd', '百度', 'fw_005', 'aaaa', '张三', 'test03', '2023-02-18 20:18:12', 'aaa', 'test03--', '2023-02-18 20:23:23', 'nice', '☆☆☆', '2023-02-18 20:23:33', '2023-02-18 20:17:59');
INSERT INTO `sys_customer_serve` VALUES (94, '咨询', 'aaa', '百度', 'fw_005', 'aaa', '张三', 'admin', '2023-02-18 20:20:11', 'aaaa', '张三', '2023-02-18 20:20:43', 'nice', '☆☆☆☆☆', '2023-02-18 20:21:26', '2023-02-18 20:18:46');
INSERT INTO `sys_customer_serve` VALUES (95, '咨询', 'asd', '腾讯', 'fw_002', 'sdf', '张三', 'admin', '2023-05-06 21:43:18', NULL, NULL, NULL, NULL, NULL, '2023-05-06 21:43:18', '2023-05-02 12:55:08');
INSERT INTO `sys_customer_serve` VALUES (96, '咨询', 'asd', '风驰科技', 'fw_002', 'dsggh', 'admin', 'admin', '2023-05-06 21:43:12', NULL, NULL, NULL, NULL, NULL, '2023-05-06 21:43:12', '2023-05-06 20:11:47');
INSERT INTO `sys_customer_serve` VALUES (97, '投诉', 'dsfgfh', '腾讯', 'fw_002', 'gffhfhg', 'admin', 'Louise', '2023-05-06 22:20:07', NULL, NULL, NULL, NULL, NULL, '2023-05-06 22:20:07', '2023-05-06 22:19:52');
INSERT INTO `sys_customer_serve` VALUES (98, '投诉', 'sdfd', '风驰科技', 'fw_005', 'ghgfdd', 'admin', 'admin', '2023-05-06 22:20:53', '测试', 'admin', '2023-05-07 00:49:45', '是德国v的风格', '☆', '2023-05-07 01:07:52', '2023-05-06 22:20:41');
INSERT INTO `sys_customer_serve` VALUES (99, '投诉', '测试一下', 'test', 'fw_005', '你的产品有问题', 'admin', 'admin', '2023-05-07 01:20:38', '客户没有认真看说明书', 'admin', '2023-05-07 01:22:51', '教授顾客怎么使用', '☆☆☆☆☆', '2023-05-07 01:24:31', '2023-05-07 01:19:35');
INSERT INTO `sys_customer_serve` VALUES (100, '咨询', '不给admin', '百度', 'fw_002', '不给admin', 'admin', 'Frank', '2023-05-07 01:20:45', NULL, NULL, NULL, NULL, NULL, '2023-05-07 01:20:45', '2023-05-07 01:20:06');

-- ----------------------------
-- Table structure for sys_datadic
-- ----------------------------
DROP TABLE IF EXISTS `sys_datadic`;
CREATE TABLE `sys_datadic`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `data_dic_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典名',
  `data_dic_value` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典值',
  `create_date` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `FK_t_datadic`(`data_dic_value`) USING BTREE,
  INDEX `data_dic_name`(`data_dic_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_datadic
-- ----------------------------
INSERT INTO `sys_datadic` VALUES (1, '客户等级', '普通客户', '2020-02-20 10:04:27', '2020-02-20 10:04:48');
INSERT INTO `sys_datadic` VALUES (2, '客户等级', '重点开发客户', '2020-02-20 10:04:30', '2020-02-20 10:04:51');
INSERT INTO `sys_datadic` VALUES (3, '客户等级', '大客户', '2020-02-20 10:04:33', '2020-02-20 10:04:53');
INSERT INTO `sys_datadic` VALUES (4, '客户等级', '合作伙伴', '2020-02-20 10:04:35', '2020-02-20 10:04:56');
INSERT INTO `sys_datadic` VALUES (5, '客户等级', '战略合作伙伴', '2020-02-20 10:04:37', '2020-02-20 10:04:59');
INSERT INTO `sys_datadic` VALUES (6, '服务类型', '咨询', '2020-02-20 10:04:40', '2020-02-20 10:05:01');
INSERT INTO `sys_datadic` VALUES (7, '服务类型', '投诉', '2020-02-20 10:04:43', '2020-02-20 10:05:04');
INSERT INTO `sys_datadic` VALUES (8, '服务类型', '建议', '2020-02-20 10:04:45', '2016-08-24 15:48:46');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单主键ID',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `icon` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '#' COMMENT '菜单图标',
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父菜单ID',
  `order_num` int(11) NULL DEFAULT 0 COMMENT '显示顺序',
  `path` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组件路径',
  `menu_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `perms` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '权限标识',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 96 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '系统管理', 'system', 0, 10, '/sys', '', 'M', '', '2022-07-04 14:56:29', '2022-07-04 14:56:31', '系统管理目录');
INSERT INTO `sys_menu` VALUES (3, '用户管理', 'user', 1, 1, '/sys/user', 'sys/user/index', 'C', 'system:user:list', '2022-07-04 15:20:51', '2022-07-04 15:20:53', '用户管理菜单');
INSERT INTO `sys_menu` VALUES (4, '角色管理', 'peoples', 1, 2, '/sys/role', 'sys/role/index', 'C', 'system:role:list', '2022-07-04 15:23:35', '2022-07-04 15:23:39', '角色管理菜单');
INSERT INTO `sys_menu` VALUES (5, '菜单管理', 'tree-table', 1, 3, '/sys/menu', 'sys/menu/index', 'C', 'system:menu:list', '2022-07-04 15:23:41', '2022-07-04 15:23:43', '菜单管理菜单');
INSERT INTO `sys_menu` VALUES (8, '用户新增', '#', 3, 2, '', '', 'F', 'system:user:add', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '添加用户按钮');
INSERT INTO `sys_menu` VALUES (9, '用户修改', '#', 3, 3, '', '', 'F', 'system:user:edit', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '修改用户按钮');
INSERT INTO `sys_menu` VALUES (10, '用户删除', '#', 3, 4, '', '', 'F', 'system:user:delete', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '删除用户按钮');
INSERT INTO `sys_menu` VALUES (11, '分配角色', '#', 3, 5, '', '', 'F', 'system:user:role', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '分配角色按钮');
INSERT INTO `sys_menu` VALUES (12, '重置密码', '#', 3, 6, '', '', 'F', 'system:user:resetPwd', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '重置密码按钮');
INSERT INTO `sys_menu` VALUES (13, '角色新增', '#', 4, 2, '', '', 'F', 'system:role:add', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '添加用户按钮');
INSERT INTO `sys_menu` VALUES (14, '角色修改', '#', 4, 3, '', '', 'F', 'system:role:edit', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '修改用户按钮');
INSERT INTO `sys_menu` VALUES (15, '角色删除', '#', 4, 4, '', NULL, 'F', 'system:role:delete', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '删除用户按钮');
INSERT INTO `sys_menu` VALUES (16, '分配权限', '#', 4, 5, '', '', 'F', 'system:role:menu', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '分配权限按钮');
INSERT INTO `sys_menu` VALUES (17, '菜单新增', '#', 5, 2, '', NULL, 'F', 'system:menu:add', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '添加菜单按钮');
INSERT INTO `sys_menu` VALUES (18, '菜单修改', '#', 5, 3, '', NULL, 'F', 'system:menu:edit', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '修改菜单按钮');
INSERT INTO `sys_menu` VALUES (19, '菜单删除', '#', 5, 4, '', NULL, 'F', 'system:menu:delete', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '删除菜单按钮');
INSERT INTO `sys_menu` VALUES (20, '用户查询', '#', 3, 1, '', NULL, 'F', 'system:user:query', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '用户查询按钮');
INSERT INTO `sys_menu` VALUES (21, '角色查询', '#', 4, 1, '', NULL, 'F', 'system:role:query', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '角色查询按钮');
INSERT INTO `sys_menu` VALUES (22, '菜单查询', '#', 5, 1, '', NULL, 'F', 'system:menu:query', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '菜单查询按钮');
INSERT INTO `sys_menu` VALUES (37, '营销管理', 'money', 0, 1, '/sale', NULL, 'M', '', '2023-05-02 00:46:39', '2023-05-02 00:46:42', '营销管理目录');
INSERT INTO `sys_menu` VALUES (38, '营销机会管理', 'phone', 37, 1, '/sale/chance', 'sale/chance/index', 'C', 'sale:chance:list', '2023-05-02 00:49:30', '2023-05-02 00:49:33', '营销机会管理目录');
INSERT INTO `sys_menu` VALUES (39, '客户开发计划', 'edit', 37, 2, '/sale/cusDevPlan', 'sale/cusDevPlan/index', 'C', 'sale:cusDevPlan:list', '2023-05-02 00:52:45', '2023-05-02 00:52:48', '客户开发计划管理');
INSERT INTO `sys_menu` VALUES (40, '营销机会新增', '#', 38, 2, '', '', 'F', 'sale:chance:add', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '添加营销机会按钮');
INSERT INTO `sys_menu` VALUES (41, '营销机会修改', '#', 38, 3, '', '', 'F', 'sale:chance:edit', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '修改营销机会按钮');
INSERT INTO `sys_menu` VALUES (42, '营销机会删除', '#', 38, 4, '', '', 'F', 'sale:chance:delete', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '删除营销机会按钮');
INSERT INTO `sys_menu` VALUES (43, '营销机会查询', '#', 38, 1, '', NULL, 'F', 'sale:chance:query', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '营销机会查询按钮');
INSERT INTO `sys_menu` VALUES (44, '客户开发计划新增', '#', 39, 2, '', '', 'F', 'sale:cusDevPlan:add', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '添加客户开发计划机会按钮');
INSERT INTO `sys_menu` VALUES (45, '客户开发计划修改', '#', 39, 3, '', '', 'F', 'sale:cusDevPlan:edit', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '修改客户开发计划按钮');
INSERT INTO `sys_menu` VALUES (46, '客户开发计划删除', '#', 39, 4, '', '', 'F', 'sale:cusDevPlan:delete', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '删除客户开发计划按钮');
INSERT INTO `sys_menu` VALUES (47, '客户开发计划查询', '#', 39, 1, '', NULL, 'F', 'sale:cusDevPlan:query', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '客户开发计划查询按钮');
INSERT INTO `sys_menu` VALUES (48, '统计报表', 'monitor', 0, 4, '/report', '', 'M', '', '2022-07-04 14:56:29', '2022-07-04 14:56:31', '统计报表目录');
INSERT INTO `sys_menu` VALUES (53, '客户构成分析', 'dashboard', 48, 1, '/report/make', 'report/make/index', 'C', 'report:make:list', '2022-07-04 15:23:35', '2022-07-04 15:23:39', '客户构成分析菜单');
INSERT INTO `sys_menu` VALUES (54, '客户服务分析', 'dashboard', 48, 2, '/report/serve', 'report/serve/index', 'C', 'report:serve:list', '2022-07-04 15:23:41', '2022-07-04 15:23:43', '客户服务分析菜单');
INSERT INTO `sys_menu` VALUES (55, '客户贡献分析', 'table', 48, 3, '/report/contrib', 'report/contrib/index', 'C', 'report:contrib:list', '2022-07-04 15:20:51', '2022-07-04 15:20:53', '客户贡献分析菜单');
INSERT INTO `sys_menu` VALUES (56, '客户流失分析', 'table', 48, 4, '/report/loss', 'report/loss/index', 'C', 'report:loss:list', '2022-07-04 15:24:40', '2022-07-04 15:24:44', '客户流失分析菜单');
INSERT INTO `sys_menu` VALUES (59, '客户管理', 'people', 0, 2, '/cus', '', 'M', '', '2022-07-04 14:56:29', '2022-07-04 14:56:31', '客户管理目录');
INSERT INTO `sys_menu` VALUES (60, '客户信息管理', 'peoples', 59, 1, '/cus/info', 'cus/info/index', 'C', 'cus:info:list', '2022-07-04 15:23:35', '2022-07-04 15:23:39', '客户信息管理菜单');
INSERT INTO `sys_menu` VALUES (61, '客户流失管理', 'guide', 59, 2, '/cus/loss', 'cus/loss/index', 'C', 'cus:loss:list', '2022-07-04 15:23:41', '2022-07-04 15:23:43', '客户流失管理菜单');
INSERT INTO `sys_menu` VALUES (62, '客户信息查询', '#', 60, 1, '', '', 'F', 'cus:info:query', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '客户信息查询按钮');
INSERT INTO `sys_menu` VALUES (63, '客户信息新增', '#', 60, 2, '', '', 'F', 'cus:info:add', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '添加客户信息按钮');
INSERT INTO `sys_menu` VALUES (64, '客户信息修改', '#', 60, 3, '', '', 'F', 'cus:info:edit', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '修改客户信息按钮');
INSERT INTO `sys_menu` VALUES (65, '客户信息删除', '#', 60, 4, '', '', 'F', 'cus:info:delete', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '删除客户信息按钮');
INSERT INTO `sys_menu` VALUES (66, '客户订单查看', '#', 60, 5, '', '', 'F', 'cus:order:list', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '客户订单查看按钮');
INSERT INTO `sys_menu` VALUES (67, '客户订单详情查看', '#', 60, 6, '', '', 'F', 'cus:orderItem:list', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '客户订单详情查看按钮');
INSERT INTO `sys_menu` VALUES (68, '客户流失信息查询', '#', 61, 1, '', '', 'F', 'cus:loss:query', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '客户流失信息查询按钮');
INSERT INTO `sys_menu` VALUES (69, '客户流失暂缓信息详情', '#', 61, 2, '', '', 'F', 'cus:lossRep:list', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '客户流失暂缓信息按钮');
INSERT INTO `sys_menu` VALUES (71, '暂缓流失信息新增', '#', 61, 4, '', '', 'F', 'cus:lossRep:add', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '暂缓流失信息新增按钮');
INSERT INTO `sys_menu` VALUES (72, '暂缓流失信息修改', '#', 61, 5, '', '', 'F', 'cus:lossRep:edit', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '修改暂缓流失信息按钮');
INSERT INTO `sys_menu` VALUES (73, '暂缓流失信息删除', '#', 61, 6, '', '', 'F', 'cus:lossRep:delete', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '删除暂缓流失信息按钮');
INSERT INTO `sys_menu` VALUES (74, '确认流失', '#', 61, 7, '', '', 'F', 'cus:lossRep:sure', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '确认客户流失按钮');
INSERT INTO `sys_menu` VALUES (75, '服务管理', 'example', 0, 3, '/serve', '', 'M', '', '2022-07-04 14:56:29', '2022-07-04 14:56:31', '服务管理目录');
INSERT INTO `sys_menu` VALUES (76, '服务创建', 'create', 75, 1, '/serve/create', 'serve/create/index', 'C', 'serve:create:list', '2022-07-04 15:23:35', '2022-07-04 15:23:39', '服务创建菜单');
INSERT INTO `sys_menu` VALUES (77, '服务分配', 'assign', 75, 2, '/serve/assign', 'serve/assign/index', 'C', 'serve:assign:list', '2022-07-04 15:23:41', '2022-07-04 15:23:43', '服务分配菜单');
INSERT INTO `sys_menu` VALUES (78, '服务处理', 'proce', 75, 3, '/serve/proce', 'serve/proce/index', 'C', 'serve:proce:list', '2022-07-04 15:20:51', '2022-07-04 15:20:53', '服务处理菜单');
INSERT INTO `sys_menu` VALUES (79, '服务反馈', 'feedback', 75, 4, '/serve/feed', 'serve/feed/index', 'C', 'serve:feed:list', '2022-07-04 15:24:40', '2022-07-04 15:24:44', '服务反馈菜单');
INSERT INTO `sys_menu` VALUES (80, '服务归档', 'archive', 75, 5, '/serve/archive', 'serve/archive/index', 'C', 'serve:archive:list', '2022-07-04 15:24:40', '2022-07-04 15:24:44', '服务归档菜单');
INSERT INTO `sys_menu` VALUES (81, '服务信息查询', '#', 76, 1, '', '', 'F', 'serve:query', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '服务信息查询按钮');
INSERT INTO `sys_menu` VALUES (82, '服务创建信息新增', '#', 76, 2, '', '', 'F', 'serve:create:add', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '暂服务创建信息新增按钮');
INSERT INTO `sys_menu` VALUES (84, '服务分配信息分配', '#', 77, 2, '', '', 'F', 'serve:assign:do', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '服务分配信息分配按钮');
INSERT INTO `sys_menu` VALUES (86, '服务处理信息处理', '#', 78, 2, '', '', 'F', 'serve:proce:do', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '服务处理信息处理按钮');
INSERT INTO `sys_menu` VALUES (88, '服务反馈信息反馈', '#', 79, 2, '', '', 'F', 'serve:feed:do', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '服务反馈信息反馈按钮');
INSERT INTO `sys_menu` VALUES (90, '字典管理', 'dict', 1, 4, '/sys/dict', 'sys/dict/index', 'C', 'sys:dict:list', '2023-05-02 21:34:01', '2023-05-02 21:34:03', '字典管理菜单');
INSERT INTO `sys_menu` VALUES (91, '字典信息查询', '#', 90, 1, '', '', 'F', 'system:dict:query', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '字典信息查询按钮');
INSERT INTO `sys_menu` VALUES (92, '字典信息新增', '#', 90, 2, '', '', 'F', 'system:dict:add', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '字典信息新增按钮');
INSERT INTO `sys_menu` VALUES (93, '字典信息修改', '#', 90, 3, '', '', 'F', 'system:dict:edit', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '修改字典信息按钮');
INSERT INTO `sys_menu` VALUES (94, '字典信息删除', '#', 90, 4, '', '', 'F', 'system:dict:delete', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '删除字典信息按钮');
INSERT INTO `sys_menu` VALUES (95, '流失暂缓客户确认流失', '#', 61, 1, '', '', 'F', 'cus:loss:sureLoss', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '客户流失信息确认流失按钮');

-- ----------------------------
-- Table structure for sys_order_details
-- ----------------------------
DROP TABLE IF EXISTS `sys_order_details`;
CREATE TABLE `sys_order_details`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_id` bigint(20) NULL DEFAULT NULL COMMENT '订单id',
  `goods_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `goods_num` int(11) NULL DEFAULT NULL COMMENT '商品数量',
  `unit` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品单位',
  `price` float NULL DEFAULT NULL COMMENT '单价',
  `sum` float NULL DEFAULT NULL COMMENT '总金额',
  `create_date` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_order_details
-- ----------------------------
INSERT INTO `sys_order_details` VALUES (1, 5, '联想笔记本', 2, '台', 4900, 9800, '2016-11-29 14:59:32', '2016-11-29 14:59:34');
INSERT INTO `sys_order_details` VALUES (2, 5, '惠普音响', 4, '台', 200, 5000, '2017-03-01 11:32:34', '2017-03-01 11:32:36');
INSERT INTO `sys_order_details` VALUES (3, 8, '罗技键盘', 10, '个', 90, 2000, '2017-03-01 11:32:39', '2017-03-01 11:32:41');
INSERT INTO `sys_order_details` VALUES (4, 6, '艾利鼠标', 20, '个', 20, 1000, '2017-03-01 11:32:46', '2017-03-01 11:32:48');
INSERT INTO `sys_order_details` VALUES (5, 7, '东芝U盘', 5, '个', 105, 525, '2017-03-01 11:32:51', '2017-03-01 11:32:53');
INSERT INTO `sys_order_details` VALUES (6, 7, '充电器', 1, '个', 30, 30, '2017-03-01 11:32:55', '2017-03-01 11:32:57');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色主键ID',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色权限字符串',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', 'admin', '2022-07-04 14:40:44', '2022-07-04 14:40:47', '拥有系统\r\n最高权限');
INSERT INTO `sys_role` VALUES (22, '销售主管', 'super_sale', '2023-04-29 17:54:15', '2023-05-02 14:55:00', '销售主管');
INSERT INTO `sys_role` VALUES (23, '销售', 'sale', '2023-05-02 14:55:22', '2023-05-02 14:55:22', '销售员工');
INSERT INTO `sys_role` VALUES (24, '市场分析', 'market_analysis', '2023-05-02 14:58:07', '2023-05-02 15:00:43', '市场数据分析');
INSERT INTO `sys_role` VALUES (25, '人事主管', 'hr_super', '2023-05-02 15:00:27', '2023-05-02 15:00:27', '人事主管');
INSERT INTO `sys_role` VALUES (26, '人事', 'hr', '2023-05-02 19:25:22', '2023-05-02 19:25:22', '人事员工');
INSERT INTO `sys_role` VALUES (27, '售后', 'aftermarket', '2023-05-02 19:27:02', '2023-05-02 19:27:02', '售后');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色菜单主键ID',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NULL DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `role_id`(`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 819 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (795, 1, 1);
INSERT INTO `sys_role_menu` VALUES (796, 1, 3);
INSERT INTO `sys_role_menu` VALUES (803, 1, 4);
INSERT INTO `sys_role_menu` VALUES (809, 1, 5);
INSERT INTO `sys_role_menu` VALUES (798, 1, 8);
INSERT INTO `sys_role_menu` VALUES (799, 1, 9);
INSERT INTO `sys_role_menu` VALUES (800, 1, 10);
INSERT INTO `sys_role_menu` VALUES (801, 1, 11);
INSERT INTO `sys_role_menu` VALUES (802, 1, 12);
INSERT INTO `sys_role_menu` VALUES (805, 1, 13);
INSERT INTO `sys_role_menu` VALUES (806, 1, 14);
INSERT INTO `sys_role_menu` VALUES (807, 1, 15);
INSERT INTO `sys_role_menu` VALUES (808, 1, 16);
INSERT INTO `sys_role_menu` VALUES (811, 1, 17);
INSERT INTO `sys_role_menu` VALUES (812, 1, 18);
INSERT INTO `sys_role_menu` VALUES (813, 1, 19);
INSERT INTO `sys_role_menu` VALUES (797, 1, 20);
INSERT INTO `sys_role_menu` VALUES (804, 1, 21);
INSERT INTO `sys_role_menu` VALUES (810, 1, 22);
INSERT INTO `sys_role_menu` VALUES (746, 1, 37);
INSERT INTO `sys_role_menu` VALUES (747, 1, 38);
INSERT INTO `sys_role_menu` VALUES (752, 1, 39);
INSERT INTO `sys_role_menu` VALUES (749, 1, 40);
INSERT INTO `sys_role_menu` VALUES (750, 1, 41);
INSERT INTO `sys_role_menu` VALUES (751, 1, 42);
INSERT INTO `sys_role_menu` VALUES (748, 1, 43);
INSERT INTO `sys_role_menu` VALUES (754, 1, 44);
INSERT INTO `sys_role_menu` VALUES (755, 1, 45);
INSERT INTO `sys_role_menu` VALUES (756, 1, 46);
INSERT INTO `sys_role_menu` VALUES (753, 1, 47);
INSERT INTO `sys_role_menu` VALUES (788, 1, 48);
INSERT INTO `sys_role_menu` VALUES (789, 1, 53);
INSERT INTO `sys_role_menu` VALUES (790, 1, 54);
INSERT INTO `sys_role_menu` VALUES (791, 1, 55);
INSERT INTO `sys_role_menu` VALUES (793, 1, 56);
INSERT INTO `sys_role_menu` VALUES (792, 1, 57);
INSERT INTO `sys_role_menu` VALUES (794, 1, 58);
INSERT INTO `sys_role_menu` VALUES (757, 1, 59);
INSERT INTO `sys_role_menu` VALUES (758, 1, 60);
INSERT INTO `sys_role_menu` VALUES (765, 1, 61);
INSERT INTO `sys_role_menu` VALUES (759, 1, 62);
INSERT INTO `sys_role_menu` VALUES (760, 1, 63);
INSERT INTO `sys_role_menu` VALUES (761, 1, 64);
INSERT INTO `sys_role_menu` VALUES (762, 1, 65);
INSERT INTO `sys_role_menu` VALUES (763, 1, 66);
INSERT INTO `sys_role_menu` VALUES (764, 1, 67);
INSERT INTO `sys_role_menu` VALUES (766, 1, 68);
INSERT INTO `sys_role_menu` VALUES (768, 1, 69);
INSERT INTO `sys_role_menu` VALUES (769, 1, 71);
INSERT INTO `sys_role_menu` VALUES (770, 1, 72);
INSERT INTO `sys_role_menu` VALUES (771, 1, 73);
INSERT INTO `sys_role_menu` VALUES (772, 1, 74);
INSERT INTO `sys_role_menu` VALUES (773, 1, 75);
INSERT INTO `sys_role_menu` VALUES (774, 1, 76);
INSERT INTO `sys_role_menu` VALUES (777, 1, 77);
INSERT INTO `sys_role_menu` VALUES (780, 1, 78);
INSERT INTO `sys_role_menu` VALUES (783, 1, 79);
INSERT INTO `sys_role_menu` VALUES (786, 1, 80);
INSERT INTO `sys_role_menu` VALUES (775, 1, 81);
INSERT INTO `sys_role_menu` VALUES (776, 1, 82);
INSERT INTO `sys_role_menu` VALUES (778, 1, 83);
INSERT INTO `sys_role_menu` VALUES (779, 1, 84);
INSERT INTO `sys_role_menu` VALUES (781, 1, 85);
INSERT INTO `sys_role_menu` VALUES (782, 1, 86);
INSERT INTO `sys_role_menu` VALUES (784, 1, 87);
INSERT INTO `sys_role_menu` VALUES (785, 1, 88);
INSERT INTO `sys_role_menu` VALUES (787, 1, 89);
INSERT INTO `sys_role_menu` VALUES (814, 1, 90);
INSERT INTO `sys_role_menu` VALUES (815, 1, 91);
INSERT INTO `sys_role_menu` VALUES (816, 1, 92);
INSERT INTO `sys_role_menu` VALUES (817, 1, 93);
INSERT INTO `sys_role_menu` VALUES (818, 1, 94);
INSERT INTO `sys_role_menu` VALUES (767, 1, 95);
INSERT INTO `sys_role_menu` VALUES (636, 22, 37);
INSERT INTO `sys_role_menu` VALUES (637, 22, 38);
INSERT INTO `sys_role_menu` VALUES (642, 22, 39);
INSERT INTO `sys_role_menu` VALUES (639, 22, 40);
INSERT INTO `sys_role_menu` VALUES (640, 22, 41);
INSERT INTO `sys_role_menu` VALUES (641, 22, 42);
INSERT INTO `sys_role_menu` VALUES (638, 22, 43);
INSERT INTO `sys_role_menu` VALUES (644, 22, 44);
INSERT INTO `sys_role_menu` VALUES (645, 22, 45);
INSERT INTO `sys_role_menu` VALUES (646, 22, 46);
INSERT INTO `sys_role_menu` VALUES (643, 22, 47);
INSERT INTO `sys_role_menu` VALUES (647, 22, 59);
INSERT INTO `sys_role_menu` VALUES (648, 22, 60);
INSERT INTO `sys_role_menu` VALUES (655, 22, 61);
INSERT INTO `sys_role_menu` VALUES (649, 22, 62);
INSERT INTO `sys_role_menu` VALUES (650, 22, 63);
INSERT INTO `sys_role_menu` VALUES (651, 22, 64);
INSERT INTO `sys_role_menu` VALUES (652, 22, 65);
INSERT INTO `sys_role_menu` VALUES (653, 22, 66);
INSERT INTO `sys_role_menu` VALUES (654, 22, 67);
INSERT INTO `sys_role_menu` VALUES (656, 22, 68);
INSERT INTO `sys_role_menu` VALUES (657, 22, 69);
INSERT INTO `sys_role_menu` VALUES (658, 22, 70);
INSERT INTO `sys_role_menu` VALUES (659, 22, 71);
INSERT INTO `sys_role_menu` VALUES (660, 22, 72);
INSERT INTO `sys_role_menu` VALUES (661, 22, 73);
INSERT INTO `sys_role_menu` VALUES (662, 22, 74);
INSERT INTO `sys_role_menu` VALUES (722, 23, 37);
INSERT INTO `sys_role_menu` VALUES (725, 23, 39);
INSERT INTO `sys_role_menu` VALUES (724, 23, 41);
INSERT INTO `sys_role_menu` VALUES (723, 23, 43);
INSERT INTO `sys_role_menu` VALUES (727, 23, 44);
INSERT INTO `sys_role_menu` VALUES (728, 23, 45);
INSERT INTO `sys_role_menu` VALUES (729, 23, 46);
INSERT INTO `sys_role_menu` VALUES (726, 23, 47);
INSERT INTO `sys_role_menu` VALUES (730, 23, 59);
INSERT INTO `sys_role_menu` VALUES (731, 23, 60);
INSERT INTO `sys_role_menu` VALUES (738, 23, 61);
INSERT INTO `sys_role_menu` VALUES (732, 23, 62);
INSERT INTO `sys_role_menu` VALUES (733, 23, 63);
INSERT INTO `sys_role_menu` VALUES (734, 23, 64);
INSERT INTO `sys_role_menu` VALUES (735, 23, 65);
INSERT INTO `sys_role_menu` VALUES (736, 23, 66);
INSERT INTO `sys_role_menu` VALUES (737, 23, 67);
INSERT INTO `sys_role_menu` VALUES (739, 23, 68);
INSERT INTO `sys_role_menu` VALUES (740, 23, 69);
INSERT INTO `sys_role_menu` VALUES (741, 23, 70);
INSERT INTO `sys_role_menu` VALUES (742, 23, 71);
INSERT INTO `sys_role_menu` VALUES (743, 23, 72);
INSERT INTO `sys_role_menu` VALUES (744, 23, 73);
INSERT INTO `sys_role_menu` VALUES (745, 23, 74);
INSERT INTO `sys_role_menu` VALUES (478, 24, 48);
INSERT INTO `sys_role_menu` VALUES (479, 24, 53);
INSERT INTO `sys_role_menu` VALUES (480, 24, 54);
INSERT INTO `sys_role_menu` VALUES (481, 24, 55);
INSERT INTO `sys_role_menu` VALUES (483, 24, 56);
INSERT INTO `sys_role_menu` VALUES (482, 24, 57);
INSERT INTO `sys_role_menu` VALUES (484, 24, 58);
INSERT INTO `sys_role_menu` VALUES (492, 25, 1);
INSERT INTO `sys_role_menu` VALUES (493, 25, 3);
INSERT INTO `sys_role_menu` VALUES (495, 25, 8);
INSERT INTO `sys_role_menu` VALUES (496, 25, 9);
INSERT INTO `sys_role_menu` VALUES (497, 25, 10);
INSERT INTO `sys_role_menu` VALUES (498, 25, 11);
INSERT INTO `sys_role_menu` VALUES (499, 25, 12);
INSERT INTO `sys_role_menu` VALUES (494, 25, 20);
INSERT INTO `sys_role_menu` VALUES (500, 26, 3);
INSERT INTO `sys_role_menu` VALUES (502, 26, 8);
INSERT INTO `sys_role_menu` VALUES (503, 26, 9);
INSERT INTO `sys_role_menu` VALUES (504, 26, 10);
INSERT INTO `sys_role_menu` VALUES (505, 26, 11);
INSERT INTO `sys_role_menu` VALUES (501, 26, 20);
INSERT INTO `sys_role_menu` VALUES (506, 27, 75);
INSERT INTO `sys_role_menu` VALUES (507, 27, 76);
INSERT INTO `sys_role_menu` VALUES (510, 27, 77);
INSERT INTO `sys_role_menu` VALUES (513, 27, 78);
INSERT INTO `sys_role_menu` VALUES (516, 27, 79);
INSERT INTO `sys_role_menu` VALUES (519, 27, 80);
INSERT INTO `sys_role_menu` VALUES (508, 27, 81);
INSERT INTO `sys_role_menu` VALUES (509, 27, 82);
INSERT INTO `sys_role_menu` VALUES (511, 27, 83);
INSERT INTO `sys_role_menu` VALUES (512, 27, 84);
INSERT INTO `sys_role_menu` VALUES (514, 27, 85);
INSERT INTO `sys_role_menu` VALUES (515, 27, 86);
INSERT INTO `sys_role_menu` VALUES (517, 27, 87);
INSERT INTO `sys_role_menu` VALUES (518, 27, 88);
INSERT INTO `sys_role_menu` VALUES (520, 27, 89);

-- ----------------------------
-- Table structure for sys_sale_chance
-- ----------------------------
DROP TABLE IF EXISTS `sys_sale_chance`;
CREATE TABLE `sys_sale_chance`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `chance_source` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机会来源',
  `customer_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户名称',
  `cgjl` int(2) NULL DEFAULT NULL COMMENT '成功几率',
  `overview` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '概要',
  `link_man` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人',
  `link_phone` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `description` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_man` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `assign_man` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分配人',
  `assign_time` datetime NULL DEFAULT NULL COMMENT '分配时间',
  `state` tinyint(1) NULL DEFAULT NULL COMMENT '分配状态',
  `dev_result` int(11) NULL DEFAULT NULL COMMENT '开发结果',
  `create_date` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_sale_chance
-- ----------------------------
INSERT INTO `sys_sale_chance` VALUES (1, '官网', '阿里巴巴', 80, '测试数据', '李四', '18200000000', '测试数据', 'admin', 'admin', '2022-11-19 22:17:53', 1, 1, '2022-11-16 20:20:14', '2022-11-19 22:17:53');
INSERT INTO `sys_sale_chance` VALUES (3, '官网', '百度', 90, '添加机会信息测试数据', '李彦宏', '18200001234', '添加机会信息测试数据', 'admin', 'Kyle', '2023-05-03 15:41:28', 1, 1, '2022-11-17 19:51:11', '2023-05-03 15:41:28');
INSERT INTO `sys_sale_chance` VALUES (4, '官网', '百度', 95, '添加机会信息测试数据2', '李彦宏', '18200001234', '添加机会信息测试数据2', 'admin', 'Marjorie', '2023-05-03 15:41:20', 1, 2, '2022-11-17 19:54:42', '2023-05-03 15:41:20');
INSERT INTO `sys_sale_chance` VALUES (6, '官网', '百度', 97, '添加机会信息测试数据3', '李彦宏', '18200001234', '添加机会信息测试数据3', 'admin', 'Nicole', '2023-05-03 15:41:13', 1, 3, '2022-11-17 19:55:52', '2023-05-03 15:41:13');
INSERT INTO `sys_sale_chance` VALUES (7, '官网', '网易', 100, '测试数据', '丁磊', '15200001524', '测试数据', 'admin', 'admin', '2022-12-03 20:30:24', 1, 1, '2022-11-17 20:16:32', '2022-12-03 20:30:24');
INSERT INTO `sys_sale_chance` VALUES (8, '官网', '百度', 100, '测试数据', '丁磊', '15200001524', '', 'admin', 'admin', '2023-02-21 15:59:37', 1, 2, '2023-02-15 15:51:52', '2023-02-21 15:59:37');
INSERT INTO `sys_sale_chance` VALUES (9, '主动联系', '长沙学院', 77, '无', '张老师', '18220001234', '测试啦啦啦', 'admin', 'Nicole', '2023-05-03 15:44:37', 1, 1, '2023-05-03 15:44:12', '2023-05-03 15:44:37');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `phone_number` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '手机号码',
  `status` tinyint(1) NULL DEFAULT 0 COMMENT '帐号状态（0正常 1停用）',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '$2a$10$4pEK2pdnxJuRNcg87TP9c.hp8ycLFpSGvLCuW4uBWIho9ish0Ez6a', '18212345678', 0, '2022-06-09 08:47:52', '2023-04-28 01:15:04', '备注');
INSERT INTO `sys_user` VALUES (2, 'Frank', '$2a$10$Kib4zuVhTzg3I1CoqJfd0unuY9G9ysI7cfbhyT3fi7k7Z/4pr3bGW', '18779363119', 0, '2023-04-19 19:58:50', '2023-04-25 22:59:49', '就是简单备注一下');
INSERT INTO `sys_user` VALUES (3, 'Cindy', '$2a$10$Kib4zuVhTzg3I1CoqJfd0unuY9G9ysI7cfbhyT3fi7k7Z/4pr3bGW', '14741270070', 0, '2023-03-24 04:45:40', '2023-02-21 15:34:01', '就是简单备注一下拉');
INSERT INTO `sys_user` VALUES (4, 'Terry', '$2a$10$Kib4zuVhTzg3I1CoqJfd0unuY9G9ysI7cfbhyT3fi7k7Z/4pr3bGW', '17008866173', 0, '2023-03-19 06:41:19', '2023-04-23 02:35:17', '就是简单备注一下拉');
INSERT INTO `sys_user` VALUES (5, 'Ryan', '$2a$10$Kib4zuVhTzg3I1CoqJfd0unuY9G9ysI7cfbhyT3fi7k7Z/4pr3bGW', '14711909040', 0, '2023-03-10 03:06:32', '2023-03-18 21:22:10', '就是简单备注一下拉');
INSERT INTO `sys_user` VALUES (6, 'Tammy', '$2a$10$Kib4zuVhTzg3I1CoqJfd0unuY9G9ysI7cfbhyT3fi7k7Z/4pr3bGW', '15516835725', 0, '2023-03-14 12:42:03', '2023-02-26 19:53:07', '就是简单备注一下拉');
INSERT INTO `sys_user` VALUES (7, 'Larry', '$2a$10$Kib4zuVhTzg3I1CoqJfd0unuY9G9ysI7cfbhyT3fi7k7Z/4pr3bGW', '18672225004', 0, '2023-03-26 09:51:13', '2023-03-19 05:06:11', '就是简单备注一下拉');
INSERT INTO `sys_user` VALUES (8, 'Janet', '$2a$10$Kib4zuVhTzg3I1CoqJfd0unuY9G9ysI7cfbhyT3fi7k7Z/4pr3bGW', '15609809227', 0, '2023-04-06 10:15:41', '2023-03-18 12:57:11', '就是简单备注一下拉');
INSERT INTO `sys_user` VALUES (9, 'Nicole', '$2a$10$Kib4zuVhTzg3I1CoqJfd0unuY9G9ysI7cfbhyT3fi7k7Z/4pr3bGW', '13537807055', 0, '2023-03-31 13:08:53', '2023-03-08 06:13:20', '就是简单备注一下拉');
INSERT INTO `sys_user` VALUES (10, 'Nathan', '$2a$10$Kib4zuVhTzg3I1CoqJfd0unuY9G9ysI7cfbhyT3fi7k7Z/4pr3bGW', '13232076544', 0, '2023-04-11 02:00:51', '2023-04-26 19:51:00', '就是简单备注一下拉');
INSERT INTO `sys_user` VALUES (11, 'Kyle', '$2a$10$Kib4zuVhTzg3I1CoqJfd0unuY9G9ysI7cfbhyT3fi7k7Z/4pr3bGW', '13720955871', 0, '2023-03-18 18:15:22', '2023-03-04 19:50:21', '就是简单备注一下拉');
INSERT INTO `sys_user` VALUES (12, 'Louise', '$2a$10$Kib4zuVhTzg3I1CoqJfd0unuY9G9ysI7cfbhyT3fi7k7Z/4pr3bGW', '17736800670', 0, '2023-02-22 01:28:11', '2023-03-15 22:28:10', '就是简单备注一下拉');
INSERT INTO `sys_user` VALUES (13, 'Keith', '$2a$10$Kib4zuVhTzg3I1CoqJfd0unuY9G9ysI7cfbhyT3fi7k7Z/4pr3bGW', '13851554639', 0, '2023-04-06 11:14:43', '2023-02-04 17:09:49', '就是简单备注一下拉');
INSERT INTO `sys_user` VALUES (14, 'Jessica', '$2a$10$Kib4zuVhTzg3I1CoqJfd0unuY9G9ysI7cfbhyT3fi7k7Z/4pr3bGW', '17705180212', 0, '2023-04-11 23:36:54', '2023-04-12 07:17:46', '就是简单备注一下拉');
INSERT INTO `sys_user` VALUES (15, 'Kelly', '$2a$10$Kib4zuVhTzg3I1CoqJfd0unuY9G9ysI7cfbhyT3fi7k7Z/4pr3bGW', '18283419329', 0, '2023-04-05 15:29:12', '2023-02-04 21:28:59', '就是简单备注一下拉');
INSERT INTO `sys_user` VALUES (16, 'Marjorie', '$2a$10$Kib4zuVhTzg3I1CoqJfd0unuY9G9ysI7cfbhyT3fi7k7Z/4pr3bGW', '18265277095', 0, '2023-03-25 22:08:23', '2023-03-16 00:38:52', '就是简单备注一下拉');
INSERT INTO `sys_user` VALUES (17, 'Jesse', '$2a$10$Kib4zuVhTzg3I1CoqJfd0unuY9G9ysI7cfbhyT3fi7k7Z/4pr3bGW', '17848362483', 0, '2023-02-23 00:54:02', '2023-04-14 17:14:32', '就是简单备注一下拉');
INSERT INTO `sys_user` VALUES (18, 'Laura', '$2a$10$Kib4zuVhTzg3I1CoqJfd0unuY9G9ysI7cfbhyT3fi7k7Z/4pr3bGW', '17791937266', 0, '2023-03-19 09:42:24', '2023-04-01 17:07:48', '就是简单备注一下拉');
INSERT INTO `sys_user` VALUES (19, 'Billy', '$2a$10$Kib4zuVhTzg3I1CoqJfd0unuY9G9ysI7cfbhyT3fi7k7Z/4pr3bGW', '18980762077', 0, '2023-02-03 08:52:53', '2023-03-14 01:17:18', '就是简单备注一下拉');
INSERT INTO `sys_user` VALUES (20, 'Manuel', '$2a$10$Kib4zuVhTzg3I1CoqJfd0unuY9G9ysI7cfbhyT3fi7k7Z/4pr3bGW', '15735916824', 0, '2023-03-10 07:55:23', '2023-03-17 22:59:39', '就是简单备注一下拉');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户角色主键ID',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_id`(`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 189 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (184, 1, 1);
INSERT INTO `sys_user_role` VALUES (182, 1, 22);
INSERT INTO `sys_user_role` VALUES (183, 1, 23);
INSERT INTO `sys_user_role` VALUES (185, 1, 24);
INSERT INTO `sys_user_role` VALUES (188, 1, 25);
INSERT INTO `sys_user_role` VALUES (186, 1, 26);
INSERT INTO `sys_user_role` VALUES (187, 1, 27);
INSERT INTO `sys_user_role` VALUES (170, 2, 27);
INSERT INTO `sys_user_role` VALUES (171, 3, 25);
INSERT INTO `sys_user_role` VALUES (172, 4, 26);
INSERT INTO `sys_user_role` VALUES (148, 5, 27);
INSERT INTO `sys_user_role` VALUES (173, 6, 23);
INSERT INTO `sys_user_role` VALUES (174, 7, 24);
INSERT INTO `sys_user_role` VALUES (175, 8, 26);
INSERT INTO `sys_user_role` VALUES (153, 9, 22);
INSERT INTO `sys_user_role` VALUES (176, 10, 23);
INSERT INTO `sys_user_role` VALUES (147, 11, 23);
INSERT INTO `sys_user_role` VALUES (164, 12, 27);
INSERT INTO `sys_user_role` VALUES (141, 13, 23);
INSERT INTO `sys_user_role` VALUES (177, 14, 27);
INSERT INTO `sys_user_role` VALUES (167, 15, 24);
INSERT INTO `sys_user_role` VALUES (178, 16, 23);
INSERT INTO `sys_user_role` VALUES (180, 17, 23);
INSERT INTO `sys_user_role` VALUES (181, 18, 24);
INSERT INTO `sys_user_role` VALUES (151, 19, 26);
INSERT INTO `sys_user_role` VALUES (150, 20, 26);

SET FOREIGN_KEY_CHECKS = 1;
