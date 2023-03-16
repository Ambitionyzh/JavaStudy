package com.spring;

import org.springframework.beans.BeansException;
import org.springframework.lang.Nullable;

/**
 * @author yongzh
 * @version 1.0
 * @description: TODO
 * @date 2023/3/16 22:49
 */
public interface BeanPostProcessor {
    @Nullable
     Object postProcessBeforeInitialization(Object bean, String beanName);
    @Nullable
     Object postProcessAfterInitialization(Object bean, String beanName);
}

