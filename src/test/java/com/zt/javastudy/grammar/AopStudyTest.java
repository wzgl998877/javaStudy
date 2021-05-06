package com.zt.javastudy.grammar;

import com.zt.javastudy.grammar.service.TestService;
import com.zt.javastudy.grammar.service.impl.StaticProxyImpl;
import com.zt.javastudy.grammar.service.impl.StaticProxyTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest()
public class AopStudyTest {
    @Autowired
    private AopStudy aopStudy;
    @Qualifier("testServiceImpl")
    @Autowired
    private TestService testService;
    @Qualifier("testServiceI2mpl")
    @Autowired
    private TestService testService2;

    @Test
    public void test(){
        aopStudy.test();
    }

    @Test
    public void testJdkProxy(){
        // 接口
        IJdkProxyStudy jdkProxyStudy = new JdkProxyStudyImpl();
        MyInvocationHandler invocationHandler = new MyInvocationHandler(jdkProxyStudy);
        IJdkProxyStudy proxy = (IJdkProxyStudy) invocationHandler.getProxy();
        proxy.add();
        // 没有实现接口的类，使用jdk代理报错
        CglibTest cglibTest = new CglibTest();
        MyInvocationHandler invocationHandler1 = new MyInvocationHandler(cglibTest);
        CglibTest proxy1 = (CglibTest) invocationHandler1.getProxy();
        proxy1.test();
    }

    @Test
    public void testCglibProxy(){
        CglibTest cglibTest = new CglibTest();
        CgLibProxy cgLibProxy = new CgLibProxy(cglibTest);
        CglibTest proxy = (CglibTest) cgLibProxy.getProxy();
        proxy.test();
    }

    @Test
    public void testStaticProxy(){
        StaticProxy staticProxy = new StaticProxyTest(new StaticProxyImpl());
        staticProxy.test();
    }

    @Test
    public void testService(){
        testService.test();
        testService2.test();
    }

}