package com.faraz.finance.tools;

import lombok.Data;

@Data
public class SimpleResponse<T> {
    private final T status;
    private final String message;

}
