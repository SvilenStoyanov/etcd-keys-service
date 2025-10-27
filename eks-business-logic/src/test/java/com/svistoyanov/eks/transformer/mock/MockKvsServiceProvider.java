package com.svistoyanov.eks.transformer.mock;

import com.svistoyanov.eks.utils.kvs.KeyValueService;
import com.svistoyanov.eks.utils.kvs.KvsServiceProvider;

public class MockKvsServiceProvider implements KvsServiceProvider {

    @Override
    public KeyValueService getKeyValueService() {
        return new MockKeyValueService();
    }

    @Override
    public void close() {

    }
}
