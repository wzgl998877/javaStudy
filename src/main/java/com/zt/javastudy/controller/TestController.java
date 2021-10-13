package com.zt.javastudy.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.jmx.export.SpringModelMBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.spring5.context.SpringContextUtils;
import org.yeauty.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhengtao
 * @description test
 * @date 2021/4/21
 */
@RestController
@Slf4j
public class TestController {
    @RequestMapping("/test")
    public String test(HttpServletRequest request, HttpServletResponse response) {
        TestResponse response1 = new TestResponse();
        response1.setUser_name("zt");
        response1.setTeacher_name("zt");
        SerializeConfig config = new SerializeConfig();
        TestFastRequest testFastRequest = new TestFastRequest();
        log.info(JSON.toJSONString(testFastRequest));
        config.propertyNamingStrategy = PropertyNamingStrategy.PascalCase;
        // 返回的json就是下划线的
        return JSON.toJSONString(response1, SerializerFeature.WriteMapNullValue);
    }

    @RequestMapping("/test2")
    public Object test2(HttpServletRequest request, HttpServletResponse response) {
        String response1 = "{\"teacher_name\":\"zt\",\"user_name\":null}";
        return JSON.parseObject(response1);
    }

    @RequestMapping("/test3")
    public Object test3() {
        TestResponse response1 = new TestResponse();
        response1.setUser_name("zt");
        response1.setTeacher_name("zt");
        return response1;
    }

    @GetMapping("page")
    public ModelAndView page() {
        return new ModelAndView("websocket");
    }

    @RequestMapping("/test4")
    public Object test4(@RequestParam String req, @RequestParam String req2) {
        return req + req2;
    }
}
