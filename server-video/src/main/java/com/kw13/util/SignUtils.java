package com.kw13.util;

import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Desc 生成签名 工具类
 * @Author cq
 * @Date 2019/3/14
 */
public class SignUtils {

    /**
     * 生成签名
     * @param param 镀锡
     * @param key 签名key
     * @return 签名
     * @throws Exception 向上抛出异常
     */
    public static String encodeOrderSign(Object param, String key) throws Exception {
        StringBuilder sb = new StringBuilder();
        Map<String, String> map = getSortMap(param);
        for (Map.Entry<String, String> e : map.entrySet()) {
            if ("sign".equals(e.getKey()) || "signature".equals(e.getKey())) {
                continue;
            }
            sb.append(e.getKey()).append("=").append(e.getValue()).append("&");
        }
        // 拼接key
        sb.append("key=").append(key);
        // 因为package关键字 无法命名 在加密之前重新替换
        String sss = sb.toString().replaceAll("packageStr", "package");
        return Md5Utils.md5(sss).toUpperCase();
    }

    /**
     * 对象转按ASCII排序字符串
     * @param obj 对象
     * @return 结果
     * @throws IllegalAccessException 向上抛出异常
     */
    private static Map<String, String> getSortMap(Object obj) throws IllegalAccessException {
        Field[] fields = obj.getClass().getDeclaredFields();
        Map<String, String> map = new HashMap<>(12);
        for (Field field : fields) {
            field.setAccessible(true);
            String name = field.getName();
            Object value = field.get(obj);
            if(value != null) {
                map.put(name, value.toString());
            }
        }
        Map<String, String> sortMap = new TreeMap<>(
                Comparator.naturalOrder());
        sortMap.putAll(map);
        return sortMap;
    }
}
