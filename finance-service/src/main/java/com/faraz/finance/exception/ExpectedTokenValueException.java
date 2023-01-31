package com.faraz.finance.exception;

import lombok.Getter;

@Getter
public class ExpectedTokenValueException extends RuntimeException {

    private final String[] params;

    public ExpectedTokenValueException(String message, String... params) {
        this(message, null, params);
    }

    public ExpectedTokenValueException(String message, Throwable cause) {
        this(message, cause, (String[]) null);
    }

    public ExpectedTokenValueException(String message, Throwable cause, String... params) {
        super(message, cause);
        this.params = params;
    }
}
