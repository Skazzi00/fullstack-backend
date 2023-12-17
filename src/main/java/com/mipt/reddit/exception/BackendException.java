package com.mipt.reddit.exception;

import org.springframework.http.HttpStatus;

public class BackendException extends RuntimeException {

    private final HttpStatus status;

    public BackendException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
