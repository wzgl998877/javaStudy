package com.zt.javastudy.grammar;

import lombok.Data;

/**
 * 测试注解
 *
 * @author zhengtao on 2021/9/16
 */
@Data
@Fruit(genName = "富士康苹果", genColor = "红色")
public class Apple {
    private String name;
    private String color;
}
