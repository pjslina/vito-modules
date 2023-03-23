package com.vito.bank.infra.configuration;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author panjin
 */
@Configuration
@MapperScan("com.vito.bank.infra.persistence.mapper")
public class MybatisPlusConfiguration {

//    @Bean
//    public MybatisPlusInterceptor mybatisPlusInterceptor() {
//        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
//        PaginationInnerInterceptor pageInterceptor = new PaginationInnerInterceptor();
//        pageInterceptor.setDbType(DbType.MYSQL);
//        interceptor.addInnerInterceptor(pageInterceptor);
//        return interceptor;
//    }
}
