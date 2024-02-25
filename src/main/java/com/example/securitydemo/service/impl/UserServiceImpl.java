package com.example.securitydemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.securitydemo.mapper.UserMapper;
import com.example.securitydemo.pojo.LoginUser;
import com.example.securitydemo.security.DBUserDetailManager;
import com.example.securitydemo.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * @author kw
 * @program security-demo
 * @description
 * @create 2024 - 02 - 25 11:11
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, LoginUser> implements UserService {

    @Resource
    private DBUserDetailManager dbUserDetailManager;

    @Override
    public void saveUserDetails(LoginUser loginUser) {
        UserDetails userDetails = User.withDefaultPasswordEncoder()
                .username(loginUser.getName())
                .password(loginUser.getPassword())
//                .roles()
                .build();

        dbUserDetailManager.createUser(userDetails);
    }
}
