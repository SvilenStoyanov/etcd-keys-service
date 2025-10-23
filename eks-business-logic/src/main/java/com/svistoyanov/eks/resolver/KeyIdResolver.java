package com.svistoyanov.eks.resolver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

/**
 * @author svilen on 23/10/2025
 */
public class KeyIdResolver {

    private static final Logger logger = LoggerFactory.getLogger(KeyIdResolver.class);

    public static final KeyIdResolver instance = new KeyIdResolver();

    private KeyIdResolver() {
        //Singleton
    }

    public List<String> resolve(String keyToResolve) {
        logger.info("Key value is: {}", keyToResolve);
        if (keyToResolve == null) {
            return Collections.emptyList();
        }
        return List.of(keyToResolve.toLowerCase().split("_"));
    }
}
