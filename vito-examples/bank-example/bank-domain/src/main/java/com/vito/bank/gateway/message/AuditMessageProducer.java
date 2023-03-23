package com.vito.bank.gateway.message;

import com.vito.bank.domain.types.AuditMessage;

/**
 * @author panjin
 */
public interface AuditMessageProducer {

    /**
     * 发送消息
     * @param message
     */
    void send(AuditMessage message);
}
