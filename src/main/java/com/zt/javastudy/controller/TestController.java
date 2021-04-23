package com.zt.javastudy.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializeConfig;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhengtao
 * @description test
 * @date 2021/4/21
 */
@RestController
public class TestController {
    @RequestMapping("/test")
    public String test(HttpServletRequest request, HttpServletResponse response) {
        TestResponse response1 = new TestResponse();
        response1.setUserName("zt");
        response1.setTeacherName("zt");
        SerializeConfig config = new SerializeConfig();
        config.propertyNamingStrategy = PropertyNamingStrategy.SnakeCase;
        // 返回的json就是下划线的
        return JSON.toJSONString(response1, config);
    }
    @RequestMapping("/test2")
    public Object test2(HttpServletRequest request, HttpServletResponse response) {
        String response1 = "{\"teacher_name\":\"zt\",\"user_name\":\"zt\"}";
        // 转为下划线
        /*ParserConfig parserConfig =  new  ParserConfig();
        parserConfig.propertyNamingStrategy = PropertyNamingStrategy.SnakeCase;*/
        return JSON.parseObject(response1, TestResponse.class);
    }
    @RequestMapping("/test3")
    public Object test3() {
        TestResponse response1 = new TestResponse();
        response1.setUserName("zt");
        response1.setTeacherName("zt");
        return response1;
    }
}
