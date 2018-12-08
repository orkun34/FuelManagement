package com.lombardi.fuelmng.common.exception;

public class MissingElementException extends RuntimeException{

    private static final long serialVersionUID = -971454849644061074L;

    public MissingElementException(final String message) {
        super(message);
    }


}
