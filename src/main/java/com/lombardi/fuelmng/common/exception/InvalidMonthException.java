package com.lombardi.fuelmng.common.exception;

public class InvalidMonthException extends Exception{

    private static final long serialVersionUID = -910054849644061074L;

    public InvalidMonthException(final String message) {
        super(message);
    }
}
