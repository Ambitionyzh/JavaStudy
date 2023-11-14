package org.example.Result;

import com.alibaba.nacos.api.utils.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yongzh
 * @version 1.0
 * @program: JavaTest
 * @description:
 * @date 2023/11/13 23:26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    private Integer code;

    private String msg;

    private T data;

    //成功码
    public static final Integer SUCCESS_CODE = 200;
    //成功消息
    public static final String SUCCESS_MSG = "SUCCESS";

    //失败
    public static final Integer ERROR_CODE = 201;
    public static final String ERROR_MSG = "系统异常,请联系管理员";
    //没有权限的响应码
    public static final Integer NO_AUTH_COOD = 999;

    //执行成功
    public static <T> Result<T> success(T data){
        return new Result<>(SUCCESS_CODE,SUCCESS_MSG,data);
    }
    //执行失败
    public static <T> Result failed(String msg){
        msg = StringUtils.isEmpty(msg)? ERROR_MSG : msg;
        return new Result(ERROR_CODE,msg,"");
    }
    //传入错误码的方法
    public static <T> Result failed(int code,String msg){
        msg = StringUtils.isEmpty(msg)? ERROR_MSG : msg;
        return new Result(code,msg,"");
    }
    //传入错误码的数据
    public static <T> Result failed(int code,String msg,T data){
        msg = StringUtils.isEmpty(msg)? ERROR_MSG : msg;
        return new Result(code,msg,data);
    }
}
