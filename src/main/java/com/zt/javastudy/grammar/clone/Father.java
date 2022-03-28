package com.zt.javastudy.grammar.clone;

import lombok.Data;

/**
 * @author zhengtao on 2022/3/26
 */
@Data
public class Father implements Cloneable{
    private String name;

    @Override
    protected Father clone() throws CloneNotSupportedException {
        return (Father) super.clone();
    }
}
