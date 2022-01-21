package com.lry.lostchildinfo.common;

import org.springframework.util.ObjectUtils;

import java.util.HashMap;

/**
 * @author jdl
 * @create 2021-1-21 16:19
 * @description
 */
public class Result extends HashMap<String, Object> {


    private static final String CODE_TAG = "code";
    private static final String MSG_TAG = "msg";
    private static final String DATA_TAG = "data";

    /* 初始化 */
    public Result(){

    }
    /* 两个构造 */
    public Result(String code, String msg){
        this.put(CODE_TAG,code);
        this.put(MSG_TAG,msg);
    }
    /* 构造 */
    public Result(int code , String msg, Object data){
        this.put(CODE_TAG,code);
        this.put(MSG_TAG,msg);
        if (!ObjectUtils.isEmpty(data)){
            this.put(DATA_TAG,data);
        }
    }
    // 返回success
    public static Result success(){
        return Result.success("操作成功");
    }

    public static Result success(String msg){
        return  Result.success(msg,null);
    }

    public static Result success(Object data){
        return  Result.success("操作成功",data);
    }

    public static Result success(String msg,Object data){
        return new Result(HttpState.HTTP_OK,msg,data);
    }

    // 返回error

    public static Result error(){
       return  Result.error("操作失败");
    }

    public static Result error(String msg){
      return  Result.error(msg,null);
    }

    public static Result error(Object data){
      return  Result.error("操作失败",data);
    }

    public static Result error(String msg,Object data){
        return new Result(HttpState.HTTP_ERROR,msg,data);
    }

}
