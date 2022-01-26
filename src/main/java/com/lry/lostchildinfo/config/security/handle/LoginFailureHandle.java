package com.lry.lostchildinfo.config.security.handle;

import com.alibaba.fastjson.JSON;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : jdl
 * @description : 登录失败处理类
 * @ClassName : LoginFailureHandle
 * @create : 2022-01-23 17:24
 */
@Component
public class LoginFailureHandle implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("code",HttpServletResponse.SC_BAD_REQUEST);
        map.put("msg","账号或密码错误");
        map.put("data", Collections.emptyList());
//        writer.write("{\"status\":\"403\",\"msg\":\"权限不足，请联系管理员\"}");
        writer.write(JSON.toJSONString(map));
        writer.flush();
        writer.close();
    }
}
