package com.lry.lostchildinfo.aspectj;

import com.lry.lostchildinfo.annotation.OperationLog;
import com.lry.lostchildinfo.entity.pojo.Log;
import com.lry.lostchildinfo.entity.pojo.User;
import com.lry.lostchildinfo.service.LogService;
import com.lry.lostchildinfo.utils.HttpUtil;
import com.lry.lostchildinfo.utils.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author : jdl
 * @description : 面向切面
 * @ClassName : LogAspectJ
 * @create : 2022-01-21 16:34
 */
@Aspect
@Component
public class LogAspectJ {

    @Autowired
    LogService logService;

    // 自定义切面
    @Pointcut("@annotation(com.lry.lostchildinfo.annotation.OperationLog)")
    public void operationLog(){
    }

    @After("operationLog()") //后置通知
    public void myAfter(JoinPoint joinPoint){
        System.out.println("后置通知");
    }

    // 环绕通知
    @Around(value = "operationLog()")
    public Object myAround(ProceedingJoinPoint joinPoint){
        //TODO....
        System.out.println("-------------------------------------------------->进入日志");
        Long startTime = System.currentTimeMillis();
        Object result = null;
        try {
            result =  joinPoint.proceed();
        }catch (Throwable e){
            e.printStackTrace();
        }

        Long endTime = System.currentTimeMillis();
        saveLong(joinPoint,endTime-startTime);
        return result;
    }

    public void saveLong(ProceedingJoinPoint joinPoint , Long executeTime){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 获取方法
        Method method = signature.getMethod();
        // 获得OperationLog注解
        OperationLog log = method.getAnnotation(OperationLog.class);
        // 是否插入数据库
        if (log.value()){
            // 获取方法名
            String methodName = method.getName();
            Object[] args = joinPoint.getArgs(); // 获得参数
            String[] paramsName = signature.getParameterNames(); // 获得请求参数的名称
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < paramsName.length; i++) {
                sb.append(" ")
                        .append(paramsName[i])
                        .append(":")
                        .append(args[i]);
            }
            // ip和url
            HttpServletRequest request = HttpUtil.getHttpServletRequest();
            StringBuffer requestURL = request.getRequestURL(); // 请求路径
            String requestIP = HttpUtil.getIPAddress(request); // 请求Ip
            if ("0:0:0:0:0:0:0:1".equals(requestIP)){
                requestIP = "127.0.0.1";
            }
            // 当前用户
            User user = SecurityUtil.getCurrentUser();
            Log myLog = new Log();
            myLog.setUserId(user.getUserId());
            myLog.setUserCode(user.getUserCode());
            myLog.setExecutionTime(executeTime);
            myLog.setMethodName(methodName);
            myLog.setRequestUrl(requestURL.toString());
            myLog.setRequestIp(requestIP);
            myLog.setParameterName(sb.toString());
            if(StringUtils.isNotBlank(log.describe()))
                myLog.setDescription(log.describe());
            logService.save(myLog);
        }
    }
}
