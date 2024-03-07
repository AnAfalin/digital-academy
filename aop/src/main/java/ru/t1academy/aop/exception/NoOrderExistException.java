package ru.t1academy.aop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoOrderExistException extends RuntimeException{
    public NoOrderExistException(String message) {
        super(message);
    }
}
