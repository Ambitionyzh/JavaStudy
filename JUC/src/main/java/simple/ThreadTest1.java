package simple;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yongzh
 * @version 1.0
 * @program: JavaTest
 * @description:
 * @date 2023/9/3 9:36
 */
@Slf4j
public class ThreadTest1 {
    public static void main(String[] args) {
        Thread t1 = new Thread("t1") {
            @Override
            public void run() {

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }


            }
        };
        t1.start();
        log.debug("{}",t1.getState());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.debug("{}",t1.getState());
    }
}
