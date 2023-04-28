package com.vito.framework.common.utils;

import cn.hutool.core.date.LocalDateTimeUtil;
import org.mapstruct.Named;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @author panjin
 */
@Named("mapStructUtil")
public class MapStructUtil {

    /**
     * 将long类型时间转换成LocalDateTime
     * @param time
     * @return
     */
    @Named("longToLdt")
    public LocalDateTime longToLdt(Long time) {
        if (null == time || 0L == time) {
            return null;
        }
        return LocalDateTimeUtil.of(time);
    }

    /**
     * 将时间转换成protobuf的long类型，毫秒级（13位长度）
     * @param dateTime 时间
     * @return
     */
    @Named("ldtToLongByMilli")
    public Long ldtToLongByMilli(LocalDateTime dateTime) {
        if (null == dateTime) {
            return null;
        }
        long epochMilli = LocalDateTimeUtil.toEpochMilli(dateTime);
        return epochMilli;
    }

    /**
     * 将时间转换成long类型，秒级（10位长度）
     * @param dateTime 时间 UTC格式
     * @return
     */
    @Named("ldtToLongBySecond")
    public Long ldtToLongBySecond(LocalDateTime dateTime) {
        if (null == dateTime) {
            return null;
        }
        Instant instant = dateTime.toInstant(ZoneOffset.UTC);
        return instant.getEpochSecond();
    }

    /**
     * 将秒级的long类型的时间转换为字符串
     * @param time long类型时间，秒级（长度10位）
     * @param datePattern 时间格式
     * @see cn.hutool.core.date.DatePattern
     * @return
     */
    @Named("longToStrBySecond")
    public String longToStrBySecond(Long time, String datePattern) {
        if (null == time || 0L == time) {
            return null;
        }
        LocalDateTime localDateTime = LocalDateTimeUtil.of(time * 1000L);
        return LocalDateTimeUtil.format(localDateTime, datePattern);
    }

    /**
     * 将毫秒级的long类型的时间转换为字符串
     * @param time long类型时间，毫秒级（长度13位）
     * @param datePattern
     * @see cn.hutool.core.date.DatePattern
     * @return
     */
    @Named("longToStrByMilli")
    public String longToStrByMilli(Long time, String datePattern) {
        if (null == time || 0L == time) {
            return null;
        }
        LocalDateTime localDateTime = LocalDateTimeUtil.of(time);
        return LocalDateTimeUtil.format(localDateTime, datePattern);
    }
}
