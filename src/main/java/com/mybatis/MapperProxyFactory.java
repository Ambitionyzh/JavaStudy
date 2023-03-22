package com.mybatis;

import com.mybatisDemo.User;

import javax.xml.transform.Result;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yongzh
 * @version 1.0
 * @program: JavaTest
 * @description:
 * @date 2023/3/22 21:51
 */
public class MapperProxyFactory {
    public static<T> T getMapper(Class<T> mapper){
        Object proxyInstance = Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{mapper}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
               //解析sql----->执行sql------>结果处理
                //JDBC
                Class.forName("com.mysql.cj.jdbc.Driver");

                //1.创建数据库连接
                Connection connection = getConnection();

                Select annotation = method.getAnnotation(Select.class);
                String sql = annotation.value();

                Map<String,Object> paramValueMapping = new HashMap<>();
                Parameter[] parameters = method.getParameters();
                for (int i = 0; i < parameters.length; i++) {
                    Parameter parameter = parameters[i];
                    String name = parameter.getAnnotation(Param.class).value();
                    paramValueMapping.put(parameter.getName(),args[i]);
                    paramValueMapping.put(name,args[i]);
                }

                //2.构造PreparedStatement
                PreparedStatement statement = connection.prepareStatement("select * from user1 where name=? and age = ? and first_name = ?");

                statement.setString(1,"wuhu");

                //3.执行PreparedStatement
                statement.execute();

                //4.根据当前执行方法的返回类型封装结果
                List<User> list = new ArrayList<>();
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()){
                    User user = new User();
                    user.setId(resultSet.getInt("id"));
                    user.setName(resultSet.getString("name"));
                    user.setAge(resultSet.getInt("age"));
                    list.add(user);
                }
                System.out.println(list);

                //5.关闭数据库连接
                connection.close();

                return list;
            }
        });
        return (T) proxyInstance;
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?useUnicode=true&characterEncoding=utf8&useSSL=TRUE&serverTimezone=UTC", "root", "root");
    }

}
