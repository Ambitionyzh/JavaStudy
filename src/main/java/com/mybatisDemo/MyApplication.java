package com.mybatisDemo;

import com.mybatis.MapperProxyFactory;

import java.util.List;

/**
 * @author yongzh
 * @version 1.0
 * @program: JavaTest
 * @description:
 * @date 2023/3/22 21:43
 */
public class MyApplication {
    public static void main(String[] args) {
        UserMapper userMapper = MapperProxyFactory.getMapper(UserMapper.class);
        List<User> result = userMapper.getUser("wuhu",21);
        System.out.println(result);

        //userMapper.getUserById();
    }
}
