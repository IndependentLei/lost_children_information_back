package com.lry.lostchildinfo.service.serviceImpl;

import com.lry.lostchildinfo.common.Result;
import com.lry.lostchildinfo.config.security.LoginUser;
import com.lry.lostchildinfo.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author : jdl
 * @description : 登录
 * @ClassName : LoginServiceImpl
 * @create : 2022-01-23 13:38
 */
@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

//    @Autowired
//    AuthenticationManager authenticationManager;

    @Override
    public String login(String userCode ,String userPwd) {

        log.info("进入登录页面");
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userCode,userPwd);

        return "111";
    }
}
