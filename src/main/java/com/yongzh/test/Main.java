package com.yongzh.test;

import com.yongzh.spring.YongzhApplicationContext;

/**
 * @author yongzh
 * @version 1.0
 * @program: YongzhSpring
 * @description:
 * @date 2023/3/17 0:34
 */
public class Main {
    public static void main(String[] args) {
        //用Spring测试

        YongzhApplicationContext yongzhApplicationContext = new YongzhApplicationContext(AppConfig.class);
        UserService userService = (UserService) yongzhApplicationContext.getBean("userService");
        System.out.println(userService);
    }
}
