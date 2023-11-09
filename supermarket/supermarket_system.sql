/*
 Navicat Premium Data Transfer

 Source Server         : WoNiu
 Source Server Type    : MySQL
 Source Server Version : 50540
 Source Host           : localhost:3306
 Source Schema         : supermarket_system

 Target Server Type    : MySQL
 Target Server Version : 50540
 File Encoding         : 65001

 Date: 10/11/2023 06:48:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for aftersale
-- ----------------------------
DROP TABLE IF EXISTS `aftersale`;
CREATE TABLE `aftersale`  (
  `as_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `dd_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `xd_date` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fk_date` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fh_date` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sh_date` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `wc_date` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sq_date` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `jc_date` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `th_date` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fin_date` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `as_type` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `as_status` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `as_reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `as_explain` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `isdelete` int(11) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of aftersale
-- ----------------------------
INSERT INTO `aftersale` VALUES ('T205962c2706', 'T20b49fb0fb15ad4225b', '2023-10-16 9:20:22', '2023-10-16 9:40:00', '', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `aftersale` VALUES ('T20acd718b3d', 'T204b1d187372204a629', '2023-10-04 11:10:31', '2023-10-04 11:15:23', '2023-10-05 8:30:00', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `aftersale` VALUES ('T20ea88623d0', 'T20c211636e9e8c45d79', '2023-08-17 9:20:39', '2023-08-17 19:21:43', '2023-08-18 8:30:01', '2023-08-20 20:39:14', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `aftersale` VALUES ('T20ea88623d0', 'T2010341c9d6bfb43dab', '2023-09-08 18:47:30', '', '', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `aftersale` VALUES ('T20b883ab47b', 'T20226914cdec174e67a', '2023-07-12 23:07:25', '2023-07-12 23:15:49', '2023-07-13 8:29:58', '2023-07-17 17:29:33', '2023-07-17 23:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `aftersale` VALUES ('T201c8724417', 'T2059f6d7e88a114ad4b', '2023-10-21 17:44:28', '2023-10-21 17:46:01', '2023-10-25 10:22:20', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `aftersale` VALUES ('T20ea0c12dde', 'T207727c0ea08794a888', '2023-10-21 16:35:34', '2023-10-21 16:50:01', '', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `aftersale` VALUES ('T20d793c9737', 'T20cbeb38bce2a74dbca', '2023-10-23 10:47:21', '2023-10-23 11:01:54', '2023-10-24 8:29:54', '2023-10-28 12:01:20', '2023-10-28 23:00:01', '2023-10-29 08:30:20', '2023-10-24 13:51:27', '2023-10-24 13:51:30', '2023-10-24 11:12:19', '退货退款', '已完成', '商品质量有问题', '与描述不符', 0);
INSERT INTO `aftersale` VALUES ('T201b855367f', 'T201bd3471474eb4b4da', '2023-01-20 9:42:23', '2023-01-20 9:50:52', '2023-10-25 10:23:24', '2023-01-25 18:30:10', '2023-01-25-23:00:10', '2023-01-27 15:40:10', NULL, NULL, NULL, '退货退款', '待审核', '商品存在破损', '快递都破了', 0);
INSERT INTO `aftersale` VALUES ('T2031f39ebd4', 'T20952e738db039411f9', '2023-10-23 10:47:21', '2023-10-23 11:01:54', '2023-10-24 8:29:54', '2023-10-28 12:01:20', '2023-10-28 23:00:01', '2023-10-29 01:25:20', NULL, NULL, NULL, '仅退货', '待审核', '物流无跟踪进度', '看不到物流', 0);
INSERT INTO `aftersale` VALUES ('T201b2ba3f4b', 'T20fe9f00b4c3974aeca', '2023-06-12 23:07:25', '2023-06-12 23:15:49', '2023-06-13 8:29:58', '2023-06-17 17:29:33', '2023-06-17 23:00:00', '2023-06-18 09:01:00', NULL, NULL, NULL, '换货', '待审核', '商品错发，漏发', '颜色和选择的不一致', 0);
INSERT INTO `aftersale` VALUES ('T20c99e5ca37', 'T20694cd21034554573b', '2023-05-17 9:20:39', '2023-05-17 19:21:43', '2023-05-18 8:30:01', '2023-05-20 20:39:14', '2023-05-20 23:00:00', '2023-05-21 09:08:33', NULL, NULL, '2023-05-21 11:08:33', '退货退款', '已驳回', '商品质量有问题', '其他', 0);

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`  (
  `gk_id` int(11) NOT NULL AUTO_INCREMENT,
  `gk_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gk_phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gk_location` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gk_email` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gk_birthday` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `isdelete` int(11) NOT NULL,
  PRIMARY KEY (`gk_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES (1, '高垚淼', '111', '四川省成都市', '19115561755@163.com', '2001-10-25', 0);
INSERT INTO `customer` VALUES (2, '李四', '222', '重庆市', '1@qq.com', '2001-10-25', 0);
INSERT INTO `customer` VALUES (3, '王五', '333', '北京市', '2@qq.com', '1999-03-15', 0);
INSERT INTO `customer` VALUES (4, '赵六', '444', '上海市', '4@qq.com', '1998-07-24', 0);
INSERT INTO `customer` VALUES (5, '张三', '555', '广东省深圳市', '4@qq.com', '1997-12-31', 0);
INSERT INTO `customer` VALUES (6, '刘七', '666', '江苏省南京市', '5@qq.com', '1996-01-01', 0);
INSERT INTO `customer` VALUES (7, '陈八', '777', '浙江省杭州市', '6@qq.com', '1995-04-18', 0);
INSERT INTO `customer` VALUES (8, '杨九', '888', '山东省青岛市', '7@qq.com', '1994-09-09', 0);
INSERT INTO `customer` VALUES (9, '周十', '999', '湖北省武汉市', '8@qq.com', '1993-06-06', 0);
INSERT INTO `customer` VALUES (10, '吴一十一', '1010', '湖南省长沙市', '9@qq.com', '1992-11-11', 0);
INSERT INTO `customer` VALUES (11, '郑一十二', '1212', '河南省郑州市', '10@qq.com', '1991-02-12', 0);
INSERT INTO `customer` VALUES (12, '孙一十三', '1313', '山西省太原市', '11@qq.com', '1990-05-13', 0);
INSERT INTO `customer` VALUES (13, '李一十四', '1414', '陕西省西安市', '12@qq.com', '2009-08-14', 0);
INSERT INTO `customer` VALUES (14, '周一十五', '1515', '云南省昆明市', '13@qq.com', '2008-10-15', 0);
INSERT INTO `customer` VALUES (15, '吴一十六', '1616', '广西省南宁市', '14@qq.com', '2007-01-16', 0);
INSERT INTO `customer` VALUES (16, '赵一十七', '1717', '内蒙古自治区呼和浩特市', '15@qq.com', '2006-03-17', 0);
INSERT INTO `customer` VALUES (17, '王一十八', '1818', '新疆维吾尔自治区乌鲁木齐市', '16@qq.com', '2005-07-18', 0);
INSERT INTO `customer` VALUES (18, '李一十九', '1919', '西藏自治区拉萨市', '17@qq.com', '2004-09-19', 0);
INSERT INTO `customer` VALUES (19, '张二十', '2020', '青海省西宁市', '18@qq.com', '2003-12-20', 0);
INSERT INTO `customer` VALUES (20, '刘二十一', '2121', '甘肃省兰州市', '19@qq.com', '2002-04-21', 0);
INSERT INTO `customer` VALUES (21, '陈二十二', '2222', '宁夏回族自治区银川市', '20@qq.com', '2001-06-22', 0);
INSERT INTO `customer` VALUES (22, '杨二十三', '2323', '河北省石家庄市', '21@qq.com', '2000-08-23', 0);
INSERT INTO `customer` VALUES (23, '周二十四', '2424', '辽宁省沈阳市', '22@qq.com', '1999-11-24', 0);
INSERT INTO `customer` VALUES (24, '吴二十五', '2525', '吉林省长春市', '23@qq.com', '1998-02-25', 0);
INSERT INTO `customer` VALUES (25, '赵二十六', '2626', '黑龙江省哈尔滨市', '24@qq.com', '1997-05-26', 0);
INSERT INTO `customer` VALUES (26, '王二十七', '2727', '安徽省合肥市', '25@qq.com', '1996-07-27', 0);
INSERT INTO `customer` VALUES (27, '李二十八', '2828', '福建省福州市', '26@qq.com', '1995-10-28', 0);
INSERT INTO `customer` VALUES (28, '张二十九', '2929', '江西省南昌市', '27@qq.com', '1994-01-29', 0);
INSERT INTO `customer` VALUES (29, '刘三十', '3030', '山东省济南市', '28@qq.com', '1993-03-30', 0);
INSERT INTO `customer` VALUES (30, '陈三十一', '3131', '河南省洛阳市', '29@qq.com', '1992-06-31', 0);
INSERT INTO `customer` VALUES (31, '高二', '1234', '成都市', '30@qq.com', '1996-06-06', 0);

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `emp_id` int(11) NULL DEFAULT NULL,
  `emp_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `emp_age` int(11) NULL DEFAULT NULL,
  `emp_gender` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `emp_phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `emp_date` varbinary(20) NULL DEFAULT NULL,
  `user_id` int(11) NULL DEFAULT NULL,
  `file_id` int(11) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of employee
-- ----------------------------

-- ----------------------------
-- Table structure for festival
-- ----------------------------
DROP TABLE IF EXISTS `festival`;
CREATE TABLE `festival`  (
  `jr_id` int(11) NOT NULL AUTO_INCREMENT,
  `jr_name` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `jr_bless` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `jr_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `isdelete` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`jr_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of festival
-- ----------------------------
INSERT INTO `festival` VALUES (1, '三阶段答辩日', '祝您，答辩顺利，成绩优秀，毕业快乐！', '10-25', 0);
INSERT INTO `festival` VALUES (2, '春节', '祝您，新春快乐，万事如意，恭喜发财！', '2-23', 0);
INSERT INTO `festival` VALUES (3, '程序员节', '祝您，程序员节快乐，代码无bug，工作顺心！', '10-24', 0);
INSERT INTO `festival` VALUES (4, '元宵节', '祝您，元宵节快乐，花灯照亮您的美好生活！', '2-15', 0);
INSERT INTO `festival` VALUES (5, '清明节', '祝您，清明节平安，敬祖尊先，感恩生活！', '4-5', 0);
INSERT INTO `festival` VALUES (6, '端午节', '祝您，端午节安康，粽子香甜，龙舟助威！', '5-5', 0);
INSERT INTO `festival` VALUES (7, '中秋节', '祝您，中秋节团圆，月圆人圆，花好事好！', '8-15', 0);
INSERT INTO `festival` VALUES (8, '国庆节', '祝您，国庆节快乐，祖国繁荣昌盛，人民幸福安康！', '10-1', 0);
INSERT INTO `festival` VALUES (9, '重阳节', '祝您，重阳节健康，登高望远，菊花飘香！', '9-9', 0);
INSERT INTO `festival` VALUES (10, '腊八节', '祝您，腊八节吉祥，腊八粥香甜，新年喜气洋洋！', '12-8', 0);

-- ----------------------------
-- Table structure for indent
-- ----------------------------
DROP TABLE IF EXISTS `indent`;
CREATE TABLE `indent`  (
  `dd_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dd_ordertime` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dd_num` int(11) NULL DEFAULT NULL,
  `dd_paystatus` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dd_payway` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dd_yunfei` decimal(10, 2) NULL DEFAULT NULL,
  `dd_delivertime` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dd_status` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dd_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dd_comment` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gk_id` int(11) NULL DEFAULT NULL,
  `sp_id` int(11) NULL DEFAULT NULL,
  `sh_id` int(11) NULL DEFAULT NULL,
  `isdelete` int(11) NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of indent
-- ----------------------------
INSERT INTO `indent` VALUES ('T20b49fb0fb15ad4225b', '2023-10-16', 10, '已支付', '微信', 8.00, '2023-10-7', '付款成功', '进行中', '尽快发货', 1, 1, 1, 0);
INSERT INTO `indent` VALUES ('T204b1d187372204a629', '2023-10-04', 5, '已支付', '支付宝', 8.00, '2023-10-5', '商品出库', '进行中', '无', 2, 2, 1, 0);
INSERT INTO `indent` VALUES ('T20c211636e9e8c45d79', '2023-08-17', 1, '已支付', '线下支付', 0.00, '2023-8-18', '订单完成', '已完成', '自提', 3, 3, 2, 0);
INSERT INTO `indent` VALUES ('T2010341c9d6bfb43dab', '2023-09-08', 3, '未支付', '支付宝', 5.00, '2023-9-10', '买家下单', '未开始', '无', 4, 4, 3, 0);
INSERT INTO `indent` VALUES ('T20226914cdec174e67a', '2023-07-12', 7, '已支付', '网上银行', 13.00, '2023-7-13', '订单完成', '已完成', '无', 5, 5, 4, 0);
INSERT INTO `indent` VALUES ('T2059f6d7e88a114ad4b', '2023-10-21', 1, '已支付', '网上银行', 13.00, '2023-10-22', '商品出库', '进行中', '新增信息1', 8, 1, 1, 0);
INSERT INTO `indent` VALUES ('T207727c0ea08794a888', '2023-10-21', 2, '已支付', '网上银行', 8.00, '2023-10-22', '付款成功', '进行中', '新增信息2', 8, 2, 4, 0);
INSERT INTO `indent` VALUES ('T20cbeb38bce2a74dbca', '2023-10-21', 3, '已支付', '支付宝', 13.00, '2023-10-22', '退货换货', '进行中', '新增信息3', 9, 5, 1, 0);
INSERT INTO `indent` VALUES ('T201bd3471474eb4b4da', '2023-10-24', 4, '已支付', '微信', 13.00, '2023-10-25 20:20:50', '订单完成', '已完成', 'one', 10, 11, 1, 0);
INSERT INTO `indent` VALUES ('T20952e738db039411f9', '2023-01-20', 5, '已支付', '微信', 8.00, '2023-01-21', '订单完成', '已完成', 'two', 11, 6, 4, 0);
INSERT INTO `indent` VALUES ('T20fe9f00b4c3974aeca', '2023-10-25', 1, '已支付', '支付宝', 13.00, '2023-10-26 09:19:10', '付款成功', '进行中', 'three', 12, 7, 1, 0);
INSERT INTO `indent` VALUES ('T20694cd21034554573b', '2023-10-25', 5, '已支付', '支付宝', 13.00, '2023-10-26 09:19:10', '付款成功', '进行中', 'four', 13, 8, 1, 0);

-- ----------------------------
-- Table structure for kind
-- ----------------------------
DROP TABLE IF EXISTS `kind`;
CREATE TABLE `kind`  (
  `zl_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `zl_id` int(11) NOT NULL,
  PRIMARY KEY (`zl_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of kind
-- ----------------------------

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `mid` int(11) NOT NULL AUTO_INCREMENT,
  `mname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `pmid` int(11) NOT NULL,
  `mpath` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `isdelete` char(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`mid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 55 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, '表格', 0, NULL, '0');
INSERT INTO `menu` VALUES (2, '表单', 0, NULL, '0');
INSERT INTO `menu` VALUES (3, '员工管理', 0, NULL, '0');
INSERT INTO `menu` VALUES (4, '商品管理', 0, NULL, '0');
INSERT INTO `menu` VALUES (5, '订单管理', 0, NULL, '0');
INSERT INTO `menu` VALUES (6, '会员管理', 0, '', '0');
INSERT INTO `menu` VALUES (7, '仓库管理', 0, NULL, '0');
INSERT INTO `menu` VALUES (8, '系统设置', 0, NULL, '0');
INSERT INTO `menu` VALUES (9, '原生版', 1, '/commonTable1', '0');
INSERT INTO `menu` VALUES (10, '朴素版', 1, '/commonTable2', '0');
INSERT INTO `menu` VALUES (11, '3D版', 1, '/commonTable3', '0');
INSERT INTO `menu` VALUES (12, '鲜艳版', 1, '/commonTable4', '0');
INSERT INTO `menu` VALUES (13, '基础表单1', 2, '/commonForm1', '0');
INSERT INTO `menu` VALUES (14, '基础表单2', 2, '/commonForm2', '0');
INSERT INTO `menu` VALUES (15, '员工添加', 3, '/EmployeeAdd', '0');
INSERT INTO `menu` VALUES (16, '员工列表', 3, '/EmployeeList', '0');
INSERT INTO `menu` VALUES (17, '门店管理', 4, '/shopManagement', '0');
INSERT INTO `menu` VALUES (18, '商品管理', 4, '/productManagement', '0');
INSERT INTO `menu` VALUES (19, '供货商管理', 4, '/supplyManagement', '0');
INSERT INTO `menu` VALUES (20, '商品类别管理', 4, '/kindMangement', '0');
INSERT INTO `menu` VALUES (21, '店商品管理', 4, '/shopProductManagement', '0');
INSERT INTO `menu` VALUES (22, '商品上架', 4, '/putProduct', '0');
INSERT INTO `menu` VALUES (23, '订单详情', 5, '/RetailOrder', '0');
INSERT INTO `menu` VALUES (24, '售后订单', 5, '/AfterSaleOrder', '0');
INSERT INTO `menu` VALUES (25, '基本管理', 6, NULL, '0');
INSERT INTO `menu` VALUES (26, '新增会员', 25, '/vipAdd', '0');
INSERT INTO `menu` VALUES (27, '会员查询', 25, '/vipSelect', '0');
INSERT INTO `menu` VALUES (28, '找回密码', 25, '/vipFindPassword', '0');
INSERT INTO `menu` VALUES (29, '余额管理', 6, NULL, '0');
INSERT INTO `menu` VALUES (30, '余额充值', 29, '/vipBalance', '0');
INSERT INTO `menu` VALUES (31, '充值记录', 29, '/vipBalanceDetail', '0');
INSERT INTO `menu` VALUES (32, '积分管理', 6, NULL, '0');
INSERT INTO `menu` VALUES (33, '积分映射', 32, '/vipPointAmountManage', '0');
INSERT INTO `menu` VALUES (34, '等级映射', 32, '/vipPointLevelManage', '0');
INSERT INTO `menu` VALUES (35, '积分商品', 32, '/vipIndent', '0');
INSERT INTO `menu` VALUES (36, '积分兑换', 32, '/vipPoint', '0');
INSERT INTO `menu` VALUES (37, '兑换记录', 32, '/vipPointDetail', '0');
INSERT INTO `menu` VALUES (38, '会员关怀', 6, NULL, '0');
INSERT INTO `menu` VALUES (39, '节日管理', 38, '/vipFestival', '0');
INSERT INTO `menu` VALUES (40, '会员关怀', 38, '/vipCare', '0');
INSERT INTO `menu` VALUES (41, '数据分析', 6, NULL, '0');
INSERT INTO `menu` VALUES (42, '会员等级统计', 41, '/VipDataLevelCount', '0');
INSERT INTO `menu` VALUES (43, '余额充值统计', 41, '/vipDataBalanceCount', '0');
INSERT INTO `menu` VALUES (44, '积分兑换统计', 41, '/vipDataPointCount', '0');
INSERT INTO `menu` VALUES (45, '杂七杂八', 6, NULL, '0');
INSERT INTO `menu` VALUES (46, '抽奖转盘', 45, '/lottery', '0');
INSERT INTO `menu` VALUES (47, '仓库列表', 7, '/storeList', '0');
INSERT INTO `menu` VALUES (48, '入库单添加', 7, '/GoodsAdd', '0');
INSERT INTO `menu` VALUES (49, '入库单列表', 7, '/GoodsList', '0');
INSERT INTO `menu` VALUES (50, '商品统计图', 7, '/NewChart', '0');
INSERT INTO `menu` VALUES (51, '用户管理', 8, '/userList', '0');
INSERT INTO `menu` VALUES (52, '菜单管理', 8, '/menuList', '0');
INSERT INTO `menu` VALUES (53, '角色管理', 8, '/roleList', '0');
INSERT INTO `menu` VALUES (54, '权限管理', 8, '/permissionList', '0');

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `pname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ppath` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ppid` int(11) NULL DEFAULT NULL,
  `isdelete` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`pid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 104 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES (1, '员工管理', NULL, 0, 0);
INSERT INTO `permission` VALUES (2, '商品管理', NULL, 0, 0);
INSERT INTO `permission` VALUES (3, '订单管理', NULL, 0, 0);
INSERT INTO `permission` VALUES (4, '会员管理', NULL, 0, 0);
INSERT INTO `permission` VALUES (5, '仓库管理', NULL, 0, 0);
INSERT INTO `permission` VALUES (6, '系统设置', NULL, 0, 0);
INSERT INTO `permission` VALUES (7, '添加员工', '/employee/add', 1, 0);
INSERT INTO `permission` VALUES (8, '删除员工', '/employee/remove', 1, 0);
INSERT INTO `permission` VALUES (9, '修改员工', '/employee/modify', 1, 0);
INSERT INTO `permission` VALUES (10, '查询员工', '/employee/get', 1, 0);
INSERT INTO `permission` VALUES (11, NULL, NULL, NULL, 0);
INSERT INTO `permission` VALUES (12, NULL, NULL, NULL, 0);
INSERT INTO `permission` VALUES (13, NULL, NULL, NULL, 0);
INSERT INTO `permission` VALUES (14, NULL, NULL, NULL, 0);
INSERT INTO `permission` VALUES (15, NULL, NULL, NULL, 0);
INSERT INTO `permission` VALUES (16, NULL, '', NULL, 0);
INSERT INTO `permission` VALUES (17, NULL, NULL, NULL, 0);
INSERT INTO `permission` VALUES (18, NULL, NULL, NULL, 0);
INSERT INTO `permission` VALUES (19, NULL, NULL, NULL, 0);
INSERT INTO `permission` VALUES (20, '商品种类条件查询', '/kind/getAll', 2, 0);
INSERT INTO `permission` VALUES (21, '商品种类添加', '/kind/add', 2, 0);
INSERT INTO `permission` VALUES (22, '商品种类编辑', '/kind/edit\r\n/kind/edit', 2, 0);
INSERT INTO `permission` VALUES (23, '商品种类删除', '/kind/deleteByZlId', 2, 0);
INSERT INTO `permission` VALUES (24, '商品种类批量删除', '/kind/removeAll', 2, 0);
INSERT INTO `permission` VALUES (25, '商品种类查询全部', '/kind/getAllKinds', 2, 0);
INSERT INTO `permission` VALUES (26, '商品条件查询', '/product/getAll', 2, 0);
INSERT INTO `permission` VALUES (27, '商品添加', '/product/add', 2, 0);
INSERT INTO `permission` VALUES (28, '商品编辑', '/product/edit', 2, 0);
INSERT INTO `permission` VALUES (29, '商品删除', '/product/deleteBySpId', 2, 0);
INSERT INTO `permission` VALUES (30, '商品批量删除', '/product/removeAll', 2, 0);
INSERT INTO `permission` VALUES (31, '商品数据导出', '/product/export', 2, 0);
INSERT INTO `permission` VALUES (32, '门店条件查询', '/shop/getAll', 2, 0);
INSERT INTO `permission` VALUES (33, '门店图片上传', '/shop/upload', 2, 0);
INSERT INTO `permission` VALUES (34, '门店添加', '/shop/add', 2, 0);
INSERT INTO `permission` VALUES (35, '门店编辑', '/shop/edit', 2, 0);
INSERT INTO `permission` VALUES (36, '门店删除', '/shop/deleteByMdId', 2, 0);
INSERT INTO `permission` VALUES (37, '门店批量删除', '/shop/removeAll', 2, 0);
INSERT INTO `permission` VALUES (38, '门店数据导出', '/shop/export', 2, 0);
INSERT INTO `permission` VALUES (39, '门店查询全部', '/shop/getAllShops', 2, 0);
INSERT INTO `permission` VALUES (40, '门店商品上架', '/shopProduct/add', 2, 0);
INSERT INTO `permission` VALUES (41, '门店商品批量上架', '/shopProduct/putAll', 2, 0);
INSERT INTO `permission` VALUES (42, '门店商品条件查询', '/shopProduct/getAll', 2, 0);
INSERT INTO `permission` VALUES (43, '门店商品下架', '/shopProduct/pullProductBySpId', 2, 0);
INSERT INTO `permission` VALUES (44, '门店商品重新上架', '/shopProduct/putProductBySpId', 2, 0);
INSERT INTO `permission` VALUES (45, '供应商条件查询', '/supply/getAll', 2, 0);
INSERT INTO `permission` VALUES (46, '添加供应商', '/supply/add', 2, 0);
INSERT INTO `permission` VALUES (47, '供应商编辑', '/supply/edit', 2, 0);
INSERT INTO `permission` VALUES (48, '供应商删除', '/supply/deleteBySuId', 2, 0);
INSERT INTO `permission` VALUES (49, '批量删除供应商', '/supply/removeAll', 2, 0);
INSERT INTO `permission` VALUES (50, '获取全面供应商', '/supply/getAllSupplies', 2, 0);
INSERT INTO `permission` VALUES (51, NULL, NULL, NULL, NULL);
INSERT INTO `permission` VALUES (52, NULL, NULL, NULL, NULL);
INSERT INTO `permission` VALUES (53, NULL, NULL, NULL, NULL);
INSERT INTO `permission` VALUES (54, NULL, NULL, NULL, NULL);
INSERT INTO `permission` VALUES (55, NULL, NULL, NULL, NULL);
INSERT INTO `permission` VALUES (59, '查询所有', '/indent/get  ', 3, 0);
INSERT INTO `permission` VALUES (60, '获取所有配送方式', '/indent/sendWay/all ', 3, 0);
INSERT INTO `permission` VALUES (61, '获取所有商品种类', '/indent/kind/all', 3, 0);
INSERT INTO `permission` VALUES (62, '根据商品种类获取商品名', '/indent/getAllProductName	', 3, 0);
INSERT INTO `permission` VALUES (63, '根据商品名获取价格信息', '/indent/getPriceByName', 3, 0);
INSERT INTO `permission` VALUES (64, '支付订单', '/indent/payMoney	', 3, 0);
INSERT INTO `permission` VALUES (65, '商品出库', '/indent/deliverGoods', 3, 0);
INSERT INTO `permission` VALUES (66, '确认收货', '/indent/recivePackage', 3, 0);
INSERT INTO `permission` VALUES (67, '确认交易完成', '/indent/finishIndent	', 3, 0);
INSERT INTO `permission` VALUES (68, '添加新订单', '/indent/addNewOrder', 3, 0);
INSERT INTO `permission` VALUES (69, '根据id删除订单', '/indent/delete', 3, 0);
INSERT INTO `permission` VALUES (70, '批量删除订单', '/indent/removeBatch', 3, 0);
INSERT INTO `permission` VALUES (71, '修改收货信息', '/indent/modifyFlow	', 3, 0);
INSERT INTO `permission` VALUES (72, '导出excel表格', '/indent/export', 3, 0);
INSERT INTO `permission` VALUES (73, '带条件查询所有', '/afterSale/get', 3, 0);
INSERT INTO `permission` VALUES (74, '审核通过', '/afterSale/agree', 3, 0);
INSERT INTO `permission` VALUES (75, '驳回审核', '/afterSale/reject	', 3, 0);
INSERT INTO `permission` VALUES (76, '商品售后状态处理1', '/afterSale/dealRequest1', 3, 0);
INSERT INTO `permission` VALUES (77, '商品售后状态处理2', '/afterSale/dealRequest2', 3, 0);
INSERT INTO `permission` VALUES (78, '完成售后服务', '/afterSale/finAfterSale', 3, 0);
INSERT INTO `permission` VALUES (79, '添加售后信息', '/afterSale/changeAsType', 3, 0);
INSERT INTO `permission` VALUES (80, NULL, NULL, NULL, NULL);
INSERT INTO `permission` VALUES (81, NULL, NULL, NULL, NULL);
INSERT INTO `permission` VALUES (82, NULL, NULL, NULL, NULL);
INSERT INTO `permission` VALUES (83, NULL, NULL, NULL, NULL);
INSERT INTO `permission` VALUES (84, NULL, NULL, NULL, NULL);
INSERT INTO `permission` VALUES (85, NULL, NULL, NULL, NULL);
INSERT INTO `permission` VALUES (86, NULL, NULL, NULL, NULL);
INSERT INTO `permission` VALUES (87, NULL, NULL, NULL, NULL);
INSERT INTO `permission` VALUES (88, NULL, NULL, NULL, NULL);
INSERT INTO `permission` VALUES (89, '查询菜单', '/menu/get/all', 6, 0);
INSERT INTO `permission` VALUES (90, '添加子菜单', '/menu/addMenus', 6, 0);
INSERT INTO `permission` VALUES (91, '添加父菜单', '/menu/addParentMenus', 6, 0);
INSERT INTO `permission` VALUES (92, '删除菜单', '/menu/remove', 6, 0);
INSERT INTO `permission` VALUES (93, '分配菜单', '/role/allocationMenu', 6, 0);
INSERT INTO `permission` VALUES (94, '查询角色', '/role/get/all', 6, 0);
INSERT INTO `permission` VALUES (95, '添加角色', '/role/add', 6, 0);
INSERT INTO `permission` VALUES (96, '查询权限', '/permission/get/all', 6, 0);
INSERT INTO `permission` VALUES (97, '添加子权限', '/permission/addPermissions', 6, 0);
INSERT INTO `permission` VALUES (98, '添加父权限', '/permission/addParentPermission', 6, 0);
INSERT INTO `permission` VALUES (99, '删除权限', '/permission/remove', 6, 0);
INSERT INTO `permission` VALUES (100, '分配权限', '/role/allocationPermission', 6, 0);
INSERT INTO `permission` VALUES (101, '修改密码', '/changePassword', NULL, 0);
INSERT INTO `permission` VALUES (102, '登录', '/user/login', NULL, 0);
INSERT INTO `permission` VALUES (103, '发送邮箱', '/email', NULL, 0);

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `sp_id` int(11) NOT NULL AUTO_INCREMENT,
  `sp_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sp_price` decimal(10, 0) NULL DEFAULT NULL,
  `sp_num` int(11) NULL DEFAULT NULL,
  `zl_id` int(11) NULL DEFAULT NULL,
  `isdelete` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`sp_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of product
-- ----------------------------

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `rid` int(11) NOT NULL AUTO_INCREMENT,
  `rname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `isdelete` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`rid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'Boss', 0);
INSERT INTO `role` VALUES (2, '店主', 0);

-- ----------------------------
-- Table structure for rolemenu
-- ----------------------------
DROP TABLE IF EXISTS `rolemenu`;
CREATE TABLE `rolemenu`  (
  `rmid` int(11) NOT NULL AUTO_INCREMENT,
  `rid` int(11) NOT NULL,
  `mid` int(11) NOT NULL,
  `isdelete` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`rmid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 87 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of rolemenu
-- ----------------------------
INSERT INTO `rolemenu` VALUES (1, 1, 1, 0);
INSERT INTO `rolemenu` VALUES (2, 1, 2, 0);
INSERT INTO `rolemenu` VALUES (3, 1, 3, 0);
INSERT INTO `rolemenu` VALUES (4, 1, 4, 0);
INSERT INTO `rolemenu` VALUES (5, 1, 5, 0);
INSERT INTO `rolemenu` VALUES (6, 1, 6, 0);
INSERT INTO `rolemenu` VALUES (7, 1, 7, 0);
INSERT INTO `rolemenu` VALUES (8, 1, 8, 0);
INSERT INTO `rolemenu` VALUES (9, 1, 9, 0);
INSERT INTO `rolemenu` VALUES (10, 1, 10, 0);
INSERT INTO `rolemenu` VALUES (11, 1, 11, 0);
INSERT INTO `rolemenu` VALUES (12, 1, 12, 0);
INSERT INTO `rolemenu` VALUES (13, 1, 13, 0);
INSERT INTO `rolemenu` VALUES (14, 1, 14, 0);
INSERT INTO `rolemenu` VALUES (15, 1, 15, 0);
INSERT INTO `rolemenu` VALUES (16, 1, 16, 0);
INSERT INTO `rolemenu` VALUES (17, 1, 17, 0);
INSERT INTO `rolemenu` VALUES (18, 1, 18, 0);
INSERT INTO `rolemenu` VALUES (19, 1, 19, 0);
INSERT INTO `rolemenu` VALUES (20, 1, 20, 0);
INSERT INTO `rolemenu` VALUES (21, 1, 21, 0);
INSERT INTO `rolemenu` VALUES (22, 1, 22, 0);
INSERT INTO `rolemenu` VALUES (23, 1, 23, 0);
INSERT INTO `rolemenu` VALUES (24, 1, 24, 0);
INSERT INTO `rolemenu` VALUES (25, 1, 25, 0);
INSERT INTO `rolemenu` VALUES (26, 1, 26, 0);
INSERT INTO `rolemenu` VALUES (27, 1, 27, 0);
INSERT INTO `rolemenu` VALUES (28, 1, 28, 0);
INSERT INTO `rolemenu` VALUES (29, 1, 29, 0);
INSERT INTO `rolemenu` VALUES (30, 1, 30, 0);
INSERT INTO `rolemenu` VALUES (31, 1, 31, 0);
INSERT INTO `rolemenu` VALUES (32, 1, 32, 0);
INSERT INTO `rolemenu` VALUES (33, 1, 33, 0);
INSERT INTO `rolemenu` VALUES (34, 1, 34, 0);
INSERT INTO `rolemenu` VALUES (35, 1, 35, 0);
INSERT INTO `rolemenu` VALUES (36, 1, 36, 0);
INSERT INTO `rolemenu` VALUES (37, 1, 37, 0);
INSERT INTO `rolemenu` VALUES (38, 1, 38, 0);
INSERT INTO `rolemenu` VALUES (39, 1, 39, 0);
INSERT INTO `rolemenu` VALUES (40, 1, 40, 0);
INSERT INTO `rolemenu` VALUES (41, 1, 41, 0);
INSERT INTO `rolemenu` VALUES (42, 1, 42, 0);
INSERT INTO `rolemenu` VALUES (43, 1, 43, 0);
INSERT INTO `rolemenu` VALUES (44, 1, 44, 0);
INSERT INTO `rolemenu` VALUES (45, 1, 45, 0);
INSERT INTO `rolemenu` VALUES (46, 1, 46, 0);
INSERT INTO `rolemenu` VALUES (47, 1, 47, 0);
INSERT INTO `rolemenu` VALUES (48, 1, 48, 0);
INSERT INTO `rolemenu` VALUES (49, 1, 49, 0);
INSERT INTO `rolemenu` VALUES (50, 1, 50, 0);
INSERT INTO `rolemenu` VALUES (51, 1, 51, 0);
INSERT INTO `rolemenu` VALUES (52, 1, 52, 0);
INSERT INTO `rolemenu` VALUES (53, 1, 53, 0);
INSERT INTO `rolemenu` VALUES (54, 1, 54, 0);
INSERT INTO `rolemenu` VALUES (55, 2, 1, 0);
INSERT INTO `rolemenu` VALUES (56, 2, 2, 0);
INSERT INTO `rolemenu` VALUES (57, 2, 3, 0);
INSERT INTO `rolemenu` VALUES (58, 2, 4, 0);
INSERT INTO `rolemenu` VALUES (59, 2, 5, 0);
INSERT INTO `rolemenu` VALUES (60, 2, 6, 0);
INSERT INTO `rolemenu` VALUES (61, 2, 7, 0);
INSERT INTO `rolemenu` VALUES (62, 2, 8, 0);
INSERT INTO `rolemenu` VALUES (63, 2, 9, 0);
INSERT INTO `rolemenu` VALUES (64, 2, 10, 0);
INSERT INTO `rolemenu` VALUES (65, 2, 11, 0);
INSERT INTO `rolemenu` VALUES (66, 2, 12, 0);
INSERT INTO `rolemenu` VALUES (67, 2, 13, 0);
INSERT INTO `rolemenu` VALUES (68, 2, 14, 0);
INSERT INTO `rolemenu` VALUES (69, 2, 15, 0);
INSERT INTO `rolemenu` VALUES (70, 2, 16, 0);
INSERT INTO `rolemenu` VALUES (71, 2, 17, 0);
INSERT INTO `rolemenu` VALUES (72, 2, 18, 0);
INSERT INTO `rolemenu` VALUES (73, 2, 19, 0);
INSERT INTO `rolemenu` VALUES (74, 2, 20, 0);
INSERT INTO `rolemenu` VALUES (75, 2, 21, 0);
INSERT INTO `rolemenu` VALUES (76, 2, 22, 0);
INSERT INTO `rolemenu` VALUES (77, 2, 23, 0);
INSERT INTO `rolemenu` VALUES (78, 2, 24, 0);
INSERT INTO `rolemenu` VALUES (79, 2, 25, 0);
INSERT INTO `rolemenu` VALUES (80, 2, 26, 0);
INSERT INTO `rolemenu` VALUES (81, 2, 27, 0);
INSERT INTO `rolemenu` VALUES (82, 2, 28, 0);
INSERT INTO `rolemenu` VALUES (83, 2, 51, 0);
INSERT INTO `rolemenu` VALUES (84, 2, 52, 0);
INSERT INTO `rolemenu` VALUES (85, 2, 53, 0);
INSERT INTO `rolemenu` VALUES (86, 2, 54, 0);

-- ----------------------------
-- Table structure for rolepermission
-- ----------------------------
DROP TABLE IF EXISTS `rolepermission`;
CREATE TABLE `rolepermission`  (
  `rpid` int(11) NOT NULL AUTO_INCREMENT,
  `rid` int(11) NOT NULL,
  `pid` int(11) NOT NULL,
  `isdelete` int(11) NOT NULL,
  PRIMARY KEY (`rpid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 136 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of rolepermission
-- ----------------------------
INSERT INTO `rolepermission` VALUES (1, 1, 1, 0);
INSERT INTO `rolepermission` VALUES (2, 1, 2, 0);
INSERT INTO `rolepermission` VALUES (3, 1, 3, 0);
INSERT INTO `rolepermission` VALUES (4, 1, 4, 0);
INSERT INTO `rolepermission` VALUES (5, 1, 5, 0);
INSERT INTO `rolepermission` VALUES (6, 1, 6, 0);
INSERT INTO `rolepermission` VALUES (7, 1, 7, 0);
INSERT INTO `rolepermission` VALUES (8, 1, 8, 0);
INSERT INTO `rolepermission` VALUES (9, 1, 9, 0);
INSERT INTO `rolepermission` VALUES (10, 1, 10, 0);
INSERT INTO `rolepermission` VALUES (11, 1, 11, 0);
INSERT INTO `rolepermission` VALUES (12, 1, 12, 0);
INSERT INTO `rolepermission` VALUES (13, 1, 13, 0);
INSERT INTO `rolepermission` VALUES (14, 1, 14, 0);
INSERT INTO `rolepermission` VALUES (15, 1, 15, 0);
INSERT INTO `rolepermission` VALUES (16, 1, 16, 0);
INSERT INTO `rolepermission` VALUES (17, 1, 17, 0);
INSERT INTO `rolepermission` VALUES (18, 1, 18, 0);
INSERT INTO `rolepermission` VALUES (19, 1, 19, 0);
INSERT INTO `rolepermission` VALUES (20, 1, 20, 0);
INSERT INTO `rolepermission` VALUES (21, 1, 21, 0);
INSERT INTO `rolepermission` VALUES (22, 1, 22, 0);
INSERT INTO `rolepermission` VALUES (23, 1, 23, 0);
INSERT INTO `rolepermission` VALUES (24, 1, 24, 0);
INSERT INTO `rolepermission` VALUES (25, 1, 25, 0);
INSERT INTO `rolepermission` VALUES (26, 1, 26, 0);
INSERT INTO `rolepermission` VALUES (27, 1, 27, 0);
INSERT INTO `rolepermission` VALUES (28, 1, 28, 0);
INSERT INTO `rolepermission` VALUES (29, 1, 29, 0);
INSERT INTO `rolepermission` VALUES (30, 1, 30, 0);
INSERT INTO `rolepermission` VALUES (31, 1, 31, 0);
INSERT INTO `rolepermission` VALUES (32, 1, 32, 0);
INSERT INTO `rolepermission` VALUES (33, 1, 33, 0);
INSERT INTO `rolepermission` VALUES (34, 1, 34, 0);
INSERT INTO `rolepermission` VALUES (35, 1, 35, 0);
INSERT INTO `rolepermission` VALUES (36, 1, 36, 0);
INSERT INTO `rolepermission` VALUES (37, 1, 37, 0);
INSERT INTO `rolepermission` VALUES (38, 1, 38, 0);
INSERT INTO `rolepermission` VALUES (39, 1, 39, 0);
INSERT INTO `rolepermission` VALUES (40, 1, 40, 0);
INSERT INTO `rolepermission` VALUES (41, 1, 41, 0);
INSERT INTO `rolepermission` VALUES (42, 1, 42, 0);
INSERT INTO `rolepermission` VALUES (43, 1, 43, 0);
INSERT INTO `rolepermission` VALUES (44, 1, 44, 0);
INSERT INTO `rolepermission` VALUES (45, 1, 45, 0);
INSERT INTO `rolepermission` VALUES (46, 1, 46, 0);
INSERT INTO `rolepermission` VALUES (47, 1, 47, 0);
INSERT INTO `rolepermission` VALUES (48, 1, 48, 0);
INSERT INTO `rolepermission` VALUES (49, 1, 49, 0);
INSERT INTO `rolepermission` VALUES (50, 1, 50, 0);
INSERT INTO `rolepermission` VALUES (51, 1, 51, 0);
INSERT INTO `rolepermission` VALUES (52, 1, 52, 0);
INSERT INTO `rolepermission` VALUES (53, 1, 53, 0);
INSERT INTO `rolepermission` VALUES (54, 1, 54, 0);
INSERT INTO `rolepermission` VALUES (55, 1, 55, 0);
INSERT INTO `rolepermission` VALUES (56, 1, 56, 0);
INSERT INTO `rolepermission` VALUES (57, 1, 57, 0);
INSERT INTO `rolepermission` VALUES (58, 1, 58, 0);
INSERT INTO `rolepermission` VALUES (59, 1, 59, 0);
INSERT INTO `rolepermission` VALUES (60, 1, 60, 0);
INSERT INTO `rolepermission` VALUES (61, 1, 61, 0);
INSERT INTO `rolepermission` VALUES (62, 1, 62, 0);
INSERT INTO `rolepermission` VALUES (63, 1, 63, 0);
INSERT INTO `rolepermission` VALUES (64, 1, 64, 0);
INSERT INTO `rolepermission` VALUES (65, 1, 65, 0);
INSERT INTO `rolepermission` VALUES (66, 1, 66, 0);
INSERT INTO `rolepermission` VALUES (67, 1, 67, 0);
INSERT INTO `rolepermission` VALUES (68, 1, 68, 0);
INSERT INTO `rolepermission` VALUES (69, 1, 69, 0);
INSERT INTO `rolepermission` VALUES (70, 1, 70, 0);
INSERT INTO `rolepermission` VALUES (71, 1, 71, 0);
INSERT INTO `rolepermission` VALUES (72, 1, 72, 0);
INSERT INTO `rolepermission` VALUES (73, 1, 73, 0);
INSERT INTO `rolepermission` VALUES (74, 1, 74, 0);
INSERT INTO `rolepermission` VALUES (75, 1, 75, 0);
INSERT INTO `rolepermission` VALUES (76, 1, 76, 0);
INSERT INTO `rolepermission` VALUES (77, 1, 77, 0);
INSERT INTO `rolepermission` VALUES (78, 1, 78, 0);
INSERT INTO `rolepermission` VALUES (79, 1, 79, 0);
INSERT INTO `rolepermission` VALUES (80, 1, 80, 0);
INSERT INTO `rolepermission` VALUES (81, 1, 81, 0);
INSERT INTO `rolepermission` VALUES (82, 1, 82, 0);
INSERT INTO `rolepermission` VALUES (83, 1, 83, 0);
INSERT INTO `rolepermission` VALUES (84, 1, 84, 0);
INSERT INTO `rolepermission` VALUES (85, 1, 85, 0);
INSERT INTO `rolepermission` VALUES (86, 1, 86, 0);
INSERT INTO `rolepermission` VALUES (87, 1, 87, 0);
INSERT INTO `rolepermission` VALUES (88, 1, 88, 0);
INSERT INTO `rolepermission` VALUES (89, 1, 89, 0);
INSERT INTO `rolepermission` VALUES (90, 1, 90, 0);
INSERT INTO `rolepermission` VALUES (91, 1, 91, 0);
INSERT INTO `rolepermission` VALUES (92, 1, 93, 0);
INSERT INTO `rolepermission` VALUES (93, 1, 92, 0);
INSERT INTO `rolepermission` VALUES (94, 1, 94, 0);
INSERT INTO `rolepermission` VALUES (95, 1, 95, 0);
INSERT INTO `rolepermission` VALUES (96, 1, 96, 0);
INSERT INTO `rolepermission` VALUES (97, 1, 97, 0);
INSERT INTO `rolepermission` VALUES (98, 1, 98, 0);
INSERT INTO `rolepermission` VALUES (99, 1, 99, 0);
INSERT INTO `rolepermission` VALUES (100, 1, 100, 0);
INSERT INTO `rolepermission` VALUES (101, 1, 101, 0);
INSERT INTO `rolepermission` VALUES (102, 1, 102, 0);
INSERT INTO `rolepermission` VALUES (103, 1, 103, 0);
INSERT INTO `rolepermission` VALUES (104, 2, 1, 0);
INSERT INTO `rolepermission` VALUES (105, 2, 2, 0);
INSERT INTO `rolepermission` VALUES (106, 2, 3, 0);
INSERT INTO `rolepermission` VALUES (107, 2, 4, 0);
INSERT INTO `rolepermission` VALUES (108, 2, 5, 0);
INSERT INTO `rolepermission` VALUES (109, 2, 6, 0);
INSERT INTO `rolepermission` VALUES (110, 2, 7, 0);
INSERT INTO `rolepermission` VALUES (111, 2, 8, 0);
INSERT INTO `rolepermission` VALUES (112, 2, 9, 0);
INSERT INTO `rolepermission` VALUES (113, 2, 10, 0);
INSERT INTO `rolepermission` VALUES (114, 2, 11, 0);
INSERT INTO `rolepermission` VALUES (115, 2, 12, 0);
INSERT INTO `rolepermission` VALUES (116, 2, 13, 0);
INSERT INTO `rolepermission` VALUES (117, 2, 14, 0);
INSERT INTO `rolepermission` VALUES (118, 2, 15, 0);
INSERT INTO `rolepermission` VALUES (119, 2, 16, 0);
INSERT INTO `rolepermission` VALUES (120, 2, 17, 0);
INSERT INTO `rolepermission` VALUES (121, 2, 18, 0);
INSERT INTO `rolepermission` VALUES (122, 2, 19, 0);
INSERT INTO `rolepermission` VALUES (123, 2, 20, 0);
INSERT INTO `rolepermission` VALUES (124, 2, 21, 0);
INSERT INTO `rolepermission` VALUES (125, 2, 22, 0);
INSERT INTO `rolepermission` VALUES (126, 2, 23, 0);
INSERT INTO `rolepermission` VALUES (127, 2, 24, 0);
INSERT INTO `rolepermission` VALUES (128, 2, 25, 0);
INSERT INTO `rolepermission` VALUES (129, 2, 26, 0);
INSERT INTO `rolepermission` VALUES (130, 2, 27, 0);
INSERT INTO `rolepermission` VALUES (131, 2, 28, 0);
INSERT INTO `rolepermission` VALUES (132, 2, 29, 0);
INSERT INTO `rolepermission` VALUES (133, 2, 30, 0);
INSERT INTO `rolepermission` VALUES (134, 2, 31, 0);
INSERT INTO `rolepermission` VALUES (135, 2, 32, 0);

-- ----------------------------
-- Table structure for sendway
-- ----------------------------
DROP TABLE IF EXISTS `sendway`;
CREATE TABLE `sendway`  (
  `sh_id` int(11) NOT NULL,
  `sh_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`sh_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sendway
-- ----------------------------

-- ----------------------------
-- Table structure for shop
-- ----------------------------
DROP TABLE IF EXISTS `shop`;
CREATE TABLE `shop`  (
  `md_id` int(11) NOT NULL AUTO_INCREMENT,
  `md_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `md_address` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `md_phone` varchar(13) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `md_cheif` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `md_describe` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `md_licensesrc` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `isdelete` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`md_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of shop
-- ----------------------------

-- ----------------------------
-- Table structure for store
-- ----------------------------
DROP TABLE IF EXISTS `store`;
CREATE TABLE `store`  (
  `kf_id` int(11) NOT NULL AUTO_INCREMENT,
  `kf_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`kf_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of store
-- ----------------------------

-- ----------------------------
-- Table structure for supply
-- ----------------------------
DROP TABLE IF EXISTS `supply`;
CREATE TABLE `supply`  (
  `su_id` int(11) NOT NULL,
  `su_name` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `isdelete` int(11) NOT NULL,
  PRIMARY KEY (`su_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of supply
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` char(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_pwd` varchar(111) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_email` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `rid` int(11) NULL DEFAULT NULL,
  `isdelete` int(11) NOT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'aaa', ' 202cb962ac59075b964b07152d234b70', '111@qq.com', 1, 0);
INSERT INTO `user` VALUES (2, 'bbb', '	bcbe3365e6ac95ea2c0343a2395834dd', '222@qq.com', 2, 0);

-- ----------------------------
-- Table structure for vip
-- ----------------------------
DROP TABLE IF EXISTS `vip`;
CREATE TABLE `vip`  (
  `gk_id` int(11) NULL DEFAULT NULL COMMENT '顾客id',
  `hy_id` int(11) NOT NULL COMMENT '会员卡id',
  `hy_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '会员姓名',
  `hy_username` int(11) NULL DEFAULT NULL COMMENT '会员账号',
  `hy_password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '会员密码',
  `hy_level` int(11) NULL DEFAULT NULL COMMENT '会员等级',
  `hy_discount` decimal(11, 2) NULL DEFAULT NULL COMMENT '会员消费时的折扣率，如0.8',
  `hy_balance` decimal(11, 2) NULL DEFAULT NULL COMMENT '会员卡余额',
  `hy_point` int(11) NULL DEFAULT NULL COMMENT '会员总积分（根据消费金额转换，不会变）',
  `hy_totalpoint` int(11) NULL DEFAULT NULL COMMENT '会员现有积分（可用于兑换积分商品）',
  `isdelete` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`hy_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of vip
-- ----------------------------
INSERT INTO `vip` VALUES (1, 984390, '高垚淼', 984390, '14e1b600b1fd579f47433b88e8d85291', 2, 0.90, 1300.00, 33560, 46213, 0);
INSERT INTO `vip` VALUES (2, 984391, '李四', 8906416, '28c8edde3d61a0411511d3b1866f0636', 2, 0.90, 123.00, 1579, 1700, 1);

-- ----------------------------
-- Table structure for vipbalance
-- ----------------------------
DROP TABLE IF EXISTS `vipbalance`;
CREATE TABLE `vipbalance`  (
  `cz_id` int(11) NOT NULL AUTO_INCREMENT,
  `hy_id` int(11) NULL DEFAULT NULL,
  `hy_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cz_amount` int(11) NULL DEFAULT NULL,
  `cz_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cz_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cz_statue` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `isdelete` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`cz_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of vipbalance
-- ----------------------------
INSERT INTO `vipbalance` VALUES (3, 984390, '高垚淼', 999, '2023-01-18 15:57:51', '支付宝', '成功', 1);
INSERT INTO `vipbalance` VALUES (4, 984390, '高垚淼', 888, '2023-03-18 15:57:51', '支付宝', '成功', 0);
INSERT INTO `vipbalance` VALUES (5, 984390, '高垚淼', 100, '2023-05-18 17:47:04', '微信', '成功', 0);
INSERT INTO `vipbalance` VALUES (6, 8906416, '李四', 500, '2023-07-18 18:12:34', '现金', '成功', 0);
INSERT INTO `vipbalance` VALUES (7, 1234567, '王五', 300, '2023-09-18 19:23:45', '微信', '成功', 0);
INSERT INTO `vipbalance` VALUES (8, 2345678, '赵六', 800, '2023-02-18 20:34:56', '支付宝', '成功', 0);
INSERT INTO `vipbalance` VALUES (9, 3456789, '张三', 600, '2023-04-18 21:45:67', '现金', '成功', 0);
INSERT INTO `vipbalance` VALUES (10, 4567890, '刘七', 400, '2023-06-19 09:12:13', '微信', '成功', 0);
INSERT INTO `vipbalance` VALUES (11, 5678901, '陈八', 200, '2023-08-19 10:23:24', '支付宝', '成功', 0);
INSERT INTO `vipbalance` VALUES (12, 6789012, '杨九', 900, '2023-10-19 11:34:35', '现金', '成功', 0);
INSERT INTO `vipbalance` VALUES (13, 7890123, '周十', 700, '2023-03-19 12:45:46', '微信', '成功', 0);
INSERT INTO `vipbalance` VALUES (14, 8901234, '吴一十一', 500, '2023-05-19 13:56:57', '支付宝', '成功', 0);
INSERT INTO `vipbalance` VALUES (15, 9012345, '郑一十二', 1500, '2023-01-19 14:01:02', '现金', '成功', 0);
INSERT INTO `vipbalance` VALUES (16, 123456, '孙一十三', 800, '2023-01-19 15:12:13', '微信', '成功', 0);
INSERT INTO `vipbalance` VALUES (17, 1123456, '李一十四', 600, '2023-03-19 16:23:24', '支付宝', '成功', 0);
INSERT INTO `vipbalance` VALUES (18, 2234567, '周一十五', 1000, '2023-04-19 17:34:35', '现金', '成功', 0);
INSERT INTO `vipbalance` VALUES (19, 3345678, '吴一十六', 700, '2023-07-20 08:45:46', '微信', '成功', 0);
INSERT INTO `vipbalance` VALUES (20, 4456789, '赵一十七', 900, '2023-09-20 09:56:57', '支付宝', '成功', 0);
INSERT INTO `vipbalance` VALUES (21, 5567890, '王一十八', 1000, '2023-10-20 10:01:02', '现金', '成功', 0);
INSERT INTO `vipbalance` VALUES (22, 6678901, '李一十九', 800, '2023-10-20 11:12:13', '微信', '成功', 0);
INSERT INTO `vipbalance` VALUES (23, 7789012, '张二十', 600, '2023-10-20 12:23:24', '支付宝', '成功', 0);
INSERT INTO `vipbalance` VALUES (24, 8890123, '刘二十一', 1000, '2023-04-20 13:34:35', '现金', '成功', 0);
INSERT INTO `vipbalance` VALUES (25, 9901234, '陈二十二', 700, '2023-10-20 14:45:46', '微信', '成功', 0);
INSERT INTO `vipbalance` VALUES (26, 12345, '杨二十三', 900, '2023-01-20 15:56:57', '支付宝', '成功', 0);
INSERT INTO `vipbalance` VALUES (27, 1112345, '周二十四', 500, '2023-07-21 08:01:02', '现金', '成功', 0);
INSERT INTO `vipbalance` VALUES (28, 2223456, '吴二十五', 600, '2023-10-21 09:12:13', '微信', '成功', 0);
INSERT INTO `vipbalance` VALUES (29, 3334567, '赵二十六', 800, '2023-10-21 10:23:24', '支付宝', '成功', 0);
INSERT INTO `vipbalance` VALUES (30, 4445678, '王二十七', 1000, '2023-10-21 11:34:35', '现金', '成功', 0);
INSERT INTO `vipbalance` VALUES (31, 984390, '高垚淼', 60, '2023-10-25 14:31:50', '微信', '成功', 0);

-- ----------------------------
-- Table structure for vipexchange
-- ----------------------------
DROP TABLE IF EXISTS `vipexchange`;
CREATE TABLE `vipexchange`  (
  `dh_id` int(11) NOT NULL AUTO_INCREMENT,
  `vi_id` int(11) NULL DEFAULT NULL,
  `hy_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dh_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `isdelete` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`dh_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of vipexchange
-- ----------------------------
INSERT INTO `vipexchange` VALUES (1, 1, '984390', '2023-10-21 14:30:14', 0);
INSERT INTO `vipexchange` VALUES (19, 1, '984390', '2023-10-20 10:30:14', 0);
INSERT INTO `vipexchange` VALUES (20, 2, '984391', '2023-10-20 11:45:23', 0);
INSERT INTO `vipexchange` VALUES (21, 3, '984392', '2023-10-20 12:56:34', 0);
INSERT INTO `vipexchange` VALUES (22, 4, '984393', '2023-10-20 14:07:45', 0);
INSERT INTO `vipexchange` VALUES (23, 5, '984394', '2023-10-20 15:18:56', 0);
INSERT INTO `vipexchange` VALUES (24, 6, '984395', '2023-10-20 16:29:07', 0);
INSERT INTO `vipexchange` VALUES (25, 7, '984396', '2023-10-20 17:40:18', 0);
INSERT INTO `vipexchange` VALUES (26, 8, '984397', '2023-10-20 18:51:29', 0);
INSERT INTO `vipexchange` VALUES (27, 9, '984398', '2023-10-20 20:02:40', 0);
INSERT INTO `vipexchange` VALUES (28, 10, '984399', '2023-10-20 21:13:51', 0);
INSERT INTO `vipexchange` VALUES (29, 1, '984400', '2023-10-21 09:25:02', 0);
INSERT INTO `vipexchange` VALUES (30, 2, '984401', '2023-10-21 10:36:13', 0);
INSERT INTO `vipexchange` VALUES (31, 1, '984402', '2023-10-21 10:36:13', 0);
INSERT INTO `vipexchange` VALUES (32, 2, '984403', '2023-10-21 10:43:13', 0);
INSERT INTO `vipexchange` VALUES (33, 5, '984404', '2023-10-21 10:43:13', 0);
INSERT INTO `vipexchange` VALUES (34, 3, '984404', '2023-10-21 10:43:13', 0);
INSERT INTO `vipexchange` VALUES (35, 4, '984405', '2023-10-21 10:43:13', 0);
INSERT INTO `vipexchange` VALUES (36, 4, '984406', '2023-10-21 10:43:13', 0);
INSERT INTO `vipexchange` VALUES (37, 4, '984407', '2023-10-21 10:43:13', 0);
INSERT INTO `vipexchange` VALUES (38, 1, '984390', '2023-10-23 22:31:33', 0);
INSERT INTO `vipexchange` VALUES (39, 1, '984390', '2023-10-25 14:35:59', 0);

-- ----------------------------
-- Table structure for vipindent
-- ----------------------------
DROP TABLE IF EXISTS `vipindent`;
CREATE TABLE `vipindent`  (
  `vi_id` int(11) NOT NULL AUTO_INCREMENT,
  `vi_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `vi_point` int(11) NULL DEFAULT NULL,
  `vi_number` int(11) NULL DEFAULT NULL,
  `isdelete` int(11) NOT NULL,
  PRIMARY KEY (`vi_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of vipindent
-- ----------------------------
INSERT INTO `vipindent` VALUES (1, '香皂', 100, 998, 0);
INSERT INTO `vipindent` VALUES (2, '洗发水', 300, 400, 0);
INSERT INTO `vipindent` VALUES (3, '牙刷', 200, 500, 0);
INSERT INTO `vipindent` VALUES (4, '毛巾', 400, 300, 0);
INSERT INTO `vipindent` VALUES (5, '拖鞋', 500, 200, 0);
INSERT INTO `vipindent` VALUES (6, '雨伞', 600, 100, 0);
INSERT INTO `vipindent` VALUES (7, '书包', 800, 50, 1);
INSERT INTO `vipindent` VALUES (8, '手表', 1000, 20, 0);
INSERT INTO `vipindent` VALUES (9, '耳机', 1200, 10, 0);
INSERT INTO `vipindent` VALUES (10, '手机', 1500, 5, 0);
INSERT INTO `vipindent` VALUES (11, '1', 1, 1, 1);

-- ----------------------------
-- Table structure for viplevel
-- ----------------------------
DROP TABLE IF EXISTS `viplevel`;
CREATE TABLE `viplevel`  (
  `dj_id` int(11) NOT NULL COMMENT '等级id',
  `dj_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '等级名称',
  `dj_min` int(11) NULL DEFAULT NULL COMMENT '等级所需最小金额',
  `dj_max` int(11) NULL DEFAULT NULL COMMENT '等级所需最大金额',
  `dj_discount` decimal(11, 2) NULL DEFAULT NULL COMMENT '等级优惠折扣',
  `isdelete` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`dj_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of viplevel
-- ----------------------------
INSERT INTO `viplevel` VALUES (1, '普通会员', 1, 299, 0.95, 0);
INSERT INTO `viplevel` VALUES (2, '银牌会员', 300, 4999, 0.90, 0);
INSERT INTO `viplevel` VALUES (3, '金牌会员', 5000, 9999, 0.85, 0);
INSERT INTO `viplevel` VALUES (4, '钻石会员', 10000, 19999, 0.80, 0);
INSERT INTO `viplevel` VALUES (5, '至尊会员', 20000, NULL, 0.75, 0);

SET FOREIGN_KEY_CHECKS = 1;
