package org.springboot;

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
public class JettyCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {

        try {
            context.getClassLoader().loadClass("org.eclipse.jetty.server.Server");
        } catch (ClassNotFoundException e) {
            return false;
        }
        return true;
    }
}
