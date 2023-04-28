package com.vito.example.grpc.user;

import com.vito.framework.dto.BaseDTO;
import lombok.Data;

/**
 * @author panjin
 */
@Data
public class UserCO extends BaseDTO {

    private Long id;
    private String userName;
    private String userPassword;
    private String idCard;
    private String phone;
    private String address;
    private Long createBy;
    private Long updateBy;
    private Long createTime;
    private Long updateTime;
}
