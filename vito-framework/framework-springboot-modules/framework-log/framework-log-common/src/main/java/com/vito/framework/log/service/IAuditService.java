package com.vito.framework.log.service;

import com.vito.framework.log.model.Audit;

/**
 * 审计日志接口
 * @author panjin
 */
public interface IAuditService {

    /**
     * 保存日志
     * @param audit
     */
    void save(Audit audit);
}
