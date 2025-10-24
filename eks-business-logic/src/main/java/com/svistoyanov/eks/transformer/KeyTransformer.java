package com.svistoyanov.eks.transformer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

/**
 * @author svilen on 23/10/2025
 */
public class KeyTransformer {

    public static final String KEY_ID_PATTERN_ERROR_MESSAGE = "Key ID should match the correct pattern.";
    public static final String KEY_ID_PATTERN               = "^(cs|mts)(_[-A-Za-z0-9\\s]*){2}$";

    private static final Logger logger = LoggerFactory.getLogger(KeyTransformer.class);

    public static final KeyTransformer instance = new KeyTransformer();

    private KeyTransformer() {
        //Singleton
    }

    public List<String> transformKey(String keyToResolve) {
        logger.info("Key value is: {}", keyToResolve);
        if (keyToResolve == null) {
            return Collections.emptyList();
        }

        Utils.verifyTrue(
                KEY_ID_PATTERN_ERROR_MESSAGE,
                keyToResolve.matches(KEY_ID_PATTERN));

        return List.of(keyToResolve.toLowerCase().split("_"));
    }
}
