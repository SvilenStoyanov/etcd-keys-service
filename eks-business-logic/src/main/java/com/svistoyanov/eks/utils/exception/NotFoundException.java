package com.svistoyanov.eks.utils.exception;

/**
 *
 * @author svilen on 24/10/2025
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
