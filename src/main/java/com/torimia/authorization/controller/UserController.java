package com.torimia.authorization.controller;

import com.torimia.authorization.model.dto.AuthorizationResponse;
import com.torimia.authorization.model.dto.AuthorizationUserDto;
import com.torimia.authorization.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("auth")
public class UserController {

    private final UserService service;

    @PostMapping
    public AuthorizationResponse authorize(@Valid @RequestBody AuthorizationUserDto dto) {
        return service.authorize(dto);
    }
}
