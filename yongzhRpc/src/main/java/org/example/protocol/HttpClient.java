package org.example.protocol;

import org.apache.commons.io.IOUtils;
import org.example.common.Invocation;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author yongzh
 * @version 1.0
 * @program: JavaTest
 * @description:
 * @date 2023/3/24 22:49
 */
public class HttpClient {
    public String send(String hostname, Integer port, Invocation invocation) throws IOException {
        //用户的配置
        URL url = new URL("http",hostname,port,"/");
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setDoOutput(true);

        //配置
        OutputStream outputStream = httpURLConnection.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(outputStream);

        //根据oos发送invocation对象
        oos.writeObject(invocation);
        oos.flush();
        oos.close();

        //获取请求的结果
        InputStream inputStream = httpURLConnection.getInputStream();
        String result = IOUtils.toString(inputStream);
        return result;


    }
}
