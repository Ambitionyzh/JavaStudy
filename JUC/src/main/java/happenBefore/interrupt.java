package happenBefore;

/**
 * @author yongzh
 * @version 1.0
 * @program: JavaTest
 * @description: 测试interrupt
 * @date 2023/9/11 21:28
 */
public class interrupt {
    static int x;
    public static void main(String[]args){
        Thread t2 = new Thread(()->{
            while(true){
                if(Thread.currentThread().isInterrupted()){
                    System.out.println(x);
                    break;
                }}
            },"t2");
                t2.start();
                new Thread(()->{
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    x = 10;
                    t2.interrupt();
                },"t1").start();

                while(!t2.isInterrupted()){
                    Thread.yield();
                }

                System.out.println(x);

}

        }
