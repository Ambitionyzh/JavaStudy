package com.example.Controller;


import com.example.Result.Result;
import com.example.Service.SysLogService;
import com.example.annotation.RepeatSubmit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yongzh
 * @version 1.0
 * @program: JavaTest
 * @description:
 * @date 2023/11/13 23:27
 */

@RestController
@RequestMapping("/test")
public class TestController {



    // 默认1s，方便测试查看，写10s
    @RepeatSubmit(expireTime = 10)
    @PostMapping("/saveSysLog")
    public Result saveSysLog(){
        return Result.success("wuhu");
    }
}