package com.faraz.finance.exception;

import com.faraz.finance.tools.SimpleResponse;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@AllArgsConstructor
@ControllerAdvice
public class FarazExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(ClientException.class)
    private ResponseEntity<SimpleResponse<Void>> handleClientErrors(ClientException ex) {
        String message = this.messageSource.getMessage(
                ex.getMessage(),
                ex.getParams(),
                LocaleContextHolder.getLocale());
        SimpleResponse<Void> simpleResponse = new SimpleResponse<>(null, message);
        return new ResponseEntity<>(simpleResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExpectedTokenValueException.class)
    private ResponseEntity<SimpleResponse<Void>> handleExpectedTokenValueErrors(ExpectedTokenValueException ex) {
        String message = this.messageSource.getMessage(
                ex.getMessage(),
                ex.getParams(),
                LocaleContextHolder.getLocale());
        SimpleResponse<Void> simpleResponse = new SimpleResponse<>(null, message);
        return new ResponseEntity<>(simpleResponse, HttpStatus.UNAUTHORIZED);
    }


    @ExceptionHandler(ServerException.class)
    private ResponseEntity<SimpleResponse<Void>> handleServerErrors(ServerException ex) {
        String message = this.messageSource.getMessage(
                ex.getMessage(),
                ex.getParams(),
                LocaleContextHolder.getLocale());
        SimpleResponse<Void> simpleResponse = new SimpleResponse<>(null, message);
        return new ResponseEntity<>(simpleResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
