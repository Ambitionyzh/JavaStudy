package happenBefore;

/**
 * @author yongzh
 * @version 1.0
 * @program: JavaTest
 * @description: volatile测试
 * @date 2023/9/11 21:33
 */
public class volatileTest {
    volatile static int x;
    static int y;
    public static void main(String[] args) {



        new Thread(()->{
            y = 10;
            x = 20;
        },"t1").start();
        new Thread(()->{
//x=20对t2可见，同时y=10也对t2可见
            System.out.println(x);
        },"t2").start();

    }

}
