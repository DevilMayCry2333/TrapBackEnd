package cn.huihongcloud.exception;

import cn.huihongcloud.entity.common.Result;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by 钟晖宏 on 2018/10/8
 */
@RestControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public Result handleException(Exception e){

        e.printStackTrace();
        return Result.failed();
    }
}
