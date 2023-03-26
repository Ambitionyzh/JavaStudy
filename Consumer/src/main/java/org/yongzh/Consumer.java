package org.yongzh;

import org.example.HelloService;
import org.example.common.Invocation;
import org.example.protocol.HttpClient;
import org.example.proxy.ProxyFactory;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class Consumer
{
    public static void main( String[] args ) throws IOException {
        //创建helloService的代理对象使得调用helloService方法就像调用本地方法一样
        HelloService helloService= ProxyFactory.getProxy(HelloService.class);
        String result = helloService.sayHello("1231321");
        System.out.println(result);

    }
}
