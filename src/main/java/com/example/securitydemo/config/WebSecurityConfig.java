package com.example.securitydemo.config;

import com.example.securitydemo.security.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author kw
 * @program security-demo
 * @description spring security config
 * @create 2024 - 02 - 24 22:37
 **/
//@Configuration
// springboot 中可以省略
//@EnableWebSecurity
public class WebSecurityConfig {

    //    @Bean
    public UserDetailsService userDetailsService() {
        // 创建基于内存的用户信息管理器
        InMemoryUserDetailsManager memoryUserDetailsManager = new InMemoryUserDetailsManager();
        // 创建UserDetails对象，用于管理用户名、密码、角色、权限等
        UserDetails userDetails = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build();
        // 使用InMemoryUserDetailsManager管理UserDetails对象
        memoryUserDetailsManager.createUser(userDetails);
        return memoryUserDetailsManager;
    }

//    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(
                        authorize -> authorize
                                // 资源授权
//                                .requestMatchers("/user/list").hasAnyAuthority("USER_LIST")
//                                .requestMatchers("/user/save").hasAnyAuthority("USER_ADD")

                                .requestMatchers("/user/**").hasRole("ADMIN")  // 用户与角色
                                .anyRequest()
                                .authenticated()
                );
        // 自定义登录页
        http.formLogin(form -> form.loginPage("/login")
                .permitAll() // 无需授权
                .usernameParameter("name") // 自定义表单参数
                .failureUrl("/login?error") // 登录失败跳转
                .successHandler(new LoginSuccessAuthenticationHandler()) // 认证成功处理
                .failureHandler(new LoginFailureAuthenticationHandler()) // 认证失败处理
        );
        // 注销成功处理
        http.logout(logout -> logout.logoutSuccessHandler(new LogOutSuccessHandler()));

        http.exceptionHandling(ex -> {
                    ex.authenticationEntryPoint(new AuthenticationEntryPointHandler());  // 请求未认证处理
                    ex.accessDeniedHandler(new MyAccessDeniedHandler()); // 没有权限处理
                }
        );
        // 跨域
        http.cors(Customizer.withDefaults());
        // 会话 互踢
        http.sessionManagement(session ->
                session.maximumSessions(1)
                                .

                        expiredSessionStrategy(new SessionInformationExpiredStrategyHandler()));
//                .csrf(Customizer.withDefaults())
//                .httpBasic(Customizer.withDefaults())
        // 基于表单默认
//                .formLogin(Customizer.withDefaults());

        return http.build();
    }

}
