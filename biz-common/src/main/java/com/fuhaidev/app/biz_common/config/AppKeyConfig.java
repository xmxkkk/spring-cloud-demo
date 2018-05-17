package com.fuhaidev.app.biz_common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xumingxun on 2018/5/14.
 */
@Configuration
@ConfigurationProperties
@Data
public class AppKeyConfig {
    Map<String,String> appKeys=new HashMap<String, String>();
}
