package com.vito.bank.gateway;

import com.vito.bank.domain.entity.Account;
import com.vito.bank.domain.types.AccountId;
import com.vito.bank.domain.types.AccountNumber;
import com.vito.bank.domain.types.UserId;

/**
 * @author panjin
 */
public interface AccountGateway {

    /**
     * 查询
     * @param id
     * @return
     * @throws Exception
     */
    Account find(AccountId id);

    /**
     * 查询
     * @param accountNumber
     * @return
     * @throws Exception
     */
    Account find(AccountNumber accountNumber);

    /**
     * 查询
     * @param userId
     * @return
     * @throws Exception
     */
    Account find(UserId userId);

    /**
     * 查询
     * @param account
     * @return
     * @throws Exception
     */
    Account save(Account account);
}
