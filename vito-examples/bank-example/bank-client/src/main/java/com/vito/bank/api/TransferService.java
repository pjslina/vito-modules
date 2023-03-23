package com.vito.bank.api;

import com.vito.bank.dto.TransferCmd;
import com.vito.framework.dto.Response;

/**
 * @author panjin
 */
public interface TransferService {

    /**
     * 转账
     * @param transferCmd
     * @return
     */
    Response transfer(TransferCmd transferCmd);
}
