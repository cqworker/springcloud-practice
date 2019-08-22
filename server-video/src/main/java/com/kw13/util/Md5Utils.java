package com.kw13.util;

import java.security.MessageDigest;

/**
 * @Desc Md5加密工具类
 * @Author yejx
 * @Date 2019/3/14
 */
public class Md5Utils {

    private static final String ALGORITHM_MD5 = "MD5";
    private static final String ALGORITHM_SHA256 = "SHA-256";
    private static final String ALGORITHM_SHA1 = "SHA1";
    private static final String ENCODE = "UTF-8";
    private static final char[] HEX_CHARS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a',
            'b', 'c', 'd', 'e', 'f'};

    /**
     * md5加密
     *
     * @param data 要加密的内容
     * @return 加密结果
     */
    static String md5(String data) throws Exception {
        return encrypt(data, ALGORITHM_MD5);
    }

    /**
     * SHA-256加密
     *
     * @param str 要加密的内容
     * @return 加密结果
     */
    public static String sha256(String str) throws Exception {
        return encrypt(str, ALGORITHM_SHA256);
    }

    /**
     * SHA1加密
     *
     * @param str 要加密的内容
     * @return 加密结果
     */
    public static String getSha1(String str) throws Exception {
        return encrypt(str, ALGORITHM_SHA1);
    }

    /**
     * 加密
     * @param str 加密内容
     * @param algo 加密算法
     * @return 加密结果
     * @throws Exception 向上抛出异常
     */
    private static String encrypt(String str, String algo) throws Exception {
        if (null == str || 0 == str.length()){
            return null;
        }
        MessageDigest mdTemp = MessageDigest.getInstance(algo);
        mdTemp.update(str.getBytes(ENCODE));

        byte[] md = mdTemp.digest();
        int j = md.length;
        char[] buf = new char[j * 2];
        int k = 0;
        for (byte byte0 : md) {
            buf[k++] = HEX_CHARS[byte0 >>> 4 & 0xf];
            buf[k++] = HEX_CHARS[byte0 & 0xf];
        }
        return new String(buf);
    }

}
