package com.zt.javastudy.async;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import java.io.IOException;

/**
 * 异步监听器
 *
 * @author zhengtao on 2021/9/23
 */
@Slf4j
public class HttpAsyncListener implements AsyncListener {
    @Override
    public void onComplete(AsyncEvent event) throws IOException {
        log.info("http异步请求完成");
    }

    @Override
    public void onTimeout(AsyncEvent event) throws IOException {
        log.info("http请求超时");
    }

    @Override
    public void onError(AsyncEvent event) throws IOException {
        log.info("http请求失败");
    }

    @Override
    public void onStartAsync(AsyncEvent event) throws IOException {
        log.info("http异步请求开始");
    }
}
