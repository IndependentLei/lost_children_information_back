package com.lry.lostchildinfo.config.security.handle;

import com.alibaba.fastjson.JSON;
import com.lry.lostchildinfo.entity.JwtProperties;
import com.lry.lostchildinfo.utils.JwtUtil;
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

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("登录成功");
        log.info(jwtProperties.getHeader());
        log.info(jwtProperties.getPrefix());
        log.info(jwtProperties.getSecret());
        log.info(""+jwtProperties.getExpire());
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("code",HttpServletResponse.SC_OK);
        map.put("msg","登录成功");
        //生成token
        map.put(jwtProperties.getHeader(), jwtUtil.createJwt(authentication.getName()));

        writer.write(JSON.toJSONString(map));
        writer.flush();
        writer.close();
    }
}
