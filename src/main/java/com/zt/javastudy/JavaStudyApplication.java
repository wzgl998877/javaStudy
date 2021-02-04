package com.zt.javastudy;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author zhengtao
 */
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
// 引入lombok注解，加上这个注释就可以使用log打印日志了
@Slf4j
public class JavaStudyApplication {
    public static final Logger logger = LoggerFactory.getLogger(JavaStudyApplication.class);
    public static void main(String[] args) {
        logger.info("java-study starting.....");
        log.error("slf4j starting...");
        SpringApplication.run(JavaStudyApplication.class, args);
        logger.info("java-study ok.....");
        log.error("slf4j ok.........");
    }

}
