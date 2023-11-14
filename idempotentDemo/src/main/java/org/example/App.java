package org.example;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@Aspect
public class App 
{
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
