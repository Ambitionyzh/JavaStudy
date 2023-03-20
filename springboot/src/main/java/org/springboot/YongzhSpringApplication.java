package org.springboot;

import org.apache.catalina.*;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.util.Map;

/**
 * @author yongzh
 * @version 1.0
 * @program: SpringBootDemos
 * @description:
 * @date 2023/3/20 22:50
 */
public class YongzhSpringApplication {
    public static void run(Class<?> configClazz){
        //创建Spring容器
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(configClazz);
        applicationContext.refresh();


        WebServer webServer =getWebServer(applicationContext);
        webServer.Start();
        //startTomcat(applicationContext);
    }

    private static WebServer getWebServer(ApplicationContext applicationContext) {
        //启动tomcat//启动jetty
        Map<String,WebServer> webServerMap = applicationContext.getBeansOfType(WebServer.class);
        if(webServerMap.isEmpty()){
            throw new NullPointerException();
        }
        if(webServerMap.size() > 1){
            throw  new IllegalStateException();
        }
        //返回唯一一个
        return webServerMap.values().stream().findFirst().get();
    }

    private static void startTomcat(WebApplicationContext applicationContext){
        Tomcat tomcat = new Tomcat();

        Server server = tomcat.getServer();
        Service service = server.findService("Tomcat");

        Connector connector = new Connector();
        connector.setPort(8081);

        Engine engine = new StandardEngine();
        engine.setDefaultHost("localhost");

        Host host = new StandardHost();
        host.setName("localhost");

        String conntextPath = "";
        Context context = new StandardContext();
        context.setPath(conntextPath);
        context.addLifecycleListener(new Tomcat.FixContextListener());

        host.addChild(context);
        engine.addChild(host);

        service.setContainer(engine);
        service.addConnector(connector);


        tomcat.addServlet(conntextPath,"dispatcher",new DispatcherServlet(applicationContext));
        context.addServletMappingDecoded("/*","dispatcher");
        try {
            tomcat.start();
        }catch (LifecycleException e){
            e.printStackTrace();
        }

    }
}
