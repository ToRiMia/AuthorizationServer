package com.torimia.authorization.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.torimia.authorization.AuthorizationException;
import com.torimia.authorization.mapper.UserMapper;
import com.torimia.authorization.model.dto.AuthorizationResponse;
import com.torimia.authorization.model.dto.AuthorizationUserDto;
import com.torimia.authorization.model.dto.JwtUser;
import com.torimia.authorization.model.entity.User;
import com.torimia.authorization.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;

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
    public ResponseEntity<AuthorizationResponse> authorize(AuthorizationUserDto dto) {
        Optional<User> u = repository.findUserByUsername(dto.getUsername());

        if (u.isEmpty()){
            return new ResponseEntity("User with username: " + dto.getUsername() + " don`t exist!", HttpStatus.FORBIDDEN);
        }
        User user = u.get();

        if (!user.getPassword().equals(dto.getPassword())) {
            return new ResponseEntity("Wrong password!", HttpStatus.FORBIDDEN);
        }

        JwtUser jwtUser = mapper.toJwt(user);

        String token = JWT.create()
                .withSubject(objectMapper.writeValueAsString(jwtUser))
                .withExpiresAt(Date.from(Instant.now().plus(30, ChronoUnit.MINUTES)))
                .sign(Algorithm.HMAC512(secret));

        return ResponseEntity.ok(new AuthorizationResponse("Bearer " + token));
    }
}
