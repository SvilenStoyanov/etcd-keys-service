package com.svistoyanov.eks.configuration;

import com.svistoyanov.eks.EtcdKeyTransformer;
import com.svistoyanov.eks.EtcdKeyTransformerImpl;
import com.svistoyanov.eks.PublicKeyService;
import com.svistoyanov.eks.Validator;
import com.svistoyanov.eks.ValidatorImpl;
import com.svistoyanov.eks.transformer.KeyIdTransformer;
import com.svistoyanov.eks.transformer.KeyIdTransformerImpl;
import com.svistoyanov.eks.utils.kvs.KvsServiceProvider;
import com.svistoyanov.eks.utils.kvs.KvsServiceProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    public static final long REQUEST_TIMEOUT_MILLIS = 1500;

    @Bean
    public KvsServiceProvider kvsServiceProvider(@Autowired EtcdProperties props) {
        return new KvsServiceProviderImpl(props.user(), props.password(), REQUEST_TIMEOUT_MILLIS, props.endpoints());
    }

    @Bean
    public PublicKeyService publicKeysService(KvsServiceProvider kvsServiceProvider, Validator validator, EtcdKeyTransformer etcdKeyTransformer, KeyIdTransformer keyIdTransformer) {
        return new PublicKeyService(kvsServiceProvider, validator, etcdKeyTransformer, keyIdTransformer);
    }

    @Bean
    public Validator validator() {
        return new ValidatorImpl();
    }

    @Bean
    public EtcdKeyTransformer etcdKeyBuilder() {
        return EtcdKeyTransformerImpl.instance;
    }

    @Bean
    public KeyIdTransformer keyIdResolver() {
        return KeyIdTransformerImpl.instance;
    }

}
