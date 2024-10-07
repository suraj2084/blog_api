package com.suraj.blog_api.surajblogapi.Payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {

    private Integer c_id;

    @NotEmpty(message = "Category title must not be empty.")
    @Size(min = 3, max = 7, message = "Category Title min = 3 and max = 7 Char.")
    private String c_title;
    @NotEmpty(message = "Category Description must not be empty.")
    @Size(min = 5, max = 50, message = "Category Title min = 5 and max = 50 Char.")
    private String c_description;

}
