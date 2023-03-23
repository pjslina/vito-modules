package com.vito.bank.infra.persistence;

import com.baomidou.mybatisplus.annotation.TableName;
import com.vito.framework.database.BaseDO;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author panjin
 */
@Data
@TableName(value = "t_bank_account")
public class AccountDO extends BaseDO {

    private Long id;
    private String accountNumber;
    private Long userId;
    private BigDecimal availableAmount;
    private BigDecimal dailyLimitAmount;
    private String currency;
}
