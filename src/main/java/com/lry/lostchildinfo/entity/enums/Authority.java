package com.lry.lostchildinfo.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description : 权限
 * @ClassName : Authority
 * @Author : jdl
 * @Create : 2022-04-04 11:04
 */
@Getter
@AllArgsConstructor
public enum Authority {
    ADMINISTRATOR("ROLE_ADMIN","管理员"),
    CUSTOMER("ROLE_CUSTOMER","客户"),
    VOLUNTEER("ROLE_VOLUNTEER","自愿者");

    private final String role;
    private final String value;
}
