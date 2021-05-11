package com.admire.secyrity.security;

import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

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
     * 载荷校验，前文已经提及。
     * 获取key。如果是keyBytes则通过keyBytes及算法名生成key对象。
     * 将所使用签名算法写入header。如果使用压缩，将压缩算法写入header。
     * 将Json形式的header转为bytes，再Base64编码
     * 将Json形式的claims转为bytes，如果需要压缩则压缩，再进行Base64编码
     * 拼接header和claims。如果签名key为空，则不进行签名(末尾补分隔符" . ")；如果签名key不为空，以拼接的字符串作为参数，按照指定签名算法进行签名计算签名部分 sign(String jwtWithoutSignature)，签名部分同样也会进行Base64编码。
     * 返回完整JWT
     */
    public String createToken(String username){
        String token = Jwts.builder()
                //设置主体部分
                .setSubject(username)
                //设置有效时长
                .setExpiration(new Date(System.currentTimeMillis()+tokenEcpiraten))
                //设置签名使用的签名算法和签名使用的秘钥
                .signWith(SignatureAlgorithm.HS512,tokenSignKey)
                // 设置压缩方法可采用jjwt实现的两种压缩方法CompressionCodecs.GZIP和CompressionCodecs.DEFLATE
                .compressWith(CompressionCodecs.GZIP)
                //生成JWT
                .compact();
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
