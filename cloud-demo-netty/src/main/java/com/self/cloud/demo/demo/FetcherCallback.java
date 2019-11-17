package com.self.cloud.demo.demo;

/**
 * Created by liruichuan on 2018/9/7.
 */
public interface FetcherCallback {

    void onData(FetchData data);

    void onError(Throwable exception);
}
