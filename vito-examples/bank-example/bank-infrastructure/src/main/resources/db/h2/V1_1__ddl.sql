-- 没有经过验证，可能有错误
CREATE TABLE t_bank_account
(
    id                 BIGINT UNSIGNED  NOT NULL COMMENT '主键ID',
    account_number     VARCHAR(32)      NOT NULL COMMENT '账号',
    user_id            BIGINT UNSIGNED  NOT NULL COMMENT '用户id',
    available_amount   DECIMAL(10, 2)   NOT NULL DEFAULT 0 COMMENT '可用余额',
    daily_limit_amount DECIMAL(10, 2)   NOT NULL DEFAULT 0 COMMENT '日限额',
    currency           VARCHAR(20)      NOT NULL COMMENT '币种',
    is_deleted         TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除，非0-删除，0-不删除',
    create_by          BIGINT UNSIGNED  NOT NULL COMMENT '创建人',
    create_time        TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_by          BIGINT UNSIGNED  NOT NULL COMMENT '更新人',
    update_time        TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    primary key (`id`)
);
