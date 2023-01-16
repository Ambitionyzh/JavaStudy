package com.yongzh.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author yongzh
 * @version 1.0
 * @program: JavaTest
 * @description: AOP切面
 * @date 2023/1/16 23:20
 */
/*@Aspect
@Component*/
public class AopAspect {

    @Before("execution(public void com.yongzh.service.UserService.test())")
    public  void aopBefore(JoinPoint joinPoint){
        System.out.println("AopBefore");
    }

}
