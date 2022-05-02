package com.lry.lostchildinfo.controller;

import com.lry.lostchildinfo.common.Result;
import com.lry.lostchildinfo.config.security.LoginUser;
import com.lry.lostchildinfo.entity.JwtProperties;
import com.lry.lostchildinfo.entity.pojo.Log;
import com.lry.lostchildinfo.service.LoginService;
import com.lry.lostchildinfo.utils.RedisUtil;
import com.lry.lostchildinfo.utils.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author : jdl
 * @description : 用户登录前端控制器
 * @ClassName : SysLoginController
 * @create : 2022-01-23 13:35
 */
@RestController
@Slf4j
public class SysLoginController {

    @Resource
    LoginService loginService;

    @Resource
    RedisUtil redisUtil;

    @Resource
    JwtProperties jwtProperties;

    @PostMapping("/login")
    public Result login(LoginUser loginUser){
//        log.info("账号为:"+loginUser.(),loginUser.getUserPwd())
        return Result.success("成功");
    }

    @GetMapping("/logout/success")
    public Result logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication){
        log.error("进入退出登录。。。。。。。。。。。。。。。。。。");
        if (redisUtil.hasKey(jwtProperties.getHeader()+ authentication.getPrincipal())) {
            redisUtil.del(jwtProperties.getHeader()+authentication.getPrincipal());
            return Result.success("退出成功");
        }
        if(authentication != null){
            new SecurityContextLogoutHandler().logout(request,response,authentication);
            return Result.success("退出成功");
        }
        return Result.error("退出失败");
    }
}
