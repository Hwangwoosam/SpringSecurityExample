package com.example.demo.mvc.controller;

import com.example.demo.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class mainController {

    @Autowired
    private UserService userService;

    @GetMapping("/registerPage")
    public String showRegistrationForm() {
        return "register";
    }

    @GetMapping("/loginPage")
    public String loginPage(){
        return "../static/index.html";
    }

    @GetMapping("/loginSuccess")
    public String loginSuccess(){
        return "loginSuccess";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password) {
        userService.registerNewUser(username, password);
        return "redirect:/loginPage";
    }
}
