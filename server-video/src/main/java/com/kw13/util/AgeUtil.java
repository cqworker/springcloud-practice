package com.kw13.util;

import java.util.Date;

/**
 * Created by cheng on 2019/4/2.
 * 根据用户身份证获取年龄
 */
public class AgeUtil {

    public static int getAgeByIdCard(String idCard){
        Integer uyear = Integer.valueOf(idCard.substring(6, 10));
        Integer umon = Integer.valueOf(idCard.substring(10, 12));
        Integer uday = Integer.valueOf(idCard.substring(12, 14));
        String s = DateUtils.dateToString(new Date(), DateUtils.DAY_PATTERN);
        Integer nyear = Integer.valueOf(s.substring(0, 4));
        Integer nmon = Integer.valueOf(s.substring(4, 6));
        Integer nday = Integer.valueOf(s.substring(6, 8));
        int age = nyear - uyear;
        if(age>0){
            if ((nmon - umon) < 0) {
                age--;
            } else if((nmon - umon) == 0){
                if ((nday - uday) < 0) {
                    age--;
                }
            }
        }else if(age ==0){
            //出生大于30天age为0,小于30天为-1
            if((nmon-umon)<1){
                age--;
            }

        }
        return age;
    }
}
