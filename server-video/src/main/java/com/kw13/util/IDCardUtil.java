package com.kw13.util;

/**
 * Created by cheng on 2019/4/11.
 * 用于校验身份证号
 */
public class IDCardUtil {

    public static char getValidateCode(String id17){
        int[] weight = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};    //十七位数字本体码权重
        char[] validate = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};    //mod11,对应校验码字符值
        int sum=0;
        int mode=0;
        for(int i=0;i<id17.length();i++){
            sum=sum+Integer.parseInt(String.valueOf(id17.charAt(i)))*weight[i];
        }
        mode=sum%11;
        return validate[mode];
    }
}
