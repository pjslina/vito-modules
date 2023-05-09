package com.vito.example.grpc.user;

import com.vito.framework.jdbc.entity.LongIdAndSoftDelBaseDO;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author panjin
 */
@Data
@Entity
@Table(name = "t_bank_user")
public class UserDO extends LongIdAndSoftDelBaseDO {

    private static final long serialVersionUID = 4592721594364358831L;

    @Column(nullable = false, length = 63)
    private String userName;
    private String userPassword;
    private String idCard;
    private String phone;
    private String address;
}
