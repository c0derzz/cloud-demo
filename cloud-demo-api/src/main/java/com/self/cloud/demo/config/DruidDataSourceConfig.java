package com.self.cloud.demo.config;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: LiRuiChuan
 * @Date: 2019/11/16 16:58
 * @Description: DruidDataSource 监控配置
 */
@Configuration
//@Import(XXX.class)  //导入其他配置
//@ImportResource("classpath:dataSource.yaml")
public class DruidDataSourceConfig {
    /**
     * druid内置提供一个StatViewServlet用于展示druid的统计信息
     * StatViewServlet的用途有两个:
     * 		提供监控信息展示的html页面
     * 		提供监控信息的JSON API
     * 属性配置 :
     * 		1.urlMapping - 监控界面的访问路径（默认 /druid/*）
     * 		2.loginUsername - 监控界面登录用户名称
     * 		3.loginPassword - 监控界面登录用户密码
     * 		4.allow - 允许访问的主机地址，不设置则为运行所有主机访问
     * 		5.deny - 拒绝访问的主机地址
     * 		6.restEnable - 是否可一次性清楚所有监控数据
     * @return
     */
    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean reg = new ServletRegistrationBean();
        reg.setServlet(new StatViewServlet());
        reg.addUrlMappings("/druid/*");
        reg.addInitParameter("loginUsername", "root");
        reg.addInitParameter("loginPassword", "root");
        reg.addInitParameter("allow", "");
        reg.addInitParameter("deny", "");
        reg.addInitParameter("restEnable", "");
        return reg;
    }

    /**
     * druid连接池与web关联配置
     * 属性配置 :
     * 		1.urlPatter - 过滤的路径
     *		2.exclusions - 过滤的资源
     *		3.sessinStatMaxCount - session的最多个数
     *		4.profilEnable - 监控统计单个URL的SQL列表
     *		5.sessionStatEnable - session统计
     *		6.principalSessionName - session用户名称统计
     *		7.principalCookieName - cookie统计
     * @return
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        filterRegistrationBean.addInitParameter("profileEnable", "true");
        return filterRegistrationBean;
    }

    /**
     * 自定义statFilter配置
     * 属性配置 :
     * 		1.慢SQL语句（默认3000）
     * 		2.慢SQL语句展示
     * 		3.SQL参数化合并监控
     * @return
     */
    @Bean
    public StatFilter statFilter(){
        StatFilter statFilter = new StatFilter();
        statFilter.setSlowSqlMillis(1000);	// slowSqlMillis的缺省值为3000，也就是3秒。
        statFilter.setLogSlowSql(true); 	// slowSqlMillis用来配置SQL慢的标准，执行时间超过slowSqlMillis的就是慢。
        statFilter.setMergeSql(true); 		// SQL参数化合并配置
        return statFilter;
    }

    /**
     * druid防SQL注入配置
     * @return
     */
    @Bean
    public WallFilter wallFilter(){
        WallFilter wallFilter = new WallFilter();
        // 允许执行多条SQL
        WallConfig config = new WallConfig();
        config.setMultiStatementAllow(true);
        wallFilter.setConfig(config);
        return wallFilter;
    }

    @Bean
    @Primary
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource druidDataSource() {
        DruidDataSource datasource = new DruidDataSource();
        /*datasource.setUrl(url);
        datasource.setUsername(username);
        datasource.setPassword(password);
        datasource.setDriverClassName(driverClassName);*/

        datasource.setInitialSize(10);
        datasource.setMinIdle(10);
        datasource.setMaxActive(10);
        datasource.setMaxWait(60000);
        //datasource.setConnectionProperties(connectionProperties);
        // try {
        // datasource.setFilters(filters);
        //  } catch (SQLException e) {
        // log.error("druid configuration initialization filter", e);
        // }
        /**
         * 配置代理filters，可添加我们自定义的filter，且proxyFilters和filter不是替代关系，而是组合作用关系
         * 这一段可省略
         */
        List<Filter> proxyFilters=new ArrayList<>();
        proxyFilters.add(statFilter());
        proxyFilters.add(wallFilter());
        datasource.setProxyFilters(proxyFilters);
        return datasource;
    }
}
