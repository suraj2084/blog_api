package com.suraj.blog_api.surajblogapi.Payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    private long id;

    @NotEmpty(message = "Name must not be Empty")
    @Size(min = 4, message = "Username must be of 4 Character.")
    private String name;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9_.±]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$", message = "Kindly Enter Valid Email.")
    private String email;

    @NotNull
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$", message = "Enter Valid password one Capial Latter,Special Character and Number.")
    @Size(min = 3, max = 10, message = "password must be min of 3 and max of 10")
    private String password;
    @Size(min = 3, max = 2000, message = "password must be min of 3 and max of 2000 Character.")
    private String about;
}
