package com.lombardi.fuelmng.common.exception;

public class FileParsingException extends RuntimeException{

    private static final long serialVersionUID = -971454849644061074L;

    public FileParsingException(final String message) {
        super(message);
    }
}
