package org.example;

import javax.servlet.Servlet;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yongzh
 * @version 1.0
 * @program: JavaTest
 * @description:
 * @date 2023/3/29 22:27
 */
public class Context {
    private String appName;
    private Map<String, Servlet> urlPatternMapping = new HashMap<>();

    public Context(String appName) {
        this.appName = appName;
    }
    public void addUrlPatternMapping(String urlPattern,Servlet servlet){
        urlPatternMapping.put(urlPattern,servlet);
    }
    public Servlet getByUrlPattern(String urlPattern){

        for (String key : urlPatternMapping.keySet()) {
            if(key.contains(urlPattern)){
                return urlPatternMapping.get(key);
            }
        }

        return null;
    }

}
