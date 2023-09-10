package middle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.context.Theme;

/**
 * @author yongzh
 * @version 1.0
 * @program: JavaTest
 * @description: 两阶段终止模式
 * @date 2023/9/3 12:10
 */
@Slf4j
public class TwoPhase {
    public static void main(String[] args) throws InterruptedException {
        TwoPhaseTermination termination = new TwoPhaseTermination();
        termination.start();
        Thread.sleep(3500);
        termination.stop();//一阶段调用stop的interrupt设置标记位
    }
}
@Slf4j
class TwoPhaseTermination{
    private Thread monitor;

    public void start(){
        monitor = new Thread(()->{
            while (true){
                Thread current = Thread.currentThread();
                if(current.isInterrupted()){
                    log.debug("线程被中断了");
                    break;
                }

                try {
                    Thread.sleep(1000);
                    log.debug("执行监控记录");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    //重新设置打断标记
                    current.interrupt();
                }


            }
        });
        monitor.start();
    }
    public void stop(){
        monitor.interrupt();
    }
}
