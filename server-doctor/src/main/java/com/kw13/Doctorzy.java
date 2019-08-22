package com.kw13;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RestController;

@EnableEurekaClient
@EnableDiscoveryClient
@RestController
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableScheduling
@MapperScan("com.kw13.*.mapper")
public class Doctorzy {

    public static void main(String[] args) {
        SpringApplication.run( Doctorzy.class, args );
    }

}



