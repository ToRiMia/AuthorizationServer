package com.torimia.authorization.model.dto;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class AuthorizationUserDto {

    @NotNull
    private String username;

    @NotNull
    private String password;
}
