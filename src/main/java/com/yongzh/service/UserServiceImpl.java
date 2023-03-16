package com.yongzh.service;

import com.spring.*;


/**
 * @author yongzh
 * @version 1.0
 * @program: JavaTest
 * @description:
 * @date 2023/3/12 11:12
 */
@Component("userService")
public class UserServiceImpl implements Userservice1 {


    @Autowired
    private OrderService1 orderService1;

    private String beanName;
    private String name;

    public void setName(String name) {
        this.name = name;
    }


  /*  @Override
    public void setBeanName(String name) {
        beanName = name;
    }*/
    public void test(){
        System.out.println(orderService1);
        System.out.println(name);
    }



}
