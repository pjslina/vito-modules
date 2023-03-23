package com.vito.bank.domain.types;

import lombok.Data;

/**
 * @author panjin
 */
@Data
public class AccountNumber {

    private String value;

    public AccountNumber(String value) {
        if (value == null || "".equals(value)){
            throw new IllegalArgumentException("账号不能为空");
        }
        this.value = value;
    }
}
