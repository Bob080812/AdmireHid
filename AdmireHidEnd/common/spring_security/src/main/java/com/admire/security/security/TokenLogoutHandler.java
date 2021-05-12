package com.admire.security.security;

import com.admire.common.domain.ReturnValue;

import com.admire.common.util.ResponseUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TokenLogoutHandler implements LogoutHandler {
    private TokenManager tokenManager;
    private RedisTemplate redisTemplate;

    public TokenLogoutHandler(TokenManager tokenManager,RedisTemplate redisTemplate){
        this.tokenManager = tokenManager;
        this.redisTemplate = redisTemplate;
    }
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        //1.从header获取token
        String token = request.getHeader("token");
        //2.token不为空，移除token，删除redis中token
        if (token != null){
            //移除token
            tokenManager.removeToken(token);
            //从token获取用户名
            String username = tokenManager.getUserInfoFromToken(token);
            //从redis中删除
            redisTemplate.delete(username);
        }
        ResponseUtil.out(response, ReturnValue.ok());
    }
}
