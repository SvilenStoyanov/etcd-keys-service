package com.svistoyanov.eks.api.dto.exception;

public class InvalidEnumValueException extends RuntimeException {

    public InvalidEnumValueException(String enumValue) {
        super("Invalid value: " + enumValue);
    }
}
