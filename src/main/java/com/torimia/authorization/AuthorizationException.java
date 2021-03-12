package com.torimia.authorization;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class AuthorizationException extends RuntimeException {

    private final String message;

    public AuthorizationException(String message) {
        super(message);
        this.message = message;
    }
}
