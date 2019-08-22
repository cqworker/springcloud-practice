package com.kw13;

import org.mybatis.spring.annotation.MapperScan;
        import org.springframework.boot.SpringApplication;
        import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableScheduling
@MapperScan("com.kw13.*.mapper")
public class VideozyApplication {
    public static void main(String[] args) {
        SpringApplication.run(VideozyApplication.class, args);
    }
}
