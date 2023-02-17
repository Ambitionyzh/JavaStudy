package com.yongzh.reflection;

import java.lang.annotation.*;
import java.lang.reflect.Field;

/**
 * @author yongzh
 * @version 1.0
 * @program: JavaTest
 * @description: 注解映射
 * @date 2023/2/17 20:15
 */
public class AnnotationTest {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException {
        Class c1 = Class.forName("com.yongzh.reflection.Student2");

        //获取注解
        Annotation[] annotations =  c1.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
        }

        //获得注解value的值
        Tableyongzh tableyongzh = (Tableyongzh)c1.getAnnotation(Tableyongzh.class);
        String value = tableyongzh.value();
        System.out.println(value);

        //获得类指定的注解
        Field name = c1.getDeclaredField("name");
        Filedyongzh annotation = name.getAnnotation(Filedyongzh.class);
        System.out.println(annotation.columName());
        System.out.println(annotation.type());
        System.out.println(annotation.length());

    }


}
@Tableyongzh("db_student")
class Student2{
    @Filedyongzh(columName = "db_id",type = "int",length = 10)
    private int id;
    @Filedyongzh(columName = "db_age",type = "int",length = 10)
    private int age;
    @Filedyongzh(columName = "db_name",type = "varchar",length = 3)
    private String name;

    public Student2() {
    }

    public Student2(int id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student2{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}

//类名的注解
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface Tableyongzh{
    String value();
}

//属性的注解
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface Filedyongzh{
    String columName();
    String type();
    int length();
}

