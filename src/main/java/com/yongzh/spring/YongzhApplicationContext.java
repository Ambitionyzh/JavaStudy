package com.yongzh.spring;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yongzh
 * @version 1.0
 * @program: YongzhSpring
 * @description:
 * @date 2023/3/17 0:41
 */
public class YongzhApplicationContext {

    private  Class configClass;

    //单例池
    private ConcurrentHashMap<String,Object> singletonObjects = new ConcurrentHashMap<>();

    //Spring中定义了哪些bean
    private ConcurrentHashMap<String,BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    private List<BeanPostProcessor> beanPostProcessorList = new ArrayList<>();

    public YongzhApplicationContext(Class configClass) throws Exception {
        this.configClass = configClass;

        scan(configClass);


        preInstantiateSingletons();//实例化单例--->单例池

    }

    private void preInstantiateSingletons() throws Exception {
        for (Map.Entry<String,BeanDefinition> entry: beanDefinitionMap.entrySet()) {
            String beanName = entry.getKey();
            BeanDefinition beanDefinition =beanDefinitionMap.get(beanName);


            if("singleTon".equals(beanDefinition.getScope())){
                Object bean =createBean(beanName,beanDefinition);
                singletonObjects.put(beanName,bean);
            }


        }
    }

    public Object createBean(String beanName,BeanDefinition beanDefinition) throws Exception {

        Class clazz = beanDefinition.getClazz();
        //bean实例化
        Object instance = clazz.newInstance();
        //依赖注入，遍历类所有的属性
        for (Field declaredField : clazz.getDeclaredFields()) {
            //找到带有Autowired注解的
            if(declaredField.isAnnotationPresent(Autowired.class)){

                Object bean = getBean(declaredField.getName());

                declaredField.setAccessible(true);
                declaredField.set(instance,bean);
            }

        }
        //初始化前
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
            instance = beanPostProcessor.postProcessBeforeInitialization(instance,beanName);
        }

        //初始化
        if(instance instanceof  InitializiBean){
            ((InitializiBean)instance).afterPropertieSet();
        }
        //初始化后
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
            instance = beanPostProcessor.postProcessAfterInitialization(instance,beanName);
        }


        return instance;
    }

        //扫描-->找到Component注解的类--->生成bean对象
    private void scan(Class configClass) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        ComponentScan annotation = (ComponentScan) configClass.getAnnotation(ComponentScan.class);
        String path = annotation.value();
        path = path.replace(".","/");
        ClassLoader classLoader = YongzhApplicationContext.class.getClassLoader();
        URL resource = classLoader.getResource(path);
        File file = new File(resource.getFile());
        File[] files =file.listFiles();
        for (File file1 : files) {

            String fileName = file1.getAbsolutePath();
            if(fileName.endsWith(".class")){
            String className = fileName.substring(fileName.indexOf("com"),fileName.indexOf(".class"));
            className = className.replace("\\",".");
            Class<?> clazz = classLoader.loadClass(className);
            if(clazz.isAnnotationPresent(Component.class)){
                if (BeanPostProcessor.class.isAssignableFrom(clazz)) {
                    BeanPostProcessor instance = (BeanPostProcessor) clazz.newInstance();
                    beanPostProcessorList.add(instance);
                }


                Component component = clazz.getDeclaredAnnotation(Component.class);
                String beanName = component.value();

                BeanDefinition beanDefinition = new BeanDefinition();
                beanDefinition.setClazz(clazz);

                if(clazz.isAnnotationPresent(Scope.class)){
                    Scope scope = clazz.getDeclaredAnnotation(Scope.class);
                    beanDefinition.setScope(scope.value());
                }else{
                    beanDefinition.setScope("singleTon");
                }
                beanDefinitionMap.put(beanName,beanDefinition);
            }

            }

        }
    }

    public Object getBean(String beanName) throws Exception {
        if(beanDefinitionMap.containsKey(beanName)){
            BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
            if(beanDefinition.getScope().equals("singleTon")){
                Object o = singletonObjects.get(beanName);
                return o;
            }else {
                Object bean =createBean(beanName,beanDefinition);
                return bean;
            }

        }else{

        }
        return  null;
    }
}
