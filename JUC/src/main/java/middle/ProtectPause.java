package middle;

import lombok.extern.slf4j.Slf4j;

import java.util.Hashtable;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * @author yongzh
 * @version 1.0
 * @program: JavaTest
 * @description: 保护性暂停-解耦实现
 * @date 2023/9/8 19:17
 */
@Slf4j
public class ProtectPause {

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 3; i++) {
             new People().start();
        }
        Thread.sleep(1000);
        for (Integer id : MainBox.getIds()) {
            new Postman(id,"内容"+id).start();
        }

    }
}
    @Slf4j
     class People extends Thread{
        @Override
        public void run() {
            ProtectPauseObject object = MainBox.CreateObject();
            log.debug("开始收信 id:{}",object.getId());

             Object mail = object.get(5000);
            log.debug("收到信 id:{},内容:{}",object.getId(),mail);


        }
    }
    @Slf4j
     class Postman extends Thread{
        private int id;
        private String mail;

        public Postman(int id, String mail) {
            this.id = id;
            this.mail = mail;
        }

        @Override
        public void run() {
            ProtectPauseObject object = MainBox.getObject(id);
            log.debug("送信id :{} , 内容:{}",id,mail);
            object.complete(mail);
    }
}
    class MainBox{
        private static Map<Integer,ProtectPauseObject> boxes = new Hashtable<>();
        private static int id = 1;
        private   static synchronized  int  generateId(){
            return id++;
        }
        public static ProtectPauseObject CreateObject(){
            ProtectPauseObject object = new ProtectPauseObject(generateId());
            boxes.put(object.getId(),object );
            return object;
        }
        public static ProtectPauseObject getObject(int id){
            return boxes.remove(id);
        }
        public static Set<Integer>getIds(){return boxes.keySet();}


    }

    @Slf4j
    class ProtectPauseObject {
        private int id;


        public int getId(){
            return id;
        }
        public ProtectPauseObject(int id) {
            this.id = id;
        }
        private Object response;
       // timeout表示要等待多久
        public Object get(long timeout) {
            synchronized (this){
                long times = System.currentTimeMillis();
                long passedTime = 0;
                    while (response == null){
                        //剩余需要等待的时间
                        long waitTime = timeout - passedTime;
                        if(waitTime <= 0 ){
                            break;
                        }
                        try {
                            this.wait(waitTime);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        passedTime = System.currentTimeMillis() - times;
                    }

                return response;



            }
        }
        public void complete(String response){
            synchronized (this){
                this.response = response;
                this.notifyAll();
            }

        }

    }

