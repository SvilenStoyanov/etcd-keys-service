package com.svistoyanov.eks.api.rest;

import com.svistoyanov.eks.PublicKeyService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author svilen on 24/10/2025
 */
public class PublicKeyRestServiceImpl implements PublicKeyRestService {

    private final PublicKeyService publicKeyService;

    public PublicKeyRestServiceImpl(PublicKeyService publicKeysService) {
        this.publicKeyService = publicKeysService;
    }

    @GetMapping(path = "/{publicKeyId}", produces = MediaType.TEXT_PLAIN_VALUE)
    public String getPublicKey(@PathVariable("publicKeyId") String publicKeyId) {
        return publicKeyService.getKey(publicKeyId);
    }

}
