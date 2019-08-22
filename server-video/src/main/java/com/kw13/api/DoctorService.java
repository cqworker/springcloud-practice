package com.kw13.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 */
@FeignClient(value = "service-doctor")
public interface DoctorService {
    /**
     * 获取可用医生列表
     * @return
     */
    @RequestMapping(value = "/available",method = RequestMethod.GET)
    String getAvailableDoctors();
}

