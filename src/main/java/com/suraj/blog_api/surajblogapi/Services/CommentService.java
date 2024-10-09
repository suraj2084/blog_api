package com.suraj.blog_api.surajblogapi.Services;

import com.suraj.blog_api.surajblogapi.Payloads.ApiResponse;
import com.suraj.blog_api.surajblogapi.Payloads.CommentDto;
import java.util.List;

public interface CommentService {

    // create Comment
    ApiResponse createComment(CommentDto commentDto, Integer user_id, Integer post_id);

    // Get All Comment
    List<CommentDto> getAllComments();

    // Update Comment
    ApiResponse updateComment(Integer comment_id, CommentDto commentDto);

    // DeleteByID Comment
    ApiResponse deleteCommentById(Integer comment_id);

    // Delete All Comment
    ApiResponse deleteAllComment();

    List<CommentDto> getCommentsByPost(Integer postId);

}
