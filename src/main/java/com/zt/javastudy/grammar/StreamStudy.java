package com.zt.javastudy.grammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author zhengtao
 * @description java8流处理
 * @date 2021/3/31
 */
public class StreamStudy {
    public static void main(String[] args) {
        List<String> list1 = new ArrayList<>();
        list1.add("1");
        list1.add("2");
        list1.add("3");
        // foreach可以改变对象的值(不可以改变基本类型和String)，但不能删除或添加对象
        // TODO: 2021/3/31 这是一个问题，为什么list中存储的基本类型包括String不能被改变而数组可以被改变 
        list1.forEach(s -> {
            s = String.valueOf(Integer.parseInt(s)+1);
        });
        String[] ints = {"1","2","3"};
        for (int i=0;i<ints.length;i++){
            ints[i] = String.valueOf(Integer.parseInt(ints[i])+1);;
        }
        for (String i : ints) {
            System.out.println(i);
        }
        list1.forEach(System.out::print);
        System.out.println();
        String string = "1";
        String s1 = string;
        s1 = "2";

        // filter 过滤掉不符合条件的
        // 注意使用asList是不能修改元素的它的add/remove/clear方法会抛出UnsupportedOperationException异常
        List<String> list = Arrays.asList("1","2","3","123456");
        list.stream().filter(s -> s!="1").forEach(System.out::print);
        System.out.println();

        // map 映射每个元素到对应的结果,这里将所有元素加上字符串1
        list.stream().map(i -> Integer.parseInt(i)+1).forEach(e-> System.out.print(e+" "));
        list.forEach(System.out::print);
        System.out.println();

        // mapToInt 这里就是将每一个元素改为string.length(),最后相加
        System.out.println(list.stream().mapToInt(String::length).sum());

        // 排序，去重等使用
        List<Integer> list2 = Arrays.asList(1,2,3,4,4,5,5,232,34,43545,5445,455445);
        list2.stream().filter(integer -> integer > 3)
                      .distinct() // 去重
                      .skip(2) // 跳过两个元素
                      .sorted() // 排序
                      .limit(3) // 限制三个
                      .forEach(System.out::println);
        System.out.println();

        // collect 转换为各种数据结构。
        list2.stream().collect(Collectors.toSet()).forEach(System.out::println);

        // optional  1、提示用户要注意该对象有可能为null 2、简化if else代码
        User user = new User("zt",18);
        User user1 = new User("zt1",19,90);
        User user2 = new User(null,null,80);
        User user3 = null;
        List<User> users = Arrays.asList(user, user1, user2, user3);

        // 找出学生列表中age大于16 而且score大于80
        // 因为student有可能为null，score有可能为null age 有可能为null
        for (User user4 : users){
            if (user4 != null){
                if (user4.getAge() != null && user4.getAge() > 16){
                    if (user4.getScore() != null && user4.getScore() > 80){
                        System.out.println(user4.getName());
                    }
                }
            }
        }
        // 这东西其实没什么用的感觉用处不是很大
        for (User user4 : users){
            Optional<User> userOptional = Optional.ofNullable(user4);
            Integer score = userOptional.filter(user5 -> user5.getAge() != null && user5.getAge() > 16).map(User::getScore).orElse(0);
            if (score > 80){
                System.out.println(user4.getName());
            }
        }
        System.out.println();

        // switch中case可以这样写，0和1时都会打印1
        String se = "0";
        switch (se){
            case "0":
            case "1":
                System.out.println("1");
                break;
            case "2":
                System.out.println("2");
                break;
            case "3":
                System.out.println("3");
                break;
            default:
                System.out.println("4");
                break;
        }

    }


}
