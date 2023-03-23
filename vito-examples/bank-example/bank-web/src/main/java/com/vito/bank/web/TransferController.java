package com.vito.bank.web;

import com.vito.bank.api.TransferService;
import com.vito.bank.dto.TransferCmd;
import com.vito.framework.dto.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author panjin
 */
@RestController
@RequestMapping("/transfer")
public class TransferController {

    @Autowired
    private TransferService transferService;

    @PostMapping
    public Response transfer(@RequestBody TransferCmd transferCmd) {
        return transferService.transfer(transferCmd);
    }
}
