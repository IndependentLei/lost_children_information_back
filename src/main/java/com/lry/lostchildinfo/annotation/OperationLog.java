package com.lry.lostchildinfo.annotation;

/**
 * 自定义日志注解
 */
public @interface OperationLog {
    // 是否插入数据库
    boolean value() default true;

    // 描述
    String describe();
}
