package com.torimia.authorization.model.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class JwtUser {
    private Long id;

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private int age;

    private String email;
}
