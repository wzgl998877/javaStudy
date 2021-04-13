package com.zt.javastudy.spring;

/**
 * @author zhengtao
 * @description 测试循环依赖
 * @date 2021/4/9
 */
public class TestXunHuan{
    public static void main(String[] args) {
        System.out.println(new StudentC());
    }
}

/**
 * StudentC与StudentD存在循环引用
 */
class StudentC {
    public StudentC() {
        new StudentD();
    }
}

class StudentD {
    public StudentD() {
        new StudentC();
    }
}
