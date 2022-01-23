package com.lry.lostchildinfo.config.security.handle;

import com.alibaba.fastjson.JSON;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : jdl
 * @description : 认证失败,返回未授权
 * @ClassName : AuthenticationEntryPointImpl
 * @create : 2022-01-22 22:17
 */
@Component
public class AuthenticationEntryPointHandler implements AuthenticationEntryPoint, Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("code",HttpServletResponse.SC_UNAUTHORIZED);
        map.put("msg","认证失败,请登录");
        map.put("data", Collections.emptyList());
        writer.write(JSON.toJSONString(map));
        writer.flush();
        writer.close();
    }
}
