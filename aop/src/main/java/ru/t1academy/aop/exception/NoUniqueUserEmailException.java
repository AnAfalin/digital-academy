package ru.t1academy.aop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class NoUniqueUserEmailException extends RuntimeException{

    public NoUniqueUserEmailException(String message) {
        super(message);
    }
}
