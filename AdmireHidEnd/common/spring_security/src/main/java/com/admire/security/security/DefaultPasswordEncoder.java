package com.admire.security.security;

import com.admire.utils.utils.MD5;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


/**
 * 密码处理工具类
 */
@Component
public class DefaultPasswordEncoder implements PasswordEncoder {

    public DefaultPasswordEncoder(){
        this(-1);
    }
    public DefaultPasswordEncoder(int strength){
    }

    /**
     * 进行MD5加密
     * @param rawPassword
     * @return
     */
    @Override
    public String encode(CharSequence rawPassword) {
        return MD5.encrypt(rawPassword.toString());
    }

    /**
     * 进行密码比对
     * @param rawPassword 未加密密码
     * @param encodedPassword 已加密密码
     * @return
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(MD5.encrypt(rawPassword.toString()));
    }
}
