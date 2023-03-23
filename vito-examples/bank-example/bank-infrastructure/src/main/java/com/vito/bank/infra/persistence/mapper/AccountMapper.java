package com.vito.bank.infra.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vito.bank.infra.persistence.AccountDO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author panjin
 */
@Repository
public interface AccountMapper extends BaseMapper<AccountDO> {

    /**
     * 根据账户查询
     * @param accountNumber
     * @return
     */
    @Select("<script>"
            + "select id,account_number,user_id,"
            + "available_amount,daily_limit_amount,currency "
            + "from t_bank_account "
            + "<where>"
            + "<if test=\"accountNumber != null and accountNumber != ''\">"
            + "account_number = #{accountNumber,jdbcType=VARCHAR}"
            + "</if>"
            + "</where>"
            + "</script>")
    AccountDO selectOneByAccountNumber(@Param("accountNumber") String accountNumber);

    /**
     * 根据用户Id查询账户
     * @param userId
     * @return
     */
    @Select("<script>"
            + "select id,account_number,user_id,"
            + "available_amount,daily_limit_amount,currency "
            + "from t_bank_account "
            + "<where>"
            + "<if test=\"userId != null\">"
            + "user_id = #{userId,jdbcType=NUMERIC}"
            + "</if>"
            + "</where>"
            + "</script>")
    AccountDO selectOneByUserId(@Param("userId") Long userId);
}
