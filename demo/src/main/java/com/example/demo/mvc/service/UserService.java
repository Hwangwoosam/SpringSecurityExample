package com.example.demo.mvc.service;

import com.example.demo.enums.UserRole;
import com.example.demo.mvc.entity.UserInfo;
import com.example.demo.mvc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean registerNewUser(String username, String password) {
        if (userRepository.findByUsername(username) != null) {
            throw new RuntimeException("Username already exists");
        }
        UserInfo user = new UserInfo(username,passwordEncoder.encode(password));

        userRepository.insert(user);
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfoDTO = this.userRepository.findByUsername(username);


        if(userInfoDTO == null){
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }

        List<GrantedAuthority> authorityList = new ArrayList<>();
        if("administer".equals(username)){
            authorityList.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue()));
        }else{
            authorityList.add(new SimpleGrantedAuthority(UserRole.USER.getValue()));
        }

        return new org.springframework.security.core.userdetails.User(userInfoDTO.getUsername(),userInfoDTO.getPassword(),authorityList);
    }

}
