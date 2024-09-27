package com.suraj.blog_api.surajblogapi.Payloads;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    private long id;

    @NotNull
    private String name;

    @NotNull
    // @Pattern(regexp = "^[a-zA-Z0-9_.±]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$", message =
    // "Kindly Enter Valid Email.")
    private String email;

    @NotNull
    // @Pattern(regexp =
    // "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$", message =
    // "Kindly Enter Valid Email.")
    private String password;

    private String about;
}
