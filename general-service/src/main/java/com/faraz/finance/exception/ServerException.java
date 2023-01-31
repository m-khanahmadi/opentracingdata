package com.faraz.finance.exception;

import lombok.Getter;

@Getter
public class ServerException extends RuntimeException {

    private final String[] params;

    public ServerException(String message, String... params) {
        this(message, null, params);
    }

    public ServerException(String message, Throwable cause) {
        this(message, cause, (String[]) null);
    }

    public ServerException(String message, Throwable cause, String... params) {
        super(message, cause);
        this.params = params;
    }

}
