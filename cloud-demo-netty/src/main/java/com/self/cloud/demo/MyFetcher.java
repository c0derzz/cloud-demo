package com.self.cloud.demo;

/**
 * Created by liruichuan on 2018/9/7.
 *
 * 回调处理 异步请求
 *
 */
public class MyFetcher implements Fetcher {

    final FetchData fetchData;

    public MyFetcher(FetchData fetchData) {
        this.fetchData = fetchData;
    }

    @Override
    public void doFetchData(FetcherCallback fetcherCallback) {
        try{
            fetcherCallback.onData(this.fetchData);
        }catch(Exception e){
            fetcherCallback.onError(e);
            System.out.println("error msg is:" + e.getMessage());
        }
    }
}
