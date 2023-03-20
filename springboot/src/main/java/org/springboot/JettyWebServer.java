package org.springboot;

/**
 * @author yongzh
 * @version 1.0
 * @description: TODO
 * @date 2023/3/21 0:08
 */
public class JettyWebServer implements WebServer{
    @Override
    public void Start() {
        System.out.println("启动Jetty");
    }
}
