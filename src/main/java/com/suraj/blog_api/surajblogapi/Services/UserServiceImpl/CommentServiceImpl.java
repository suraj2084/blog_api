package com.suraj.blog_api.surajblogapi.Services.UserServiceImpl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.suraj.blog_api.surajblogapi.Entities.Comment;
import com.suraj.blog_api.surajblogapi.Entities.Post;
import com.suraj.blog_api.surajblogapi.Entities.User;
import com.suraj.blog_api.surajblogapi.Exceptions.ResourceNotFoundException;
import com.suraj.blog_api.surajblogapi.Payloads.ApiResponse;
import com.suraj.blog_api.surajblogapi.Payloads.CommentDto;
import com.suraj.blog_api.surajblogapi.Repository.CommentRepo;
import com.suraj.blog_api.surajblogapi.Repository.PostRepo;
import com.suraj.blog_api.surajblogapi.Repository.UserRepo;
import com.suraj.blog_api.surajblogapi.Services.CommentService;

public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private ModelMapper modelMapper;

    // @Autowired
    // private UserRepo userRepo;
    @Autowired
    private PostRepo postRepo;

    @Override
    public ApiResponse createComment(CommentDto commentDto, Integer user_id, Integer post_id) {
        // User user = userRepo.findById(user_id)
        // .orElseThrow(() -> new ResourceNotFoundException("User", "Id", user_id));
        Post post = postRepo.findById(post_id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Id", post_id));
        Comment comment = modelMapper.map(commentDto, Comment.class);
        comment.setCreatedDate(new Date());
        comment.setStatus("active");
        comment.setPost(post);
        // comment.setUser(user);
        commentRepo.save(comment);
        return new ApiResponse("Comment Created Successfully", true);
    }

    @Override
    public List<CommentDto> getAllComments() {
        List<Comment> comments = commentRepo.findAll();
        List<CommentDto> commentDtos = comments.stream().map(comment -> modelMapper.map(comment, CommentDto.class))
                .collect(Collectors.toList());
        return commentDtos;
    }

    @Override
    public ApiResponse updateComment(Integer comment_id, CommentDto commentDto) {
        Comment comment = commentRepo.findById(comment_id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Id", comment_id));
        comment.setContent(commentDto.getContent());

        commentRepo.save(comment);
        return new ApiResponse("Comment Updated Successfully", true);

    }

    @Override
    public ApiResponse deleteCommentById(Integer comment_id) {
        Comment comment = commentRepo.findById(comment_id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Id", comment_id));
        commentRepo.delete(comment);
        return new ApiResponse("Comment Deleted Successfully", true);
    }

    @Override
    public ApiResponse deleteAllComment() {
        commentRepo.deleteAll();
        return new ApiResponse("All Comments Deleted Successfully", true);

    }

    @Override
    public List<CommentDto> getCommentsByPost(Integer postId) {
        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
        List<Comment> comments = commentRepo.findByPost(post);
        return comments.stream()
                .map(comment -> modelMapper.map(comment, CommentDto.class))
                .collect(Collectors.toList());
    }

}
