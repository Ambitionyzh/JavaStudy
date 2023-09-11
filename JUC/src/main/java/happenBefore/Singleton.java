package happenBefore;

import java.io.Serializable;

/**
 * @author yongzh
 * @version 1.0
 * @program: JavaTest
 * @description: 单例模式 5问
 * @date 2023/9/11 21:56
 */
//问题1问什么加final 防止子类重写破坏父类方法
//如果实现了序列化接口，需要用 Object readResolve()方法来防止反序列化破护单例
public final  class Singleton implements Serializable {
    //即使设置为私有，也不能防止反射破坏单例。可以使用枚举单例来防止反射破坏
    private Singleton() {
    }
    //静态变量在初始化时已经赋好值
    private static final Singleton INSTANCE= new Singleton();
    public static Singleton getInstance(){
        return INSTANCE;
    }
    public Object readResolve(){
        return INSTANCE;
    }
}

