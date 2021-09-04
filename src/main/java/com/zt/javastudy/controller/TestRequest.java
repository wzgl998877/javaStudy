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
}
