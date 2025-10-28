package com.svistoyanov.eks.api.dto;

import com.svistoyanov.eks.api.exception.InvalidEnumValueException;

public enum ErrorCodesDto {

    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR"),
    COMMON_INSUFFICIENT_ACCESS_RIGHTS("COMMON_INSUFFICIENT_ACCESS_RIGHTS"),
    FORBIDDEN("FORBIDDEN"),
    NOT_FOUND("NOT_FOUND"),
    CONFLICT("CONFLICT"),
    SERVICE_UNAVAILABLE("SERVICE_UNAVAILABLE"),
    VALIDATION("VALIDATION");

    private final String jsonValue;

    ErrorCodesDto(String jsonValue) {
        this.jsonValue = jsonValue;
    }

    public static ErrorCodesDto fromString(String string) {
        for (ErrorCodesDto value : values()) {
            if (value.jsonValue.equals(string)) {
                return value;
            }
        }
        throw new InvalidEnumValueException(string);
    }
}
