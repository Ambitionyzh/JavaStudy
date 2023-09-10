package simple;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingDeque;

/**
 * @author yongzh
 * @version 1.0
 * @program: JavaTest
 * @description: 优雅中断正在运行的线程
 * @date 2023/9/3 11:52
 */
@Slf4j
public class IsInterrupted {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
            while (true){
                boolean interrupted = Thread.currentThread().isInterrupted();
                if(interrupted){
                    log.debug("线程被打断了");
                    break;
                }
            }
        },"t1");
        t1.start();
        Thread.sleep(1000);
        log.debug("打断t1线程");
        t1.interrupt();

    }
}
