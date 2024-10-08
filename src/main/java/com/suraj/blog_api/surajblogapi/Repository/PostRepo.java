package com.suraj.blog_api.surajblogapi.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.suraj.blog_api.surajblogapi.Entities.Category;
import com.suraj.blog_api.surajblogapi.Entities.Post;
import com.suraj.blog_api.surajblogapi.Entities.User;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {

    List<Post> findAllByCategory(Category category);

    List<Post> findAllByUser(User user);

    // Find all posts by content containing a specific keyword
    Page<Post> findByContentContaining(String keyword, Pageable pageable);

}
