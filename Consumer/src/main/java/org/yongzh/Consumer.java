package org.yongzh;

import org.example.HelloService;
import org.example.common.Invocation;
import org.example.protocol.HttpClient;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class Consumer
{
    public static void main( String[] args ) throws IOException {
        /*HelloService helloService=?;
        String result = helloService.sayHello("wuhu");*/
        Invocation invocation = new Invocation(HelloService.class.getName(),"sayHello",new Class[]{String.class},new Object[]{"whuhu"});

        HttpClient httpClient = new HttpClient();
        String result = httpClient.send("localhost", 8080, invocation);
        System.out.println(result);
    }
}
