package com.lry.lostchildinfo.exception;

import com.lry.lostchildinfo.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author : jdl
 * @description : 全局异常捕获
 * @ClassName : ExceptionHandle
 * @create : 2022-01-21 19:45
 */
@ControllerAdvice
@Slf4j
public class ExceptionHandle {
    /**
     * 捕获运行抛出的异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = RuntimeException.class)
    public Result runtimeException(RuntimeException e){
        log.error(e.getMessage());
        return Result.error(e.getMessage());
    }


    /**
     * 捕获入参异常
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result exceptionHandler(MethodArgumentNotValidException e) {
        log.error(e.getMessage());
        return Result.error(e.getMessage());
    }
    /**
     * 捕获业务的异常
     * @param e
     * @return
     */
    @ExceptionHandler(ServiceException.class)
    public Result serviceException(ServiceException e){
        log.error(e.getMessage());
        return Result.error(e.getMessage());
    }
}