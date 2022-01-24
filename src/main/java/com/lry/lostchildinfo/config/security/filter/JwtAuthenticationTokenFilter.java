package com.lry.lostchildinfo.config.security.filter;

import com.lry.lostchildinfo.config.security.service.UserDetailsServiceImpl;
import com.lry.lostchildinfo.entity.JwtProperties;
import com.lry.lostchildinfo.entity.pojo.User;
import com.lry.lostchildinfo.service.UserService;
import com.lry.lostchildinfo.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Security;

/**
 * @author : jdl
 * @description : 校验token的有效性
 * @ClassName : JwtAuthenticationTokenFilter
 * @create : 2022-01-22 22:13
 */
@Component
public class JwtAuthenticationTokenFilter extends BasicAuthenticationFilter {

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    JwtProperties jwtProperties;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    UserService userService;

    public JwtAuthenticationTokenFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //TODO...校验token
        String token = request.getHeader(jwtProperties.getHeader());
        logger.info("拦截请求，解析token--------------->{}"+token);
        if (StringUtils.isBlank(token)){
            filterChain.doFilter(request,response);
            return;
        }


        // 解析token
        Claims claims = jwtUtil.getClaimByToken(token);
        if ( claims ==null){
            throw new JwtException("token异常");
        }

        // 是否过期
        if ( jwtUtil.tokenExpired(claims)){
            throw new JwtException("token已过期,请重新登录");
        }

        // 得到用户主体
        String username = claims.getSubject();

        User user = userService.getUserByName(username);

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(username,null, AuthorityUtils.commaSeparatedStringToAuthorityList(userDetailsService.getAuthority(user.getUserId())));

        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        filterChain.doFilter(request,response);
    }
}
