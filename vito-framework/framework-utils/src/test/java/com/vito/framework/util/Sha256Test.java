package com.vito.framework.util;

import cn.hutool.core.lang.Assert;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.Sign;
import cn.hutool.crypto.asymmetric.SignAlgorithm;
import cn.hutool.crypto.digest.DigestUtil;
import org.junit.Test;

public class Sha256Test {

    @Test
    public void testSha01() {
        String sha256Hex = DigestUtil.sha256Hex("我是一段测试字符串");
        System.out.println(sha256Hex);
    }

    @Test
    public void testSign01() {
        byte[] data = "我是一段测试字符串".getBytes();
        Sign sign = SecureUtil.sign(SignAlgorithm.SHA256withRSA);
        // 签名
        byte[] signed = sign.sign(data);
        // 验签
        boolean verify = sign.verify(data, signed);
        Assert.isTrue(verify);
    }
}
