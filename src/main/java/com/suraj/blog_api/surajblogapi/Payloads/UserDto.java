package com.suraj.blog_api.surajblogapi.Payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    private long id;
    private String name;
    private String email;
    private String password;
    private String about;
}
