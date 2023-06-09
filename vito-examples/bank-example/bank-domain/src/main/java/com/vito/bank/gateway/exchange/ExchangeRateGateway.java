package com.vito.bank.gateway.exchange;

import com.vito.bank.domain.types.Currency;
import com.vito.bank.domain.types.ExchangeRate;

import java.math.BigDecimal;

/**
 * @author panjin
 */
public interface ExchangeRateGateway {

    /**
     * 获取汇率
     * @param rage 汇率
     * @param source
     * @param target
     * @return
     */
    ExchangeRate getExchangeRate(BigDecimal rage, Currency source, Currency target);
}
