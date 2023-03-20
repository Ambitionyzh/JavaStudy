package com.yongzh.spring;


/**
 * @author yongzh
 * @version 1.0
 * @description: TODO
 * @date 2023/3/16 22:49
 */
public interface BeanPostProcessor {

     Object postProcessBeforeInitialization(Object bean, String beanName);

     Object postProcessAfterInitialization(Object bean, String beanName);
}

