package org.yongzh;

import org.example.HelloService;
import org.example.protocol.HttpServer;
import org.example.register.LocalRegister;

/**
 * @author yongzh
 * @version 1.0
 * @program: JavaTest
 * @description:
 * @date 2023/3/24 22:14
 */
public class Provider {
    public static void main(String[] args) {

        LocalRegister.regist(HelloService.class.getName(), "v1",HelloServiceImpl.class);
        LocalRegister.regist(HelloService.class.getName(), "v2",HelloServiceImpl2.class);

        HttpServer httpServer = new HttpServer();
        httpServer.start("localhost",8080);
    }
}
