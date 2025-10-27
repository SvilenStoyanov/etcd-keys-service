package com.svistoyanov.eks.transformer.mock;

import com.svistoyanov.eks.transformer.KeyIdTransformer;

import java.util.List;

public class MockKeyIdTransformer implements KeyIdTransformer {

    @Override
    public List<String> transform(String keyToResolve) {
        return List.of(keyToResolve);
    }
}
