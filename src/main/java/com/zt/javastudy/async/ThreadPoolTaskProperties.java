package com.zt.javastudy.async;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author lujianyuan
 */
@Component
@ConfigurationProperties(prefix = "jlpay.framework.work.request.threadpool")
@Data
@Slf4j
public class ThreadPoolTaskProperties implements Serializable {
    private int corePoolSize;
    private int maxPoolSize;
    private int queueCapacity;
    private int keepAliveSeconds;
    private String threadNamePrefix="business-request-";
    /**
     * 打印配置日志
     */
    public void printExecutorInfo(String poolName){
        String info = "the {} config is:codePoolSize={},maxPoolSize={},queueCapacity={},keepAliveSeconds={},threadNamePrefix={}";
        log.info(info,poolName,corePoolSize,maxPoolSize,queueCapacity,keepAliveSeconds,threadNamePrefix);
    }
}
