package com.suraj.blog_api.surajblogapi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suraj.blog_api.surajblogapi.Entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {
    User findByName(String name);
}
