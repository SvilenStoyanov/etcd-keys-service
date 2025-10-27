package com.svistoyanov.eks.transformer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author svilen on 23/10/2025
 */
public class KeyIdTransformerImpl implements KeyIdTransformer {

    private static final Logger logger = LoggerFactory.getLogger(KeyIdTransformerImpl.class);

    public static final KeyIdTransformerImpl instance = new KeyIdTransformerImpl();

    private KeyIdTransformerImpl() {
        //Singleton
    }

    public List<String> transform(String keyToTransform) {
        logger.debug("Key value is: {}", keyToTransform);
        return List.of(keyToTransform.toLowerCase().split("_"));
    }
}
