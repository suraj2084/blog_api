package com.suraj.blog_api.surajblogapi.Payloads;

import java.util.Date;
import java.util.Locale.Category;

import com.suraj.blog_api.surajblogapi.Entities.User;

import jakarta.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

    private Integer p_id;

    @NotBlank(message = "Post title should not be blank")
    private String p_title;

    @NotBlank(message = "Post content should not be blank")
    private String Content;

    private Date addedDate;

    private String imageUrl;

    private Category category;

    private User user;
}
