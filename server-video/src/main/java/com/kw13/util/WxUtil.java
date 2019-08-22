package com.kw13.util;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.encoders.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.Security;

/**
 * 微信工具类
 *
 */
public class WxUtil {

//    public static void main(String[] args) {
//        String result = decryptData(
//                "BmoplPvZP3oEROL0alr71Acic1UH4cTmyFE/M+8e0hShKgGAAfVH3sDz4CW8E0nlPMrNm2AGKp4qt1wPPdwSvV7chD7GNN59Qx0tIJOHOdAQLSyccz5gxz3M33oubWU0ImYix/EhM057jBLd6JRq+djBnDosfFAUUjp/u5KoRR6hVD4bsJ+GSaDbUH0Py3BccoND5TBqPxVJxjpkB4UHnDk9F5vW+Y8KOxXhaV+ni2sUsJAhYFQUmKC6AN3gxQaDOFYpTP9AHp7oXnB8xWlokHKJPshqHaTAMUq8XWxqWJsbl9ktGhT7WvaWWfNe1qx17LasFeOhcC0uKiyBJI9YPfqYNFSp+fdYEVsTcf/ncI7Up7vA+mOprulE6mLIVr82ejfennNmdVWMGgIsnXubMY45MppX5uE4wxuNusEYY64Au6bu7OdfAuquxB8BCrDMQ5MZrkxs8DMklsXunS7OH0wos7popNUBp01dVC7rRjwCFQLfBYNc1A6h7TT0UxRFNQdwIQCKs3mBPK/S9zRsrLMPkyIlc7GyntXS4iJlaW4=",
//                "2gCRTCyEpjQTW5Fc89yg==",
//                "8bCU2PC+0qCJVGcvWq+oyA=="
//        );
//        System.out.println("result = " + result);
//    }

    /**
     * 解密微信信息
     * @param encryptDataB64
     * @param sessionKeyB64
     * @param ivB64
     * @return
     */
    public static String decryptData(String encryptDataB64, String sessionKeyB64, String ivB64) {
        return new String(
                decryptOfDiyIV(
                        Base64.decode(encryptDataB64),
                        Base64.decode(sessionKeyB64),
                        Base64.decode(ivB64)
                )
        );
    }

    private static final String KEY_ALGORITHM = "AES";
    private static final String ALGORITHM_STR = "AES/CBC/PKCS7Padding";
    private static Key key;
    private static Cipher cipher;

    private static void init(byte[] keyBytes) {
        // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
        int base = 16;
        if (keyBytes.length % base != 0) {
            int groups = keyBytes.length / base + (keyBytes.length % base != 0 ? 1 : 0);
            byte[] temp = new byte[groups * base];
            Arrays.fill(temp, (byte) 0);
            System.arraycopy(keyBytes, 0, temp, 0, keyBytes.length);
            keyBytes = temp;
        }
        // 初始化
        Security.addProvider(new BouncyCastleProvider());
        // 转化成JAVA的密钥格式
        key = new SecretKeySpec(keyBytes, KEY_ALGORITHM);
        try {
            // 初始化cipher
            cipher = Cipher.getInstance(ALGORITHM_STR, "BC");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 解密方法
     *
     * @param encryptedData 要解密的字符串
     * @param keyBytes      解密密钥
     * @param ivs           自定义对称解密算法初始向量 iv
     * @return 解密后的字节数组
     */
    private static byte[] decryptOfDiyIV(byte[] encryptedData, byte[] keyBytes, byte[] ivs) {
        byte[] encryptedText = null;
        init(keyBytes);
        try {
            cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(ivs));
            encryptedText = cipher.doFinal(encryptedData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedText;
    }

}
