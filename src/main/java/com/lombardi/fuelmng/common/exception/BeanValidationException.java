package com.lombardi.fuelmng.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class BeanValidationException extends RuntimeException {


    public BeanValidationException(String exception) {
        super(exception);
    }


}
