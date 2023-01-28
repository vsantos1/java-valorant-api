package com.vsantos1.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class UserNotAllowedException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public UserNotAllowedException(String message) {
        super(message);
    }


}
