package com.zt.javastudy.grammar;

import java.io.Serializable;

/**
 * @author zhengtao
 * @description 序列化测试
 * @date 2020/12/23
 */
public class User implements Serializable{
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public static void main(String[] args) {
        User user = new User();
        user.setAge(1);
        User user1 = user;
        user = new User();
        user.setAge(2);
    }
}