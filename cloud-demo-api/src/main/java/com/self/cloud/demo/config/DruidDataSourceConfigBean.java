package com.self.cloud.demo.config;

import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: LiRuiChuan
 * @Date: 2019/11/23 17:46
 * @Description: 数据库连接池配置信息
 *
 */
/*@Component
@ConfigurationProperties(prefix="spring.datasource")*/
@Data
public class DruidDataSourceConfigBean implements Serializable {
    /**
     * # 连接数据库的相关属性
     * spring.datasource.url =
     * spring.datasource.username =
     * spring.datasource.password =
     * # 非必填可根据 url 推断
     * spring.datasource.driver-class-name =
     *
     * # 初始化连接的数量
     * spring.datasource.initial-size =
     * # 数据库连接的最大数量
     * spring.datasource.max-active =
     * # 最小连接数
     * spring.datasource.min-idle =
     * # 获取连接的最大等待时间
     * spring.datasource.max-wait =
     * # 是否缓存预编译语句，对支持游标的数据库性能提升巨大
     * spring.datasource.pool-prepared-statements =
     * # 最大缓存预编译语句的数量大小，当大于 0 时，pool-prepared-statements 自动触发修改为 true
     * spring.datasource.max-pool-prepared-statement-per-connection-size =
     * # spring.datasource.max-open-prepared-statements = # 等价于上面的 max-pool-prepared-statement-per-connection-size
     * # 检测连接是否有效的 SQL 语句，一般为查询语句
     * spring.datasource.validation-query = select 1 from dual
     * # 检测连接是否有效语句执行超时
     * spring.datasource.validation-query-timeout =
     * # 获取连接时检测连接是否有效
     * spring.datasource.test-on-borrow =
     * # 返回连接时检测连接是否有效
     * spring.datasource.test-on-return =
     * # 对空闲连接进行检测，如果空闲时间大于 time-between-eviction-runs-millis 检测连接是否有效
     * spring.datasource.test-while-idle =
     * # 检测空闲连接是否有效的时间间隔
     * spring.datasource.time-between-eviction-runs-millis =
     * # 连接的最小生存时间
     * spring.datasource.min-evictable-idle-time-millis =
     * # 是否支持异步关闭连接
     * spring.datasource.async-close-connection-enable =
     * # 配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙
     * spring.datasource.filters = config,stat,wall,log4j
     * # 通过 connectProperties 属性来打开 mergeSql（sql参数化合并） 功能；慢 sql 记录以及密码加密实现
     * spring.datasource.connectionProperties = druid.stat.mergeSql = true;druid.stat.slowSqlMillis = 5000;config.decrypt = true;config.decrypt.key  =
     *
     */
    private String url;
    private String username;
    private String password;
    private String driverClassName;

    private Integer initialSize;
    private Integer minIdle;
    private Integer maxActive;
    private Integer maxWait;
    private Integer timeBetweenEvictionRunsMillis;
    private Integer minEvictableIdleTimeMillis;
    private String validationQuery;
    private Boolean testWhileIdle;
    private Boolean testOnBorrow;
    private Boolean testOnReturn;
    private Boolean poolPreparedStatements;
    private Integer maxPoolPreparedStatementPerConnectionSize;
    private String connectionProperties;
}
