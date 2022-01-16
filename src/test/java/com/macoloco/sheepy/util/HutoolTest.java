package com.macoloco.sheepy.util;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class HutoolTest {

    @Test
    public void testIdGen() {
        Snowflake snowflake = IdUtil.getSnowflake(1, 1);
        Set<Long> idSet = new HashSet<>();
        int size = 100;
        for (int i = 0; i < size; i++) {
            long nextId = snowflake.nextId();
            idSet.add(nextId);
            if (i < 5) {
                log.info("雪花算法测试 序号:{} ID:{}", i, nextId);
            }
        }
        log.info("雪花算法ID生成测试 生成数量 ：{} 成功数量 ：{}", size, idSet.size());
    }

    @Test
    public void testRsaGen() {
        RSA rsa = new RSA();
        //获得私钥
        PrivateKey privateKey = rsa.getPrivateKey();
        String privateKeyBase64 = rsa.getPrivateKeyBase64();
        //获得公钥
        PublicKey publicKey = rsa.getPublicKey();
        String publicKeyBase64 = rsa.getPublicKeyBase64();
        log.info(privateKeyBase64);
        log.info(publicKeyBase64);
    }

    @Test
    public void testEncryptPassword() throws IOException {
        String publicKeyString = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDuOhlVLGjZlM5ZZPW2vuKx4QmwvQHhk+PEYxb29PcZerCmkgY2/xuJGFgBi6Mnr30PWJvYAoiBMgPo6KaZOo6WxrpzozQR+ra3z6kASAgXcM+i15NcpWW47xX52c9SnkQySDQ0vksSWAUhlpgUEd3idEXW2MYtn7tDlDvdYZ1c6wIDAQAB";
        String privateKeyString = "";
        String testPassword = "admin@123";
        File privateKey = new File("src/main/resources/assets/keypair/id_rsa");
        try (FileInputStream keyInputStream = new FileInputStream(privateKey)) {
            StringBuilder keyStringBuilder = new StringBuilder();
            byte[] bytes = new byte[1024];
            int length;
            while ((length = keyInputStream.read(bytes)) != -1) {
                keyStringBuilder.append(new String(bytes, 0, length));
            }
            log.info("读取私钥成功 ：{}", keyStringBuilder.toString());
            privateKeyString = keyStringBuilder.toString();
        }
        RSA rsa = new RSA(privateKeyString, publicKeyString);
        byte[] encrypt = rsa.encrypt(testPassword, KeyType.PublicKey);
        byte[] decrypt = rsa.decrypt(encrypt, KeyType.PrivateKey);
        log.info("解密成功 密码：{}", new String(decrypt, "UTF-8"));
    }

}