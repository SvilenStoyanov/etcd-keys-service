package com.svistoyanov.eks.utils.kvs;

import io.etcd.jetcd.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Collection;

public class KvsServiceProviderImpl implements KvsServiceProvider {

    private static final Logger logger = LoggerFactory.getLogger(KvsServiceProviderImpl.class);

    private final Client client;
    private final long   timeoutMs;

    private KeyValueService keyValueService;

    /**
     * @param username  the ETCD username
     * @param password  the ETCD password
     * @param timeoutMs the ETCD request timeout in milliseconds
     * @param endpoints the ETCD endpoints
     */
    public KvsServiceProviderImpl(String username, String password, long timeoutMs, Collection<URI> endpoints) {
        logger.debug("ETCD Request timeout is {}ms", timeoutMs);
        this.timeoutMs = timeoutMs;
        client = Client.builder()
                .user(EtcdUtils.getByteSequence(username))
                .password(EtcdUtils.getByteSequence(password))
                .endpoints(endpoints)
                .keepaliveTime(Duration.of(60, ChronoUnit.SECONDS))
                .keepaliveTimeout(Duration.of(20, ChronoUnit.SECONDS))
                .keepaliveWithoutCalls(false)                 // Don't send keepalive when idle
                .retryMaxAttempts(Integer.MAX_VALUE)
                .build();
    }

    @Override
    public KeyValueService getKeyValueService() {
        if (keyValueService == null) {
            keyValueService = new KeyValueServiceImpl(client, timeoutMs);
        }
        return keyValueService;
    }

    @Override
    public void close() {
        client.close();
        keyValueService = null;
    }
}
