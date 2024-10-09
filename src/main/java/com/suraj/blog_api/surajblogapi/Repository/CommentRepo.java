package com.suraj.blog_api.surajblogapi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suraj.blog_api.surajblogapi.Entities.Comment;
import com.suraj.blog_api.surajblogapi.Entities.Post;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment, Integer> {
    List<Comment> findByPost(Post post);
}
