package com.svistoyanov.eks.exception;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface ResponseFactory<T> {

    T create(Logger var1, Throwable var2);

    T create(Logger var1, HttpStatus var2, String var3);

    T create(Logger var1, HttpStatus var2, List<String> var3);
}
