package com.svistoyanov.eks.exception;

import com.svistoyanov.eks.utils.exception.ConcurrentException;
import com.svistoyanov.eks.utils.exception.ConflictException;
import com.svistoyanov.eks.utils.exception.ForbiddenException;
import com.svistoyanov.eks.utils.exception.NotFoundException;
import com.svistoyanov.eks.utils.exception.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;

public class EksExceptionHandler<T> extends ResponseEntityExceptionHandler {

    protected static final Logger             logger = LoggerFactory.getLogger(EksExceptionHandler.class);
    protected final        ResponseFactory<T> responseFactory;

    public EksExceptionHandler(ResponseFactory<T> responseFactory) {
        this.responseFactory = responseFactory;
    }

    @ExceptionHandler({ForbiddenException.class})
    public final ResponseEntity<T> handle(ForbiddenException e) {
        T err = (T)this.responseFactory.create(logger, HttpStatus.FORBIDDEN, e.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
    }

    @ExceptionHandler({NotFoundException.class})
    public final ResponseEntity<T> handleException(NotFoundException e) {
        T err = (T)this.responseFactory.create(logger, HttpStatus.NOT_FOUND, e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler({ ConcurrentException.class})
    public final ResponseEntity<T> handleException(ConcurrentException e) {
        T err = (T)this.responseFactory.create(logger, HttpStatus.CONFLICT, e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
    }

    @ExceptionHandler({ ConflictException.class})
    public final ResponseEntity<T> handleException(ConflictException e) {
        T err = (T)this.responseFactory.create(logger, HttpStatus.CONFLICT, e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
    }

    @ExceptionHandler({ValidationException.class})
    public final ResponseEntity<T> handleException(ValidationException e) {
        T err = (T)this.responseFactory.create(logger, HttpStatus.BAD_REQUEST, e.getMessages());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public final ResponseEntity<T> handle(IllegalArgumentException e) {
        T err = (T)this.responseFactory.create(logger, HttpStatus.BAD_REQUEST, e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler({RuntimeException.class})
    public final ResponseEntity<T> handle(RuntimeException e) {
        T err = (T)this.responseFactory.create(logger, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
    }

    @ExceptionHandler({Throwable.class})
    public final ResponseEntity<T> handle(Throwable e) {
        T err = (T)this.responseFactory.create(logger, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
    }

    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ArrayList<String> errors = new ArrayList();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError)error).getField();
            errors.add(String.format("Field [%s], validation error: %s", fieldName, error.getDefaultMessage()));
        });
        T err = (T)this.responseFactory.create(logger, HttpStatus.BAD_REQUEST, errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
}
