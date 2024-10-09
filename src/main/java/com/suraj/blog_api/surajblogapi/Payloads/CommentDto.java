package com.suraj.blog_api.surajblogapi.Payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

import com.suraj.blog_api.surajblogapi.Entities.Post;
import com.suraj.blog_api.surajblogapi.Entities.User;

import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class CommentDto {

    private Integer commentId;
    @NotBlank(message = "Comment Content should not be blank")
    private String content;
    private Date currentDate;
    private String status;
    private Post post;
    // private User user;

}
