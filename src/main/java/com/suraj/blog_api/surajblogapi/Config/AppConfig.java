package com.suraj.blog_api.surajblogapi.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.suraj.blog_api.surajblogapi.Services.UserService;
import com.suraj.blog_api.surajblogapi.Services.UserServiceImpl.UserServiceImpl;

@Configuration
public class AppConfig {
    @Bean
    public UserService userService() {
        return new UserServiceImpl();
    }
}
