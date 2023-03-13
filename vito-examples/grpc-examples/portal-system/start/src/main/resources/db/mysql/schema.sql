
CREATE TABLE IF NOT EXISTS `tbl_user_layout`
(
    id            BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    layout_id     BIGINT UNSIGNED  NOT NULL COMMENT '布局ID',
    tenant_id     BIGINT UNSIGNED  NOT NULL COMMENT '租户ID',
    user_id       BIGINT UNSIGNED  NOT NULL COMMENT '用户ID',
    component_id  BIGINT UNSIGNED  NOT NULL COMMENT '组件ID',
    is_deleted    TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除，非0-删除，0-不删除',
    create_by     BIGINT UNSIGNED  NOT NULL COMMENT '创建人',
    create_time   DATETIME         NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_by     BIGINT UNSIGNED  NOT NULL COMMENT '更新人',
    update_time   DATETIME         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id)
)
engine = InnoDB
AUTO_INCREMENT=100000
CHARACTER SET = utf8mb4
COLLATE = utf8mb4_general_ci
ROW_FORMAT = Dynamic;

CREATE TABLE IF NOT EXISTS `tbl_layout`
(
    id            BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    layout_id     BIGINT UNSIGNED  NOT NULL COMMENT '布局ID',
    layout_name   VARCHAR(32)      NOT NULL COMMENT '布局名称',
    version       INT UNSIGNED     NOT NULL COMMENT '版本',
    is_deleted    TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除，非0-删除，0-不删除',
    create_by     BIGINT UNSIGNED  NOT NULL COMMENT '创建人',
    create_time   DATETIME         NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_by     BIGINT UNSIGNED  NOT NULL COMMENT '更新人',
    update_time   DATETIME         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    KEY `layout_id` (`layout_id`)
)
engine = InnoDB
AUTO_INCREMENT=100000
CHARACTER SET = utf8mb4
COLLATE = utf8mb4_general_ci
ROW_FORMAT = Dynamic;