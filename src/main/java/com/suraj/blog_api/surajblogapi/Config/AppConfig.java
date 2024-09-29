package com.suraj.blog_api.surajblogapi.Config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.suraj.blog_api.surajblogapi.Services.CategoryService;
import com.suraj.blog_api.surajblogapi.Services.PostService;
import com.suraj.blog_api.surajblogapi.Services.UserService;
import com.suraj.blog_api.surajblogapi.Services.UserServiceImpl.CategoryServiceImpl;
import com.suraj.blog_api.surajblogapi.Services.UserServiceImpl.PostServiceImpl;
import com.suraj.blog_api.surajblogapi.Services.UserServiceImpl.UserServiceImpl;

@Configuration
public class AppConfig {
    @Bean
    public UserService userService() {
        return new UserServiceImpl();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public CategoryService categoryService() {
        return new CategoryServiceImpl();
    }

    @Bean
    public PostService postService() {
        return new PostServiceImpl();
    }

}
