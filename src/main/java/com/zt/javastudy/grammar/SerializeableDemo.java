package com.zt.javastudy.grammar;

import java.io.*;

/**
 * @author zhengtao
 * @description 测试序列化代码
 * @date 2020/12/23
 */
public class SerializeableDemo {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        User user = new User();
        user.setAge(18);
        user.setName("zt");
        // 使用java原生进行序列化
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("user"));
        oos.writeObject(user);
        oos.close();
        // 使用java原生进行反序列化
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("user"));
        User newUser = (User) ois.readObject();
        ois.close();
        System.out.println(newUser);
    }
}
