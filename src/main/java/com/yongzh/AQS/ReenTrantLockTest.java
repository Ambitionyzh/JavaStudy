package com.yongzh.AQS;

import java.util.SortedMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yongzh
 * @version 1.0
 * @program: JavaTest
 * @description:
 * @date 2023/2/28 22:45
 */
public class ReenTrantLockTest {
    private volatile int num;
    private Lock lock = new ReentrantLock();
    private int getNum(){
        return num;
    }

    private void addNum(){
        lock.lock();
        try{
            Thread.sleep(5L);
            num++;
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReenTrantLockTest t5 = new ReenTrantLockTest();
        for (int i = 0; i < 100; i++) {
            new Thread(t5::addNum).start();
        }
        Thread.sleep(3000L);
        System.out.println(t5.getNum());
    }

}
