package com.vito.bank.infra.persistence.repositories;

import com.vito.bank.infra.persistence.AccountDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author panjin
 */
@Repository
public interface AccountRepository extends JpaRepository<AccountDO, Long> {
}
