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
import com.vito.bank.infra.persistence.mapper.AccountMapper;
import com.vito.framework.exception.Assert;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author panjin
 */
@Repository
@ConditionalOnProperty(prefix = "orm", name = "type", havingValue = Constants.ORM_TYPE_MYBATIS)
public class AccountRepositoryImpl implements AccountGateway {

    @Resource
    private AccountMapper accountMapper;

    @Resource
    private AccountBuilder accountBuilder;

    @Override
    public Account find(AccountId id) {
        AccountDO accountDO = accountMapper.selectById(id.getValue());
        return accountBuilder.toAccount(accountDO);
    }

    @Override
    public Account find(AccountNumber accountNumber) {
        AccountDO accountDO = accountMapper.selectOneByAccountNumber(accountNumber.getValue());
        Assert.notNull(accountDO, BankErrorCodeEnum.ACCOUNT_IS_NULL.setArgs(new String[] { accountNumber.getValue()}));
        return accountBuilder.toAccount(accountDO);
    }

    @Override
    public Account find(UserId userId) {
        AccountDO accountDO = accountMapper.selectOneByUserId(userId.getId());
        Assert.notNull(accountDO, BankErrorCodeEnum.ACCOUNT_IS_NULL_WITH_USER.setArgs(new Long[] { userId.getId()}));
        return accountBuilder.toAccount(accountDO);
    }

    @Override
    public Account save(Account account) {
        AccountDO accountDO = accountBuilder.fromAccount(account);
        if (accountDO.getId() == null) {
            accountMapper.insert(accountDO);
        } else {
            accountMapper.updateById(accountDO);
        }
        return accountBuilder.toAccount(accountDO);
    }
}
