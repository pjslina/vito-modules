package com.vito.bank.web;

import com.vito.bank.api.TransferService;
import com.vito.bank.dto.TransferCmd;
import com.vito.framework.dto.Response;
import com.vito.framework.log.annotation.AuditLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author panjin
 */
@RestController
@RequestMapping("/transfer")
public class TransferController {

    @Autowired
    private TransferService transferService;

    @PostMapping
    @AuditLog(operation = "'用户转账:' + #transferCmd")
    public Response transfer(@Valid @RequestBody TransferCmd transferCmd) {
        return transferService.transfer(transferCmd);
    }
}
