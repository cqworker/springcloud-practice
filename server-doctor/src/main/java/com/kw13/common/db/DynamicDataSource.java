package com.kw13.common.db;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by cheng on 2019/8/12.
 */

public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        System.out.println("数据源为" + DataSourceContextHolder.getDB());
        return DataSourceContextHolder.getDB();

    }
}