package com.lry.lostchildinfo.config.security.handle;

import com.alibaba.fastjson.JSON;
import com.lry.lostchildinfo.entity.JwtProperties;
import com.lry.lostchildinfo.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
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
 * @description : 退出成功处理类
 * @ClassName : LogoutSuccessHandlerImpl
 * @create : 2022-01-22 22:18
 */
@Component
@Slf4j
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {
    @Autowired
    JwtProperties jwtProperties;
    @Autowired
    RedisUtil redisUtil;
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 退出成功,删除redis的token
        if (redisUtil.hasKey(jwtProperties.getHeader()))
            redisUtil.del(jwtProperties.getHeader());
        if(authentication != null){
            new SecurityContextLogoutHandler().logout(request,response,authentication);
        }
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("code",HttpServletResponse.SC_OK);
        map.put("msg","退出成功");
        //生成token
        map.put("data","");

        writer.write(JSON.toJSONString(map));
        writer.flush();
        writer.close();
    }
}
