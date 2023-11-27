package com.project.bloggingapp.payloads;

import com.project.bloggingapp.config.AppConstants;
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
    @Size(min = AppConstants.USER_NAME_SIZE, message = AppConstants.USER_NAME_MSG)
    private String name;

    @Email(message = AppConstants.USER_EMAIL_MSG)
    private String email;

    @NotEmpty(message = AppConstants.USER_PASSWORD_NOT_EMPTY)
    @Size(min=AppConstants.USER_PASSWORD_SIZE, message = AppConstants.USER_PASSWORD_SIZE_MSG)
    @Pattern(regexp = AppConstants.USER_PASSWORD_REGEX, message = AppConstants.USER_PASSWORD_REGEX_MSG)
    private String password;

//    @NotEmpty(message = AppConstants.USER_CONFIRMPASSWORD_NOT_EMPTY)
    @Size(min=AppConstants.USER_PASSWORD_SIZE, message = AppConstants.USER_CONFIRMPASSWORD_SIZE_MSG)
    @Pattern(regexp = AppConstants.USER_PASSWORD_REGEX, message = AppConstants.USER_CONFIRMPASSWORD_REGEX_MSG)
    private String confirmpassword;


    @NotEmpty(message = AppConstants.USER_ABOUT_NOT_EMPTY)
    private String about;
}
