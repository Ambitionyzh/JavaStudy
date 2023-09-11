package happenBefore;

/**
 * @author yongzh
 * @version 1.0
 * @program: JavaTest
 * @description: 双重检查锁实现单例
 * @date 2023/9/11 22:06
 */
public final class SingletonDouble {
    private SingletonDouble(){}
//问题1：解释为什么要加vo1ati1e?
        private static volatile SingletonDouble INSTANCE = null;
//问题2：对比实现3，说出这样做的意义
        public static SingletonDouble getInstance(){
            if (INSTANCE !=null){
                return INSTANCE;
            }
                synchronized (SingletonDouble.class){
//问题3：为什么还要在这里加为空判断，之前不是判断过了吗
                    if (INSTANCE !=null){
                        return INSTANCE;
                    }
                        INSTANCE = new SingletonDouble();
                        return INSTANCE;
                    }
        }
}


