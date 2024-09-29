package com.suraj.blog_api.surajblogapi.Services;

import com.suraj.blog_api.surajblogapi.Payloads.ApiResponse;
import com.suraj.blog_api.surajblogapi.Payloads.PostDto;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface PostService {
    ApiResponse createPost(PostDto postDto);

    ApiResponse updatePost(PostDto postDto, int post_id);

    ApiResponse deleteById(int post_id);

    ApiResponse deleteAll();

    PostDto getPostById(int post_id);

    List<PostDto> getPosts();
}
