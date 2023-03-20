package org.springboot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @author yongzh
 * @version 1.0
 * @program: SpringBootDemos
 * @description:
 * @date 2023/3/21 0:22
 */
@Configuration
public class WebServerAutoConfiguration {
    @Bean
    @Conditional(TomcatCondition.class)
    public TomcatWebServer tomcatWebServer(){
        return new TomcatWebServer();
    }
    @Bean
    @Conditional(JettyCondition.class)
    public JettyWebServer jettyWebServer(){
        return new JettyWebServer();
    }
}
