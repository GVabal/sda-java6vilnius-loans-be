package dev.vabalas.loans.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserAlreadyTerminatedException extends RuntimeException{
    public UserAlreadyTerminatedException(String message) {
        super(message);
    }
}
