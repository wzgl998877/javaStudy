package com.zt.javastudy.grammar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhengtao
 * @description 序列化测试
 * @date 2020/12/23
 */
public class User extends User1 implements Serializable{
    private static Integer a = 0;
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
                ", score=" + score +
                '}';
    }

    public static void main(String[] args) {
        User user = new User();
        user.setAge(1);
        User user1 = user;
        user = new User();
        user.setAge(2);
        List<User> list = new ArrayList<>();
        list.add(user);
        list.add(user1);
        for (int i=0; i<list.size(); i++){
            if (list.get(i).getAge() == 1){
                list.remove(list.get(i));
            }
        }
        user.test();
        a++;
        System.out.println(a);
        System.out.println(list.size());
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
    @Override
    void test(){
        a++;
        System.out.println("调用了子类方法");
    }
}
class User1{
    void test(){
        System.out.println("调用了父类方法");
    }
}