package test;

/**
 * @author yongzh
 * @version 1.0
 * @program: JavaTest
 * @description:
 * @date 2023/8/24 23:28
 */
public class Demo3_12_2 {
    public static void main(String[]args){
        int result = test();
        System.out.println(result);
    }
    public static int test(){
         try {
             int i = 1 /0;
            return 10;
         }
finally{
            return 20;
        }
}
}
