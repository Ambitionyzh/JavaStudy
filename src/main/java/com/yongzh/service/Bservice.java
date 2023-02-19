package com.yongzh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author yongzh
 * @version 1.0
 * @program: JavaTest
 * @description:
 * @date 2023/2/18 22:18
 */
@Component
public class Bservice {

    @Autowired
    private Aservice aservice;
}
