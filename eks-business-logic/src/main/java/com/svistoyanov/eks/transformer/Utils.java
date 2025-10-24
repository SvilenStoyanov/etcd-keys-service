package com.svistoyanov.eks.transformer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author svilen on 24/10/2025
 */
public class Utils {

    private static final Logger logger = LoggerFactory.getLogger(Utils.class);

    private Utils() {
        // static only
    }

    public static void verifyTrue(String message, boolean condition) {
        verifyFalse(message, !condition);
    }

    public static void verifyFalse(String message, boolean condition) {
        if (condition) {
            alert(message, new IllegalArgumentException(message));
        }

    }

    private static void alert(String msg, RuntimeException e) {
        logger.error(msg, e);
        throw e;
    }
}
