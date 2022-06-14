package com.zt.javastudy.async;

import org.springframework.beans.factory.annotation.Autowired;
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
    public ThreadPoolTaskExecutor flowThreadPool() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
//        ThreadPoolTaskProperties threadPoolTaskProperties = new ThreadPoolTaskProperties();
        // 线程池核心线程个数
        threadPoolTaskExecutor.setCorePoolSize(threadPoolTaskProperties.getCorePoolSize());
        // 线程池最大线程数量
        threadPoolTaskExecutor.setMaxPoolSize(threadPoolTaskProperties.getMaxPoolSize());
        // 阻塞队列大小
        threadPoolTaskExecutor.setQueueCapacity(threadPoolTaskProperties.getQueueCapacity());
        // 饱和策略 使用调用者所在线程来运行任务
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 前缀
        threadPoolTaskExecutor.setThreadNamePrefix(threadPoolTaskProperties.getThreadNamePrefix());
        // 存活时间。如果当前线程池中的线程数量比核心线程数量多，并且是闲置状态，则这些闲置的线程能存活的最大时间
        threadPoolTaskExecutor.setKeepAliveSeconds(threadPoolTaskProperties.getKeepAliveSeconds());
        threadPoolTaskExecutor.initialize();
        threadPoolTaskProperties.printExecutorInfo("httpWorkThreadPool");
        return threadPoolTaskExecutor;
    }
}
