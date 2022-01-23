package com.lry.lostchildinfo.utils;

import com.lry.lostchildinfo.entity.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author : jdl
 * @description : JWT工具包
 * @ClassName : JwtUtil
 * @create : 2022-01-23 22:32
 */
@Component
public class JwtUtil {

    @Autowired
    JwtProperties jwtProperties;

    final Long Expire = 3600000L;
    final String secret = "whoareyou";

    /**
     * 生成token
     * @param username
     * @return
     */
    public  String createJwt(String username){
//        System.out.println(jwtProperties.toString());
        Date now = new Date();
        Date expireDate = new Date(now.getTime()+ Expire);

        return  Jwts.builder()
                        .setSubject(username)
                        .setIssuedAt(now)
                        .setExpiration(expireDate)
                        .signWith(SignatureAlgorithm.HS256 , secret )
                        .compact();
    }

    /**
     * 解析token
     * @param token
     * @return
     */
    public  Claims getClaimByToken(String token){
        try {
            return Jwts.parser()
                    .setSigningKey(jwtProperties.getSecret())
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e){
            return null;
        }
    }

    /**
     * 判断token是否过期
     * @param claims
     * @return
     */
    public boolean tokenExpired(Claims claims){
        return claims.getExpiration().before(new Date());
    }
}
