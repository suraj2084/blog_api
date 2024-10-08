package com.suraj.blog_api.surajblogapi.Services;

import com.suraj.blog_api.surajblogapi.Payloads.ApiResponse;
import com.suraj.blog_api.surajblogapi.Payloads.PageResponse;
import com.suraj.blog_api.surajblogapi.Payloads.PostDto;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface PostService {
    ApiResponse createPost(PostDto postDto, Integer user_id, Integer category_id);

    ApiResponse updatePost(PostDto entity, Integer post_id);

    ApiResponse deleteById(Integer post_id);

    ApiResponse deleteAll();

    PostDto getPostById(Integer post_id);

    PageResponse<PostDto> getAllPosts(Integer pageNo, Integer PageSize, String sortby, String sortDir);

    List<PostDto> getPostByUser(Integer user_id);

    List<PostDto> getPostByCategory(Integer category_id);

    PageResponse<PostDto> searchPostsByContent(String Keyword, Integer pageNo, Integer pageSize);
}
