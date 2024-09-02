package com.example.demo.mvc.entity;

import lombok.Data;
import lombok.Getter;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Getter
public class UserInfo{
    private String username;
    private String password;

    public UserInfo(String username,String password){
        this.username = username;
        this.password = password;
    }
}
