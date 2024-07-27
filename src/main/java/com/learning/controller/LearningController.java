package com.learning.controller;

import com.learning.config.JWTService;
import com.learning.dao.UserDetailsRepository;
import com.learning.entity.UserDetailsEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LearningController {


    public UserDetailsRepository userDetailsRepository;
    public PasswordEncoder passwordEncoder;
    public final JWTService jwtService;
    @GetMapping("/home")
    public String home(){
        return jwtService.generateToken(User.builder().username("charanm@clarium.tech").password("$2a$10$e9zOx6k4nbyUMmR7AeTooekB45UWpLddw5OwUSOXjm6WkgcObaBuS").roles("ADMIN").build());
    }
    @GetMapping("/user/home")
    public String homeUser(){
        return "user_home";
    }@GetMapping("/admin/home")
    public String homeAdmin(){
        return "admin_home";
    }

//    public LearningController(UserDetailsRepository userDetailsRepository, PasswordEncoder passwordEncoder, JWTService jwtService){
//        this.userDetailsRepository=userDetailsRepository;
//        this.passwordEncoder=passwordEncoder;
//        this.jwtService = jwtService;
//    }

    @PostMapping("/add-user")
    public String addUser(@RequestBody UserDetailsEntity userDetailsEntity){
        userDetailsEntity.setPassword(passwordEncoder.encode(userDetailsEntity.getPassword()));
        userDetailsRepository.save(userDetailsEntity);
        return "User Added Successfully";
    }
}
