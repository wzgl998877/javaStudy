package com.zt.javastudy.controller;

import lombok.Data;

import java.util.Date;

/**
 * 测试
 *
 * @author zhengtao on 2021/8/30
 */
@Data
public class TestRequest {
    private String code = "1";
    protected String value;
    private Date time = new Date();
    private Long a = 1L;

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Long.parseLong("50.00"));
    }
}
