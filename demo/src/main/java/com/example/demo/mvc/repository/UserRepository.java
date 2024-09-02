package com.example.demo.mvc.repository;

import com.example.demo.mvc.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserRepository {
    UserInfo findByUsername(String userName);

    void insert(UserInfo user);
}
