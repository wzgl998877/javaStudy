package com.zt.javastudy.work;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author zhengtao
 * @description 测试异步
 * @date 2021/3/26
 */
@Component

public class TestAsync {
    @Async
    public void test() throws InterruptedException {
        Thread.sleep(3000);
        System.out.println("测试异步");
    }
    public void test1(){
        System.out.println("测试同步");
    }
}
