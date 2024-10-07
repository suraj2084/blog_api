package com.suraj.blog_api.surajblogapi.Services.UserServiceImpl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.suraj.blog_api.surajblogapi.Entities.User;
import com.suraj.blog_api.surajblogapi.Entities.Category;
import com.suraj.blog_api.surajblogapi.Entities.Post;
import com.suraj.blog_api.surajblogapi.Exceptions.ResourceNotFoundException;
import com.suraj.blog_api.surajblogapi.Payloads.ApiResponse;
import com.suraj.blog_api.surajblogapi.Payloads.PostDto;
import com.suraj.blog_api.surajblogapi.Repository.CategoryRepo;
import com.suraj.blog_api.surajblogapi.Repository.PostRepo;
import com.suraj.blog_api.surajblogapi.Repository.UserRepo;
import com.suraj.blog_api.surajblogapi.Services.PostService;

public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public ApiResponse createPost(PostDto postDto, Integer user_id, Integer category_id) {

        User user = userRepo.findById(user_id).orElseThrow(() -> new ResourceNotFoundException("User", "id", user_id));
        Category category = categoryRepo.findById(category_id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", category_id));

        Post post = modelMapper.map(postDto, Post.class);
        post.setP_imageUrl("default.jpg");
        post.setAddDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        postRepo.save(post);
        return new ApiResponse("Post Create Successfully", true);
    }

    @Override
    public ApiResponse updatePost(PostDto postDto, Integer post_id) {
        Post post = postRepo.findById(post_id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", post_id));
        post.setP_Content(postDto.getContent());
        post.setP_imageUrl(postDto.getImageUrl());
        post.setP_title(postDto.getP_title());
        post.setAddDate(new Date());
        return new ApiResponse("Post Updated Successfully", true);
    }

    @Override
    public ApiResponse deleteById(Integer post_id) {
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
    public PostDto getPostById(Integer post_id) {
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

    @Override
    public List<PostDto> getPostByUser(Integer user_id) {
        User user = userRepo.findById(user_id).orElseThrow(() -> new ResourceNotFoundException("User", "id", user_id));
        List<Post> posts = postRepo.findAllByUser(user);
        List<PostDto> postDtos = posts.stream().map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> getPostByCategory(Integer category_id) {
        Category category = categoryRepo.findById(category_id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", category_id));
        List<Post> posts = postRepo.findAllByCategory(category);
        List<PostDto> postDtos = posts.stream().map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> searchPosts(String Keyword) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchPosts'");
    }

}
