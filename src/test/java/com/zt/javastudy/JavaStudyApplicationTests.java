package com.zt.javastudy;

import com.zt.javastudy.work.TestAsync;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class JavaStudyApplicationTests {
    @Autowired
    private TestAsync testAsync;
    @Test
    void testAsync() throws InterruptedException {
        System.out.println("测试开始");
        testAsync.test();
        testAsync.test1();
        System.out.println("测试结束");
    }

}
