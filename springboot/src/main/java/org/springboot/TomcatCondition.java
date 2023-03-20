package org.springboot;

import org.apache.catalina.startup.Tomcat;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author yongzh
 * @version 1.0
 * @program: SpringBootDemos
 * @description:
 * @date 2023/3/21 0:24
 */
public class TomcatCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {

        try {
            context.getClassLoader().loadClass("org.apache.catalina.startup.Tomcat");
        } catch (ClassNotFoundException e) {
            return false;
        }
        return true;
    }
}
