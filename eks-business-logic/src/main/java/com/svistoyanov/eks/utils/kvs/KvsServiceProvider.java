package com.svistoyanov.eks.utils.kvs;

/**
 *
 * @author svilen on 24/10/2025
 */
public interface KvsServiceProvider extends AutoCloseable {

    KeyValueService getKeyValueService();
}
