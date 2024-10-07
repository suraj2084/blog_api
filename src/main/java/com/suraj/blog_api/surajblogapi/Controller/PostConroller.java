package com.suraj.blog_api.surajblogapi.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suraj.blog_api.surajblogapi.Payloads.ApiResponse;
import com.suraj.blog_api.surajblogapi.Payloads.PostDto;
import com.suraj.blog_api.surajblogapi.Services.PostService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.*;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/")
public class PostConroller {

    @Autowired
    PostService postService;

    @PostMapping("user/{user_id}/category/{category_id}/posts")
    public ResponseEntity<ApiResponse> createPost(@Valid @RequestBody PostDto postDto, @PathVariable Integer user_id,
            @PathVariable Integer category_id) {
        ApiResponse CreatedPost = postService.createPost(postDto, user_id, category_id);
        return new ResponseEntity<ApiResponse>(CreatedPost, HttpStatus.CREATED);
    }

    @GetMapping("posts/")
    public ResponseEntity<?> getPosts() {
        List<PostDto> postdtos = postService.getAllPosts();
        if (postdtos.isEmpty()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse("No Data Available", false), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(postdtos, HttpStatus.OK);
    }

    @GetMapping("category/{category_id}/posts")
    public ResponseEntity<?> getPostByCategory(@PathVariable Integer category_id) {
        List<PostDto> postdtos = postService.getPostByCategory(category_id);
        System.out.println("Category ID: " + category_id); // Debugging line
        if (postdtos.isEmpty()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse("No Data Available", false), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(postdtos, HttpStatus.OK);
    }

    @GetMapping("user/{user_id}/posts")
    public ResponseEntity<?> getPostByUser(@PathVariable Integer user_id) {
        List<PostDto> postdtos = postService.getPostByUser(user_id);
        if (postdtos.isEmpty()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse("No Data Available", false), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(postdtos, HttpStatus.OK);
    }

    @PutMapping("posts/{id}")
    public ResponseEntity<ApiResponse> updatePost(@PathVariable Integer id, @RequestBody PostDto entity) {
        ApiResponse updatedPost = postService.updatePost(entity, id);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);

    }

    @DeleteMapping("posts/")
    public ResponseEntity<ApiResponse> deleteAllPost() {
        ApiResponse deleteAll = postService.deleteAll();
        return new ResponseEntity<>(deleteAll, HttpStatus.OK);

    }

    @DeleteMapping("posts/{id}")
    public ResponseEntity<ApiResponse> deletePostPostById(@PathVariable Integer id) {
        ApiResponse deleteById = postService.deleteById(id);
        return new ResponseEntity<>(deleteById, HttpStatus.OK);

    }

}
