package com.torimia.authorization.model.entity;

import com.sun.istack.NotNull;
import com.torimia.authorization.model.AccountStatus;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "usr")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    private int age;

    private String email;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private AccountStatus status = AccountStatus.ACTIVE;
}
