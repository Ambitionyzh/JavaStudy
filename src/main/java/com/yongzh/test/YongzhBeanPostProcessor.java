package com.yongzh.test;

import com.yongzh.spring.BeanPostProcessor;
import com.yongzh.spring.Component;

/**
 * @author yongzh
 * @version 1.0
 * @program: YongzhSpring
 * @description:
 * @date 2023/3/19 9:41
 */
@Component()
public class YongzhBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {

        if("userService".equals(beanName)){
            System.out.println(bean);
            System.out.println("初始化前");
        }


        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {


        if("userService".equals(beanName)){
            System.out.println(bean);
            System.out.println("初始化后");
        }

        return bean;
    }
}
