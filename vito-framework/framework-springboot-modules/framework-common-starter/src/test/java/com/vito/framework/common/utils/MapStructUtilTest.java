package com.vito.framework.common.utils;

import com.vito.framework.exception.BizException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MapStructUtilTest {

    private MapStructUtil mapStructUtil;

    @BeforeAll
    public void setUp() {
        mapStructUtil = new MapStructUtil();
    }

    @Test
    public void testLongToLdtOfZero() {
        Assertions.assertNull(mapStructUtil.longToLdt(0L));
    }

    @Test
    public void testLongToLdt() {
        Assertions.assertNotNull(mapStructUtil.longToLdt(System.currentTimeMillis()));
    }

    @Test
    public void testLdtToLongByMilli() {
        Assertions.assertNotNull(mapStructUtil.ldtToLongByMilli(LocalDateTime.now()));
    }

    @Test
    public void testLdtToLongBySecond() {
        Assertions.assertNotNull(mapStructUtil.ldtToLongBySecond(LocalDateTime.now()));
    }

    @Test
    public void testLongToStrBySecondOfPatternWithSecond() {
        String strBySecond = mapStructUtil.longToStrBySecond(System.currentTimeMillis() / 1000L, "yyyy-MM-dd HH:mm:ss");
        Assertions.assertTrue(isValidDateTime(strBySecond));
    }

    @Test
    public void testLongToStrBySecondOfPatternWithNanos() {
        String strBySecond = mapStructUtil.longToStrBySecond(System.currentTimeMillis() / 1000L, "yyyy-MM-dd HH:mm:ss.SSS");
        Assertions.assertTrue(isValidDateTime(strBySecond));
    }

    @Test
    public void testLongToStrBySecondOfPatternWithMinute() {
        String strBySecond = mapStructUtil.longToStrBySecond(System.currentTimeMillis() / 1000L, "yyyy-MM-dd HH:mm");
        Assertions.assertTrue(isValidDateTime(strBySecond));
    }

    @Test
    public void testLongToStrBySecondByNanos() {
        Assertions.assertThrowsExactly(BizException.class, () -> {
            mapStructUtil.longToStrBySecond(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss");
        });
    }
    @Test
    public void testLongToStrByMilli() {
        String strByMilli = mapStructUtil.longToStrByMilli(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss");
        Assertions.assertTrue(isValidDateTime(strByMilli));
    }

    @Test
    public void testLongToStrByMilliOfPatternWithSecond() {
        Assertions.assertThrowsExactly(BizException.class, () -> {
            mapStructUtil.longToStrByMilli(System.currentTimeMillis() / 1000L, "yyyy-MM-dd HH:mm:ss");
        });
    }

    private static boolean isValidDateTime(String dateTimeString) {
        DateFormat[] formats = {
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS"),
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"),
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"),
                new SimpleDateFormat("yyyy-MM-dd HH:mm"),
                new SimpleDateFormat("yyyy-MM-dd")
        };

        for (DateFormat format : formats) {
            try {
                format.parse(dateTimeString);
                return true;
            } catch (ParseException e) {
            }
        }

        return false;
    }
}
