package com.vito.bank.web.logrecord;

import cn.hutool.json.JSONUtil;
import com.vito.bank.infra.persistence.LogRecordDO;
import com.vito.bank.infra.persistence.repositories.LogRecordRepository;
import com.vito.framework.log.operation.beans.LogRecord;
import com.vito.framework.log.operation.service.ILogRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author panjin
 */
@Primary
@Service
@Slf4j
public class DbLogRecordServiceImpl implements ILogRecordService {

    @Autowired
    private LogRecordRepository logRecordRepository;

    @Override
    public void saveLog(LogRecord logRecord) {
        log.info("record for db:{}", logRecord);
        LogRecordDO logRecordDO = new LogRecordDO();
        BeanUtils.copyProperties(logRecord, logRecordDO);
        logRecordDO.setCodeVariable(JSONUtil.toJsonStr(logRecord.getCodeVariable()));
        logRecordRepository.save(logRecordDO);
    }

    @Override
    public List<LogRecord> queryLog(String bizNo, String type) {
        LogRecordDO logRecordDO = new LogRecordDO();
        logRecordDO.setBizNo(bizNo);
        logRecordDO.setType(type);
        Example<LogRecordDO> example = Example.of(logRecordDO);
        List<LogRecordDO> all = logRecordRepository.findAll(example);
        List<LogRecord> result = new ArrayList<>();
        all.forEach(logRecordDO1 -> {
            LogRecord logRecord = new LogRecord();
            BeanUtils.copyProperties(logRecordDO1, logRecord);
            logRecord.setCodeVariable(JSONUtil.toBean(logRecordDO1.getCodeVariable(), Map.class));
            result.add(logRecord);
        });
        return result;
    }

    @Override
    public List<LogRecord> queryLogByBizNo(String bizNo, String type, String subType) {
        return null;
    }
}
