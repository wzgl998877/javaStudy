package com.zt.javastudy.async;

/**
 * 异步客户端
 */
public interface IAsyncClient {
    /**
     * 获取服务uri标识
     * @return
     */
    String getUri();

    /**
     * 获取日志标识
     * @return
     */
    String getLogId();
    /**
     * 异步客户端发送
     * @param context
     * @param clientCallback
     */
    void send(String context, IClientCallback clientCallback);
}
