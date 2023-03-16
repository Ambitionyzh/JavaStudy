package com.yongzh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
  * @description:
  * @param: null
  * @return:
  * @author yognzh
  * @date: 2023/1/6 21:19
  */
//@Component它的作用就是实现bean的注入
/*@Component
public class UserServiceBase {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(propagation =  Propagation.NEVER)
    public void a(){
        jdbcTemplate.execute("insert into payment values (46,123)");
    }
}*/
