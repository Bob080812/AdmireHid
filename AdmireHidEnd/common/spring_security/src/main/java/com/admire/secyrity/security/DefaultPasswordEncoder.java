package com.admire.secyrity.security;

import cn.hutool.crypto.SecureUtil;
import io.netty.util.internal.StringUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author lx
 */
@Component
public class DefaultPasswordEncoder implements PasswordEncoder {

    public DefaultPasswordEncoder(){
        this(-1);
    }
    public DefaultPasswordEncoder(int strength){
    }

    /**
     * MD5加密
     * @param rawPassword
     * @return
     */
    @Override
    public String encode(CharSequence rawPassword) {
        return SecureUtil.md5(String.valueOf(rawPassword));
    }

    /**
     * 密码比对
     * @param rawPassword 未加密密码
     * @param encodedPassword  已加密密码
     * @return
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(SecureUtil.md5(String.valueOf(rawPassword)));
    }
}
