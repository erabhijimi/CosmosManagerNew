package com.cosmos.error;

public class UserAlreadyAvailableException extends RuntimeException {
    public UserAlreadyAvailableException() {
        super();
    }

    public UserAlreadyAvailableException(String message) {
        super(message);
    }

    public UserAlreadyAvailableException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserAlreadyAvailableException(Throwable cause) {
        super(cause);
    }

    protected UserAlreadyAvailableException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
