package com.yongzh;

import java.lang.annotation.Annotation;

import com.yongzh.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Administrator
 * @version 1.0
 * @program: LeetCode
 * @description: Bean推断构造方法
 * @date 2023/1/6 20:41
 */
public class Test {
    public  static void main(String[] args){
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = (UserService)annotationConfigApplicationContext.getBean("userService");
        userService.test();

        System.out.println(annotationConfigApplicationContext.getBean("orderService"));
        System.out.println(annotationConfigApplicationContext.getBean("orderService1"));
        System.out.println(annotationConfigApplicationContext.getBean("orderService2"));


    }
}
