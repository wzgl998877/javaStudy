package com.zt.javastudy.async;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.entity.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest()
public class HttpAsyncClientTest {
    @Autowired
    private IClientCallback iClientCallback;

    @Test
    public void test() {
        MDC.put("logId", String.valueOf(System.currentTimeMillis()));
        HttpAsyncClient httpAsyncClient = HttpAsyncClient.builder().url("http://localhost:19003/testCommon").contentType(ContentType.APPLICATION_JSON).build();
        httpAsyncClient.send("", new IClientCallbackImpl());
    }

}