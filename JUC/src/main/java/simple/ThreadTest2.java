package simple;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.context.Theme;

/**
 * @author yongzh
 * @version 1.0
 * @program: JavaTest
 * @description:
 * @date 2023/9/3 9:43
 */
@Slf4j
public class ThreadTest2 {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread("t1") {
            @Override
            public void run() {
                log.debug("enter sleep");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    log.debug("wake up");
                    throw new RuntimeException(e);
                }
            }
        };
        t1.start();
        Thread.sleep(1000);
        log.debug("intererupt");
        t1.interrupt();

    }
}
