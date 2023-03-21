package org.springboot;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;

/**
 * @author yongzh
 * @version 1.0
 * @program: SpringBootDemos
 * @description:
 * @date 2023/3/21 0:24
 */
public class YongzhOnClassCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        //metadata为该类上所有注解的信息
        try {
            Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(YongzhConditionalONClass.class.getName());
            String className = (String) annotationAttributes.get("value");
            context.getClassLoader().loadClass(className);
            return true;

        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}
