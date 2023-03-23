package com.vito.bank.domain.types;

import lombok.Data;

/**
 * @author panjin
 */
@Data
public class Currency {

    private String value;

    public Currency(String value) {
        if (value == null || "".equals(value)) {
            throw new IllegalArgumentException("货币不能为空");
        }
        this.value = value;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "value='" + value + '\'' +
                '}';
    }
}
