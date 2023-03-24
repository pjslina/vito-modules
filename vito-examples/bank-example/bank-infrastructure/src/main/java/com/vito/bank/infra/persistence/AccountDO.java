package com.vito.bank.infra.persistence;

import com.baomidou.mybatisplus.annotation.TableName;
import com.vito.bank.infra.common.BaseDO;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author panjin
 */
@Data
@TableName(value = "t_bank_account")
@Entity
@Table(name = "t_bank_account")
public class AccountDO extends BaseDO {

    /**
     * 新增TableGenerator注解是为了解决自增主键初始化值的问题，
     * 第一条数据id是100000
     */
    @Id
    @TableGenerator(name = "t_bank_seq", initialValue = 99999, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "t_bank_seq")
    private Long id;
    private String accountNumber;
    private Long userId;
    private BigDecimal availableAmount;
    private BigDecimal dailyLimitAmount;
    private String currency;
}
