package com.wonit.zola.controller.handler;

import com.wonit.zola.controller.model.SimpleResponse;
import com.wonit.zola.exception.EmptyQueueException;
import java.util.NoSuchElementException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public SimpleResponse handle(IllegalArgumentException e) {
        return new SimpleResponse(e.getMessage());
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public SimpleResponse handle(NoSuchElementException e) {
        return new SimpleResponse(e.getMessage());
    }

    @ExceptionHandler(EmptyQueueException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public SimpleResponse handle(EmptyQueueException e) {
        return new SimpleResponse(e.getMessage());
    }
}
