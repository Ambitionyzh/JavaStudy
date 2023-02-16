package com.yongzh.reflection;

/**
 * @author yongzh
 * @version 1.0
 * @program: JavaTest
 * @description: 类加载器
 * @date 2023/2/17 0:18
 */
public class ClassLoad02 {
    public static void main(String[] args) throws ClassNotFoundException {
        //获取系统类加载器
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);
        //获取系统类加载器的父类加载器->拓展类加载器
        ClassLoader parent = systemClassLoader.getParent();
        System.out.println(parent);

        //获取拓展类加载器的父类加载器->根加载器
        ClassLoader parent1 = parent.getParent();
        System.out.println(parent1);
        //测试当前类是那个类加载器加载的
        ClassLoader classLoader = Class.forName("com.yongzh.reflection.ClassLoad02").getClassLoader();
        System.out.println(classLoader);
        //测试JDK内置的类是谁加载的
        classLoader = Class.forName("java.lang.Object").getClassLoader();
        System.out.println(classLoader);

        System.out.println(System.getProperty("java.class.path"));



    }
}
