package com.svistoyanov.eks;

/**
 *
 * @author svilen on 24/10/2025
 */
public class Constants {

    public static final String ETCD_KEY_PREFIX                   = "/tls/public/";
    public static final String KEY_NOT_FOUND_ERROR_MESSAGE       = "The key not found";
    public static final String KEY_DUPLICATE_FOUND_ERROR_MESSAGE = "More than one key found";

    private Constants() {
        // Singleton
    }
}
