package com.self.cloud.demo.redis;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author: liruichuan
 * @Date: 2020/1/2 15:23
 * @Description: 自己实现的redis 客户端
 */
public class OwnRedisClient {

    private InputStream in;

    private OutputStream out;

    private final char cr = '\r';

    private final char lf = '\n';

    public OwnRedisClient(String host,int port) throws Exception{
        Socket socket = new Socket(host,port);
        in = socket.getInputStream();
        out = socket.getOutputStream();
    }

    /**
     * 写入方法
     * @param key
     * @param value
     * @return
     */
    public String set(String key,String value) throws Exception{
        StringBuffer sb = new StringBuffer();
        //设置命令的长度 *+长度+\r\n
        sb.append("*3").append(cr).append(lf);
        //命令的长度
        sb.append("$3").append(cr).append(lf);
        sb.append("set").append(cr).append(lf);
        //key 的长度
        sb.append("$"+key.length()).append(cr).append(lf);
        //key的值
        sb.append(key).append(cr).append(lf);
        //value 的长度
        sb.append("$"+value.length()).append(cr).append(lf);
        //value 的值
        sb.append(value).append(cr).append(lf);

        System.out.println("set 命令为："+sb.toString());

        out.write(sb.toString().getBytes());
        byte[] resp = new byte[1024];
        in.read(resp);
        return new String(resp);
    }
}
