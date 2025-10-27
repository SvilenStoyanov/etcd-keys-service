package com.svistoyanov.eks.transformer.mock;

import com.svistoyanov.eks.utils.kvs.KeyValue;
import com.svistoyanov.eks.utils.kvs.KeyValueService;
import com.svistoyanov.eks.utils.kvs.KvSnapshot;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class MockKeyValueService implements KeyValueService {

    @Override
    public CompletableFuture<KvSnapshot> get(String inputParameter) {
        return switch (inputParameter) {
            case "zero" -> CompletableFuture.completedFuture(new KvSnapshot(0, Collections.emptyList()));
            case "moreThanOne" -> {
                final var kvSnapshot = new KvSnapshot(0, List.of(new KeyValue("0", "0"), new KeyValue("0", "0")));
                yield CompletableFuture.completedFuture(kvSnapshot);
            }
            case "valid" -> {
                final var kvSnapshot = new KvSnapshot(0, List.of(new KeyValue("ETCDkey", "ETCDValue")));
                yield CompletableFuture.completedFuture(kvSnapshot);
            }
            default -> CompletableFuture.failedFuture(new IllegalArgumentException("Unknown argument: " + inputParameter));
        };
    }

}
