package com.svistoyanov.eks.utils;

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

    public static <T> void verifyNotNull(T arg, ValidationResult result, Printable message) {
        verifyTrue(arg != null, result, message);
    }

    public static void verifyTrue(boolean condition, ValidationResult result, Printable message) {
        verifyFalse(!condition, result, message);
    }

    public static void verifyFalse(boolean condition, ValidationResult result, Printable message) {
        if (condition) {
            logger.error(message.print());
            result.addError(message);
        }
    }

    public static void verifyTrue(String message, boolean condition) {
        verifyFalse(message, !condition);
    }

    public static void verifyFalse(String message, boolean condition) {
        if (condition) {
            alert(message, new IllegalArgumentException(message));
        }
    }

    public static void verifyNotBlank(String arg, ValidationResult result, Printable message) {
        verifyFalse(arg.isBlank(), result, message);
    }

    private static void alert(String msg, RuntimeException e) {
        logger.error(msg, e);
        throw e;
    }
}
