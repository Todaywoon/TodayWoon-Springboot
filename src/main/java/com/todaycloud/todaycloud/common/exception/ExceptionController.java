package com.todaycloud.todaycloud.common.exception;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(ResponseException.class)
    protected ResponseEntity<ErrorCode> handleResponseException(ResponseException responseException) {

        return ResponseEntity.badRequest().body(responseException.getErrorCode());
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    protected ResponseEntity<ErrorCode> handleMissingHeader(MissingRequestHeaderException missingRequestHeaderException) {

        return ResponseEntity.badRequest().body(ErrorCode.MISMATCH_USER_INFO);
    }
}
