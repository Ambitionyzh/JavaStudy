package org.springboot;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@ComponentScan
@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(WebServerAutoConfiguration.class)
public @interface YongzhSpringBootApplication {

}
