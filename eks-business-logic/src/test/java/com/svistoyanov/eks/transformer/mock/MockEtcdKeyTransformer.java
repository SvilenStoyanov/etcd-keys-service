package com.svistoyanov.eks.transformer.mock;

import com.svistoyanov.eks.EtcdKeyTransformer;

import java.util.List;

public class MockEtcdKeyTransformer implements EtcdKeyTransformer {

    public static final MockEtcdKeyTransformer instance = new MockEtcdKeyTransformer();

    private MockEtcdKeyTransformer() {
        // Singleton
    }

    @Override
    public String transform(List<String> identifiers) {
        return identifiers.getFirst();
    }
}
