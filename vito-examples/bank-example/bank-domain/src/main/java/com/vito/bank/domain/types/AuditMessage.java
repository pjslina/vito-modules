package com.vito.bank.domain.types;

import com.vito.bank.domain.entity.Account;
import lombok.Data;

/**
 * @author panjin
 */
@Data
public class AuditMessage {

    private Account sourceAccount;
    private Account targetAccount;
    private Money targetMoney;

    public AuditMessage(Account sourceAccount, Account targetAccount, Money targetMoney) {
        this.sourceAccount = sourceAccount;
        this.targetAccount = targetAccount;
        this.targetMoney = targetMoney;
    }
}
