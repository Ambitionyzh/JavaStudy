package org.example.proxy;

import org.example.LoadBalance.LoadBalance;
import org.example.common.Invocation;
import org.example.common.URL;
import org.example.protocol.HttpClient;
import org.example.register.MapRemoteRegister;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yongzh
 * @version 1.0
 * @program: JavaTest
 * @description:
 * @date 2023/3/26 9:24
 */
public class ProxyFactory {

    public static <T> T getProxy(Class interfaceClass){
        Object proxyInstance = Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                String mock =System.getProperty("mock");
                if(mock !=null && mock.startsWith("return:")){
                    String result = mock.replace("return:","");
                    return result;
                }


                Invocation invocation = new Invocation(interfaceClass.getName(),method.getName(),method.getParameterTypes(),args);

                HttpClient httpClient = new HttpClient();

                //这里原先是写死的写法，但是不够具有适用性。想到要根据接口名称去找到提供服务的ip地址及端口号，那就使用服务注册。
                //String result = httpClient.send("localhost", 8080, invocation);

                //服务发现
                List<URL> list = MapRemoteRegister.get(interfaceClass.getName());

                //发现注册中心有多个提供服务的机器，怎么发到其中的一台？负载均衡


                List<URL> invokeUrls = new ArrayList<>();
                //服务调用
                String result = "";
                int max =3;
                while(max > 0){

                    //负载均衡
                    URL url = LoadBalance.random(list);
                    list.remove(url);
                    invokeUrls.add(url);
                try {
                    result = httpClient.send(url.getHostName(),url.getPort(),invocation);
                }catch (Exception e){

                    //服务容错回调的处理可以写在这里
                    if(max-- != 0) continue;;
                    return "报错了";
                }
                }


                return result;
            }
        });
        return (T) proxyInstance;
    }
}
