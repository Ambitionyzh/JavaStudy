package test;

/**
 * @author yongzh
 * @version 1.0
 * @program: JavaTest
 * @description:
 * @date 2023/7/19 0:20
 */
import com.alibaba.ttl.TransmittableThreadLocal;

public class TransmittableThreadLocalExample {
    private static TransmittableThreadLocal<String> threadLocal = new TransmittableThreadLocal<>();

    public static void main(String[] args) {
        threadLocal.set("Hello World");

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("子线程获取到的值：" + threadLocal.get());
                threadLocal.set("Hello from child thread");
                System.out.println("子线程修改后的值：" + threadLocal.get());
            }
        });

        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("父线程获取到的值：" + threadLocal.get());
    }
}

