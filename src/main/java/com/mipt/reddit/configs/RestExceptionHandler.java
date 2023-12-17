package com.mipt.reddit.configs;

import com.mipt.reddit.dtos.ErrorDto;
import com.mipt.reddit.exception.BackendException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = { BackendException.class })
    @ResponseBody
    public ResponseEntity<ErrorDto> handleException(BackendException ex) {
        return ResponseEntity
                .status(ex.getStatus())
                .body(new ErrorDto(ex.getMessage()));
    }
}