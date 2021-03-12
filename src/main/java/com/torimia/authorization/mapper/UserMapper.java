package com.torimia.authorization.mapper;

import com.torimia.authorization.model.dto.JwtUser;
import com.torimia.authorization.model.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    JwtUser toJwt(User user);
}
