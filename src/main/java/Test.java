import java.sql.SQLOutput;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.concurrent.*;
import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.locks.LockSupport;

/**
 * @author Administrator
 * @version 1.0
 * @program: LeetCode
 * @description: mapTest
 * @date 2022/12/27 21:37
 */

public class Test {
    public  static void main(String[] args) throws InterruptedException {
        ConcurrentHashMap<String,String> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put("1","1");
        //HashMap
        //System.out.println(hash(456));
       // LockSupport
       /* seeThread();
        justRun();*/
        System.out.println(System.currentTimeMillis()%1000);
        //Executors



    }
    static final int hash(Object key) {
        int h;
        System.out.println(key.hashCode());
        int a =(h = key.hashCode()) ^ (h >>> 16);
        return  a;
    }
    static void justRun() throws InterruptedException {
        while(Instant.now().isBefore(Instant.now().plus(1,ChronoUnit.DAYS))){
            System.out.println("running");
            TimeUnit.SECONDS.sleep(1);


        }
    }
    static void seeThread(){
        Thread t = new Thread(()->{
            System.out.println("this is in a thread");
            try {
                TimeUnit.HOURS.sleep(1);
            }catch (InterruptedException e){
                throw new RuntimeException();
            }

        });
        t.setName("THREAD_DEMO");
        t.start();

    }
}

