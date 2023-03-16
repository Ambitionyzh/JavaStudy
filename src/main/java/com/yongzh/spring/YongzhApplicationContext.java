package com.yongzh.spring;

import java.lang.annotation.Annotation;

/**
 * @author yongzh
 * @version 1.0
 * @program: YongzhSpring
 * @description:
 * @date 2023/3/17 0:41
 */
public class YongzhApplicationContext {

    private  Class configClass;

    public YongzhApplicationContext(Class configClass) {
        this.configClass = configClass;
        ComponentScan annotation = (ComponentScan) configClass.getAnnotation(ComponentScan.class);
        String path = annotation.value();
        System.out.println(path);

        //扫描-->找到Component注解的类--->生成bean对象

    }

    public Object getBean(String beanName){
        return  null;
    }
}
