package com.torimia.authorization.service;

import com.torimia.authorization.model.dto.AuthorizationResponse;
import com.torimia.authorization.model.dto.AuthorizationUserDto;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<AuthorizationResponse> authorize(AuthorizationUserDto dto);
}
