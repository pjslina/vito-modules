package com.vito.bank.infra.repository.impl;

import com.vito.bank.common.enums.BankErrorCodeEnum;
import com.vito.bank.domain.entity.Account;
import com.vito.bank.domain.types.AccountId;
import com.vito.bank.domain.types.AccountNumber;
import com.vito.bank.domain.types.UserId;
import com.vito.bank.gateway.AccountGateway;
import com.vito.bank.infra.common.Constants;
import com.vito.bank.infra.persistence.AccountBuilder;
import com.vito.bank.infra.persistence.AccountDO;
import com.vito.bank.infra.persistence.repositories.AccountRepository;
import com.vito.framework.exception.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author panjin
 */
@Repository
@ConditionalOnProperty(prefix = "orm", name = "type", havingValue = Constants.ORM_TYPE_JPA)
public class AccountRepositoryJpaImpl implements AccountGateway {

    @Autowired
    private AccountRepository accountRepository;
    @Resource
    private AccountBuilder accountBuilder;

    @Override
    public Account find(AccountId id) {
        Optional<AccountDO> accountDO = accountRepository.findById(id.getValue());
        Assert.isTrue(accountDO.isPresent(), BankErrorCodeEnum.ACCOUNT_IS_NULL.setArgs(new Long[] { id.getValue()}));
        return accountBuilder.toAccount(accountDO.get());
    }

    @Override
    public Account find(AccountNumber accountNumber) {
        AccountDO accountDO = new AccountDO();
        accountDO.setAccountNumber(accountNumber.getValue());
        Example<AccountDO> example = Example.of(accountDO);
        Optional<AccountDO> accountDB = accountRepository.findOne(example);
        Assert.isTrue(accountDB.isPresent(), BankErrorCodeEnum.ACCOUNT_IS_NULL.setArgs(new String[] { accountNumber.getValue()}));
        return accountBuilder.toAccount(accountDB.get());
    }

    @Override
    public Account find(UserId userId) {
        AccountDO accountDO = new AccountDO();
        accountDO.setUserId(userId.getId());
        Example<AccountDO> example = Example.of(accountDO);
        Optional<AccountDO> account = accountRepository.findOne(example);
        Assert.isTrue(account.isPresent(), BankErrorCodeEnum.ACCOUNT_IS_NULL_WITH_USER.setArgs(new Long[] { userId.getId()}));
        return accountBuilder.toAccount(account.get());
    }

    @Override
    public Account save(Account account) {
        AccountDO accountDO = accountBuilder.fromAccount(account);
        accountRepository.save(accountDO);
        return accountBuilder.toAccount(accountDO);
    }
}
