package com.self.cloud.demo.config;

import org.springframework.cloud.config.environment.Environment;
import org.springframework.cloud.config.server.environment.EnvironmentRepository;

/**
 * Created by liruichuan on 2018/7/5.
 */
public class DemoEnvironmentRepository implements EnvironmentRepository {
    @Override
    public Environment findOne(String config, String profile, String label) {
        return null;
    }
}
