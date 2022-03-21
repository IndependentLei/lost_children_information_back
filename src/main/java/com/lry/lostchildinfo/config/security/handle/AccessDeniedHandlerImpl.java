package com.lry.lostchildinfo.config.security.handle;

import com.alibaba.fastjson.JSON;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : jdl
 * @description : 自定义权限异常
 * @ClassName : AccessDeniedHandlerImpl
 * @create : 2022-01-23 12:24
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("code",HttpServletResponse.SC_FORBIDDEN);
        map.put("msg","权限不足，请联系管理员");
        map.put("data", Collections.emptyList());
//        writer.write("{\"status\":\"403\",\"msg\":\"权限不足，请联系管理员\"}");
        writer.write(JSON.toJSONString(map));
        writer.flush();
        writer.close();
    }
}
