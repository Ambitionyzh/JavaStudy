package org.example;

import javax.servlet.Servlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Hello world!
 *
 */
public class Tomcat
{
    private Map<String,Context> contextMap = new HashMap<>();
    public void start(){
        try {
            ExecutorService executorService = Executors.newFixedThreadPool(20);

            ServerSocket serverSocket = new ServerSocket(8080);

            while (true){
                Socket socket = serverSocket.accept();
                executorService.execute(new SocketProcessor(socket,this));
            }

        }catch (IOException e){

        }

    }
    private void processSocket(Socket socket){

    }
    public static void main( String[] args )
    {
        Tomcat tomcat = new Tomcat();
        tomcat.deployApps();
        tomcat.start();
    }

    private void deployApps() {
        File webapps = new File("H:\\Project\\JavaTest\\tomcat\\webapps");

        for (String app : webapps.list()) {
           deployApp(webapps,app);
            
        }
    }

    private void deployApp(File webapps,String appName) {
        //有哪些servlet

        Context context = new Context(appName);

        File appDirectory = new File(webapps,appName);
        File classesDirectory = new File(appDirectory,"classes");
        List<File> files = getAllFilePath(classesDirectory);
        for (File clazz : files) {
            //找到所有的servlet类
            String name = clazz.getPath();
            name = name.replace(classesDirectory.getPath() + "\\", "");
            name = name.replace(".class", "");
            name = name.replace("\\", ".");

            Class<?> servletClass = null;
            try {
                //自定义类加载器加载servlet类,因为webapaps不在src文件夹下，所以spring无法识别并用类加载器加载。
                WebappClassLoader webappClassLoader = new WebappClassLoader(new URL[]{classesDirectory.toURL()});
                servletClass =webappClassLoader.loadClass(name);

                //判断当前servlet类是否继承了HttpServlet
                if(HttpServlet.class.isAssignableFrom(servletClass)){
                    //当前servlet类是否有WebServlet注解
                    if(servletClass.isAnnotationPresent(WebServlet.class)){
                        WebServlet annotation = servletClass.getAnnotation(WebServlet.class);
                        //拿到注解的url路径
                        String[] urlPatterns = annotation.urlPatterns();
                       //将该路径绑定到当前servlet类上，即加入urlPatternMapping。后面请求这个路径都有该servlet类去处理
                        for (String urlPattern : urlPatterns) {
                            context.addUrlPatternMapping(urlPattern,(Servlet) servletClass.newInstance());
                        }

                    }
                }

            } catch (ClassNotFoundException | MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }

        }
        contextMap.put(appName,context);
    }
    public List<File> getAllFilePath(File srcFile) {
        List<File> result = new ArrayList<>();
        File[] files = srcFile.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    result.addAll(getAllFilePath(file));
                } else {
                    result.add(file);
                }
            }
        }

        return result;

    }

    public Map<String, Context> getContextMap() {
        return contextMap;
    }
}
