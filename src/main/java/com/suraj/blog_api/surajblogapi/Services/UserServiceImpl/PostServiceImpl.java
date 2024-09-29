package com.suraj.blog_api.surajblogapi.Services.UserServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.suraj.blog_api.surajblogapi.Entities.Post;
import com.suraj.blog_api.surajblogapi.Exceptions.ResourceNotFoundException;
import com.suraj.blog_api.surajblogapi.Payloads.ApiResponse;
import com.suraj.blog_api.surajblogapi.Payloads.PostDto;
import com.suraj.blog_api.surajblogapi.Repository.PostRepo;
import com.suraj.blog_api.surajblogapi.Services.PostService;

public class PostServiceImpl implements PostService {

    @Autowired
    PostRepo postRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public ApiResponse createPost(PostDto postDto) {
        Post post = modelMapper.map(postDto, Post.class);
        postRepo.save(post);
        return new ApiResponse("Post Create Successfully", true);
    }

    @Override
    public ApiResponse updatePost(PostDto postDto, int post_id) {
        Post post = postRepo.findById(post_id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", post_id));
        post.setContent(postDto.getContent());
        post.setP_imageUrl(postDto.getP_imageUrl());
        post.setP_title(postDto.getP_title());
        // post.setDate(postDto.getAddeddate());
        return new ApiResponse("Post Updated Successfully", true);
    }

    @Override
    public ApiResponse deleteById(int post_id) {
        Post post = postRepo.findById(post_id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", post_id));
        postRepo.delete(post);
        return new ApiResponse("Post Deleted Successfully", true);
    }

    @Override
    public ApiResponse deleteAll() {
        postRepo.deleteAll();
        return new ApiResponse("All Post Deleted Successfully", true);
    }

    @Override
    public PostDto getPostById(int post_id) {
        Post post = postRepo.findById(post_id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", post_id));
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public List<PostDto> getPosts() {
        List<Post> posts = postRepo.findAll();
        List<PostDto> postDtos = posts.stream().map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
        return postDtos;
    }

}
