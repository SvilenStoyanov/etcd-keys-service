package com.svistoyanov.eks.utils.kvs;

import io.etcd.jetcd.Client;

import java.util.concurrent.CompletableFuture;

public class KeyValueServiceImpl implements KeyValueService {

    private final Client jetcdClient;
    private final long   timeoutMs;

    public KeyValueServiceImpl(Client jetcdClient, long timeoutMs) {
        this.jetcdClient = jetcdClient;
        this.timeoutMs = timeoutMs;
    }

    @Override
    public CompletableFuture<KvSnapshot> get(String var1) {
        return null;
    }
}
