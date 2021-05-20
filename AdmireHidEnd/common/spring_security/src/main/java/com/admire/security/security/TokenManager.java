package com.admire.security.security;

import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Token工具类
 */
@Component
public class TokenManager {

    /**
     * Token有效时长
     */
    private long tokenEcpiraten = 24*60*60*1000;

    /**
     * 编码密钥
     */
    private String tokenSignKey = "123456";

    /**
     * 使用jwt根据用户名生成token
     * @param username 用户名
     * @return
     */
    public String createToken(String username){
        String token = Jwts.builder()
                //设置主体部分
                .setSubject(username)
                //设置有效时长
                .setExpiration(new Date(System.currentTimeMillis()+tokenEcpiraten))
                .signWith(SignatureAlgorithm.HS512,tokenSignKey).compressWith(CompressionCodecs.GZIP).compact();
        return token;
    }

    /**
     * 根据token获得用户信息
     * @param token
     * @return
     */
    public String getUserInfoFromToken(String token){
        String userinfo = Jwts.parser().
                setSigningKey(tokenSignKey).
                parseClaimsJws(token).getBody().getSubject();
        return userinfo;
    }

    /**
     * 删除token
     */
    public void removeToken(String token){ }
}
