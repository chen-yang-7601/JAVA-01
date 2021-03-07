
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` int(11) NOT NULL COMMENT 'category_id',
  `cate_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT 'cate_name',
  `creator` bigint(20) NULL DEFAULT NULL COMMENT 'creator',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT 'create_time',
  `updater` bigint(20) NULL DEFAULT NULL COMMENT 'updater',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT 'update_time',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'customer_id',
  `nickname` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT 'nickname',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT 'name',
  `cert_card` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'cert_card',
  `gender` tinyint(4) NOT NULL DEFAULT 0,
  `phone_number` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'phone_number',
  `reg_date` timestamp(0) NULL DEFAULT NULL COMMENT 'reg_date',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT 'update_time',
  `updater` bigint(20) NULL DEFAULT NULL COMMENT 'updater',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10001 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `id` int(11) NOT NULL COMMENT 'employee_id',
  `emp_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT 'employee_name',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 502 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'goods_id',
  `category_id` int(11) NOT NULL DEFAULT 0 COMMENT 'goods_category',
  `store_id` bigint(20) NOT NULL DEFAULT 0 COMMENT 'store_id',
  `goods_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT 'show_name',
  `price` double NOT NULL DEFAULT 0 COMMENT 'price',
  `discount` double NOT NULL DEFAULT 1 COMMENT 'discount',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT 'description',
  `status` tinyint(4) NOT NULL DEFAULT 1 COMMENT 'off_flag',
  `creator` bigint(20) NOT NULL DEFAULT 0 COMMENT 'creater',
  `create_time` timestamp(0) NOT NULL COMMENT 'create_time',
  `updater` bigint(20) NULL DEFAULT 0 COMMENT 'updater',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT 'update_time',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20001 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for order_details
-- ----------------------------
DROP TABLE IF EXISTS `order_details`;
CREATE TABLE `order_details`  (
  `id` bigint(20) NOT NULL,
  `order_id` bigint(20) NOT NULL,
  `seq` smallint(6) NOT NULL,
  `store_id` bigint(20) NULL DEFAULT NULL,
  `goods_id` bigint(20) NULL DEFAULT NULL,
  `goods_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'goods_name',
  `price` double NULL DEFAULT NULL,
  `payed_amount` double NULL DEFAULT NULL,
  `remark` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'remark',
  `customer_id` bigint(20) NOT NULL,
  `category_id` int(11) NULL DEFAULT NULL,
  `create_time` timestamp(0) NOT NULL,
  `updater` bigint(20) NULL DEFAULT NULL,
  `update_time` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4001000 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` bigint(20) NOT NULL COMMENT 'order_id',
  `customer_id` bigint(20) NOT NULL COMMENT 'customer_id',
  `customer_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'customer_name',
  `deliver_address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `total_price` double NULL DEFAULT 0 COMMENT 'total_price',
  `payed_amount` double NULL DEFAULT 0 COMMENT 'payed_amount',
  `discount` decimal(5, 2) NULL DEFAULT 1.00 COMMENT 'discount',
  `order_status` tinyint(4) NULL DEFAULT 1 COMMENT 'order_status',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT 'create_time',
  `pay_cust_id` bigint(20) NULL DEFAULT NULL COMMENT 'pay_cust_id',
  `pay_time` timestamp(0) NULL DEFAULT NULL COMMENT 'pay_time',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for simpleorders
-- ----------------------------
DROP TABLE IF EXISTS `simpleorders`;
CREATE TABLE `simpleorders`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `store_id` bigint(20) NULL DEFAULT NULL,
  `goods_id` bigint(20) NOT NULL,
  `goods_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'goods_name',
  `price` double NULL DEFAULT NULL,
  `payed_amount` double NULL DEFAULT NULL,
  `remark` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'remark',
  `customer_id` bigint(20) NOT NULL,
  `category_id` int(11) NULL DEFAULT NULL,
  `create_time` timestamp(0) NOT NULL,
  `updater` bigint(20) NULL DEFAULT NULL,
  `update_time` timestamp(0) NULL DEFAULT NULL,
  `status` tinyint(4) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10000001 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for store
-- ----------------------------
DROP TABLE IF EXISTS `store`;
CREATE TABLE `store`  (
  `id` bigint(20) NOT NULL COMMENT 'store_id',
  `store_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'store_name',
  `reg_date` timestamp(0) NULL DEFAULT NULL COMMENT 'reg_date',
  `current_rank` tinyint(4) NULL DEFAULT NULL COMMENT 'current_rank',
  `admin_id` bigint(20) NULL DEFAULT NULL COMMENT 'admin_id',
  `updater` bigint(20) NULL DEFAULT NULL COMMENT 'updater',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT 'update_time',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
