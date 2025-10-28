package com.svistoyanov.eks.utils.exception;

public class ConcurrentException extends RuntimeException {
    public ConcurrentException(String message) {
        super(message);
    }

    public ConcurrentException(String message, Throwable cause) {
        super(message, cause);
    }
}
