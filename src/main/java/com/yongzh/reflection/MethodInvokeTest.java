package com.yongzh.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author yongzh
 * @version 1.0
 * @program: JavaTest
 * @description: 通过反射调用方法
 * @date 2023/2/22 0:12
 */
public class MethodInvokeTest {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String str = "hello";
        Method m = str.getClass().getMethod("toUpperCase");
        System.out.println(m.invoke(str));
    }
}
