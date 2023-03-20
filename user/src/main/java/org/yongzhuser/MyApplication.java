package org.yongzhuser;

import org.springboot.TomcatWebServer;
import org.springboot.WebServerAutoConfiguration;
import org.springboot.YongzhSpringApplication;
import org.springboot.YongzhSpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

/**
 * Hello world!
 *
 */
@YongzhSpringBootApplication

public class MyApplication
{

    public static void main( String[] args )
    {
        YongzhSpringApplication.run(MyApplication.class);
    }
}
