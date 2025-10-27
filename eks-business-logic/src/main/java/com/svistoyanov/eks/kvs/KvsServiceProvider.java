package com.svistoyanov.eks.kvs;

/**
 *
 * @author svilen on 24/10/2025
 */
public interface KvsServiceProvider extends AutoCloseable {

    KeyValueService getKeyValueService();
}
