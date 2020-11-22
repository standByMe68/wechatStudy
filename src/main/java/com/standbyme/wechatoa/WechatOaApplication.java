package com.standbyme.wechatoa;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan("com.standbyme.*.dao")
public class WechatOaApplication {

    public static void main(String[] args) {
        SpringApplication.run(WechatOaApplication.class, args);
    }

}
