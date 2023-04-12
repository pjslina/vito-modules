package com.vito.framework.log.service.impl;

import com.vito.framework.log.model.Audit;
import com.vito.framework.log.service.IAuditService;
import lombok.extern.slf4j.Slf4j;

import java.time.format.DateTimeFormatter;

/**
 * @author panjin
 */
@Slf4j
public class LoggerAuditServiceImpl implements IAuditService {

    private static final String MSG_PATTERN = "{}|{}|{}|{}|{}|{}|{}|{}";

    @Override
    public void save(Audit audit) {
        log.debug(MSG_PATTERN
                , audit.getTimestamp().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"))
                , audit.getApplicationName(), audit.getClassName(), audit.getMethodName()
                , audit.getUserId(), audit.getUserName(), audit.getTenantId()
                , audit.getOperation());
    }
}
