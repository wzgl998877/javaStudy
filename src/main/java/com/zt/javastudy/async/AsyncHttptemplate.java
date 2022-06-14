package com.zt.javastudy.async;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.entity.ContentType;
import org.asynchttpclient.*;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Slf4j
@Component
public class AsyncHttptemplate {
    @Value("${jlpay.framework.asyn-http.connectTimeout}")
    private int connectTimeout;
    @Value("${jlpay.framework.asyn-http.threadCount}")
    private int threadCount;
    @Value("${jlpay.framework.asyn-http.maxConnTotal}")
    private int maxConnTotal;
    @Value("${jlpay.framework.asyn-http.maxConnPerRoute}")
    private int maxConnPerRoute;
    @Value("${jlpay.framework.asyn-http.readTimeout:3000}")
    private int readTimeout;

    private AsyncHttpClient asyncHttpClient;
    @Autowired
    @Qualifier("httpWorkThreadPool")
    private ThreadPoolTaskExecutor httpResponseThreadPool;

    @PostConstruct
    public void init() throws Exception {
        log.info("init asyn http client");
        log.info("finish to init asyn http client");

        SslContext sslContext = SslContextBuilder.forClient().trustManager(InsecureTrustManagerFactory.INSTANCE).build();
        DefaultAsyncHttpClientConfig asyncHttpClientConfig = new DefaultAsyncHttpClientConfig.Builder()
                .setSslContext(sslContext)
                .setIoThreadsCount(threadCount)
                .setMaxConnections(maxConnTotal)
                .setConnectTimeout(connectTimeout)
                .setMaxConnectionsPerHost(maxConnPerRoute)
                .build();
        asyncHttpClient = Dsl.asyncHttpClient(asyncHttpClientConfig);
    }

    /**
     * 同步请求
     *
     * @param contentType
     * @param url
     * @param context
     * @return
     * @throws NetworkException
     */
    public String executeSync(ContentType contentType, String url, String context) throws InterruptedException, ExecutionException {

        ListenableFuture<Response> listenableFuture = asyncHttpClient.preparePost(url)
                .setBody(context)
                .setHeader("Content-Type", contentType.toString())
                .setHeader("logId", MDC.get("logId"))
                .execute();

        try {
            return listenableFuture.get().getResponseBody();
        } catch (InterruptedException e) {
            log.error("http请求异常", e);
            throw e;
        } catch (ExecutionException e) {
            log.error("http请求异常", e);
            throw e;
        }
    }

    /***
     * 异步请求
     * @param client
     * @param context
     * @param clientCallback
     */
    public void execute(HttpAsyncClient client, String context, IClientCallback clientCallback, Map<String, String> headers) {

        log.info("异步 HTTP POST 请求地址:{}，请求数据:{}", client.getUrl(), context);
        BoundRequestBuilder requestBuilder = asyncHttpClient.preparePost(client.getUrl())
                .setBody(context)
                .setHeader("logId", MDC.get("logId"))
                .setHeader("Content-Type", client.getContentType().toString());

        if (headers != null && !headers.isEmpty()) {
            for (Map.Entry<String, String> header : headers.entrySet()) {
                requestBuilder.setHeader(header.getKey(), header.getValue());
            }
        }

        ListenableFuture<Response> listenableFuture = requestBuilder.execute();
        listenableFuture.addListener(() -> {
            try {
                Response response = listenableFuture.get(readTimeout, TimeUnit.SECONDS);
                clientCallback.onSuccess(response.getResponseBody());
            } catch (InterruptedException e) {
                log.error("http请求异常", e);
                clientCallback.onFail(e);
            } catch (ExecutionException e) {
                log.error("http请求异常", e);
                clientCallback.onFail(e);
            } catch (TimeoutException e) {
                log.error("http请求异常", e);
                clientCallback.onFail(e);
            }
        }, httpResponseThreadPool);
        httpResponseThreadPool.execute(() -> {

        });
    }

}
