package com.suraj.blog_api.surajblogapi.Services.UserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.suraj.blog_api.surajblogapi.Entities.User;
import com.suraj.blog_api.surajblogapi.Payloads.UserPrinciple;
import com.suraj.blog_api.surajblogapi.Repository.UserRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User byUsername = userRepo.findByName(username);
        if (byUsername == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        return new UserPrinciple(byUsername);

    }

}
