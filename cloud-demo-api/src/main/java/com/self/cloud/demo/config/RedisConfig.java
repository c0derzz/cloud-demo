package com.self.cloud.demo.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.ObjectUtils;

import java.time.Duration;
import java.time.temporal.TemporalUnit;

/**
 * @Auther: LiRuiChuan
 * @Date: 2019/11/16 15:05
 * @Description: redis  配置
 */
@Configuration
public class RedisConfig{


    @Bean
    public RedisTemplate redisTemplate(
            @Value("${spring.redis.database}") int database,
            @Value("${spring.redis.timeout}") long timeout,
            @Value("${spring.redis.jedis.pool.max-active}") int maxActive,
            @Value("${spring.redis.jedis.pool.max-wait}") long maxWait,
            @Value("${spring.redis.jedis.pool.max-idle}") int maxIdle,
            @Value("${spring.redis.jedis.pool.min-idle}") int minIdle,
            @Value("${spring.redis.host}") String hostName,
            @Value("${spring.redis.port}") int port){

        /** ========= 基本配置 ========= */
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName(hostName);
        configuration.setPort(port);
        configuration.setDatabase(database);
        /*if (!ObjectUtils.isEmpty(password)) {
            RedisPassword redisPassword = RedisPassword.of(password);
            configuration.setPassword(redisPassword);
        }*/

        /** ========= 连接池通用配置 ========= */
        GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
        genericObjectPoolConfig.setMaxTotal(maxActive);
        genericObjectPoolConfig.setMinIdle(minIdle);
        genericObjectPoolConfig.setMaxIdle(maxIdle);
        genericObjectPoolConfig.setMaxWaitMillis(maxWait);

        /** ========= jedis pool ========= */
        JedisClientConfiguration.DefaultJedisClientConfigurationBuilder builder = (JedisClientConfiguration.DefaultJedisClientConfigurationBuilder) JedisClientConfiguration
                .builder();
        builder.connectTimeout(Duration.ofMillis(timeout));
        builder.usePooling();
        builder.poolConfig(genericObjectPoolConfig);
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory(configuration, builder.build());
        // 连接池初始化
        connectionFactory.afterPropertiesSet();
        /** ========= 创建 template ========= */
        return createRedisTemplate(connectionFactory);
    }

    /**
     * json 实现 redisTemplate
     * <p>
     * 该方法不能加 @Bean 否则connectionFactory会是默认配置
     *
     * @param redisConnectionFactory
     * @return
     */
    public RedisTemplate createRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.activateDefaultTyping(BasicPolymorphicTypeValidator.builder().build());
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

}
