package com.suraj.blog_api.surajblogapi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suraj.blog_api.surajblogapi.Entities.Post;

public interface PostRepo extends JpaRepository<Post, Integer> {

}
