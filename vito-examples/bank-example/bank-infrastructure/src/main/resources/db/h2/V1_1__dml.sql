-- 没有经过验证，可能有错误
INSERT INTO `t_bank_account` (id,account_number,user_id,available_amount,daily_limit_amount,currency,is_deleted,create_by,update_by)
VALUES (100000, '123456789', 1, 1000, 10000, 'CNY', 0, 1, 1),
       (100001, '987654321', 2, 2000, 10000, 'CNY', 0, 2, 2);