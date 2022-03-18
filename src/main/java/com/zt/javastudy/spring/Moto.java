package com.zt.javastudy.spring;

/**
 * @author zhengtao
 */
public class Moto implements Car{
    @Override
    public void speed() {
        System.out.println("摩托可以开100码？");
    }
}
