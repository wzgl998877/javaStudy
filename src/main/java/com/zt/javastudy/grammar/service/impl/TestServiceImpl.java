package com.zt.javastudy.grammar.service.impl;

import com.zt.javastudy.grammar.service.TestService;
import org.springframework.stereotype.Service;

/**
 * @author zhengtao
 * @description
 * @date 2021/4/29
 */
@Service
public class TestServiceImpl implements TestService {
    @Override
    public void test() {
        System.out.println("实现1");
    }
}
