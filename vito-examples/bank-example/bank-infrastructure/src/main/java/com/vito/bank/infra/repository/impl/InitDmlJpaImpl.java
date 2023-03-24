package com.vito.bank.infra.repository.impl;

import com.vito.bank.enums.CurrencyEnum;
import com.vito.bank.infra.common.Constants;
import com.vito.bank.infra.persistence.AccountDO;
import com.vito.bank.infra.persistence.repositories.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * dml语句通过代码方式初始化
 * @author panjin
 */
@Repository
@ConditionalOnProperty(prefix = "orm", name = "type", havingValue = Constants.ORM_TYPE_JPA)
@Slf4j
public class InitDmlJpaImpl {

    @Autowired
    private AccountRepository accountRepository;

    @PostConstruct
    public void init() {
        log.info("Initialized dml by jpa");
        initAccount(100000L, 200001L, "A300001");
        initAccount(100001L, 200002L, "A300002");
    }

    private void initAccount(Long id, Long userId, String accountName) {
        Optional<AccountDO> res = accountRepository.findById(id);
        AccountDO accountDO = new AccountDO();
        if(!res.isPresent()) {
            accountDO.setId(id);
            accountDO.setUserId(userId);
            accountDO.setAccountNumber(accountName);
            accountDO.setAvailableAmount(new BigDecimal("1000000.00"));
            accountDO.setDailyLimitAmount(new BigDecimal("100000.00"));
            accountDO.setCurrency(CurrencyEnum.CNY.name());
            accountDO.setCreateBy(0L);
            accountDO.setCreateTime(LocalDateTime.now());
            accountDO.setIsDeleted(0);
        } else {
            accountDO = res.get();
            accountDO.setDailyLimitAmount(new BigDecimal("100000.00"));
            accountDO.setUpdateTime(LocalDateTime.now());
        }
        accountRepository.save(accountDO);
    }
}
