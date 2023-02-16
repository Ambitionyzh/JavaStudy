package com.yongzh.reflection;

/**
 * @author yongzh
 * @version 1.0
 * @program: JavaTest
 * @description: 类加载与初始化
 * @date 2023/2/16 23:34
 */
public class ClassLoad {
    static{
        System.out.println("Main类被加载");
    }

    public static void main(String[] args) throws ClassNotFoundException {
        //1.主动引用
        //Son son = new Son();
        //反射也会产生主动引用，但是非常消耗资源
        //Class.forName("com.yongzh.reflection.Son");

        //不会产生类引用的方法
        //System.out.println(Son.b);

        Son[] array = new Son[5];

        System.out.println(Son.M);
    }
}
class Father{
    static  int b = 2;
    static{
        System.out.println("父类被加载");
    }
}
class Son extends Father{
    static{
        System.out.println("子类被加载");
    }
    static  int m =100;
    static final  int M = 1;
}