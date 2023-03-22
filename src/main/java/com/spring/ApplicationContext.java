package com.spring;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yongzh
 * @version 1.0
 * @program: JavaTest
 * @description:
 * @date 2023/3/12 10:55
 */
public class ApplicationContext {
    private Class configClass;

    private ConcurrentHashMap<String,Object> singletonObjects = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String,BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
    private List<BeanPostProcessor> beanPostProcessorList = new ArrayList<>();

    public ApplicationContext(Class configClass) throws Exception {
        this.configClass = configClass;
        //解析配置类
        //ComponentScan注解---》扫描路径---》扫描-----》BeanDefinition--》beanDefinitionMap
        scan(configClass);

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
        Object instance = clazz.getDeclaredConstructor().newInstance();

        //依赖注入
        for (Field declaredField : clazz.getDeclaredFields()) {
            if(declaredField.isAnnotationPresent(Autowired.class)){
                Object bean = getBean(declaredField.getName());
                declaredField.setAccessible(true);
                declaredField.set(instance,bean);
            }

        }
        //Aware回调
        if(instance instanceof BeanNameAware){//实例对象是否实现了这个接口
            ((BeanNameAware)instance).setBeanName(beanName);
        }
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
            instance = beanPostProcessor.postProcessBeforeInitialization(instance,beanName);
        }

        //初始化
        if(instance instanceof  InitializiBean){
            ((InitializiBean)instance).afterPropertieSet();
        }

        for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
            instance = beanPostProcessor.postProcessAfterInitialization(instance,beanName);
        }

        return  instance;
    }

    private void scan(Class configClass) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        ComponentScan componentScanAnnotation = (ComponentScan) configClass.getDeclaredAnnotation(ComponentScan.class);
        String path = componentScanAnnotation.value();

        //扫描
        //Bootstrap -->jre/lib
        //Ext--->jre/ext/lib
        //App--->classpath
        ClassLoader classLoader = ApplicationContext.class.getClassLoader();
        URL resource = classLoader.getResource("com/yongzh/service");
        //File file = new File(resource.getFile());
        File file = new File("F:\\Program Files\\IntelliJ IDEA Community Edition 2022.3.1\\jbr\\bin\\test\\JavaTest\\production\\JavaTest\\com\\yongzh\\service");

        if(file.isDirectory()){
            File[] files =file.listFiles();
            for (File file1 : files) {
                //拿到file name 并且转换成class对象
                String fileName = file1.getAbsolutePath();
                String className = fileName.substring(fileName.indexOf("com"),fileName.indexOf(".class"));
                className = className.replace("\\",".");
                Class<?> clazz = classLoader.loadClass(className);
                if (clazz.isAnnotationPresent(Component.class)){//表示当前这个类是一个Bean

                    if (BeanPostProcessor.class.isAssignableFrom(clazz)) {
                        BeanPostProcessor instance = (BeanPostProcessor) clazz.getDeclaredConstructor().newInstance();
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
        return null;
    }
}
