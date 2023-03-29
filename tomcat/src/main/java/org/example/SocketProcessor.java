package org.example;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * @author yongzh
 * @version 1.0
 * @program: JavaTest
 * @description:
 * @date 2023/3/28 21:41
 */
public class SocketProcessor implements Runnable{

    private Socket socket;
    private Tomcat tomcat;

    public SocketProcessor(Socket socket,Tomcat tomcat) {
        this.socket = socket;
        this.tomcat = tomcat;
    }

    @Override
    public void run() {
        processSocket(socket);

    }

    private void processSocket(Socket socket) {
        try {
            //首先接受socket连接，然后从socket中去取数据，然后按照http协议格式解析数据
            InputStream inputStream =socket.getInputStream();
            byte[]bytes = new byte[1024];
            inputStream.read(bytes);

            //解析字节流，遇到一个空格就退出
            int pos = 0;
            int begin =0,end = 0;
            for (;pos < bytes.length;pos++,end++){
                if(bytes[pos] == ' ')break;
            }
            //组合空格之前的字节流，转换成字符串就是请求方法
            StringBuilder method = new StringBuilder();
            for(; begin < end; begin++){
                method.append((char) bytes[begin]);
            }

            pos++;
            begin++;
            end++;
            for (; pos < bytes.length; pos++, end++) {
                if (bytes[pos] == ' ') break;
            }
            StringBuilder url = new StringBuilder();
            for (; begin < end; begin++) {
                url.append((char) bytes[begin]);
            }

            // 解析协议版本
            pos++;
            begin++;
            end++;
            for (; pos < bytes.length; pos++, end++) {
                if (bytes[pos] == '\r') break;
            }
            StringBuilder protocl = new StringBuilder();
            for (; begin < end; begin++) {
                protocl.append((char) bytes[begin]);
            }

            //解析后得到请求
            Request request = new Request(method.toString(), url.toString(), protocl.toString(),socket);
            //构造一个响应对象
            Response response = new Response(request);

            String requestUrl = request.getRequestURL().toString();
            requestUrl = requestUrl.substring(1);
            String[] parts = requestUrl.split("/");
            System.out.println(requestUrl);
            String appName = parts[0];
            Context context = tomcat.getContextMap().get(appName);

            if(parts.length > 1){
             //匹配servlet,doGet
                Servlet servlet = context.getByUrlPattern(parts[1]);
                if(servlet != null){
                    servlet.service(request,response);

                    //发送响应
                    response.complete();
                }else{
                    DefaultServlet defaultServlet = new DefaultServlet();
                    defaultServlet.service(request,response);

                    //发送响应
                    response.complete();
                }




        /*    YongzhServlet yongzhServlet = new YongzhServlet();
            yongzhServlet.service(request,response);*/

            }


        }catch (IOException e){
            throw new RuntimeException(e);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }
}
