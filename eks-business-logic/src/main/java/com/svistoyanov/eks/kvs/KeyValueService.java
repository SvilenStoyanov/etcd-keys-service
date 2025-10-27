package com.svistoyanov.eks.kvs;

import java.util.concurrent.CompletableFuture;

/**
 *
 * @author svilen on 24/10/2025
 */
public interface KeyValueService {

    CompletableFuture<KvSnapshot> get(String var1);
}
