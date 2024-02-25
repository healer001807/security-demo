package com.example.securitydemo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * @author kw
 * @program security-demo
 * @description
 * @create 2024 - 02 - 25 10:48
 **/
@Setter
@Getter
@TableName("users")
public class LoginUser {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String name;
    private String password;
    private Boolean enabled;
}
