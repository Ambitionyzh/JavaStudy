package middle;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

/**
 * @author yongzh
 * @version 1.0
 * @program: JavaTest
 * @description: park在打断标记为真时会失效
 * @date 2023/9/3 12:22
 */
@Slf4j
public class TestPark {
    public static void main(String[] args) throws InterruptedException {
        tetsPark();
    }
    private static void tetsPark() throws InterruptedException {
        Thread t1 = new Thread(()->{
            log.debug("park start");
            LockSupport.park();
            //主线程调用interrupt
            log.debug("unpark");
            log.debug("打断状态：{}",Thread.currentThread().isInterrupted());
            //log.debug("打断状态：{}",Thread.interrupted());
            LockSupport.park();
            log.debug("ubpark");
        },"t1");
        t1.start();
        Thread.sleep(1000);
        t1.interrupt();
    }
}
