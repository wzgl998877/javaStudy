package com.zt.javastudy.grammar;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 注解学习
 *
 * @author zhengtao on 2021/10/27
 */
@Configuration
@ConditionalOnProperty(
        prefix = "test",
        name = {"enable"},
        havingValue = "true",
        matchIfMissing = false
)
@Slf4j
public class ConditionalTest {
    @Bean(initMethod = "start", destroyMethod = "shutdown")
    public ConditionalTest buildProducer() {
        log.info("创建bean了");
        ConditionalTest conditionalTest = new ConditionalTest();
        return conditionalTest;
    }

    private void start() {
    }

    private void shutdown() {
    }
}
