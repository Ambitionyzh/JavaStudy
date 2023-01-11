package com.yongzh.service;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author yongzh
 * @version 1.0
 * @program: JavaTest
 * @description: orderservice
 * @date 2023/1/6 21:23
 */
@Component
public class OrderService {

    @Bean
    public OrderService orderService1(){
        return new OrderService();
    }
    @Bean
    public OrderService orderService2(){
        return new OrderService();
    }

}
