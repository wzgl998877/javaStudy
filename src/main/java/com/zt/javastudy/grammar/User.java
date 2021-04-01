package com.zt.javastudy.grammar;

import java.io.Serializable;

/**
 * @author zhengtao
 * @description 序列化测试
 * @date 2020/12/23
 */
public class User implements Serializable{
    private String name;
    private Integer age;
    private Integer score;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
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

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public User(String name, Integer age, Integer score) {
        this.name = name;
        this.age = age;
        this.score = score;
    }

    public User(){

    }
}