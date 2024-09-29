package com.suraj.blog_api.surajblogapi.Payloads;

import java.util.Date;

import com.suraj.blog_api.surajblogapi.Entities.Category;
import com.suraj.blog_api.surajblogapi.Entities.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

    private int p_id;

    @NotBlank(message = "Post title should not be blank")
    private String p_title;

    @NotBlank(message = "Post Image is Complusroy.")
    private String p_imageUrl;

    @NotBlank(message = "Post Content should not be blank")
    @Size(min = 3, message = "Category Title min = 3")
    private String Content;

    // private Date Addeddate;

}
