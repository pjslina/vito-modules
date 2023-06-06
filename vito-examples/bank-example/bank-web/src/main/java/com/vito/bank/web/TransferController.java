package com.vito.bank.web;

import com.vito.bank.api.TransferService;
import com.vito.bank.dto.TransferCmd;
import com.vito.framework.dto.Response;
import com.vito.framework.log.annotation.AuditLog;
import com.vito.framework.log.operation.starter.annotation.LogRecord;
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
    @LogRecord(success = "{TRANSFER{#transferCmd.sourceUserId}}转账成功, 转入账户:{{#transferCmd.targetAccountNumber}}, 转账金额:{{#transferCmd.targetAmount}}, 币种:{{#transferCmd.targetCurrency}}",
            fail = "转账失败，失败原因{{#_errorMsg}}",
            type = "transfer", subType = "MANAGER_VIEW",
            bizNo = "#transferCmd.fromAccount")
    @LogRecord(success = "{TRANSFER{#transferCmd.sourceUserId}}转账成功, 转入的账户:{{#transferCmd.targetAccountNumber}}, 金额:{{#transferCmd.targetAmount}}, 币种:{{#transferCmd.targetCurrency}}",
            fail = "转账失败，失败原因{{#_errorMsg}}",
            type = "transfer", subType = "USER_VIEW",
            bizNo = "#transferCmd.fromAccount")
    public Response transfer(@Valid @RequestBody TransferCmd transferCmd) {
        return transferService.transfer(transferCmd);
    }

    @PostMapping("/fallback")
    public Response transferFallback(@Valid @RequestBody TransferCmd transferCmd) {
        return Response.buildFailure("转账失败", "转账失败");
    }
}
