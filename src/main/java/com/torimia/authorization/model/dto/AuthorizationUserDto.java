package com.torimia.authorization.model.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class AuthorizationUserDto {

    @NotNull(message = "Username can`t be empty!")
    @Size(min = 2, message = "Username can`t be less than 2 characters!")
    private String username;

    @NotNull(message = "Password can`t be empty!")
    private String password;
}
