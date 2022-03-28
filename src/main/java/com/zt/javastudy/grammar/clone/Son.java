package com.zt.javastudy.grammar.clone;

import lombok.Data;

/**
 * @author zhengtao on 2022/3/26
 */
@Data
public class Son implements Cloneable{
    private String age;
    private Father father;

    @Override
    protected Son clone() throws CloneNotSupportedException {
        Son son =  (Son) super.clone();
        son.setFather(father.clone());
        return son;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Son son1 = new Son();
        son1.setAge("1");
        Father father = new Father();
        father.setName("爸爸");
        son1.setFather(father);
        Son son2 = son1.clone();
        Son son3 = new Son();
        son3.setFather(son2.getFather());
        son3.setAge(son2.getAge());
        System.out.println(son1);
        System.out.println(son2);
        System.out.println(son3);
        System.out.println(son1 == son2);
        System.out.println(son1.getFather() == son2.getFather());
        son2.getFather().setName("儿砸");
        son2.setAge("123");
        System.out.println(son1);
        System.out.println(son2);
        System.out.println(son3);

    }
}
