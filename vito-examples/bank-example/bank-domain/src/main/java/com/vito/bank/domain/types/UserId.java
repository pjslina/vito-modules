package com.vito.bank.domain.types;

import lombok.Data;

/**
 * @author panjin
 */
@Data
public class UserId {

    private Long id;

    public UserId(Long id) {
        this.id = id;
    }
}
