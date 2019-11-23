package com.self.cloud.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by liruichuan on 2018/7/4.
 */
@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
@Data
public class PropertiesBean {

    private String username;
    private String password;
    private String maxActive;

}
