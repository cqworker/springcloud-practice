package com.kw13.common.scheduler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by cheng on 2019/8/12.
 */
@Component
public class SchedulerTask {
    public final Logger log = LogManager.getLogger(getClass());

    private int count = 0;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    //每六秒执行一次
    @Scheduled(fixedRate = 60000)
    public void reportCurrentTime() {
        log.info("现在时间：{} " , dateFormat.format(new Date()));
    }
    //凌晨1点
    @Scheduled(cron = "0 0 1 * * ?")
    private void process() {
        log.info("---------批量修改已失效卡片开始-----------");


        log.info("---------批量修改已失效卡片结束-----------");
    }
}
