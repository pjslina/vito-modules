package com.vito.bank;

import com.vito.framework.log.operation.starter.annotation.EnableLogRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author panjin
 */
@SpringBootApplication
@Slf4j
@EnableLogRecord(tenant = "", joinTransaction = true)
public class BankApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankApplication.class, args);
    }
}
