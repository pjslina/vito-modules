package com.vito.bank.gateway.exchange;

import com.vito.bank.domain.types.Currency;
import com.vito.bank.domain.types.ExchangeRate;

/**
 * @author panjin
 */
public interface ExchangeRateGateway {

    /**
     * 获取汇率
     * @param source
     * @param target
     * @return
     */
    ExchangeRate getExchangeRate(Currency source, Currency target);
}
