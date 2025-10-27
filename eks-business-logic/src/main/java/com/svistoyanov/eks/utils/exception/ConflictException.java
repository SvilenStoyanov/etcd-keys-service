package com.svistoyanov.eks.utils.exception;

/**
 *
 * @author svilen on 24/10/2025
 */
public class ConflictException extends RuntimeException {
    public ConflictException(String message) {
        super(message);
    }

    public ConflictException(String message, Throwable cause) {
        super(message, cause);
    }
}
