package com.zt.javastudy.grammar;

import javafx.scene.control.Pagination;

/**
 * @author zhengtao
 * @description jvm中对类的加载过程的学习
 * @date 2021/3/18
 */
public class JVMStudyTwo {
    static {
        System.out.println("JVM Study");
    }
    // 用户需要指定一个要执行的主类（包含main()方法的那个类），虚拟机会先初始化这个主类,所以只要运行这个main方法肯定最先初始化这个类
    public static void main(String[] args) {
        // 对于静态字段，只有直接定义这个字段的类才会被初始化，因此通过其子类来引用父类中定义的静态字段，只会触发父类的初始化而不会触发子类的初始化
        System.out.println(SubClass.value);
        // 数组定义来引用类，不会触发此类的初始化
        SuperClass[] superClasses = new SuperClass[10];
        // 常量在编译阶段已经存储到了本类的常量池中去了，其实就相当于自身常量池的引用
        System.out.println(SubClass.HELLOWORLD);
        System.out.println(SuperClass.HELLOWORLD == SubClass.HELLOWORLD);
    }
}
class SubClass extends SuperClass{
    static {
        System.out.println("子类初始化");
    }
    public static final String HELLOWORLD = "hello world！";
}
class SuperClass{
    static {
        System.out.println("父类初始化");
    }
    public static int value = 123;
    public static final String HELLOWORLD = "hello world";
}
