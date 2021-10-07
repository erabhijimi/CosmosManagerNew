package com.cosmos.error;

public class TransactionError extends RuntimeException {
    public TransactionError() {
        super();
    }

    public TransactionError(String message) {
        super(message);
    }

    public TransactionError(String message, Throwable cause) {
        super(message, cause);
    }

    public TransactionError(Throwable cause) {
        super(cause);
    }

    protected TransactionError(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
