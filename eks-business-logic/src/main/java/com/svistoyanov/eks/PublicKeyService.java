package com.svistoyanov.eks;

import com.svistoyanov.eks.utils.exception.ConflictException;
import com.svistoyanov.eks.utils.exception.NotFoundException;
import com.svistoyanov.eks.utils.kvs.KeyValue;
import com.svistoyanov.eks.utils.kvs.KvSnapshot;
import com.svistoyanov.eks.utils.kvs.KvsServiceProvider;
import com.svistoyanov.eks.transformer.KeyIdTransformer;
import com.svistoyanov.eks.utils.exception.ValidationException;
import com.svistoyanov.eks.utils.ValidationResult;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static com.svistoyanov.eks.Constants.KEY_DUPLICATE_FOUND_ERROR_MESSAGE;
import static com.svistoyanov.eks.Constants.KEY_NOT_FOUND_ERROR_MESSAGE;

/**
 *
 * @author svilen on 24/10/2025
 */
public class PublicKeyService {

    private final KvsServiceProvider kvsServiceProvider;
    private final Validator          validator;
    private final EtcdKeyTransformer etcdKeyTransformer;
    private final KeyIdTransformer   keyIdTransformer;

    public PublicKeyService(
            KvsServiceProvider kvsServiceProvider,
            Validator validator,
            EtcdKeyTransformer etcdKeyTransformer,
            KeyIdTransformer keyIdTransformer) {

        this.kvsServiceProvider = kvsServiceProvider;
        this.validator = validator;
        this.etcdKeyTransformer = etcdKeyTransformer;
        this.keyIdTransformer = keyIdTransformer;
    }

    public String getKey(String keyId) {
        final ValidationResult vr = validator.validate(keyId);

        if (vr.hasFailed()) {
            throw new ValidationException(vr.toMessages());
        }

        try {
            final List<String> identifiers = keyIdTransformer.transform(keyId);
            final String keyParameter = etcdKeyTransformer.transform(identifiers);
            final KvSnapshot kvSnapshot = kvsServiceProvider.getKeyValueService().get(keyParameter)
                    .get(2, TimeUnit.SECONDS);

            final List<KeyValue> keyToReturn = kvSnapshot.keyValues();

            if (keyToReturn.isEmpty()) {
                throw new NotFoundException(KEY_NOT_FOUND_ERROR_MESSAGE);
            }
            else if (keyToReturn.size() > 1) {
                throw new ConflictException(KEY_DUPLICATE_FOUND_ERROR_MESSAGE);
            }

            return keyToReturn.getFirst().value();
        }
        catch (ExecutionException | InterruptedException | TimeoutException e) {
            throw new RuntimeException(e);
        }
    }
}
