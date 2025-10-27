package com.svistoyanov.eks.utils.exception;

import java.util.List;

/**
 *
 * @author svilen on 27/10/2025
 */
public class ValidationException extends RuntimeException {
    private final List<String> messages;

    public ValidationException(List<String> messages) {
        this.messages = messages;
    }

    public List<String> getMessages() {
        return messages;
    }
}
