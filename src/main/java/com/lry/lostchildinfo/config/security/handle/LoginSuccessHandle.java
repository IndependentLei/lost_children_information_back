package com.lry.lostchildinfo.config.security.handle;

import com.alibaba.fastjson.JSON;
import com.lry.lostchildinfo.config.security.LoginUser;
import com.lry.lostchildinfo.entity.JwtProperties;
import com.lry.lostchildinfo.utils.JwtUtil;
import com.lry.lostchildinfo.utils.RedisUtil;
import com.lry.lostchildinfo.utils.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : jdl
 * @description : 登录成功处理类
 * @ClassName : LoginSuccessHandle
 * @create : 2022-01-23 17:29
 */
@Component
@Slf4j
public class LoginSuccessHandle implements AuthenticationSuccessHandler {

    @Autowired
    JwtProperties jwtProperties;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    RedisUtil redisUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("登录成功");
        log.info(jwtProperties.getHeader());
        log.info(jwtProperties.getPrefix());
        log.info(jwtProperties.getSecret());
        log.info("过期时间{}",jwtProperties.getExpire());
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("code",HttpServletResponse.SC_OK);
        map.put("msg","登录成功");
        //生成token
        String token = jwtUtil.createJwt(authentication.getName());
        map.put(jwtProperties.getHeader(),jwtProperties.tokenStart()+token);
        // 登录成功生成把token存入redis，过期时间为一天
        LoginUser principal = (LoginUser) authentication.getPrincipal();
        redisUtil.set(jwtProperties.getHeader()+principal.getUserId(),jwtProperties.tokenStart()+token,jwtProperties.getExpire()/1000 );
        writer.write(JSON.toJSONString(map));
        writer.flush();
        writer.close();
    }
}
