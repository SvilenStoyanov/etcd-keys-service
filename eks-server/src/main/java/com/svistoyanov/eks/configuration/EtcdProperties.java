package com.svistoyanov.eks.configuration;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.net.URI;
import java.util.List;

@ConfigurationProperties(prefix = "etcd")
@Validated
public record EtcdProperties(
        @NotNull @NotBlank String user,
        @NotNull @NotBlank String password,
        @NotNull @NotEmpty List<@NotNull URI> endpoints
) {

}
