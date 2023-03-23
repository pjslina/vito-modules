SET NAMES utf8mb4;

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
