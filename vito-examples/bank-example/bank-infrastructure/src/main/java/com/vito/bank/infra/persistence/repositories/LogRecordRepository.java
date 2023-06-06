package com.vito.bank.infra.persistence.repositories;

import com.vito.bank.infra.persistence.LogRecordDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author panjin
 */
@Repository
public interface LogRecordRepository extends JpaRepository<LogRecordDO, Long> {
}
