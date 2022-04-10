package com.lry.lostchildinfo.entity.enums;

import lombok.Data;

/**
 * @author : jdl
 * @description : 用户状态枚举
 * @ClassName : UserState
 * @create : 2022-01-23 13:08
 */
public enum  UserState {
    NORMAL(0,"正常"),
    BANNED(1,"禁言"),
    DISABLED(2,"禁止登录")
    ;
    private final Integer type;
    private final String info;

    UserState(Integer type, String info) {
        this.type = type;
        this.info = info;
    }
    public int getType() {
        return this.type;
    }

    public String getInfo() {
        return this.info;
    }

}
