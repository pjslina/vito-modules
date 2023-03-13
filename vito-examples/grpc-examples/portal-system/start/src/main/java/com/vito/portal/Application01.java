package com.vito.portal;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StopWatch;

/**
 * @author panjin
 */
@SpringBootApplication
@Slf4j
@MapperScan("com.vito.portal.persistence")
public class Application01 {

    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        SpringApplication.run(Application01.class, args);
        stopWatch.stop();
        log.info("******************** spring boot application running, total time is {} seconds ******************** ", stopWatch.getTotalTimeSeconds());
    }
}
