package com.svistoyanov.eks;

import com.svistoyanov.eks.configuration.BeanConfiguration;
import com.svistoyanov.eks.configuration.EtcdProperties;
import com.svistoyanov.eks.configuration.RestConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;

/**
 *
 * @author svilen on 24/10/2025
 */
@SpringBootApplication
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = "com.svistoyanov.*"))
@Import({
        BeanConfiguration.class,
        RestConfiguration.class
})
@EnableConfigurationProperties({
        EtcdProperties.class
})
public class SpringBootApp {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootApp.class, args);
    }
}
