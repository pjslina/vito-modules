package com.vito.example.grpc.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author panjin
 */
@Repository
public interface UserRepository extends JpaRepository<UserDO, Long> {

    /**
     * 分页查询大于指定Id的多少条数据
     * @param id
     * @param pageable
     * @return
     */
    Page<UserDO> findByIdGreaterThanOrderByIdAsc(Long id, Pageable pageable);
}
