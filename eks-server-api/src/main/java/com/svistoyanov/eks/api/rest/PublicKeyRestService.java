package com.svistoyanov.eks.api.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author svilen on 24/10/2025
 */
public interface PublicKeyRestService {

    /**
     * Returns the public key with the specified id.
     *
     * @param publicKeyId The publicKeyId to return must not be <code>null</code> .
     * @return public key from etcd.
     */
    @GetMapping(path = "/{publicKeyId}", produces = MediaType.TEXT_PLAIN_VALUE)
    String getPublicKey(@PathVariable("publicKeyId") String publicKeyId);
}
