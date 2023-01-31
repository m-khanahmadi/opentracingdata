package com.faraz.authservice.exception.exception;

import lombok.Getter;

@Getter
public class ClientException extends RuntimeException {
    private final String[] params;

    public ClientException(String message, String... params) {
        this(message, null, params);
    }

    public ClientException(String message, Throwable cause) {
        this(message, cause, (String[]) null);
    }

    public ClientException(String message, Throwable cause, String... params) {
        super(message, cause);
        this.params = params;
    }
}
