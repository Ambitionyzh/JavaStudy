package com.yongzh.service;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
  * @description:
  * @param: null
  * @return:
  * @author yognzh
  * @date: 2023/1/6 21:19
  */
//@Component它的作用就是实现bean的注入
@Component
public class UserService {

    private  OrderService orderService;


  /*  public UserService() {
        System.out.println("0");
    }*/

    @Autowired
    public UserService(OrderService orderService){
        this.orderService = orderService;
        System.out.println("1");
    }

    public UserService(OrderService orderService ,OrderService orderService1){
        this.orderService = orderService;
        System.out.println("2");
    }
    public void test(){
        System.out.println(orderService);
    }
}
