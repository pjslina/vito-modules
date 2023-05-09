package com.vito.framework.redis.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Person {

    private String name;
    private Integer age;
    private String address;
    private LocalDateTime birthday;
}
