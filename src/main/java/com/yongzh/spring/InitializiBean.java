package com.yongzh.spring;

/**
 * @author yongzh
 * @version 1.0
 * @program: JavaTest
 * @description:
 * @date 2023/3/16 22:12
 */
public interface InitializiBean {
    void afterPropertieSet() throws Exception;
}
