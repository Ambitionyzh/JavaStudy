package com.Jvm;

import java.util.Random;
import java.lang.Math;
/**
 * @author yongzh
 * @version 1.0
 * @program: JavaTest
 * @description:
 * @date 2023/4/21 21:32
 */
public class CpuTest {
    public static void main(String[] args) {
        while(true){
            double x = Math.random();
            double y = Math.random();
            double result = 0.0;
            for (int i = 0; i < 1000000; i++) {
                result = (Math.sin(x)+Math.cos(y))*(result + 1.0);
            }
            System.out.println("Result:" + result);
        }
    }
}
