package com.suraj.blog_api.surajblogapi.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suraj.blog_api.surajblogapi.Payloads.ApiResponse;
import com.suraj.blog_api.surajblogapi.Payloads.CommentDto;
import com.suraj.blog_api.surajblogapi.Services.CommentService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/")
public class CommentController {
    @Autowired
    CommentService commentService;

    @PostMapping("user/{user_id}/post/{post_id}/comment")
    public ResponseEntity<ApiResponse> createComment(@RequestBody CommentDto commentDto,
            @PathVariable("user_id") Integer user_id, @PathVariable("post_id") Integer post_id) {
        ApiResponse commentResponse = commentService.createComment(commentDto, user_id, post_id);
        return new ResponseEntity<>(commentResponse, HttpStatus.CREATED);
    }

    @GetMapping("comments/")
    public ResponseEntity<?> getAllComments() {
        List<CommentDto> allComment = commentService.getAllComments();
        if (allComment.isEmpty()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse("No Data Available", false), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(allComment, HttpStatus.OK);
        }
    }

    @PutMapping("comment/{comment_id}")
    public ResponseEntity<ApiResponse> updateComment(@PathVariable("comment_id") Integer comment_id,
            @RequestBody CommentDto commentDto) {
        ApiResponse updateComment = commentService.updateComment(comment_id, commentDto);
        return new ResponseEntity<>(updateComment, HttpStatus.OK);
    }

    @DeleteMapping("comments/")
    public ResponseEntity<ApiResponse> deleteAllComments() {
        ApiResponse deleteAllComment = commentService.deleteAllComment();
        return new ResponseEntity<>(deleteAllComment, HttpStatus.OK);
    }

    @DeleteMapping("comments/{comment_id}")
    public ResponseEntity<ApiResponse> deleteAllComment(@PathVariable("comment_id") Integer comment_id) {
        ApiResponse deletedComment = commentService.deleteCommentById(comment_id);
        return new ResponseEntity<>(deletedComment, HttpStatus.OK);
    }

}
