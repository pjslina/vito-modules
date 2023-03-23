package com.vito.bank.dto;

import com.vito.framework.dto.Command;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author panjin
 */
@Data
public class TransferCmd extends Command {

    /** 转账用户ID */
    private Long sourceUserId;
    /** 转入账户 */
    private String targetAccountNumber;
    /** 转账金额 */
    private BigDecimal targetAmount;
    /** 币种 */
    private String targetCurrency;
}
