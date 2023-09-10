package middle;

import java.util.concurrent.locks.LockSupport;

/**
 * @author yongzh
 * @version 1.0
 * @program: JavaTest
 * @description: 用wait，notify实现先打印2再打印1
 * @date 2023/9/10 20:30
 */
public class F2S1 {
    static final Object lock = new Object();
    static boolean isT2Running = false;
    public static void main(String[] args) {
        waitNotify();
        parkUnpark();
    }
    private static void waitNotify(){
        new Thread(()->{
            synchronized (lock){
                while (!isT2Running){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println("1");
            }

        },"t1").start();

        new Thread(()->{
            synchronized (lock){
                System.out.println("2");
                isT2Running = true;
                lock.notifyAll();
            }
        },"t2").start();
    }
    private static void parkUnpark(){
        Thread t1 = new Thread(() -> {
            LockSupport.park();
            System.out.println("1");
        }, "t1");
        new Thread(()->{

            System.out.println("2");
            LockSupport.unpark(t1);
        },"t2").start();
        t1.start();
    }
}
