package com.github.dhslrl321.zsmq.controller.handler;

import com.github.dhslrl321.zsmq.controller.model.SimpleResponse;
import com.github.dhslrl321.zsmq.exception.EmptyQueueException;
import com.github.dhslrl321.zsmq.exception.QueueNotFoundException;
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

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public SimpleResponse handle(QueueNotFoundException e) {
        return new SimpleResponse(e.getMessage());
    }

    @ExceptionHandler(EmptyQueueException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public SimpleResponse handle(EmptyQueueException e) {
        return new SimpleResponse(e.getMessage());
    }
}
