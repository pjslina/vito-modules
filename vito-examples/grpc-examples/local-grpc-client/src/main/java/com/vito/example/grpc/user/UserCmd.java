package com.vito.example.grpc.user;

import com.vito.example.grpc.common.CommonCommand;
import lombok.Data;

/**
 * @author panjin
 */
@Data
public class UserCmd extends CommonCommand {

    private Long id;
    private String userName;
    private String userPassword;
    private String idCard;
    private String phone;
    private String address;
    private Integer isDeleted;
}
