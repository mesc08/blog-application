package com.project.bloggingapp.payloads;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private int id;

    @NotNull
    @Size(min = 4, message = "Username should be of minsize 4")
    private String name;

    @Email(message = "given email address is not valid")
    private String email;

    @NotEmpty(message = "must add password")
    @Size(min=8, message = "Password minimum of length 8")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$", message = "confirm password is invalid not matching regex")
    private String password;

    @NotEmpty(message = "must add confirm password")
    @Size(min=8, message = "Confirm Password minimum of length 8")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$", message = "confirm password is invalid not matching regex")
    private String confirmpassword;


    @NotEmpty(message = "about section cannot be empty")
    private String about;
}
