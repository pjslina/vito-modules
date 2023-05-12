package com.vito.bank.infra.repository.impl;

import com.vito.bank.domain.entity.Account;
import com.vito.bank.domain.types.AccountNumber;
import com.vito.bank.domain.types.Money;
import com.vito.bank.domain.types.UserId;
import com.vito.bank.infra.TestApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest(classes = {TestApplication.class}, properties = {"orm.type=jpa"})
public class AccountRepositoryJpaImplTest {

    @Autowired
    AccountRepositoryJpaImpl accountRepositoryJpaImpl;

    @Test
    public void testSave() {
        Account account = new Account();
        account.setAccountNumber(new AccountNumber("1234567890"));
        account.setUserId(new UserId(1L));
        account.setDailyLimit(new Money(new BigDecimal(1000), new com.vito.bank.domain.types.Currency("CNY")));
        account.setAvailable(new Money(new BigDecimal(1000), new com.vito.bank.domain.types.Currency("CNY")));
        account.setCurrency(new com.vito.bank.domain.types.Currency("CNY"));
        Account save = accountRepositoryJpaImpl.save(account);
        Assertions.assertEquals(save.getAccountNumber().getValue(), "1234567890");
    }
}
