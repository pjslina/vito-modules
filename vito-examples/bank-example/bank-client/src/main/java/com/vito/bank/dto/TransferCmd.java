package com.vito.bank.dto;

import com.vito.framework.dto.Command;
import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author panjin
 */
@Data
public class TransferCmd extends Command {

    /** 转账用户ID */
    @NotNull(message = "user.id.null")
    private Long sourceUserId;
    /** 转入账户 */
    @NotBlank(message = "account.id.null")
    private String targetAccountNumber;
    /** 转账金额 */
    @Digits(integer = 10, fraction = 2, message = "transfer.amount")
    private BigDecimal targetAmount;
    /** 币种 */
    @NotBlank(message = "currency.null")
    private String targetCurrency;
}
