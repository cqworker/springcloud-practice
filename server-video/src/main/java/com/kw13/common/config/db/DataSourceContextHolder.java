package com.kw13.common.config.db;

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
    public static final String DEFAULT_DS = "datasource1";

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    // 设置数据源名
    public static void setDB(String dbType) {
        System.out.println("切换到{" + dbType + "}数据源");
        contextHolder.set(dbType);
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
