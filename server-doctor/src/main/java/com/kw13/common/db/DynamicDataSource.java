package com.kw13.common.db;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by cheng on 2019/8/12.
 */

public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        String db = DataSourceContextHolder.getDB();
        if(db==null){
            db = DataSourceContextHolder.DEFAULT_DS;
            System.out.println("默认数据源为" + db);
        }else{
            System.out.println("数据源为" + db);
        }
        return db;

    }
}