package simple;

/**
 * @author yongzh
 * @version 1.0
 * @program: JavaTest
 * @description: yield和优先级
 * @date 2023/9/3 9:52
 */
public class ThreadTest3 {
    public static void main(String[] args) {
        Runnable run1 = ()->{
            int count = 0;

            for(;;){
                System.out.println("----->1 :"+count++);
            }
        };
        Runnable run2 = ()->{
            int count = 0;
            for(;;){
                //Thread.yield();
                System.out.println("----->2 :"+count++);
            }
        };
        Thread t1 = new Thread(run1,"t1");
        Thread t2 = new Thread(run2,"t2");
        t1.setPriority(Thread.MIN_PRIORITY);
        t2.setPriority(Thread.MAX_PRIORITY);
        t1.start();
        t2.start();
    }
}
