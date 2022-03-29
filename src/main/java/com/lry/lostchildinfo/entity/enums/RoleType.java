package com.lry.lostchildinfo.entity.enums;

import com.lry.lostchildinfo.entity.pojo.UserRole;
import lombok.Getter;

/**
 * @Description : 用户角色
 * @ClassName : UserRole
 * @Author : jdl
 * @Create : 2022-03-20 19:38
 */
@Getter
public enum RoleType {
    ADMIN(1,"管理员"),
    CUSTOMER(2,"客户"),
    VOLUNTEER(3,"自愿者");
    private final int type;
    private final String info;

    RoleType(int type, String info) {
        this.type = type;
        this.info = info;
    }

    public String getValueByType(Integer type){
        for(RoleType roleType : values()){
            if (roleType.type == type)
                return roleType.info;
        }
        return null;
    }
}
