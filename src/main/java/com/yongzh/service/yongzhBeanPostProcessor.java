package com.yongzh.service;

import com.spring.BeanPostProcessor;
import com.spring.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author yongzh
 * @version 1.0
 * @program: JavaTest
 * @description:
 * @date 2023/3/16 22:51
 */
@Component()
public class yongzhBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {

     /*   if("userService".equals(beanName)){
            System.out.println("初始化前");
            ((UserServiceImpl)bean).setName("芜湖起飞");
        }*/
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {

        System.out.println("初始化后");
        if("userService".equals(beanName)){
            Object proxyInstance = Proxy.newProxyInstance(yongzhBeanPostProcessor.class.getClassLoader(), bean.getClass().getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    System.out.println("代理逻辑");

                    return method.invoke(bean,args);
                }
            });
            return proxyInstance;
        }


        return bean;
    }
}
