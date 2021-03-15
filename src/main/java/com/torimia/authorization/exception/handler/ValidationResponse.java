package com.torimia.authorization.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
class ValidationResponse {

    private String message;

    private Map<String, String> data;
}
