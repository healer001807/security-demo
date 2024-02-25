package com.example.securitydemo.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.securitydemo.mapper.UserMapper;
import com.example.securitydemo.pojo.LoginUser;
import jakarta.annotation.Resource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.LinkedList;

/**
 * @author kw
 * @program security-demo
 * @description
 * @create 2024 - 02 - 25 11:33
 **/
@Component
public class DBUserDetailManager implements UserDetailsManager, UserDetailsPasswordService {
    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        return null;
    }

    @Override
    public void createUser(UserDetails user) {
        LoginUser loginUser = new LoginUser();
        loginUser.setName(user.getUsername());
        loginUser.setPassword(user.getPassword());
        loginUser.setEnabled(true);
        userMapper.insert(loginUser);
    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<LoginUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", username);
        LoginUser loginUser = userMapper.selectOne(queryWrapper);
        if (null == loginUser) {
            throw new UsernameNotFoundException(username);
        } else {
            // 权限列表
            Collection<GrantedAuthority> authorities = new LinkedList<>();
            // 硬编码
//            authorities.add((GrantedAuthority) () -> "USER_LIST");
//            authorities.add((GrantedAuthority) () -> "USER_ADD");

            PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

//            User user = new User(loginUser.getName(), encoder.encode(loginUser.getPassword()), loginUser.getEnabled()
//                    , true,
//                    true,
//                    true,
//                    authorities);

            UserDetails userDetails = User
                    .withUsername(loginUser.getName())
                    .password(loginUser.getPassword())
                    .disabled(!loginUser.getEnabled())
                    .credentialsExpired(false)
                    .accountLocked(false)
                    .roles("ADMIN")
                    .build();

            return userDetails;
        }
    }
}
