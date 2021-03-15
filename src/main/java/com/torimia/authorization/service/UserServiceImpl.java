package com.torimia.authorization.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.torimia.authorization.exception.AuthorizationException;
import com.torimia.authorization.mapper.UserMapper;
import com.torimia.authorization.model.AccountStatus;
import com.torimia.authorization.model.dto.AuthorizationResponse;
import com.torimia.authorization.model.dto.AuthorizationUserDto;
import com.torimia.authorization.model.dto.JwtUser;
import com.torimia.authorization.model.entity.User;
import com.torimia.authorization.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.boot.json.JsonParser;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Value("${jwt.secret}")
    private String secret;

    private final UserRepository repository;
    private final UserMapper mapper;
    private final ObjectMapper objectMapper;

    @SneakyThrows
    @Override
    public AuthorizationResponse authorize(AuthorizationUserDto dto) {
        User user = repository.findUserByUsername(dto.getUsername()).orElseThrow(
                () -> new AuthorizationException("User with username: " + dto.getUsername() + " don`t exist!"));

        if (!user.getPassword().equals(dto.getPassword())) {
            throw new AuthorizationException("Wrong password!");
        }

        if (user.getStatus() != AccountStatus.ACTIVE) {
            throw new AuthorizationException("This account was " + user.getStatus().toString().toLowerCase() + "!");
        }

        JwtUser jwtUser = mapper.toJwt(user);
        String token = JWT.create()
                .withSubject(objectMapper.writeValueAsString(jwtUser))
                .withExpiresAt(Date.from(Instant.now().plus(60, ChronoUnit.MINUTES)))
                .sign(Algorithm.HMAC512(secret));

        return new AuthorizationResponse("Bearer " + token);
    }
}
