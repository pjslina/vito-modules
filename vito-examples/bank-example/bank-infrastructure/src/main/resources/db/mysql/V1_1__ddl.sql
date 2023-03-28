SET NAMES utf8mb4;
-- ----------------------------
-- Table structure for t_bank_user
-- ----------------------------
CREATE TABLE if NOT EXISTS t_bank_user  (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '唯一id',
    user_name VARCHAR(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '姓名',
    user_password VARCHAR(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
    id_card VARCHAR(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '身份证',
    phone VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '手机号码',
    address VARCHAR(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '住址',
    is_deleted TINYINT UNSIGNED NOT NULL COMMENT '是否有效（1=已删除， 0=未删除）',
    create_by BIGINT UNSIGNED NOT NULL COMMENT '创建者ID',
    update_by BIGINT UNSIGNED NULL DEFAULT NULL COMMENT '修改者ID',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '由数据库自动维护的记录创建时间',
    update_time DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '由数据库自动维护的记录更新时间',
    PRIMARY KEY (id) USING BTREE,
    UNIQUE INDEX uk_id_card (id_card(18))
    ) ENGINE = InnoDB AUTO_INCREMENT = 100000
    CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_general_ci COMMENT = '用户表'
    ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_bank_order
-- ----------------------------
CREATE TABLE if NOT EXISTS t_bank_order  (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '唯一id',
    user_id BIGINT UNSIGNED NOT NULL COMMENT '用户id',
    order_amount DECIMAL(10, 2) NOT NULL DEFAULT 0 COMMENT '订单金额',
    order_date   DATETIME       NOT NULL COMMENT '下单日期',
    is_deleted TINYINT UNSIGNED NOT NULL COMMENT '是否有效（1=已删除， 0=未删除）',
    create_by BIGINT UNSIGNED NOT NULL COMMENT '创建者ID',
    update_by BIGINT UNSIGNED NULL DEFAULT NULL COMMENT '修改者ID',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '由数据库自动维护的记录创建时间',
    update_time DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '由数据库自动维护的记录更新时间',
    PRIMARY KEY (id) USING BTREE
    ) ENGINE = InnoDB AUTO_INCREMENT = 100000
    CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_general_ci COMMENT = '订单表'
    ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_bank_order_product
-- ----------------------------
CREATE TABLE if NOT EXISTS t_bank_order_product  (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '唯一id',
    order_id BIGINT UNSIGNED NOT NULL COMMENT '订单id',
    product_id BIGINT UNSIGNED NOT NULL COMMENT '商品id',
    product_quantity INT UNSIGNED NOT NULL COMMENT '商品数量',
    product_price DECIMAL(10, 2) NOT NULL DEFAULT 0 COMMENT '商品价格',
    is_deleted TINYINT UNSIGNED NOT NULL COMMENT '是否有效（1=已删除， 0=未删除）',
    create_by BIGINT UNSIGNED NOT NULL COMMENT '创建者ID',
    update_by BIGINT UNSIGNED NULL DEFAULT NULL COMMENT '修改者ID',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '由数据库自动维护的记录创建时间',
    update_time DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '由数据库自动维护的记录更新时间',
    PRIMARY KEY (id) USING BTREE
    ) ENGINE = InnoDB AUTO_INCREMENT = 100000
    CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_general_ci COMMENT = '订单商品表'
    ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_bank_product
-- ----------------------------
CREATE TABLE if NOT EXISTS t_bank_product  (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '唯一id',
    product_name VARCHAR(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '商品名称',
    product_amount DECIMAL(10, 2) NOT NULL DEFAULT 0 COMMENT '商品价格',
    product_description VARCHAR(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '商品描述',
    is_deleted TINYINT UNSIGNED NOT NULL COMMENT '是否有效（1=已删除， 0=未删除）',
    create_by BIGINT UNSIGNED NOT NULL COMMENT '创建者ID',
    update_by BIGINT UNSIGNED NULL DEFAULT NULL COMMENT '修改者ID',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '由数据库自动维护的记录创建时间',
    update_time DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '由数据库自动维护的记录更新时间',
    PRIMARY KEY (id) USING BTREE,
    UNIQUE INDEX uk_product_name (product_name(32))
    ) ENGINE = InnoDB AUTO_INCREMENT = 100000
    CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_general_ci COMMENT = '商品表'
    ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_bank_account
-- ----------------------------
CREATE TABLE if NOT EXISTS t_bank_account  (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '唯一id',
    account_number VARCHAR(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '账号',
    user_id BIGINT UNSIGNED NOT NULL COMMENT '用户id',
    available_amount DECIMAL(10, 2) NOT NULL DEFAULT 0 COMMENT '可用余额',
    daily_limit_amount DECIMAL(10, 2) NOT NULL DEFAULT 0 COMMENT '日限额',
    currency VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '币种',
    is_deleted TINYINT UNSIGNED NOT NULL COMMENT '是否有效（1=已删除， 0=未删除）',
    create_by BIGINT UNSIGNED NOT NULL COMMENT '创建者ID',
    update_by BIGINT UNSIGNED NULL DEFAULT NULL COMMENT '修改者ID',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '由数据库自动维护的记录创建时间',
    update_time DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '由数据库自动维护的记录更新时间',
    PRIMARY KEY (id) USING BTREE,
    UNIQUE INDEX uk_account_number (account_number(32))
) ENGINE = InnoDB AUTO_INCREMENT = 100000
    CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_general_ci COMMENT = '账户表'
    ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_bank_form_params
-- ----------------------------
CREATE TABLE if NOT EXISTS t_bank_form_params  (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '唯一id',
    scene VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '场景，唯一',
    field_name VARCHAR(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字段名称,如"username"、"password"等',
    field_type VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字段类型,如"string"、"number"、"boolean"等',
    is_required TINYINT UNSIGNED NOT NULL COMMENT '是否必填（1=是， 0=否）',
    create_by BIGINT UNSIGNED NOT NULL COMMENT '创建者ID',
    update_by BIGINT UNSIGNED NULL DEFAULT NULL COMMENT '修改者ID',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '由数据库自动维护的记录创建时间',
    update_time DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '由数据库自动维护的记录更新时间',
    PRIMARY KEY (id) USING BTREE,
    UNIQUE INDEX uk_account_number (account_number(32))
    ) ENGINE = InnoDB AUTO_INCREMENT = 100000
    CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_general_ci COMMENT = '表单配置表'
    ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_bank_form_data
-- ----------------------------
CREATE TABLE if NOT EXISTS t_bank_form_data  (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '唯一id',
    biz_id BIGINT UNSIGNED NOT NULL COMMENT '业务id，唯一',
    form_id BIGINT UNSIGNED NOT NULL COMMENT '表单id',
    field_name VARCHAR(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字段名称,如"username"、"password"等',
    field_value VARCHAR(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字段值',
    field_type VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字段类型,如"string"、"number"、"boolean"等',
    extra_data VARCHAR(3000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '扩展字段，json格式',
    create_by BIGINT UNSIGNED NOT NULL COMMENT '创建者ID',
    update_by BIGINT UNSIGNED NULL DEFAULT NULL COMMENT '修改者ID',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '由数据库自动维护的记录创建时间',
    update_time DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '由数据库自动维护的记录更新时间',
    PRIMARY KEY (id) USING BTREE
    ) ENGINE = InnoDB AUTO_INCREMENT = 100000
    CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_general_ci COMMENT = '表单配置表'
    ROW_FORMAT = Dynamic;