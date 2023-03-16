package com.yongzh.synchronizedTest;

/**
 * @author yongzh
 * @version 1.0
 * @program: JavaTest
 * @description: synchronized修饰静态方法与非静态方法
 * @date 2023/3/15 22:39
 */
public class test {

    //synchronized修饰非静态方法
    public synchronized void function() throws InterruptedException {
        for (int i = 0; i <3; i++) {
            Thread.sleep(1000);
            System.out.println("function1 running...");
        }
    }
    //synchronized修饰静态方法
    public synchronized static void staticFunction()
            throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            Thread.sleep(1000);
            System.out.println("Static function running...");
        }
    }
//静态方法锁的是所在类的Class对象，而非静态方法锁的是this对象
    public static void main(String[] args) throws InterruptedException {
        final test demo = new test();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    staticFunction();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    demo.function();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t2.start();
        t1.start();
    }

}
