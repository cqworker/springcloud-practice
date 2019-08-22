package com.kw13.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cheng on 2019/5/17.
 */
public class FileReadUtil<T> {


    public static Map<String,Object> readFileToObject(Class clazz, File f) throws IOException, IllegalAccessException, InstantiationException {
       Map<String,Object> result = new HashMap<>();
        int countline = 0;
        String str;
        List title = new ArrayList();
        List<Map> list = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(f));
        while ((str = br.readLine()) != null) {
            int count = 0;
            countline++;
            String[] split = str.split(",");
            if (countline == 1) {
                for (String content : split) {
                    title.add(content);
                }
            } else {
                Map<String,String> map = new HashMap<>();
                for (String content : split) {
                    String field = (String)title.get(count);
                    map.put(field,content);
                    count++;
                }
                list.add(map);
            }
        }
//        System.out.println("文件总行数: " + countline);
//        System.out.println("文件标题: " + title);
//        System.out.println("文件内容size: " + list.size());
//        System.out.println("文件内容: " + list);
        result.put("TITLE",title);
        result.put("LIST",list);
        return result;
    }

    /**
     * 根据java类(Object 有内容的属性)生成csv文件第一行
     */

    public static String ObjectToCsv(String filePath, Object object) {
        StringBuffer firstLine = new StringBuffer();
        //获取class
        Class<?> clazz = object.getClass();
        // 得到所有属性
        Field[] fields = clazz.getDeclaredFields();
        int count = 0;
        for (Field field : fields) {
            count++;
            field.setAccessible(true);
            Object fieldValue = null;
            try {
                //得到属性值
                fieldValue = field.get(object);
                //得到属性类型
                Type fieldType =field.getGenericType();
                // 得到属性名
                String fieldName = field.getName();
//                System.out.println("属性类型："+fieldType+",属性名："+fieldName+",属性值："+fieldValue);
                if(fieldValue!=null){
                        firstLine.append(fieldName+",");
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        //排除myatis-plus只带的字段
        String firstLineStr = firstLine.toString().substring(17,firstLine.length()-1);
        return firstLineStr;
    }

    /**
     * 根据java类生成json 用于postman构造post请求
     */

    public static String ObjectToJson(Class clazz) {
        StringBuffer jsonStr= new StringBuffer("{");
        // 得到所有属性
        Field[] fields = clazz.getDeclaredFields();
        int count = 0;
        for (Field field : fields) {
            count++;
            //获取属性类型
            Class<?> type = field.getType();
            String simpleName = type.getSimpleName();
            switch (simpleName) {
                case "int":
                    if(count==fields.length){
                        jsonStr.append("\""+field.getName()+"\":0");
                    }else{
                        jsonStr.append("\""+field.getName()+"\":0,");
                    }
                    break;
                case "String":
                    if(count==fields.length){
                        jsonStr.append("\"" + field.getName() + "\":\"str\"");
                    }else {
                        jsonStr.append("\"" + field.getName() + "\":\"str\",");
                    }
                    break;
                case "boolean":
                    if(count==fields.length){
                        jsonStr.append("\"" + field.getName() + "\":false");
                    }else {
                        jsonStr.append("\"" + field.getName() + "\":false,");
                    }break;
                default:
                    if(count==fields.length){
                        jsonStr.append("\"" + field.getName() + "\":\"str\"");
                    }else {
                        jsonStr.append("\"" + field.getName() + "\":\"str\",");
                    }
            }

        }
        jsonStr.append("}");
        return jsonStr.toString();
    }

}
