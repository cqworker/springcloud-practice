package com.kw13.common.config.db;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by cheng on 2019/8/12.
 */
@Aspect
@Component
public class DynamicDataSourceAspect {

    /**
     * 根据方法指定了数据源,获取当次访问数据源信息
     * @param point
     */
    @Before("@annotation(ChangeDB)")
    public void beforeSwitchDS(JoinPoint point) {
        //获得当前访问的class
        Class<?> className = point.getTarget().getClass();
        //获得访问的方法名
        String methodName = point.getSignature().getName();
        //得到方法的参数的类型
        Class[] argClass = ((MethodSignature) point.getSignature()).getParameterTypes();
        String dataSource = DataSourceContextHolder.DEFAULT_DS;
        try {
            // 得到访问的方法对象
            Method method = className.getMethod(methodName, argClass);
            // 判断是否存在@ChangeDB注解
            if (method.isAnnotationPresent(ChangeDB.class)) {
                ChangeDB annotation = method.getAnnotation(ChangeDB.class);
                // 取出注解中的数据源名
                dataSource = annotation.value();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 切换数据源
        DataSourceContextHolder.setDB(dataSource);
    }

    /**
     * 方法执行完后,清空当次连接的数据源信息
     * @param point
     */
    @After("@annotation(ChangeDB)")
    public void afterSwitchDS(JoinPoint point) {
        DataSourceContextHolder.clearDB();
    }
}
