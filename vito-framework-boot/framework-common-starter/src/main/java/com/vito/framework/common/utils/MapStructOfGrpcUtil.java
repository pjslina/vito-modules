package com.vito.framework.common.utils;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.LocalDateTimeUtil;
import com.google.protobuf.Timestamp;
import com.google.protobuf.util.Timestamps;
import com.vito.framework.exception.Assert;
import com.vito.framework.exception.SysErrorCodeEnum;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Named;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * @author panjin
 */
@Named("mapStructUtil")
public class MapStructOfGrpcUtil {

    /**
     * 将string类型时间转换成protobuf的timestamp类型
     * @param date 字符串格式的时间
     * @param datePattern 时间格式，如yyyy-MM-dd HH:mm:ss
     * @see cn.hutool.core.date.DatePattern
     * @return
     */
    @Named("strToProtoTs")
    public Timestamp strToProtoTs(String date, String datePattern) {
        if (StringUtils.isBlank(date)) {
            return null;
        }
        DateTimeFormatter formatter = DatePattern.createFormatter(datePattern);
        LocalDateTime dateTime = LocalDateTimeUtil.parse(date, formatter);
        Instant instant = dateTime.toInstant(ZoneOffset.UTC);
        return Timestamp.newBuilder()
                .setSeconds(instant.getEpochSecond())
                .setNanos(instant.getNano()).build();
    }

    @Named("protoTsToLdt")
    public LocalDateTime protoTsToLdt(Timestamp ts) {
        if (null == ts) {
            return null;
        }
        return Instant.ofEpochSecond(ts.getSeconds(), ts.getNanos())
                .atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    @Named("ldtToProtoTs")
    public Timestamp ldtToProtoTs(LocalDateTime ldt) {
        return Timestamps.fromMillis(LocalDateTimeUtil.toEpochMilli(ldt));
    }

    /**
     * 将时间戳转换成protobuf的Timestamp时间
     * @param time
     * @return
     */
    @Named("longToProtoTs")
    public Timestamp longToProtoTs(Long time) {
        if (null == time || 0L == time) {
            return null;
        }
        Assert.isTrue((long) (Math.log10(time) + 1) == CommonUtil.LENGTH_MILLI_SECOND_OF_LONG, SysErrorCodeEnum.TIME_FORMAT_ERROR);
        Instant instant = Instant.ofEpochMilli(time);
        return Timestamp.newBuilder()
                .setSeconds(instant.getEpochSecond())
                .setNanos(instant.getNano())
                .build();
    }

    /**
     * 将protobuf的Timestamp时间转换成Long类型
     * @param timestamp
     * @return
     */
    @Named("protoTsToLong")
    public Long protoTsToLong(Timestamp timestamp) {
        if (null == timestamp) {
            return null;
        }
        return timestamp.getSeconds() * 1000L + timestamp.getNanos() / 1000000L;
    }
}
