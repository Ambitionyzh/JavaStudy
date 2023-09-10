package middle;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;

/**
 * @author yongzh
 * @version 1.0
 * @program: JavaTest
 * @description: 生产者消费者消息队列
 * @date 2023/9/10 9:41
 */
public class PCmodle {
    public static void main(String[] args)  {
        MessageQueue messageQueue = new MessageQueue(2);
        for (int i = 0; i < 3; i++) {
            int id = i;
            new Thread(()->{
                messageQueue.Producer(new Message(id,"值："+id));
            },"生产者"+i).start();

        }

        new Thread(()->{
                while (true){
                    try {
                        Thread.sleep(1000);
                        Message message = messageQueue.Customer();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }
        },"消费者").start();
    }
}
@Slf4j
class MessageQueue{
    //消息队列的集合
    private LinkedList<Message> list = new LinkedList<>();
    //消息队列的容量
    private int capcity;
    public MessageQueue(int capcity) {
        this.capcity = capcity;
    }
    public void  Producer(Message message)  {
        synchronized (list){
        while (list.size() == capcity){

            try {
                log.debug("队列已满，生产者等待");
                list.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
            //将消息加入队列尾部
            list.addLast(message);
            log.debug("已生产消息{}",message);
            list.notifyAll();
        }



    }
    public Message Customer()  {
        synchronized (list){
            //检查对象是否已满
            while (list.isEmpty()){

                try {
                    log.debug("消息队列为空，消费者等待");
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //从头部获取消息并返回
            Message message = list.removeFirst();
            log.debug("已消费消息{}",message);
            list.notifyAll();
            return message;
        }


    }

}

 class Message{
    private int id;
    private Object value;

    public Message(int id, Object value) {
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", value=" + value +
                '}';
    }
}
