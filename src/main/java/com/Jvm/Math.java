package com.Jvm;

import com.mybatisDemo.User;

/**
 * @author yongzh
 * @version 1.0
 * @program: JavaTest
 * @description:
 * @date 2023/3/31 21:34
 */
public class Math {

    public static final int initData = 666;
    public static User user = new User();

    public int computer(){
        int a= 1;
        int b = 2;
        int c = (a + b) *10;
        return  c;

    }
    public static void main(String[] args) {
        Math math = new Math();
        math.computer();
        System.out.println(1);
    }
}
