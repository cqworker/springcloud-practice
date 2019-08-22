package com.kw13.util;

import org.apache.commons.lang.RandomStringUtils;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * Created by cheng on 2019/4/11.
 * 生成主键
 */
public class IDGen {

    private static final int NUM_LENGTH = 6;

    /**
     * 生成序列号，精确到毫秒的时间戳 + 6位随机数字 + suffix,注意：理论上任然不保证全局唯一
     *
     * @param suffix 后缀
     * @return 序列号
     */
    public static String genSerialNo(String suffix) {
        String timeStr = DateUtils.dateToString(new Date(), DateUtils.DEFAULT_FULL_COMPACT_PATTERN);
        return timeStr + RandomStringUtils.randomNumeric(NUM_LENGTH) + suffix;
    }

    /**
     * 随机生成n位字符串
     *
     * @return 随机字符串
     */
    public static String getRandomStr(int length) {
        String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public static String get32UUID() {
        String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
        return uuid;
    }
    /**
     * 生成卡号  600开头12位,结尾8,全数字,不要4,7
     * @param length
     * @return
     */
    public static String getCardNum(int length) {
        String end = "8";
        String base = "1235689";
        Random random = new Random();
        StringBuilder sb = new StringBuilder( "600");
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        String cardNum = sb.append(end).toString();
        return cardNum;
    }


}
