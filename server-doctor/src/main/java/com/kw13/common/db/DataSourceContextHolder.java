package com.kw13.common.db;

/**
 * @Desc 加载config数据库配置
 * @Author cq
 * @Date 2019/8/12
 * Mapper扫描的目录 和依赖的容器
 */
public class DataSourceContextHolder {

    /**
     * 默认数据源
     */
    public static final String DEFAULT_DS = "datasource2";

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    // 设置数据源名
    public static void setDB(String dbType) {
        if(dbType==null){
            contextHolder.set(DEFAULT_DS);
        }else{
            contextHolder.set(dbType);
        }
        System.out.println("使用/切换到{" + dbType + "}数据源");
    }

    // 获取数据源名
    public static String getDB() {
        return (contextHolder.get());
    }

    // 清除数据源名
    public static void clearDB() {
        contextHolder.remove();
    }
}
