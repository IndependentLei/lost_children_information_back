package com.lry.lostchildinfo.common;

import lombok.Data;

/**
 * @author : jdl
 * @description : 用户状态枚举
 * @ClassName : UserState
 * @create : 2022-01-23 13:08
 */
public enum  UserState {
    normal(0,"正常"),
    banned(1,"禁言"),
    disabled(2,"禁止登录")
    ;
    private Integer type;
    private String info;

    UserState(int type, String info) {
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
