package com.lry.lostchildinfo.controller;

import com.lry.lostchildinfo.common.Result;
import com.lry.lostchildinfo.config.security.LoginUser;
import com.lry.lostchildinfo.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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

    @PostMapping("/login")
    public Result login(LoginUser loginUser){
//        log.info("账号为:"+loginUser.(),loginUser.getUserPwd())
        return Result.success("成功");
    }

    @GetMapping("/test")
    public Result test(){
        return Result.success("11111");
    }
}
