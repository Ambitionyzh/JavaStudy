package org.yongzh;

import org.example.HelloService;
import org.example.common.URL;
import org.example.protocol.HttpServer;
import org.example.register.LocalRegister;
import org.example.register.MapRemoteRegister;

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

        //注册中心注册
        URL url = new URL("localhost",8080);

        MapRemoteRegister.regist(HelloService.class.getName(),url);
        HttpServer httpServer = new HttpServer();
        httpServer.start(url.getHostName(),url.getPort());
    }
}
