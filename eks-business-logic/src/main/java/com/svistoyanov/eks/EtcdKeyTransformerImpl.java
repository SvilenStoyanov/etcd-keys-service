package com.svistoyanov.eks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.svistoyanov.eks.Constants.ETCD_KEY_PREFIX;

/**
 *
 * @author svilen on 24/10/2025
 */
public class EtcdKeyTransformerImpl implements EtcdKeyTransformer{

    private static final Logger logger = LoggerFactory.getLogger(EtcdKeyTransformerImpl.class);

    public static final EtcdKeyTransformerImpl instance = new EtcdKeyTransformerImpl();

    private EtcdKeyTransformerImpl() {
        // Singleton
    }

    @Override
    public String transform(List<String> identifiers) {
        final String etcdKey = ETCD_KEY_PREFIX + String.join("/", identifiers);
        logger.debug("The generated etcdKey is: {}", etcdKey);
        return etcdKey;
    }
}
