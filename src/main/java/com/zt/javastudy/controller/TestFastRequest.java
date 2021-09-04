package com.zt.javastudy.controller;

import com.alibaba.fastjson.JSONObject;
import com.zt.javastudy.utils.DateUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * .
 *
 * @author zhengtao on 2021/8/30
 */
@Data
@Slf4j
public class TestFastRequest extends TestRequest{
    private String fat;

    public TestFastRequest() {
        this.value = "ok";
    }

    public static void main(String[] args) throws InterruptedException {
        TestFastRequest testFastRequest = new TestFastRequest();
        log.info("test:{}", JSONObject.toJSONString(testFastRequest));
        Map<Date, String> map = new HashMap<>();
        map.put(new Date(), "1");
        Thread.sleep(3000);
        map.put(new Date(), "2");
        for (Map.Entry<Date, String> entry : map.entrySet()){
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }
}
