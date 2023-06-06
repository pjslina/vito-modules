package com.vito.framework.log.operation.service.impl;

import com.vito.framework.log.operation.beans.LogRecord;
import com.vito.framework.log.operation.service.ILogRecordService;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author muzhantong
 * create on 2020/4/29 4:34 下午
 */
@Slf4j
public class DefaultLogRecordServiceImpl implements ILogRecordService {

    @Override
    public void saveLog(LogRecord logRecord) {
        log.info("【logRecord】log={}", logRecord);
    }

    @Override
    public List<LogRecord> queryLog(String bizNo, String type) {
        return new ArrayList<>();
    }

    @Override
    public List<LogRecord> queryLogByBizNo(String bizNo, String type, String subType) {
        return new ArrayList<>();
    }


}
