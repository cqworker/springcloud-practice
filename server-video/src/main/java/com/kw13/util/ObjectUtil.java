package com.kw13.util;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

/**
 * Created by cheng on 2019/5/16.
 */
public class ObjectUtil {

    public static boolean isNull(Object object) {
        // 得到类对象
        Class clazz = (Class) object.getClass();
        // 得到所有属性
        Field fields[] = clazz.getDeclaredFields();
        //定义返回结果，默认为true
        boolean flag = true;
        for (Field field : fields) {
            field.setAccessible(true);
            Object fieldValue = null;
            String fieldName = null;
            try {
                //得到属性值
                fieldValue = field.get(object);
                //得到属性类型
                Type fieldType = field.getGenericType();
                // 得到属性名
                fieldName = field.getName();
//                System.out.println("属性类型："+fieldType+",属性名："+fieldName+",属性值："+fieldValue);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
            //只要有一个属性值不为null 就返回false 表示对象不为null
            if (fieldValue != null && !"serialVersionUID".equals(fieldName) && !"".equals(String.valueOf(fieldValue).trim())) {
                flag = false;
                break;
            }
        }
        return flag;
    }
}
