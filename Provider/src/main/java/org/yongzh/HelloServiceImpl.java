package org.yongzh;

import org.example.HelloService;

/**
 * @author yongzh
 * @version 1.0
 * @program: JavaTest
 * @description:
 * @date 2023/3/24 22:03
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        return "hello" + name;
    }
}
