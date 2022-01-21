package com.lry.lostchildinfo.exception;

/**
 * @author : jdl
 * @description : 业务异常
 * @ClassName : ServiceException
 * @create : 2022-01-21 19:44
 */
public class ServiceException extends RuntimeException{
    private String msg;

    public ServiceException(){
        super();
    }
    public ServiceException(String msg){
        super(msg);
    }
}
