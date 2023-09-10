package middle;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yongzh
 * @version 1.0
 * @program: JavaTest
 * @description:
 * @date 2023/9/3 12:50
 */
@Slf4j
public class Tea {
    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            log.debug("洗水壶");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.debug("烧开水");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        },"老王");
        Thread t2 = new Thread(()->{
            log.debug("洗茶叶");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.debug("洗水壶");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.debug("洗茶杯");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            try {
                t1.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.debug("泡茶");
        },"小王");
        t1.start();
        t2.start();
    }
}
