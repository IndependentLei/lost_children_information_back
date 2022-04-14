package com.lry.lostchildinfo.utils;

import com.lry.lostchildinfo.config.security.LoginUser;
import com.lry.lostchildinfo.entity.pojo.User;
import com.lry.lostchildinfo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * @author : jdl
 * @description : Security工具类
 * @ClassName : SecurityUtil
 * @create : 2022-01-24 22:14
 */
public class SecurityUtil {
    /**
     * 获取当前用户
     * @return
     */
    public static User  getCurrentUser(){

        if(SecurityContextHolder.getContext().getAuthentication().getCredentials().equals("")){
            return new User();
        }
        // 账号
        return  (User) SecurityContextHolder.getContext().getAuthentication().getCredentials();
    }
}
