package com.svistoyanov.eks.configuration;

import com.svistoyanov.eks.PublicKeyService;
import com.svistoyanov.eks.api.ObjectMapperSingleton;
import com.svistoyanov.eks.api.dto.EksErrorDto;
import com.svistoyanov.eks.api.exception.EksExceptionHandler;
import com.svistoyanov.eks.api.exception.EksResponseFactory;
import com.svistoyanov.eks.api.rest.PublicKeyRestServiceImpl;
import com.svistoyanov.eks.configuration.spring.BuildInfoContributor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.io.IOException;

@Configuration
public class RestConfiguration {

    @Bean
    public BuildInfoContributor buildInfoContributor() throws IOException {
        return new BuildInfoContributor();
    }

    @Bean
    public EksExceptionHandler<EksErrorDto> exceptionHandler() {
        return new EksExceptionHandler<>(new EksResponseFactory());
    }

    @Bean
    public PublicKeyRestServiceImpl publicKeysRestController(PublicKeyService publicKeysService) {
        return new PublicKeyRestServiceImpl(publicKeysService);
    }

    @Bean
    public HttpMessageConverter<Object> jsonHttpMessageConverter() {
        final var converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(ObjectMapperSingleton.instance.getObjectMapper());
        return converter;
    }
}
