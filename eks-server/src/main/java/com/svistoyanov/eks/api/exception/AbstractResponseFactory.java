package com.svistoyanov.eks.api.exception;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Objects;

public abstract class AbstractResponseFactory<T> implements ResponseFactory<T> {

    public T create(Logger logger, Throwable e) {
        logger.error("Error Message: {}", e.getMessage());
        return (T)this.createResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), List.of(e.getLocalizedMessage()));
    }

    public T create(Logger logger, HttpStatus status, String error) {
        return (T)this.createResponse(status.value(), List.of(error));
    }

    public T create(Logger logger, HttpStatus status, List<String> errors) {
        logger.error("Error code: {}", status.value());
        Objects.requireNonNull(logger);
        errors.forEach(logger::error);
        return (T)this.createResponse(status.value(), errors);
    }

    protected abstract T createResponse(int var1, List<String> var2);
}
