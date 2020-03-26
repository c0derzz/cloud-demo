package com.self.cloud.demo.service;

import com.self.cloud.demo.annotation.APILimit;
import com.self.cloud.demo.annotation.APILimitType;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author: liruichuan
 * @Date: 2020/3/26 10:50
 * @Description: 限流切面处理
 */
@Component
@Aspect
public class APILimitAspect {

    @Autowired
    private RedisTemplate redisTemplate;

    private static final String UNKNOWN = "unknown";

    @Pointcut("@annotation(com.self.cloud.demo.annotation.APILimit)")
    public void pointcut(){

    }

    /**
     * 环绕处理
     * @param joinPoint
     * @return
     * @throws Exception
     */
    @Around(value = "pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        APILimit limit = methodSignature.getMethod().getAnnotation(APILimit.class);

        APILimitType apiLimitType = limit.type();

        //默认key 为方法名大写
        String key = methodSignature.getMethod().getName().toUpperCase();
        switch (apiLimitType){
            case IP:
                key = getIpAddr(request);
                break;
            case CUSTOMER:
                key = limit.key();
                break;
            default:
                break;
        }

        RedisScript<Number> script = new DefaultRedisScript<>(buildLuaScript(),Number.class);

        StringBuffer keyStr = new StringBuffer();
        keyStr.append(limit.prefix());
        keyStr.append("_");
        keyStr.append(key);
        keyStr.append("_");
        keyStr.append(request.getRequestedSessionId());

        List<String> params = new ArrayList<>();
        params.add(keyStr.toString());

        /**
         * 保存在redis中
         */
        Number count = (Number) redisTemplate.execute(script,params,limit.count(),limit.period());
        System.out.println(MessageFormat.format("第{0}次访问key为 {1}，描述为 [{2}] 的接口", count, keyStr.toString(), limit.name()));

        //如果没有超出限制 则直接执行并返回
        if(count != null && count.intValue() < limit.count()){
            return joinPoint.proceed();
        }else{
            throw new Exception("超出访问最大限制");
        }
    }

    /**
     * 限流脚本
     * 调用的时候不超过阈值，则直接返回并执行计算器自加。
     *
     * @return lua脚本
     */
    private String buildLuaScript() {
        return "local c" +
                "\nc = redis.call('get',KEYS[1])" +
                "\nif c and tonumber(c) > tonumber(ARGV[1]) then" +
                "\nreturn c;" +
                "\nend" +
                "\nc = redis.call('incr',KEYS[1])" +
                "\nif tonumber(c) == 1 then" +
                "\nredis.call('expire',KEYS[1],ARGV[2])" +
                "\nend" +
                "\nreturn c;";
    }

    /**
     * 获取IP地址
     * 使用 Nginx等反向代理软件， 则不能通过request.getRemoteAddr()获取IP地址
     * 如果使用了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP地址，X-Forwarded-For中第一个非 unknown的有效IP字符串，则为真实IP地址
     */
    private String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }
}
