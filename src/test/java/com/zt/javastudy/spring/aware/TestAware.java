package com.zt.javastudy.spring.aware;


import com.zt.javastudy.async.SpringContextUtil;
import com.zt.javastudy.spring.StudentA;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest()

public class TestAware {
    @Test
    public void test() {
        SpringContextUtil.getBean(TestBeanNameAware.class);
    }
    @Test
    public void testBean() {
       Object object =  SpringContextUtil.getBean("testFactoryBean");
        System.out.println(object.getClass().getName());
    }
    @Test
    public void testXunHuan() {
        StudentA a = SpringContextUtil.getBean(StudentA.class);
    }
}