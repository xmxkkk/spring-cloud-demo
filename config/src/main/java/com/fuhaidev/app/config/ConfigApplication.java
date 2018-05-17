package com.fuhaidev.app.config;

import com.fuhaidev.app.biz_common.AbstractApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Created by xumingxun on 2018/5/4.
 */
@EnableConfigServer
public class ConfigApplication extends AbstractApplication{
    public static void main(String[] args) {
        SpringApplication.run(ConfigApplication.class,args);
    }
}
