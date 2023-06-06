package com.vito.bank.infra.persistence;

import com.baomidou.mybatisplus.annotation.TableName;
import com.vito.bank.infra.common.BaseDO;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * @author panjin
 */
@Data
@TableName(value = "t_bank_log_record")
@Entity
@Table(name = "t_bank_log_record")
public class LogRecordDO extends BaseDO {
    /**
     * 新增TableGenerator注解是为了解决自增主键初始化值的问题，
     * 第一条数据id是100000
     */
    @Id
    @TableGenerator(name = "t_bank_seq", initialValue = 99999, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "t_bank_seq")
    private Long id;
    /**
     * 日志绑定的业务标识
     */
    private String bizNo;
    /**
     * 保存的操作日志的类型，比如：订单类型、商品类型
     */
    @NotBlank(message = "type required")
    private String type;
    /**
     * 日志的子类型，比如订单的C端日志，和订单的B端日志，type都是订单类型，但是子类型不一样
     */
    private String subType;
    /**
     * 操作人
     */
    private String operator;
    /**
     * 日志内容
     */
    private String action;
    /**
     * 租户
     */
    private String tenant;
    /**
     * 记录是否是操作失败的日志
     */
    private boolean fail;
    /**
     * 日志的额外信息
     */
    private String extra;
    /**
     * 代码变量信息
     */
    private String codeVariable;

}
