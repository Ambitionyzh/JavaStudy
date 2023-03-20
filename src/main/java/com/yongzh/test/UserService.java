package com.yongzh.test;

import com.yongzh.spring.Autowired;
import com.yongzh.spring.Component;
import com.yongzh.spring.InitializiBean;
import com.yongzh.spring.Scope;

/**
 * @author yongzh
 * @version 1.0
 * @program: YongzhSpring
 * @description:
 * @date 2023/3/17 0:44
 */
@Component("userService")
public class UserService implements InitializiBean {


    @Autowired
    private OrderService orderService;

    private User defaultUser;
    public void test(){
        System.out.println(orderService);
        System.out.println(defaultUser);
    }

    @Override
    public void afterPropertieSet() throws Exception {
        defaultUser =  new User();
    }
}
