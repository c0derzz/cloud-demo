package com.self.cloud.demo.demo;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by liruichuan on 2018/9/7.
 */
public class NettyDemo {

    public void doWork(){
        Fetcher fetcher = new MyFetcher(new FetchData("NettyDemo","netty"));
        fetcher.doFetchData(new FetcherCallback() {
            @Override
            public void onData(FetchData data) {
                System.out.println("data is："+ JSONObject.toJSONString(data));
            }

            @Override
            public void onError(Throwable exception) {
                System.err.println("error msg is :"+exception.getMessage());
            }
        });
    }

    public static void main(String[] args) {
        NettyDemo nettyDemo = new NettyDemo();
        nettyDemo.doWork();
    }
}
