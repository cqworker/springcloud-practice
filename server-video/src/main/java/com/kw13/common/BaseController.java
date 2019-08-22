package com.kw13.common;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cheng on 2019/8/12.
 */

@RestController
public class BaseController {
    public final Logger log = LogManager.getLogger(getClass());

    @GetMapping("/health/info")
    public String info() {
        log.info("service-doctor 已启动");

        return "OK,I am online!";
    }

    @RequestMapping("/info")
    public String home(@RequestParam(value = "name", defaultValue = "kw13") String name) {
        return "hi " + name + " ,i am from video" ;
    }
}
