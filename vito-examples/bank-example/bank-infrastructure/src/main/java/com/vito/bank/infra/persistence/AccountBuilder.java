package com.vito.bank.infra.persistence;

import com.vito.bank.domain.entity.Account;

/**
 * @author panjin
 */
public interface AccountBuilder {

    /**
     * DO对象转域对象
     * @param accountDO
     * @return
     */
    Account toAccount(AccountDO accountDO);

    /**
     * 域对象转DO对象
     * @param account
     * @return
     */
    AccountDO fromAccount(Account account);
}
