package com.lry.lostchildinfo.aspectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author : jdl
 * @description : 面向切面
 * @ClassName : LogAspectJ
 * @create : 2022-01-21 16:34
 */
@Aspect
public class LogAspectJ {

    // 自定义切面
    @Pointcut("@annotation(com.lry.lostchildinfo.annotation.OperationLog)")
    public void operationLog(){
    }

    @Around(value = "operationLog()")
    public Object myAround(ProceedingJoinPoint pjp){
        //TODO....
        return 11;
    }
}
