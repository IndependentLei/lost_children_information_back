package com.lry.lostchildinfo.utils;

import com.lry.lostchildinfo.entity.pojo.User;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : jdl
 * @description : Security工具类
 * @ClassName : SecurityUtil
 * @create : 2022-01-24 22:14
 */
@Component
public class SecurityUtil {
    @Autowired
    JwtUtil jwtUtil;
    /**
     * 获取当前用户
     * @return
     */
    public static User  getCurrentUser(){
//        getCCUer();
        if(SecurityContextHolder.getContext().getAuthentication().getCredentials().equals("")){
            return new User();
        }
        // 账号
        return  (User) SecurityContextHolder.getContext().getAuthentication().getCredentials();
    }

    public User getCCUer(){
        HttpServletRequest req = HttpUtil.getHttpServletRequest();
        String token = req.getHeader("Authentication");
        token = token.substring(7);
        Claims claims = jwtUtil.getClaimByToken(token);
        String subject = claims.getSubject();
        System.out.println(subject);
        return new User();
    }
}
