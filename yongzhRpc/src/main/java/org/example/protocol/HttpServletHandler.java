package org.example.protocol;

import org.apache.commons.io.IOUtils;
import org.example.common.Invocation;
import org.example.register.LocalRegister;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author yongzh
 * @version 1.0
 * @program: JavaTest
 * @description:
 * @date 2023/3/24 22:21
 */
public class HttpServletHandler {
    public void handler(HttpServletRequest req, HttpServletResponse httpServletResponse) throws IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        //处理请求--->接口、方法、方法参数
        Invocation invocation = (Invocation)new ObjectInputStream(req.getInputStream()).readObject();
        //如何根据接口名拿到对应的实现类，本地注册
        String interfaceName=invocation.getInterfaceName();
        Class classImpl = LocalRegister.get(interfaceName,"v1");
        Method method = classImpl.getMethod(invocation.getMethodName(), invocation.getParameterTypes());
        String result =(String) method.invoke(classImpl.newInstance(), invocation.getParameters());

        IOUtils.write(result,httpServletResponse.getOutputStream());
    }
}
