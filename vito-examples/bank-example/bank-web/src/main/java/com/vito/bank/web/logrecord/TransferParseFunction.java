package com.vito.bank.web.logrecord;

import com.vito.framework.log.operation.service.IParseFunction;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * @author panjin
 */
@Slf4j
@Component
public class TransferParseFunction implements IParseFunction {

    @Override
    public String functionName() {
        return "TRANSFER";
    }

    @Override
    public String apply(Object value) {
        log.info("===========, value:{}", value);
        if (StringUtils.equals("200001", value.toString())) {
            return "张三";
        }
        return value.toString();
    }
}
