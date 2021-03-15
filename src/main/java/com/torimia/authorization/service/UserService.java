package com.torimia.authorization.service;

import com.torimia.authorization.model.dto.AuthorizationResponse;
import com.torimia.authorization.model.dto.AuthorizationUserDto;

public interface UserService {

    AuthorizationResponse authorize(AuthorizationUserDto dto);
}
