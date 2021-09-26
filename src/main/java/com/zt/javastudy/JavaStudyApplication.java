package com.zt.javastudy;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author zhengtao
 */
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@EnableScheduling
// 引入lombok注解，加上这个注释就可以使用log打印日志了
@Slf4j
@EnableAsync
public class JavaStudyApplication {
    public static final Logger logger = LoggerFactory.getLogger(JavaStudyApplication.class);
    public static void main(String[] args) {
        logger.info("java-study starting.....");
        log.error("slf4j starting...");
        SpringApplication.run(JavaStudyApplication.class, args);
//        BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath*:applicationContext.xml");
//        AnnotationConfigApplicationContext
        logger.info("java-study ok.....");
        log.error("slf4j ok.........");
    }
    /*@Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //线程池维护线程的最少数量，哪怕是空闲的
        executor.setCorePoolSize(30);
        //线程池维护线程的最大数量
        executor.setMaxPoolSize(30);
        //线程池所使用的缓冲队列，改缓冲队列的长度决定了能够缓冲的最大数量
        executor.setQueueCapacity(200);
        //线程池维护线程所允许的空闲时间
        executor.setKeepAliveSeconds(100);
        executor.setThreadNamePrefix("Asyn-TaskExecutor-");
        executor.setRejectedExecutionHandler((Runnable r, ThreadPoolExecutor exe) -> {
            System.out.println(("异步任务线程池队列已满"));
        });
        return executor;
    }*/

}
