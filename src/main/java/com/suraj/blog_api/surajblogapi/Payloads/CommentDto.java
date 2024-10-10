package com.suraj.blog_api.surajblogapi.Payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

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
    private PostDto post;
    // private UserDto user;

}
