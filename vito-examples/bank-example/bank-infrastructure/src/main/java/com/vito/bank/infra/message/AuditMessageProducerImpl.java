package com.vito.bank.infra.message;

import com.vito.bank.domain.types.AuditMessage;
import com.vito.bank.gateway.message.AuditMessageProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author panjin
 */
@Service
@Slf4j
public class AuditMessageProducerImpl implements AuditMessageProducer {

    @Override
    public void send(AuditMessage message) {
        log.info("AuditMessageProducer, message: {}", message);
    }
}
