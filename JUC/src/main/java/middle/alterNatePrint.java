package middle;

/**
 * @author yongzh
 * @version 1.0
 * @program: JavaTest
 * @description: 三个线程交替打印abc
 * @date 2023/9/10 20:52
 */
public class alterNatePrint {
    public static void main(String[] args) {
        WaitNotify watiNotify = new WaitNotify(1,5);
        new Thread(()->{
            watiNotify.print("a", 1 , 2);
        },"t1").start();
        new Thread(()->{
            watiNotify.print("b", 2, 3);
        },"t2").start();
        new Thread(()->{
            watiNotify.print("c", 3 , 1);
        },"t3").start();
    }


}
class WaitNotify{
    private int flag; // 2
    // 循环次数
    private int loopNumber;

    public WaitNotify(int flag, int loopNumber) {
        this.flag = flag;
        this.loopNumber = loopNumber;
    }

    public void print(String str, int waitFlag, int nextFlag){
        for (int i = 0; i < loopNumber; i++) {
            synchronized (this){
                while (flag != waitFlag){
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }

                System.out.print(str);
                flag = nextFlag;
                this.notifyAll();

            }
        }
    }
}

