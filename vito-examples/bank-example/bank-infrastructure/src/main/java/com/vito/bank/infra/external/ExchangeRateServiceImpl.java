package com.vito.bank.infra.external;

import com.vito.bank.domain.types.Currency;
import com.vito.bank.domain.types.ExchangeRate;
import com.vito.bank.gateway.exchange.ExchangeRateGateway;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author panjin
 */
@Service
public class ExchangeRateServiceImpl implements ExchangeRateGateway {

    @Override
    public ExchangeRate getExchangeRate(BigDecimal rage, Currency source, Currency target) {
        return new ExchangeRate(rage, source, target);
    }
}
