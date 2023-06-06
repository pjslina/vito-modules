package com.vito.bank.infra.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vito.bank.infra.persistence.LogRecordDO;
import org.springframework.stereotype.Repository;

/**
 * @author panjin
 */
@Repository
public interface LogRecordMapper extends BaseMapper<LogRecordDO> {
}
