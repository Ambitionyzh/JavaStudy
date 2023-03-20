package org.yongzhuser.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yongzh
 * @version 1.0
 * @program: SpringBootDemos
 * @description:
 * @date 2023/3/20 23:03
 */
@RestController
public class UserController {

    @GetMapping("/test")
    public String test(){
        return "yongzh123123";
    }
}
