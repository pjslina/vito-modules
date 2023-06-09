package com.vito.framework.log.operation.service;

import org.springframework.util.StopWatch;

/**
 * @author panjin
 */
public interface ILogRecordPerformanceMonitor {

     /**
      * 打印日志
      * @param stopWatch 计时器
      */
     void print(StopWatch stopWatch);

     String MONITOR_NAME = "log-record-performance";
     String MONITOR_TASK_BEFORE_EXECUTE = "before-execute";
     String MONITOR_TASK_AFTER_EXECUTE = "after-execute";
}
