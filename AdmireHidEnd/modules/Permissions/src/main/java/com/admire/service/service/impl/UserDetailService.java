package com.admire.service.service.impl;


import com.admire.security.Entity.SecurityUser;
import com.admire.service.entity.User;
import com.admire.service.service.PermissionService;
import com.admire.service.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userDetailsService")
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;

    /**
     * 根据用户名查询数据库返回spring security需要的对象
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查询数据库
        User user = userService.selectByUsername(username);
        //判断 如果用户信息为空，
        if(user == null){
            throw new UsernameNotFoundException("用户不存在");
        }
        com.admire.security.Entity.User curluser =new com.admire.security.Entity.User();
        BeanUtils.copyProperties(user,curluser);
        //根据用户查询权限列表  用户id --> 角色 --> 权限
        List<String> permissionVauleList = permissionService.selectPermissionValueByUserId(user.getId());
        SecurityUser securityUser = new SecurityUser();
        securityUser.setCurrentUserInfo(curluser);
        securityUser.setPermissionValueList(permissionVauleList);
        return securityUser;
    }
}
