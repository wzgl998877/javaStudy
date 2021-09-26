package com.zt.javastudy.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author lujianyuan
 * http服务配置
 */
@Configuration
public class HttpServerConfig {
    @Autowired
    private ThreadPoolTaskProperties threadPoolTaskProperties;
//    @Bean
//    @ConfigurationProperties(prefix = "jlpay.framework.work.request.threadpool")
//    public ThreadPoolTaskProperties threadPoolTaskProperties(){
//        return new ThreadPoolTaskProperties();
//    }

    @Bean("httpWorkThreadPool")
    public ThreadPoolTaskExecutor flowThreadPool(){
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
//        ThreadPoolTaskProperties threadPoolTaskProperties = new ThreadPoolTaskProperties();
        threadPoolTaskExecutor.setCorePoolSize(threadPoolTaskProperties.getCorePoolSize());
        threadPoolTaskExecutor.setMaxPoolSize(threadPoolTaskProperties.getMaxPoolSize());
        threadPoolTaskExecutor.setQueueCapacity(threadPoolTaskProperties.getQueueCapacity());
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        threadPoolTaskExecutor.setThreadNamePrefix(threadPoolTaskProperties.getThreadNamePrefix());
        threadPoolTaskExecutor.setKeepAliveSeconds(threadPoolTaskProperties.getKeepAliveSeconds());
        threadPoolTaskExecutor.initialize();
        threadPoolTaskProperties.printExecutorInfo("httpWorkThreadPool");
        return threadPoolTaskExecutor;
    }
}
