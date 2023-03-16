package com.yongzh.AQS;

import org.springframework.ui.context.Theme;

import java.util.concurrent.locks.LockSupport;

/**
 * @author yongzh
 * @version 1.0
 * @program: JavaTest
 * @description: LockSupport测试
 * @date 2023/2/28 22:34
 */
public class lockSupportTest {
    private void printA(Thread thread){
        try {
            Thread.sleep(20L);
            System.out.println("A");
            LockSupport.unpark(thread);

        }catch (InterruptedException e){
            throw  new RuntimeException(e);
        }
    }
    private void printB(Thread thread){
        try {
            Thread.sleep(10L);
            LockSupport.park();
            System.out.println("B");
            LockSupport.unpark(thread);

        }catch (InterruptedException e){
            throw  new RuntimeException(e);
        }
    }
    private void printC(){
        try {
            Thread.sleep(5L);
            LockSupport.park();
            System.out.println("C");

        }catch (InterruptedException e){
            throw  new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        lockSupportTest t = new lockSupportTest();
        Thread tC = new Thread(t::printC);
        Thread tB = new Thread(()->t.printB(tC));
        Thread tA = new Thread(()->t.printA(tB));
        tA.start();
        tB.start();
        tC.start();
    }

}
