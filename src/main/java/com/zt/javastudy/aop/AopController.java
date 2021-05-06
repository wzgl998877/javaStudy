package com.zt.javastudy.aop;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhengtao
 * @description aop
 * @date 2021/4/29
 */
@RestController
@RequestMapping("/aop")
public class AopController {
    @RequestMapping("/test")
    public String test(){
        return "HelloWorld!";
    }
}
