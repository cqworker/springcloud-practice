package com.kw13.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;

/**
 * @Desc 实体和XML转化工具类
 * @Author yejx
 * @Date 2019/3/14
 */
public class XmlUtils {

    /**
     * 获取XmlMapper对象
     * @return XmlMapper对象
     */
    private static XmlMapper newXmlMapper(){
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.setDefaultUseWrapper(false);
        //字段为null，自动忽略，不再序列化
        xmlMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //XML标签名:使用骆驼命名的属性名，
        xmlMapper.setPropertyNamingStrategy(PropertyNamingStrategy.UPPER_CAMEL_CASE);
        //设置转换模式
        xmlMapper.enable(MapperFeature.USE_STD_BEAN_NAMING);
        return xmlMapper;
    }

    /**
     * 对象转Xml字符串
     * @param obj 对象
     * @return xml字符串
     * @throws JsonProcessingException 向上抛出异常
     */
    public static String getXmlForObject(Object obj) throws JsonProcessingException {
        XmlMapper xmlMapper = newXmlMapper();
        String xml = xmlMapper.writeValueAsString(obj);
        return xml.replaceAll("\"", "'");
    }

    /**
     * XML转对象
     * @param xml xml字符串
     * @param t 类型class
     * @param <T> 对象
     * @return 对象
     * @throws IOException 向上抛出异常
     */
    public static <T> T getObjectForXml(String xml, Class<T> t) throws IOException {
        XmlMapper xmlMapper = newXmlMapper();
        return (T) xmlMapper.readValue(xml, t);
    }
}
