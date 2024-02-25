package com.example.securitydemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.securitydemo.pojo.LoginUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author kw
 * @program security-demo
 * @description
 * @create 2024 - 02 - 25 10:52
 **/
@Mapper
public interface UserMapper extends BaseMapper<LoginUser> {
}
