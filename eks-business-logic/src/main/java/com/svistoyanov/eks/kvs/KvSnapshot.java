package com.svistoyanov.eks.kvs;

import java.util.List;

/**
 *
 * @author svilen on 24/10/2025
 */
public record KvSnapshot(long revision, List<KeyValue> keyValues) {
}
