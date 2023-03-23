package com.vito.bank.domain.types;

import lombok.Data;

/**
 * @author panjin
 */
@Data
public class AccountId {

    private Long value;

    public AccountId(Long value) {
        this.value = value;
    }
}



